package equipe25.churninsight_backend.service.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.nivelrisco.NivelRiscoRepository;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoServiceTotalTestes {

    @Mock
    private PrevisaoRepository previsaoRepository;

    @Mock
    private PrevisaoClienteService previsaoClienteService;

    @Mock
    private NivelRiscoRepository nivelRiscoRepository;

    @Mock
    private TipoPrevisaoRepository tipoPrevisaoRepository;

    @InjectMocks
    private PrevisaoService previsaoService;

    @Nested
    class PositiveCases {
        @Test
        void deveriaRetornarTotalCorretamente() {
            when(previsaoRepository.count())
                    .thenReturn(10L);

            Long result = previsaoService.total();

            assertNotNull(result);
            assertEquals(10L, result);

            verify(previsaoRepository).count();
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaRetornarZeroQuandoNaoExistirRegistro() {
            when(previsaoRepository.count())
                    .thenReturn(0L);

            Long result = previsaoService.total();

            assertNotNull(result);
            assertEquals(0L, result);

            verify(previsaoRepository).count();
        }
    }

}
