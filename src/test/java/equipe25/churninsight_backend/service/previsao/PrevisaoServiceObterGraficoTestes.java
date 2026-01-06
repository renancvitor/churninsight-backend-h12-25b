package equipe25.churninsight_backend.service.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.nivelrisco.NivelRiscoRepository;
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoPorNivelRisco;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;
import equipe25.churninsight_backend.utils.FabricaObjetosTeste;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoServiceObterGraficoTestes {

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
        void deveriaRetornarListaComDados() {
            PrevisaoPorNivelRisco grafico = new PrevisaoPorNivelRisco(
                    FabricaObjetosTeste.nivelRiscoEnumBaixo().getDisplayName(),
                    5L);

            when(previsaoRepository.previsaoPorNivelRiscos())
                    .thenReturn(List.of(grafico));

            List<PrevisaoPorNivelRisco> result = previsaoService.obterGrafico();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(grafico, result.get(0));

            verify(previsaoRepository).previsaoPorNivelRiscos();
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaRetornarListaVaziaQuandoNaoExistirDados() {
            when(previsaoRepository.previsaoPorNivelRiscos())
                    .thenReturn(List.of());

            List<PrevisaoPorNivelRisco> result = previsaoService.obterGrafico();

            assertNotNull(result);
            assertTrue(result.isEmpty());

            verify(previsaoRepository).previsaoPorNivelRiscos();
        }
    }

}
