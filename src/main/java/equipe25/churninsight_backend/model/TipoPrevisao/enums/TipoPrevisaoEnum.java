package equipe25.churninsight_backend.model.TipoPrevisao.enums;

public enum TipoPrevisaoEnum {
    VAI_CONTINUAR(1, "Vai continuar"),
    VAI_CANCELAR(2, "Vai cancelar");

    private int id;
    private final String displayName;

    TipoPrevisaoEnum(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static TipoPrevisaoEnum fromId(int id) {
        for (TipoPrevisaoEnum previsaoChurnEnum : values()) {
            if (previsaoChurnEnum.id == id) {
                return previsaoChurnEnum;
            }
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }

}
