package equipe25.churninsight_backend.exception.domain;

public class ResultadoAindaNaoDisponivelException extends RuntimeException {

    private final String status;

    public ResultadoAindaNaoDisponivelException(String status) {
        super("Resultado ainda não disponível. Status atual: " + status);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
