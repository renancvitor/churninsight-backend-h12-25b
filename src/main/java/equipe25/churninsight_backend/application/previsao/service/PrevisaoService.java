package equipe25.churninsight_backend.application.previsao.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import equipe25.churninsight_backend.application.api.dto.ClienteRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.nivelrisco.NivelRiscoRepository;
import equipe25.churninsight_backend.application.previsao.dto.EstatisticasResponse;
import equipe25.churninsight_backend.application.previsao.dto.FatorCountAnalytics;
import equipe25.churninsight_backend.application.previsao.dto.FatorCountResponse;
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoPorNivelRisco;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;
import equipe25.churninsight_backend.exception.domain.RecursoNaoEncontradoException;
import equipe25.churninsight_backend.exception.domain.RegraNegocioException;
import equipe25.churninsight_backend.model.explicabilidade.ExplicabilidadeEnum;
import equipe25.churninsight_backend.model.previsao.Previsao;
import equipe25.churninsight_backend.model.tipoprevisao.enums.TipoPrevisaoEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrevisaoService {

    private final PrevisaoRepository previsaoRepository;
    private final PrevisaoClienteService previsaoClienteService;
    private final NivelRiscoRepository nivelRiscoRepository;
    private final TipoPrevisaoRepository tipoPrevisaoRepository;

    @Transactional
    public ClienteResponse prever(ClienteRequest request) {
        ClienteResponse response = previsaoClienteService.prever(request);

        if (response == null) {
            throw new RegraNegocioException("Erro ao obter previsão do cliente.");
        }

        Previsao previsao = new Previsao();
        previsao.setPrevisao(tipoPrevisaoRepository.findById(response.tipoPrevisao().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Tipo Previsão não encontrado para id " + response.tipoPrevisao().getId())));
        previsao.setNivelRisco(nivelRiscoRepository.findById(response.nivelRisco().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Nível Risco não encontrado para id " + response.nivelRisco().getId())));
        previsao.setProbabilidade(response.probabilidade());

        if (response.tipoPrevisao() == TipoPrevisaoEnum.VAI_CANCELAR) {
            previsao.setExplicabilidade(response.explicabilidade());
        } else {
            previsao.setExplicabilidade(List.of());
        }

        previsaoRepository.save(previsao);

        return response;
    }

    public List<PrevisaoPorNivelRisco> obterGrafico() {
        return previsaoRepository.previsaoPorNivelRiscos();
    }

    public EstatisticasResponse estatisticas() {
        Long total = previsaoRepository.count();
        Double taxaChurn = previsaoRepository.calculoTaxaChurn();

        if (taxaChurn == null) {
            taxaChurn = 0.0;
        }

        return new EstatisticasResponse(total, taxaChurn);
    }

    public List<FatorCountAnalytics> top3Fatores() {
        return previsaoRepository.topFatores(PageRequest.of(0, 3));
    }

    public List<FatorCountResponse> top3FatoresResponse() {
        return top3Fatores().stream()
                .map(valor -> new FatorCountResponse(
                        ExplicabilidadeEnum.traduzir(valor.fator()),
                        valor.total()))
                .toList();
    }

}
