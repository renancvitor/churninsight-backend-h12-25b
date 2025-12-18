package equipe25.churninsight_backend.enuns;

public enum PrevisaoChurnEnum {
    VAI_CONTINUAR(1, "Vai continuar"),
    VAI_CANCELAR(2, "Vai cancelar");

    private final int id;
    private final String displayName;

    PrevisaoChurnEnum(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PrevisaoChurnEnum fromId(int id) {
        for (PrevisaoChurnEnum previsaoChurnEnum : values()) {
            if (previsaoChurnEnum.id == id) {
                return previsaoChurnEnum;
            }
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }

}
