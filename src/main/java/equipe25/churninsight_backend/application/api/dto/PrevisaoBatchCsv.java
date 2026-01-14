package equipe25.churninsight_backend.application.api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.opencsv.bean.CsvBindByName;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrevisaoBatchCsv {

    @CsvBindByName(column = "CreditScore")
    private Integer creditScore;

    @CsvBindByName(column = "Geography")
    private String geography;

    @CsvBindByName(column = "Gender")
    private String gender;

    @CsvBindByName(column = "Age")
    private Integer age;

    @CsvBindByName(column = "Tenure")
    private Integer tenure;

    @CsvBindByName(column = "Balance")
    private BigDecimal balance;

    @CsvBindByName(column = "EstimatedSalary")
    private BigDecimal estimatedSalary;

    @CsvBindByName(column = "probabilidade")
    private BigDecimal probabilidade;

    @CsvBindByName(column = "nivel_risco")
    private String nivelRisco;

    @CsvBindByName(column = "previsao")
    private String previsao;

    @CsvBindByName(column = "explicabilidade")
    private String explicabilidade;
}
