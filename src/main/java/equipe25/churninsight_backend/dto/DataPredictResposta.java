package equipe25.churninsight_backend.dto;

import equipe25.churninsight_backend.enuns.NivelRisco;
import equipe25.churninsight_backend.enuns.PrevisaoChurn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataPredictResposta(
        //Enum
        @NotNull
        PrevisaoChurn previsaoChurn,
        @NotNull
        Float probabilidade,
        //Enum
        @NotNull
        NivelRisco nivelRisco,
        @NotBlank
        String recomendacao
){

}
