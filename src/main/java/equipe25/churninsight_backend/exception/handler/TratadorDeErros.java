package equipe25.churninsight_backend.exception.handler;

import equipe25.churninsight_backend.exception.domain.IntegracaoExternaException;
import equipe25.churninsight_backend.exception.domain.RecursoNaoEncontradoException;
import equipe25.churninsight_backend.exception.domain.RegraNegocioException;
import equipe25.churninsight_backend.exception.dto.DadosErro;
import equipe25.churninsight_backend.exception.dto.DadosErroValidacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {



    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<DadosErro> tratarErro404(RecursoNaoEncontradoException e) {
        String mensagem = e.getMessage();
        DadosErro dadosErro = new DadosErro(mensagem, 404);
        return ResponseEntity.status(dadosErro.status()).body(dadosErro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DadosErro> tratarErro500(Exception e) {
        String mensagem = "Ocorreu um erro interno no servidor.";

        DadosErro dadosErro = new DadosErro(mensagem, 500);
        return ResponseEntity.status(dadosErro.status()).body(dadosErro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException e) {

        var erros = e.getFieldErrors()
                .stream()
                .map(error -> new DadosErroValidacao(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.status(400).body(erros);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<DadosErro> tratarErro422(RegraNegocioException e) {
        String mensagem = "Erro na regra de negócio";
        DadosErro dadosErro = new DadosErro(mensagem, 422);
        return ResponseEntity.status(dadosErro.status()).body(dadosErro);
    }

    @ExceptionHandler(IntegracaoExternaException.class)
    public ResponseEntity<DadosErro> tratarErro503(IntegracaoExternaException e) {
        String mensagem = e.getMessage();
        DadosErro dadosErro = new DadosErro(mensagem, 503);
        return ResponseEntity.status(dadosErro.status()).body(dadosErro);
    }
}
