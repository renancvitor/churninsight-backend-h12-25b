package equipe25.churninsight_backend.application.previsao.dto;

import java.util.List;

public record DominioResponse<T>(
                List<T> dados) {
}
