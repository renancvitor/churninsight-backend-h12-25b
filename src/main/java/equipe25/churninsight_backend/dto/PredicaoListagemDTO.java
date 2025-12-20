package equipe25.churninsight_backend.dto;

import equipe25.churninsight_backend.enuns.NivelRiscoEnum;
import equipe25.churninsight_backend.enuns.PrevisaoChurnEnum;
import equipe25.churninsight_backend.model.PredicaoChurnEntidade;

public record PredicaoListagemDTO(
        Long id,
        String previsao,
        String nivelRisco,
        Double probabilidade,
        String recomendacao) {

    public PredicaoListagemDTO(PredicaoChurnEntidade predicao) {
        this(
                predicao.getId(),
                PrevisaoChurnEnum.valueOf(predicao.getPrevisao().getPrevisaoNome()).getDisplayName(),
                NivelRiscoEnum.valueOf(predicao.getNivelRisco().getNivelRiscoNome()).getDisplayName(),
                predicao.getProbabilidade(),
                predicao.getRecomendacao());
    }

}
