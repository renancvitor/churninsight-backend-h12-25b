package equipe25.churninsight_backend.model.pais.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import equipe25.churninsight_backend.exception.domain.RecursoNaoEncontradoException;

public enum PaisEnum {

    FRANCE(1, "France", "França"),
    GERMANY(2, "Germany", "Alemanha"),
    SPAIN(3, "Spain", "Espanha");

    private final int id;
    private final String apiValue;
    private final String label;

    PaisEnum(int id, String apiValue, String label) {
        this.id = id;
        this.apiValue = apiValue;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    @JsonValue
    public String getApiValue() {
        return apiValue;
    }

    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static PaisEnum fromJson(String valor) {
        for (PaisEnum pais : values()) {
            if (pais.apiValue.equalsIgnoreCase(valor)
                    || pais.name().equalsIgnoreCase(valor)) {
                return pais;
            }
        }
        throw new RecursoNaoEncontradoException("País inválido: " + valor);
    }
}
