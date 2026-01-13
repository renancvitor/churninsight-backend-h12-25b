package equipe25.churninsight_backend.service.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoClienteServiceBaixarResultadoTestes {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PrevisaoClienteService previsaoClienteService;

    @Nested
    class PositiveCases {
        @Test
        void deveriaBaixarResultadoDoBatch() {
            Resource csv = mock(Resource.class);

            when(restTemplate.getForObject(
                    eq("https://churn-api-hackathon.duckdns.org/previsao-lote/download/job-123"),
                    eq(Resource.class)))
                    .thenReturn(csv);

            Resource result = previsaoClienteService.baixarResultado("job-123");

            assertNotNull(result);
            assertEquals(csv, result);

            verify(restTemplate).getForObject(
                    "https://churn-api-hackathon.duckdns.org/previsao-lote/download/job-123",
                    Resource.class);
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaPropagarExcecaoQuandoApiFalhar() {
            when(restTemplate.getForObject(
                    anyString(),
                    eq(Resource.class)))
                    .thenThrow(new RuntimeException("Erro API"));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> previsaoClienteService.baixarResultado("job-123"));

            assertEquals("Erro API", exception.getMessage());
        }
    }

}
