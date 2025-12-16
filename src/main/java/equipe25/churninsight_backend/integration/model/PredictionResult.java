package equipe25.churninsight_backend.integration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredictionResult {

    /*
     * Quando enum existir, substituir:
     * private ChurnPrediction previsao;
     */
    private String previsao;
    private Double probabilidade;
    /*
     * Quando enum existir, substituir:
     * private RiskLevel nivelRisco;
     */
    private String nivelRisco;
    private String recomendacao;

}
