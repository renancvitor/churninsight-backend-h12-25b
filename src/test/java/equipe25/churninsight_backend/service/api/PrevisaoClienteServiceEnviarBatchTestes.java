package equipe25.churninsight_backend.service.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import equipe25.churninsight_backend.application.api.dto.BatchJobResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoClienteServiceEnviarBatchTestes {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PrevisaoClienteService previsaoClienteService;

    private Resource resource;

    @BeforeEach
    void setup() {
        resource = mock(Resource.class);
    }

    @Nested
    class PositiveCases {
        @Test
        void deveriaEnviarArquivoBatchERetornarJob() {
            BatchJobResponse response = new BatchJobResponse("job-123", "PROCESSANDO");

            when(restTemplate.postForObject(
                    eq("https://churn-api-hackathon.duckdns.org/previsao-lote"),
                    any(HttpEntity.class),
                    eq(BatchJobResponse.class)))
                    .thenReturn(response);

            BatchJobResponse result = previsaoClienteService.enviarBatch(resource);

            assertNotNull(result);
            assertEquals(response, result);

            verify(restTemplate).postForObject(
                    eq("https://churn-api-hackathon.duckdns.org/previsao-lote"),
                    any(HttpEntity.class),
                    eq(BatchJobResponse.class));
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaPropagarExcecaoQuandoApiFalhar() {
            when(restTemplate.postForObject(
                    anyString(),
                    any(HttpEntity.class),
                    eq(BatchJobResponse.class)))
                    .thenThrow(new RuntimeException("Erro API"));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> previsaoClienteService.enviarBatch(resource));

            assertEquals("Erro API", exception.getMessage());
        }
    }

}
