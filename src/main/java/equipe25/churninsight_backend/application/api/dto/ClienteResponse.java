package equipe25.churninsight_backend.application.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import equipe25.churninsight_backend.model.nivelrisco.enums.NivelRiscoEnum;
import equipe25.churninsight_backend.model.tipoprevisao.enums.TipoPrevisaoEnum;

public record ClienteResponse(

        @JsonProperty("previsao") TipoPrevisaoEnum tipoPrevisao,
        @JsonProperty("probabilidade") Double probabilidade,
        @JsonProperty("nivel_risco") NivelRiscoEnum nivelRisco,
        @JsonProperty("recomendacao") String recomendacao) {
}
