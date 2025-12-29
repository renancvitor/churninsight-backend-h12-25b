package equipe25.churninsight_backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import equipe25.churninsight_backend.application.api.dto.ClienteRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.nivelrisco.NivelRiscoRepository;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;
import equipe25.churninsight_backend.model.nivelrisco.NivelRiscoEntidade;
import equipe25.churninsight_backend.model.previsao.Previsao;
import equipe25.churninsight_backend.model.tipoprevisao.TipoPrevisaoEntidade;
import equipe25.churninsight_backend.utils.FabricaObjetosTeste;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoServicePreverTestes {

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

    private TipoPrevisaoEntidade tipoPrevisaoEntidade;
    private NivelRiscoEntidade nivelRiscoEntidade;
    private ClienteRequest request;
    private ClienteResponse response;

    @BeforeEach
    void setup() {
        tipoPrevisaoEntidade = FabricaObjetosTeste.criarPrevisaoVaiContinuar();
        nivelRiscoEntidade = FabricaObjetosTeste.criarNivelBaixo();

        request = FabricaObjetosTeste.criarRequest();
        response = FabricaObjetosTeste.criarResponse();
    }

    @Nested
    class PositiveCases {
        @Test
        void deveriaRetornarCorretamente() {
            when(previsaoClienteService.prever(any()))
                    .thenReturn(response);

            when(tipoPrevisaoRepository.findById(tipoPrevisaoEntidade.getId()))
                    .thenReturn(Optional.of(tipoPrevisaoEntidade));

            when(nivelRiscoRepository.findById(nivelRiscoEntidade.getId()))
                    .thenReturn(Optional.of(nivelRiscoEntidade));

            ArgumentCaptor<Previsao> captor = ArgumentCaptor.forClass(Previsao.class);

            ClienteResponse result = previsaoService.prever(request);

            assertNotNull(result);
            assertEquals(response, result);

            verify(previsaoRepository).save(captor.capture());

            Previsao previsaoSalva = captor.getValue();
            assertEquals(tipoPrevisaoEntidade, previsaoSalva.getPrevisao());
            assertEquals(nivelRiscoEntidade, previsaoSalva.getNivelRisco());
            assertEquals(response.probabilidade(), previsaoSalva.getProbabilidade());
            assertEquals(response.recomendacao(), previsaoSalva.getRecomendacao());
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveFalharQuandoTipoPrevisaoNaoEncontrado() {
            when(previsaoClienteService.prever(any()))
                    .thenReturn(response);

            when(tipoPrevisaoRepository.findById(tipoPrevisaoEntidade.getId()))
                    .thenReturn(Optional.empty());

            IllegalStateException exception = assertThrows(
                    IllegalStateException.class,
                    () -> previsaoService.prever(request));

            assertTrue(exception.getMessage().contains("Tipo Previsão não encontrado"));

            verify(previsaoRepository, never()).save(any(Previsao.class));
        }

        @Test
        void deveFalharQuandoNivelRiscoNaoEncontrado() {
            when(previsaoClienteService.prever(any()))
                    .thenReturn(response);

            when(tipoPrevisaoRepository.findById(tipoPrevisaoEntidade.getId()))
                    .thenReturn(Optional.of(tipoPrevisaoEntidade));

            when(nivelRiscoRepository.findById(nivelRiscoEntidade.getId()))
                    .thenReturn(Optional.empty());

            IllegalStateException exception = assertThrows(
                    IllegalStateException.class,
                    () -> previsaoService.prever(request));

            assertTrue(exception.getMessage().contains("Nível Risco não encontrado"));

            verify(previsaoRepository, never()).save(any(Previsao.class));
        }
    }

}
