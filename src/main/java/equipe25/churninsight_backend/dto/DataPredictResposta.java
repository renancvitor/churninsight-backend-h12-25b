package equipe25.churninsight_backend.dto;

import equipe25.churninsight_backend.enuns.NivelRisco;
import equipe25.churninsight_backend.enuns.PrevisaoChurn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataPredictResposta(
        PrevisaoChurn previsaoChurn,
        Float probabilidade,
        NivelRisco nivelRisco,
        String recomendacao
){

}
