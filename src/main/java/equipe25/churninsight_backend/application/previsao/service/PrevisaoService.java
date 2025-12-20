package equipe25.churninsight_backend.application.previsao.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import equipe25.churninsight_backend.application.api.dto.ClientRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoListagem;
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoPorNivelRisco;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.model.previsao.Previsao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrevisaoService {

    private final PrevisaoRepository previsaoRepository;
    private final WebClient webClient;

    @Transactional
    public ClienteResponse prever(ClientRequest request) {
        ClienteResponse response = webClient.post()
                .uri("/predict")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ClienteResponse.class)
                .block();

        Previsao previsao = new Previsao();
        previsao.setPrevisao(response.tipoPrevisao());
        previsao.setNivelRisco(response.nivelRisco());
        previsao.setProbabilidade(response.probabilidade());
        previsao.setRecomendacao(response.recomendacao());

        previsaoRepository.save(previsao);

        return response;
    }

    public Page<PrevisaoListagem> listar(Pageable pageable) {
        return previsaoRepository.findAll(pageable).map(PrevisaoListagem::new);
    }

    public List<PrevisaoPorNivelRisco> obterGrafico() {
        return previsaoRepository.previsaoPorNivelRiscos();
    }

}
