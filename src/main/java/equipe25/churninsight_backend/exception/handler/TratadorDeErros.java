package equipe25.churninsight_backend.exception.handler;

import equipe25.churninsight_backend.exception.domain.IntegracaoExternaException;
import equipe25.churninsight_backend.exception.domain.RecursoNaoEncontradoException;
import equipe25.churninsight_backend.exception.domain.RegraNegocioException;
import equipe25.churninsight_backend.exception.dto.DadosErro;
import equipe25.churninsight_backend.exception.dto.DadosErroValidacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {

    private static final Logger log = LoggerFactory.getLogger(TratadorDeErros.class);

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<DadosErro> tratarErro404(RecursoNaoEncontradoException e) {

        log.warn("Recurso não encontrado: {}", e.getMessage());

        return ResponseEntity
                .status(404)
                .body(new DadosErro(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException e) {

        var erros = e.getFieldErrors()
                .stream()
                .map(error -> new DadosErroValidacao(
                        error.getField(),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.status(400).body(erros);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<DadosErro> tratarErro422(RegraNegocioException e) {

        log.warn("Violação de regra de negócio: {}", e.getMessage());

        return ResponseEntity
                .status(422)
                .body(new DadosErro("Erro na regra de negócio"));
    }

    @ExceptionHandler(IntegracaoExternaException.class)
    public ResponseEntity<DadosErro> tratarErro503(IntegracaoExternaException e) {

        log.error("Falha em integração externa", e);

        return ResponseEntity
                .status(503)
                .body(new DadosErro(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DadosErro> tratarErro500(Exception e) {

        log.error("Erro interno não tratado", e);

        return ResponseEntity
                .status(500)
                .body(new DadosErro("Ocorreu um erro interno no servidor."));
    }

}
