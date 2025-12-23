package equipe25.churninsight_backend.model.pais.enums;

public enum PaisEnum {

    FRANCE("France"),
    GERMANY("Germany"),
    SPAIN("Spain");

    private final String nome;

    PaisEnum(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static PaisEnum from(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("País não pode ser nulo");
        }

        for (PaisEnum pais : values()) {
            if (pais.name().equalsIgnoreCase(valor)
                    || pais.nome.equalsIgnoreCase(valor)) {
                return pais;
            }
        }

        throw new IllegalArgumentException("País inválido: " + valor);
    }
}
