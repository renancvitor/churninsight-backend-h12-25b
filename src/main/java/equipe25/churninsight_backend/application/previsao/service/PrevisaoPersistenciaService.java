package equipe25.churninsight_backend.application.previsao.service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrevisaoPersistenciaService {

        private final PrevisaoRepository previsaoRepository;
        private final NivelRiscoRepository nivelRiscoRepository;
        private final TipoPrevisaoRepository tipoPrevisaoRepository;

        @Transactional
        public void persistirCsv(Resource csv) {

                Map<String, NivelRiscoEntidade> niveis = nivelRiscoRepository.findAll()
                                .stream()
                                .collect(Collectors.toMap(NivelRiscoEntidade::getNivelRiscoNome, Function.identity()));

                Map<String, TipoPrevisaoEntidade> tipos = tipoPrevisaoRepository.findAll()
                                .stream()
                                .collect(Collectors.toMap(TipoPrevisaoEntidade::getTipoPrevisao, Function.identity()));

                List<Previsao> previsoes = new ArrayList<>();

                try (Reader reader = new InputStreamReader(csv.getInputStream())) {

                        CsvToBean<PrevisaoBatchCsv> csvToBean = new CsvToBeanBuilder<PrevisaoBatchCsv>(reader)
                                        .withType(PrevisaoBatchCsv.class)
                                        .withIgnoreLeadingWhiteSpace(true)
                                        .build();

                        for (PrevisaoBatchCsv linha : csvToBean) {
                                previsoes.add(mapearParaEntidade(linha, niveis, tipos));
                        }

                } catch (Exception e) {
                        throw new RegraNegocioException("Erro ao processar CSV", e);
                }

                previsaoRepository.saveAll(previsoes);
        }

        private Previsao mapearParaEntidade(
                        PrevisaoBatchCsv dto,
                        Map<String, NivelRiscoEntidade> niveis,
                        Map<String, TipoPrevisaoEntidade> tipos) {

                Previsao previsao = new Previsao();
                previsao.setProbabilidade(dto.getProbabilidade().doubleValue());

                NivelRiscoEntidade nivel = niveis.get(dto.getNivelRisco());
                if (nivel == null) {
                        throw new RecursoNaoEncontradoException(
                                        "Nível de risco não encontrado: " + dto.getNivelRisco());
                }

                TipoPrevisaoEntidade tipo = tipos.get(dto.getPrevisao());
                if (tipo == null) {
                        throw new RecursoNaoEncontradoException(
                                        "Tipo de previsão não encontrado: " + dto.getPrevisao());
                }

                previsao.setNivelRisco(nivel);
                previsao.setPrevisao(tipo);
                previsao.setExplicabilidade(parseExplicabilidade(dto.getExplicabilidade()));

                return previsao;
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
