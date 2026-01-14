package equipe25.churninsight_backend.controller.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;
import equipe25.churninsight_backend.application.api.dto.BatchJobResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.previsao.controller.PrevisaoBatchController;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoBatchService;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoBatchControllerTestes {

    @Mock
    private PrevisaoBatchService previsaoBatchService;

    @Mock
    private PrevisaoClienteService previsaoClienteService;

    @InjectMocks
    private PrevisaoBatchController previsaoBatchController;

    @Mock
    private MultipartFile file;

    @Nested
    class EnviarBatch {

        @Test
        void deveriaAceitarArquivoERetornarJobComStatusAccepted() {
            BatchJobResponse response = new BatchJobResponse("job-123", "PROCESSANDO");

            when(previsaoBatchService.processarBatch(file))
                    .thenReturn(response);

            ResponseEntity<BatchJobResponse> result = previsaoBatchController.enviar(file);

            assertNotNull(result);
            assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
            assertEquals(response, result.getBody());

            verify(previsaoBatchService).processarBatch(file);
        }

        @Test
        void deveriaPropagarExcecaoQuandoServiceFalhar() {
            when(previsaoBatchService.processarBatch(file))
                    .thenThrow(new RuntimeException("Erro simulado"));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> previsaoBatchController.enviar(file));

            assertEquals("Erro simulado", exception.getMessage());
            verify(previsaoBatchService).processarBatch(file);
        }
    }

}
