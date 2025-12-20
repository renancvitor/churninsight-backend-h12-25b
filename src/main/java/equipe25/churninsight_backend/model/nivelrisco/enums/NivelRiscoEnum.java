package equipe25.churninsight_backend.model.nivelrisco.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NivelRiscoEnum {
    BAIXO(1, "Baixo"),
    MEDIO(2, "Medio"),
    ALTO(3, "Alto");

    private final int id;
    private final String displayName;

    NivelRiscoEnum(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static NivelRiscoEnum fromJson(String valor) {
        for (NivelRiscoEnum nivel : values()) {
            if (nivel.name().equalsIgnoreCase(valor)) {
                return nivel;
            }
        }
        throw new IllegalArgumentException("Nível de risco inválido: " + valor);
    }

    @JsonValue
    public String toJson() {
        return this.name();
    }

}
