package equipe25.churninsight_backend.model.tipoprevisao.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPrevisaoEnum {

    VAI_CONTINUAR(1, "Vai continuar"),
    VAI_CANCELAR(2, "Vai cancelar");

    private final int id;
    private final String displayName;

    TipoPrevisaoEnum(int id, String displayName) {
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
    public static TipoPrevisaoEnum fromJson(String valor) {
        for (TipoPrevisaoEnum previsao : values()) {
            if (previsao.displayName.equalsIgnoreCase(valor)
                    || previsao.name().equalsIgnoreCase(valor)) {
                return previsao;
            }
        }
        throw new IllegalArgumentException("Tipo previsão inválido: " + valor);
    }

}
