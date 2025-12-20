package equipe25.churninsight_backend.application.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import equipe25.churninsight_backend.model.TipoPrevisao.enums.TipoPrevisaoEnum;
import equipe25.churninsight_backend.model.nivelrisco.enums.NivelRiscoEnum;

public record ClienteResponse(

        @JsonProperty("previsao") TipoPrevisaoEnum previsaoChurn,
        @JsonProperty("probabilidade") Float probabilidade,
        @JsonProperty("nivel_risco") NivelRiscoEnum nivelRisco,
        @JsonProperty("recomendacao") String recomendacao) {
}
