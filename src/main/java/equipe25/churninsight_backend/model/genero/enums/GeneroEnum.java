package equipe25.churninsight_backend.model.genero.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GeneroEnum {

    MALE(1, "Male"),
    FEMALE(2, "Female");

    private final int id;
    private final String displayName;

    GeneroEnum(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static GeneroEnum fromJson(String valor) {
        for (GeneroEnum genero : values()) {
            if (genero.displayName.equalsIgnoreCase(valor)
                    || genero.name().equalsIgnoreCase(valor)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Gênero inválido: " + valor);
    }

}