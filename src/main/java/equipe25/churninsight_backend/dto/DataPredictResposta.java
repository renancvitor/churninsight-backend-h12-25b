package equipe25.churninsight_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import equipe25.churninsight_backend.enuns.NivelRiscoEnum;
import equipe25.churninsight_backend.enuns.PrevisaoChurnEnum;

public record DataPredictResposta(

        @JsonProperty("previsao") PrevisaoChurnEnum previsaoChurn,
        @JsonProperty("probabilidade") Float probabilidade,
        @JsonProperty("nivel_risco") NivelRiscoEnum nivelRisco,
        @JsonProperty("recomendacao") String recomendacao) {
}
