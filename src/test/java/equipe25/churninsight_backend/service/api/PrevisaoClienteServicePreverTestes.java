package equipe25.churninsight_backend.service.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import equipe25.churninsight_backend.application.api.dto.ClienteRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.utils.FabricaObjetosTeste;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoClienteServicePreverTestes {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PrevisaoClienteService previsaoClienteService;

    private ClienteRequest request;
    private ClienteResponse response;

    @BeforeEach
    void setup() {
        request = FabricaObjetosTeste.criarRequest();
        response = FabricaObjetosTeste.criarResponse();
    }

    @Nested
    class PositiveCases {
        @Test
        void deveriaChamarApiPythonERetornarResponse() {
            when(restTemplate.postForObject(
                    eq("https://churn-hackathon.onrender.com/previsao"),
                    eq(request),
                    eq(ClienteResponse.class)))
                    .thenReturn(response);

            ClienteResponse result = previsaoClienteService.prever(request);

            assertNotNull(result);
            assertEquals(response, result);

            verify(restTemplate).postForObject(
                    "https://churn-hackathon.onrender.com/previsao",
                    request,
                    ClienteResponse.class);
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaPropagarExcecaoQuandoApiFalhar() {
            when(restTemplate.postForObject(
                    anyString(),
                    any(),
                    eq(ClienteResponse.class)))
                    .thenThrow(new RuntimeException("Erro API externa"));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> previsaoClienteService.prever(request));

            assertEquals("Erro API externa", exception.getMessage());
        }
    }

}
