package equipe25.churninsight_backend.dto;

import jakarta.validation.constraints.*;

public record EntradaCliente(
        @NotNull
        String CreditScore,
        @NotNull
        String Geography,
        @NotNull
        String Gender,
        @NotNull
        String Age ,
        @NotNull
        String  Tenure,
        @NotNull
        String Balance,
        @NotNull
        String  EstimatedSalary
) {
}
