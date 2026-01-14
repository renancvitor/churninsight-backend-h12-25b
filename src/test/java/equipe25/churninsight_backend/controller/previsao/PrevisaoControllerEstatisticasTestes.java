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
import equipe25.churninsight_backend.application.previsao.controller.PrevisaoController;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import equipe25.churninsight_backend.application.previsao.dto.EstatisticasResponse;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoControllerEstatisticasTestes {

    @Mock
    private PrevisaoService previsaoService;

    @InjectMocks
    private PrevisaoController previsaoController;

    @Nested
    class PositiveCases {
        @Test
        void deveriaDelegarParaServiceERetornarEstatisticas() {
            EstatisticasResponse response = new EstatisticasResponse(120L, 32.5);

            when(previsaoService.estatisticas())
                    .thenReturn(response);

            ResponseEntity<EstatisticasResponse> result = previsaoController.stats();

            assertNotNull(result);
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(response, result.getBody());

            verify(previsaoService).estatisticas();
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaPropagarExcecaoQuandoServiceFalhar() {
            when(previsaoService.estatisticas())
                    .thenThrow(new RuntimeException("Erro simulado"));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> previsaoController.stats());

            assertEquals("Erro simulado", exception.getMessage());
            verify(previsaoService).estatisticas();
        }
    }

}
