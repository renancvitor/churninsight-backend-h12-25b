package equipe25.churninsight_backend.dto;

import equipe25.churninsight_backend.enuns.TipoPlano;
import jakarta.validation.constraints.*;

public record EntradaCliente(
        @NotNull
        @Min(value = 1, message = "O tempo de contrato deve ser maior que 1")
        @Max(240)
        Integer tempoContratoMeses,

        @NotNull
        @PositiveOrZero
        @Max(value = 3,message = "O número máximo de atrasos permitido é 3, acima disso, encerre ou bloqueie assinatura")
        Integer atrasosPagamento,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "1000.0", inclusive = true)
        Double usoMensal,

        @NotNull
        TipoPlano plano
) {
}
