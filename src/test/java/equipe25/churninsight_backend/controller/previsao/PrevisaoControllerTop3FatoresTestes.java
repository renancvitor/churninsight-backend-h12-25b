package equipe25.churninsight_backend.controller.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
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
import equipe25.churninsight_backend.application.previsao.dto.FatorCountResponse;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoControllerTop3FatoresTestes {

    @Mock
    private PrevisaoService previsaoService;

    @InjectMocks
    private PrevisaoController previsaoController;

    @Nested
    class PositiveCases {
        @Test
        void deveriaDelegarParaServiceERetornarTop3Fatores() {
            FatorCountResponse response = new FatorCountResponse("Idade", 15L);

            List<FatorCountResponse> lista = List.of(response);

            when(previsaoService.top3FatoresResponse())
                    .thenReturn(lista);

            ResponseEntity<List<FatorCountResponse>> result = previsaoController.top3Fatores();

            assertNotNull(result);
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(lista, result.getBody());

            verify(previsaoService).top3FatoresResponse();
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaPropagarExcecaoQuandoServiceFalhar() {
            when(previsaoService.top3FatoresResponse())
                    .thenThrow(new RuntimeException("Erro simulado"));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> previsaoController.top3Fatores());

            assertEquals("Erro simulado", exception.getMessage());
            verify(previsaoService).top3FatoresResponse();
        }
    }

}
