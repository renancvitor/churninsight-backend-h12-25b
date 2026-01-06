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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import equipe25.churninsight_backend.application.previsao.controller.PrevisaoController;
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoListagem;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import equipe25.churninsight_backend.model.previsao.Previsao;
import equipe25.churninsight_backend.utils.FabricaObjetosTeste;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoControllerListarTestes {

    @Mock
    private PrevisaoService previsaoService;

    @InjectMocks
    private PrevisaoController previsaoController;

    @Nested
    class PositiveCases {
        @Test
        void deveriaDelegarParaServiceERetornarPagina() {
            Pageable pageable = PageRequest.of(0, 10);

            Previsao previsao = FabricaObjetosTeste.criarPrevisao();
            Page<PrevisaoListagem> page = new PageImpl<>(
                    List.of(new PrevisaoListagem(previsao)),
                    pageable,
                    1);

            when(previsaoService.listar(pageable))
                    .thenReturn(page);

            ResponseEntity<Page<PrevisaoListagem>> result = previsaoController.listar(pageable);

            assertNotNull(result);
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(page, result.getBody());

            verify(previsaoService).listar(pageable);
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaPropagarExcecaoQuandoServiceFalhar() {
            Pageable pageable = PageRequest.of(0, 10);

            when(previsaoService.listar(pageable))
                    .thenThrow(new RuntimeException("Erro simulado"));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> previsaoController.listar(pageable));

            assertEquals("Erro simulado", exception.getMessage());
            verify(previsaoService).listar(pageable);
        }
    }

}
