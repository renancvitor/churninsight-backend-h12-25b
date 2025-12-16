package equipe25.churninsight_backend.integration;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import equipe25.churninsight_backend.integration.model.PredictionResult;
import lombok.extern.slf4j.Slf4j;

@Component
@Profile("mock")
@Slf4j
public class ChurnModelClientMock implements ChurnModelClient {

    @Override
    public PredictionResult predictionResult(Map<String, Object> payload) {

        log.info("Usando MOCK de Data Science");

        Integer creditScore = (Integer) payload.get("CreditScore");
        Integer age = (Integer) payload.get("Age");
        Double balance = payload.get("Balance") != null
                ? ((Number) payload.get("Balance")).doubleValue()
                : 0.0;

        double probabilidade;
        String previsao;

        // Regraa mockada simples
        if (creditScore != null && creditScore < 500) {
            probabilidade = 0.82;
            previsao = "Vai cancelar";
        } else if (balance < 1000) {
            probabilidade = 0.67;
            previsao = "Vai cancelar";
        } else if (age != null && age > 55) {
            probabilidade = 0.45;
            previsao = "Vai continuar";
        } else {
            probabilidade = 0.24;
            previsao = "Vai continuar";
        }

        return PredictionResult.builder()
                .previsao(previsao)
                .probabilidade(probabilidade) // nivelRisco e recomendacao trataremos na Serivce
                .build();
    }

}
