package equipe25.churninsight_backend.application.previsao.dto;

import equipe25.churninsight_backend.model.nivelrisco.enums.NivelRiscoEnum;
import equipe25.churninsight_backend.model.previsao.Previsao;
import equipe25.churninsight_backend.model.tipoprevisao.enums.TipoPrevisaoEnum;

public record PrevisaoListagem(
        Long id,
        String previsao,
        String nivelRisco,
        Double probabilidade) {
    // String recomendacao) {

    public PrevisaoListagem(Previsao predicao) {
        this(
                predicao.getId(),
                TipoPrevisaoEnum.valueOf(predicao.getPrevisao().getTipoPrevisao()).getDisplayName(),
                NivelRiscoEnum.valueOf(predicao.getNivelRisco().getNivelRiscoNome()).getDisplayName(),
                predicao.getProbabilidade());
        // predicao.getRecomendacao());
    }

}
