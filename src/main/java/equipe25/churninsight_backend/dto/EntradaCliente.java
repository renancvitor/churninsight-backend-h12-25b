package equipe25.churninsight_backend.dto;

import equipe25.churninsight_backend.enuns.GeneroEnum;
import jakarta.validation.constraints.*;

public record EntradaCliente(
        @NotNull
        String CreditScore,
        @NotNull
        @Size(min = 3, max = 30, message = "Geography deve ter entre 3 e 30 caracteres")
        String geography,
        @NotNull
        GeneroEnum Gender,
        @Max(value = 120, message = "Idade inválida")
        @Min(value = 18,message = "idade não pode ser menor que 18")
        @NotNull
        Integer Age ,
        @NotNull
        String  Tenure,
        @NotNull
        String Balance,
        @NotNull
        String EstimatedSalary
) {
}
