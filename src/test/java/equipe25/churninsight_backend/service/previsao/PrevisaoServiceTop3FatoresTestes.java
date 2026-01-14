package equipe25.churninsight_backend.service.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.nivelrisco.NivelRiscoRepository;
import equipe25.churninsight_backend.application.previsao.dto.FatorCountAnalytics;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoServiceTop3FatoresTestes {

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
        void deveriaRetornarListaComNoMaximoTresFatores() {
            FatorCountAnalytics fator = new FatorCountAnalytics("AGE", 5L);

            when(previsaoRepository.topFatores(any(PageRequest.class)))
                    .thenReturn(List.of(fator));

            List<FatorCountAnalytics> result = previsaoService.top3Fatores();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(fator, result.get(0));

            verify(previsaoRepository).topFatores(any(PageRequest.class));
        }

        @Test
        void deveriaAplicarPaginacaoCorreta() {
            when(previsaoRepository.topFatores(any(PageRequest.class)))
                    .thenReturn(List.of());

            previsaoService.top3Fatores();

            ArgumentCaptor<PageRequest> captor = ArgumentCaptor.forClass(PageRequest.class);

            verify(previsaoRepository).topFatores(captor.capture());

            PageRequest pageRequest = captor.getValue();
            assertEquals(0, pageRequest.getPageNumber());
            assertEquals(3, pageRequest.getPageSize());
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaRetornarListaVaziaQuandoNaoExistirFatores() {
            when(previsaoRepository.topFatores(any(PageRequest.class)))
                    .thenReturn(List.of());

            List<FatorCountAnalytics> result = previsaoService.top3Fatores();

            assertNotNull(result);
            assertTrue(result.isEmpty());

            verify(previsaoRepository).topFatores(any(PageRequest.class));
        }
    }

}
