package equipe25.churninsight_backend.application.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import equipe25.churninsight_backend.model.genero.enums.GeneroEnum;
import equipe25.churninsight_backend.model.pais.enums.PaisEnum;
import jakarta.validation.constraints.*;

public record ClienteRequest(
        @NotNull @Min(value = 350, message = "CreditScore deve ser no mínimo 350") @Max(value = 850, message = "CreditScore deve ser no máximo 850") @JsonProperty("CreditScore") Integer creditScore,
        @NotNull @JsonProperty("Geography") PaisEnum geography,
        @NotNull @JsonProperty("Gender") GeneroEnum gender,
        @NotNull @Min(value = 18, message = "Idade não pode ser menor que 18") @Max(value = 92, message = "Idade não pode ser maior que 92") @JsonProperty("Age") Integer age,
        @NotNull @Min(value = 0, message = "Tenure não pode ser menor que 0") @Max(value = 10, message = "Tenure não pode ser maior que 10") @JsonProperty("Tenure") Integer tenure,
        @NotNull @DecimalMin(value = "0.00", inclusive = true, message = "Balance não pode ser negativo") @DecimalMax(value = "250898.00", inclusive = true, message = "Balance excede o valor máximo permitido") @JsonProperty("Balance") Double balance,
        @NotNull @DecimalMin(value = "11.58", inclusive = true, message = "EstimatedSalary abaixo do mínimo permitido") @DecimalMax(value = "199992.48", inclusive = true, message = "EstimatedSalary acima do máximo permitido") @JsonProperty("EstimatedSalary") Double estimatedSalary) {
}
