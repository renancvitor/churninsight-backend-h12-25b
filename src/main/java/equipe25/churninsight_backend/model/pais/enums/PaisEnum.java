package equipe25.churninsight_backend.model.pais.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaisEnum {

    FRANCE(1, "France"),
    GERMANY(2, "Germany"),
    SPAIN(3, "Spain");

    private final int id;
    private final String displayName;

    PaisEnum(int id, String displayName) {
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
    public static PaisEnum fromJson(String valor) {
        for (PaisEnum pais : values()) {
            if (pais.displayName.equalsIgnoreCase(valor)
                    || pais.name().equalsIgnoreCase(valor)) {
                return pais;
            }
        }
        throw new IllegalArgumentException("Gênero inválido: " + valor);
    }

}
