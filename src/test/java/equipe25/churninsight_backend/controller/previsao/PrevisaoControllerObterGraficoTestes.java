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
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoPorNivelRisco;
import equipe25.churninsight_backend.utils.FabricaObjetosTeste;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoControllerObterGraficoTestes {

    @Mock
    private PrevisaoService previsaoService;

    @InjectMocks
    private PrevisaoController previsaoController;

    @Nested
    class PositiveCases {
        @Test
        void deveriaDelegarParaServiceERetornarListaDoGrafico() {
            PrevisaoPorNivelRisco grafico = new PrevisaoPorNivelRisco(
                    FabricaObjetosTeste.nivelRiscoEnumBaixo().name(),
                    10L);

            List<PrevisaoPorNivelRisco> lista = List.of(grafico);

            when(previsaoService.obterGrafico())
                    .thenReturn(lista);

            ResponseEntity<List<PrevisaoPorNivelRisco>> result = previsaoController.obterGrafico();

            assertNotNull(result);
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(lista, result.getBody());

            verify(previsaoService).obterGrafico();
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaPropagarExcecaoQuandoServiceFalhar() {
            when(previsaoService.obterGrafico())
                    .thenThrow(new RuntimeException("Erro simulado"));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> previsaoController.obterGrafico());

            assertEquals("Erro simulado", exception.getMessage());
            verify(previsaoService).obterGrafico();
        }
    }

}
