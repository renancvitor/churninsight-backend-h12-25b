package equipe25.churninsight_backend.controller.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;
import equipe25.churninsight_backend.application.api.dto.BatchStatusResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.previsao.controller.PrevisaoBatchController;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoBatchService;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoBatchControllerStatusBatchTestes {

    @Mock
    private PrevisaoBatchService previsaoBatchService;

    @Mock
    private PrevisaoClienteService previsaoClienteService;

    @InjectMocks
    private PrevisaoBatchController previsaoBatchController;

    @Mock
    private MultipartFile file;

    @Test
    void deveriaRetornarStatusDoBatch() {
        BatchStatusResponse status = new BatchStatusResponse("PROCESSANDO");

        when(previsaoClienteService.consultarStatus("job-123"))
                .thenReturn(status);

        ResponseEntity<BatchStatusResponse> result = previsaoBatchController.status("job-123");

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(status, result.getBody());

        verify(previsaoClienteService)
                .consultarStatus("job-123");
    }

    @Test
    void deveriaPropagarExcecaoQuandoServiceFalhar() {
        when(previsaoClienteService.consultarStatus("job-123"))
                .thenThrow(new RuntimeException("Erro simulado"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> previsaoBatchController.status("job-123"));

        assertEquals("Erro simulado", exception.getMessage());
        verify(previsaoClienteService).consultarStatus("job-123");
    }

}
