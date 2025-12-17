package equipe25.churninsight_backend.enuns;

public enum NivelRiscoEnum {
    BAIXO(1,"Baixo"),
    MEDIO(2,"Medio"),
    ALTO(3,"Alto");
    final int id;
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

    public static NivelRiscoEnum fromId(int id) {
        for (NivelRiscoEnum nivelRiscoEnum : values()) {
            if (nivelRiscoEnum.id == id) {
                return nivelRiscoEnum;
            }
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }
}
