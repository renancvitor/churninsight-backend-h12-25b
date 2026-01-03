package equipe25.churninsight_backend.application.previsao.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import equipe25.churninsight_backend.application.api.dto.ClienteRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.nivelrisco.NivelRiscoRepository;
import equipe25.churninsight_backend.application.previsao.dto.FatorCountAnalytics;
import equipe25.churninsight_backend.application.previsao.dto.FatorCountResponse;
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoListagem;
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoPorNivelRisco;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;
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

        Previsao previsao = new Previsao();
        previsao.setPrevisao(tipoPrevisaoRepository.findById(response.tipoPrevisao().getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Tipo Previsão não encontrado para id " + response.tipoPrevisao().getId())));
        previsao.setNivelRisco(nivelRiscoRepository.findById(response.nivelRisco().getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Nível Risco não encontrado para id " + response.nivelRisco().getId())));
        previsao.setProbabilidade(response.probabilidade());
        previsao.setRecomendacao(response.recomendacao());

        if (response.tipoPrevisao() == TipoPrevisaoEnum.VAI_CANCELAR) {
            previsao.setExplicabilidade(response.explicabilidade());
        } else {
            previsao.setExplicabilidade(List.of());
        }

        previsaoRepository.save(previsao);

        return response;
    }

    public Page<PrevisaoListagem> listar(Pageable pageable) {
        return previsaoRepository.findAll(pageable).map(PrevisaoListagem::new);
    }

    public List<PrevisaoPorNivelRisco> obterGrafico() {
        return previsaoRepository.previsaoPorNivelRiscos();
    }

    public Long total() {
        return previsaoRepository.count();
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
