package equipe25.churninsight_backend.model.genero.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import equipe25.churninsight_backend.exception.domain.RecursoNaoEncontradoException;

public enum GeneroEnum {

    MALE(1, "Male", "Masculino"),
    FEMALE(2, "Female", "Feminino");

    private final int id;
    private final String apiValue;
    private final String label;

    GeneroEnum(int id, String apiValue, String label) {
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
    public static GeneroEnum fromJson(String valor) {
        for (GeneroEnum genero : values()) {
            if (genero.apiValue.equalsIgnoreCase(valor)
                    || genero.name().equalsIgnoreCase(valor)) {
                return genero;
            }
        }
        throw new RecursoNaoEncontradoException("Gênero inválido: " + valor);
    }

}
