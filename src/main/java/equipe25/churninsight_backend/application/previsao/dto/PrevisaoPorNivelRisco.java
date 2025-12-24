package equipe25.churninsight_backend.application.previsao.dto;

import equipe25.churninsight_backend.model.nivelrisco.NivelRiscoEntidade;

public record PrevisaoPorNivelRisco(
                NivelRiscoEntidade nivelRisco,
                Long quantidade) {
}
