package equipe25.churninsight_backend.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum GeneroEnum {

    MALE("Male"),
    FEMALE("Female");

    private final String valor;

    GeneroEnum(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() { return valor; }

    public static GeneroEnum fromValor(String valor) {
        return Arrays.stream(values())
                .filter(p -> p.valor.equalsIgnoreCase(valor))
                .findFirst() .orElseThrow(() -> new IllegalArgumentException("Gênero inválido: " + valor) ); }
}
