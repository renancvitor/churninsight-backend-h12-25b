package equipe25.churninsight_backend.controller.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.previsao.controller.PrevisaoBatchController;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoBatchService;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoBatchControllerDownloadResultadoTestes {

    @Mock
    private PrevisaoBatchService previsaoBatchService;

    @Mock
    private PrevisaoClienteService previsaoClienteService;

    @InjectMocks
    private PrevisaoBatchController previsaoBatchController;

    @Mock
    private MultipartFile file;

    @Test
    void deveriaRetornarCsvComHeadersCorretos() {
        Resource resource = mock(Resource.class);

        when(previsaoBatchService.baixarResultadoFinalizado("job-123"))
                .thenReturn(resource);

        ResponseEntity<Resource> result = previsaoBatchController.download("job-123");

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(resource, result.getBody());

        assertEquals(
                "attachment; filename=resultado.csv",
                result.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));

        assertEquals(
                MediaType.parseMediaType("text/csv"),
                result.getHeaders().getContentType());

        verify(previsaoBatchService)
                .baixarResultadoFinalizado("job-123");
    }

    @Test
    void deveriaPropagarExcecaoQuandoServiceFalhar() {
        when(previsaoBatchService.baixarResultadoFinalizado("job-123"))
                .thenThrow(new RuntimeException("Erro simulado"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> previsaoBatchController.download("job-123"));

        assertEquals("Erro simulado", exception.getMessage());
        verify(previsaoBatchService).baixarResultadoFinalizado("job-123");
    }

}
