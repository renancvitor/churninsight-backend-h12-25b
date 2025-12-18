package equipe25.churninsight_backend.dto;

import equipe25.churninsight_backend.enuns.NivelRiscoEnum;
import equipe25.churninsight_backend.enuns.PrevisaoChurnEnum;

public record DataPredictResposta(
                PrevisaoChurnEnum previsaoChurn,
                Float probabilidade,
                NivelRiscoEnum nivelRisco,
                String recomendacao) {

}
