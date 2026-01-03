package equipe25.churninsight_backend.model.explicabilidade;

public enum ExplicabilidadeEnum {

    AGE("Age", "Idade"),
    BALANCE("Balance", "Saldo"),
    CREDIT_SCORE("CreditScore", "Score de Crédito"),
    TENURE("Tenure", "Tempo de Relacionamento"),
    ESTIMATED_SALARY("EstimatedSalary", "Salário Estimado"),

    SPAIN("SPAIN", "Espanha"),
    FRANCE("FRANCE", "França"),
    GERMANY("GERMANY", "Alemanha"),

    MALE("MALE", "Masculino"),
    FEMALE("FEMALE", "Feminino");

    private final String valorBancoDeDados;
    private final String displayName;

    ExplicabilidadeEnum(String valorBancoDeDados, String displayName) {
        this.valorBancoDeDados = valorBancoDeDados;
        this.displayName = displayName;
    }

    public String getValorBancoDeDados() {
        return valorBancoDeDados;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String traduzir(String valor) {
        for (ExplicabilidadeEnum explicabilidade : values()) {
            if (explicabilidade.valorBancoDeDados.equals(valor)) {
                return explicabilidade.displayName;
            }
        }

        return valor;
    }

}
