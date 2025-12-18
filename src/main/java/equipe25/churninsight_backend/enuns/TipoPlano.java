package equipe25.churninsight_backend.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum TipoPlano {

    BASICO("Basico"),
    PADRAO("Padrao"),
    PREMIUM("Premium");

    private final String valor;

    TipoPlano(String valor) {
        this.valor = valor;
    }

    // Aqui vou receber o valor comom String no JSON
    @JsonValue
    public String getValor() {
        return valor;
    }

    @JsonCreator // Aqui vou converter o valor recebido em String para o Enum ignorando maiúsculas e minúsculas
    public static TipoPlano fromValor(String valor) {
        return Arrays.stream(values())
                .filter(p -> p.valor.equalsIgnoreCase(valor))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Plano inválido: " + valor)
                );
    }
}