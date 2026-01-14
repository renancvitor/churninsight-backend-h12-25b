package equipe25.churninsight_backend.controller.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import equipe25.churninsight_backend.application.api.dto.ClienteRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import equipe25.churninsight_backend.application.previsao.controller.PrevisaoController;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import equipe25.churninsight_backend.utils.FabricaObjetosTeste;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoControllerPreverTestes {

    @Mock
    private PrevisaoService previsaoService;

    @InjectMocks
    private PrevisaoController previsaoController;

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
        void deveriaDelegarParaServiceERetornarResponse() {
            when(previsaoService.prever(any(ClienteRequest.class)))
                    .thenReturn(response);

            ResponseEntity<ClienteResponse> result = previsaoController.prever(request);

            assertNotNull(result);
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(response, result.getBody());

            verify(previsaoService).prever(request);
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaPropagarExcecaoQuandoServiceFalhar() {
            when(previsaoService.prever(any(ClienteRequest.class)))
                    .thenThrow(new RuntimeException("Erro simulado"));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> previsaoController.prever(request));

            assertEquals("Erro simulado", exception.getMessage());
            verify(previsaoService).prever(request);
        }
    }

}
