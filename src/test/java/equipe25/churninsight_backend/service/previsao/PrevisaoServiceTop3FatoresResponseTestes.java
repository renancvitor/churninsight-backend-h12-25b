package equipe25.churninsight_backend.service.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.nivelrisco.NivelRiscoRepository;
import equipe25.churninsight_backend.application.previsao.dto.FatorCountAnalytics;
import equipe25.churninsight_backend.application.previsao.dto.FatorCountResponse;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;
import equipe25.churninsight_backend.model.explicabilidade.ExplicabilidadeEnum;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoServiceTop3FatoresResponseTestes {

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
        void deveriaMapearERetornarFatorResponseCorretamente() {
            FatorCountAnalytics analytics = new FatorCountAnalytics("AGE", 5L);

            PrevisaoService spyService = Mockito.spy(previsaoService);

            doReturn(List.of(analytics))
                    .when(spyService).top3Fatores();

            List<FatorCountResponse> result = spyService.top3FatoresResponse();

            assertNotNull(result);
            assertEquals(1, result.size());

            FatorCountResponse response = result.get(0);
            assertEquals(
                    ExplicabilidadeEnum.traduzir("AGE"),
                    response.fator());
            assertEquals(5L, response.total());
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaRetornarListaVaziaQuandoNaoExistiremFatores() {
            PrevisaoService spyService = Mockito.spy(previsaoService);

            doReturn(List.of())
                    .when(spyService).top3Fatores();

            List<FatorCountResponse> result = spyService.top3FatoresResponse();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

}
