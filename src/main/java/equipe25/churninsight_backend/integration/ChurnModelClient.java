package equipe25.churninsight_backend.integration;

import java.util.Map;

import equipe25.churninsight_backend.integration.model.PredictionResult;

public interface ChurnModelClient {

    PredictionResult predictionResult(Map<String, Object> payload);

}
