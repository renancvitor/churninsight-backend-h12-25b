package equipe25.churninsight_backend.service.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import equipe25.churninsight_backend.application.api.dto.BatchStatusResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoClienteServiceConsultarStatusTestes {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PrevisaoClienteService previsaoClienteService;

    @Nested
    class PositiveCases {
        @Test
        void deveriaConsultarStatusDoBatch() {
            BatchStatusResponse status = new BatchStatusResponse("PROCESSANDO");

            when(restTemplate.getForObject(
                    eq("https://api-ds.duckdns.org/previsao-lote/status/job-123"),
                    eq(BatchStatusResponse.class)))
                    .thenReturn(status);

            BatchStatusResponse result = previsaoClienteService.consultarStatus("job-123");

            assertNotNull(result);
            assertEquals(status, result);

            verify(restTemplate).getForObject(
                    "https://api-ds.duckdns.org/previsao-lote/status/job-123",
                    BatchStatusResponse.class);
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaPropagarExcecaoQuandoApiFalhar() {
            when(restTemplate.getForObject(
                    anyString(),
                    eq(BatchStatusResponse.class)))
                    .thenThrow(new RuntimeException("Erro API"));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> previsaoClienteService.consultarStatus("job-123"));

            assertEquals("Erro API", exception.getMessage());
        }
    }

}
