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
import equipe25.churninsight_backend.application.previsao.dto.EstatisticasResponse;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoServiceEstatisticasTestes {

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
        void deveriaRetornarEstatisticasQuandoTaxaChurnExistir() {
            when(previsaoRepository.count())
                    .thenReturn(100L);

            when(previsaoRepository.calculoTaxaChurn())
                    .thenReturn(25.5);

            EstatisticasResponse result = previsaoService.estatisticas();

            assertNotNull(result);
            assertEquals(100L, result.total());
            assertEquals(25.5, result.taxaChurn());

            verify(previsaoRepository).count();
            verify(previsaoRepository).calculoTaxaChurn();
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaRetornarTaxaChurnZeroQuandoRepositorioRetornarNull() {
            when(previsaoRepository.count())
                    .thenReturn(50L);

            when(previsaoRepository.calculoTaxaChurn())
                    .thenReturn(null);

            EstatisticasResponse result = previsaoService.estatisticas();

            assertNotNull(result);
            assertEquals(50L, result.total());
            assertEquals(0.0, result.taxaChurn());

            verify(previsaoRepository).count();
            verify(previsaoRepository).calculoTaxaChurn();
        }
    }

}
