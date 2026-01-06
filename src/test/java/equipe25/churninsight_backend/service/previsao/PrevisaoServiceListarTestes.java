package equipe25.churninsight_backend.service.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.context.ActiveProfiles;

import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.nivelrisco.NivelRiscoRepository;
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoListagem;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;
import equipe25.churninsight_backend.model.previsao.Previsao;
import equipe25.churninsight_backend.utils.FabricaObjetosTeste;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoServiceListarTestes {

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

    private Previsao previsao;

    @BeforeEach
    void setup() {
        previsao = FabricaObjetosTeste.criarPrevisao();
    }

    @Nested
    class PositiveCases {
        @Test
        void deveriaRetornarPaginaComConteudoMapeado() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<Previsao> page = new PageImpl<>(List.of(previsao), pageable, 1);

            when(previsaoRepository.findAll(pageable))
                    .thenReturn(page);

            Page<PrevisaoListagem> result = previsaoService.listar(pageable);

            assertNotNull(result);
            assertEquals(1, result.getTotalElements());
            assertEquals(1, result.getContent().size());
            assertInstanceOf(PrevisaoListagem.class, result.getContent().get(0));

            verify(previsaoRepository).findAll(pageable);
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveriaRetornarPaginaVaziaQuandoNaoExistirRegistro() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<Previsao> page = Page.empty(pageable);

            when(previsaoRepository.findAll(pageable))
                    .thenReturn(page);

            Page<PrevisaoListagem> result = previsaoService.listar(pageable);

            assertNotNull(result);
            assertTrue(result.isEmpty());
            assertEquals(0, result.getTotalElements());

            verify(previsaoRepository).findAll(pageable);
        }
    }

}
