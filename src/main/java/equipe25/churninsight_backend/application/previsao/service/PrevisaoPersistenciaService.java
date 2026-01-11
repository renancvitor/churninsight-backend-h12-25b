package equipe25.churninsight_backend.application.previsao.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import equipe25.churninsight_backend.application.api.dto.PrevisaoBatchCsv;
import equipe25.churninsight_backend.application.nivelrisco.NivelRiscoRepository;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;
import equipe25.churninsight_backend.exception.domain.RecursoNaoEncontradoException;
import equipe25.churninsight_backend.exception.domain.RegraNegocioException;
import equipe25.churninsight_backend.model.nivelrisco.NivelRiscoEntidade;
import equipe25.churninsight_backend.model.previsao.Previsao;
import equipe25.churninsight_backend.model.tipoprevisao.TipoPrevisaoEntidade;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrevisaoPersistenciaService {

    private final PrevisaoRepository previsaoRepository;
    private final NivelRiscoRepository nivelRiscoRepository;
    private final TipoPrevisaoRepository tipoPrevisaoRepository;

    private static final int BATCH_SIZE = 250;

    @Transactional
    public void persistirCsv(Resource csv) {
        NivelRiscoEntidade alto = nivelRiscoRepository.findByNivelRiscoNome("ALTO")
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Nível de risco ALTO não encontrado"));

        NivelRiscoEntidade baixo = nivelRiscoRepository.findByNivelRiscoNome("BAIXO")
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Nível de risco BAIXO não encontrado"));

        TipoPrevisaoEntidade cancelar = tipoPrevisaoRepository.findByTipoPrevisao("Vai cancelar")
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Tipo previsão 'Vai cancelar' não encontrado"));

        TipoPrevisaoEntidade continuar = tipoPrevisaoRepository.findByTipoPrevisao("Vai continuar")
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Tipo previsão 'Vai continuar' não encontrado"));

        List<Previsao> buffer = new ArrayList<>(BATCH_SIZE);

        try (Reader reader = new BufferedReader(new InputStreamReader(csv.getInputStream()))) {

            CsvToBean<PrevisaoBatchCsv> csvToBean = new CsvToBeanBuilder<PrevisaoBatchCsv>(reader)
                    .withType(PrevisaoBatchCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (PrevisaoBatchCsv linha : csvToBean) {

                Previsao previsao = new Previsao();
                previsao.setProbabilidade(
                        linha.getProbabilidade().doubleValue());

                previsao.setNivelRisco(
                        "ALTO".equals(linha.getNivelRisco()) ? alto : baixo);

                previsao.setPrevisao(
                        "Vai cancelar".equals(linha.getPrevisao())
                                ? cancelar
                                : continuar);

                previsao.setExplicabilidade(
                        parseExplicabilidade(linha.getExplicabilidade()));

                buffer.add(previsao);

                if (buffer.size() == BATCH_SIZE) {
                    previsaoRepository.saveAll(buffer);
                    buffer.clear();
                }
            }

            if (!buffer.isEmpty()) {
                previsaoRepository.saveAll(buffer);
            }

        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao persistir CSV batch", e);
        }
    }

    private List<String> parseExplicabilidade(String valor) {
        if (valor == null || valor.isBlank()) {
            return List.of();
        }

        return Arrays.stream(valor.split(","))
                .map(String::trim)
                .toList();
    }

}
