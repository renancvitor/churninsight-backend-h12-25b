package equipe25.churninsight_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import equipe25.churninsight_backend.dto.DataPredictResposta;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PredictionService {

    private final WebClient webClient;

    public DataPredictResposta prever(PredictionRequest request) {
        return webClient.post()
                .uri("/predict")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(DataPredictResposta.class)
                .block();
    }

}
