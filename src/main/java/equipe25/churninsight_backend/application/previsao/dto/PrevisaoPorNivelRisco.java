package equipe25.churninsight_backend.application.previsao.dto;

import equipe25.churninsight_backend.model.nivelrisco.enums.NivelRiscoEnum;

public record PrevisaoPorNivelRisco(
        NivelRiscoEnum nivelRisco,
        Long quantidade) {
}
