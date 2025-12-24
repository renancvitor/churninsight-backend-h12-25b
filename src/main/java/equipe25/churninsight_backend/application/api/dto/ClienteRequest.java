package equipe25.churninsight_backend.application.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import equipe25.churninsight_backend.model.genero.enums.GeneroEnum;
import equipe25.churninsight_backend.model.pais.enums.PaisEnum;
import jakarta.validation.constraints.*;

public record ClienteRequest(
        @NotNull @JsonProperty("CreditScore") Integer creditScore,
        @NotNull @Size(min = 3, max = 30, message = "Geography deve ter entre 3 e 30 caracteres") @JsonProperty("Geography") PaisEnum geography,
        @NotNull @JsonProperty("Gender") GeneroEnum gender,
        @NotNull @Min(value = 18, message = "Idade não pode ser menor que 18") @Max(value = 120, message = "Idade inválida") @JsonProperty("Age") Integer age,
        @NotNull @JsonProperty("Tenure") Integer tenure,
        @NotNull @JsonProperty("Balance") Double balance,
        @NotNull @JsonProperty("EstimatedSalary") Double estimatedSalary) {
}
