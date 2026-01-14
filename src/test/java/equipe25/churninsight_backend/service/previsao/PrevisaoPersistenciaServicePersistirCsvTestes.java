package equipe25.churninsight_backend.service.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import equipe25.churninsight_backend.application.nivelrisco.NivelRiscoRepository;
import equipe25.churninsight_backend.application.previsao.repository.PrevisaoRepository;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoPersistenciaService;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;
import equipe25.churninsight_backend.exception.domain.RegraNegocioException;
import equipe25.churninsight_backend.model.nivelrisco.NivelRiscoEntidade;
import equipe25.churninsight_backend.model.previsao.Previsao;
import equipe25.churninsight_backend.model.tipoprevisao.TipoPrevisaoEntidade;
import equipe25.churninsight_backend.utils.FabricaObjetosTeste;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoPersistenciaServicePersistirCsvTestes {

        @Mock
        private PrevisaoRepository previsaoRepository;

        @Mock
        private NivelRiscoRepository nivelRiscoRepository;

        @Mock
        private TipoPrevisaoRepository tipoPrevisaoRepository;

        @InjectMocks
        private PrevisaoPersistenciaService previsaoPersistenciaService;

        private NivelRiscoEntidade nivelBaixo;
        private TipoPrevisaoEntidade vaiContinuar;

        @BeforeEach
        void setup() {
                nivelBaixo = FabricaObjetosTeste.criarNivelBaixoString();
                vaiContinuar = FabricaObjetosTeste.criarPrevisaoVaiContinuarString();
        }

        @Nested
        class PositiveCases {
                @SuppressWarnings("unchecked")
                @Test
                void deveriaPersistirPrevisoesQuandoCsvValido() {
                        when(nivelRiscoRepository.findAll())
                                        .thenReturn(List.of(nivelBaixo));

                        when(tipoPrevisaoRepository.findAll())
                                        .thenReturn(List.of(vaiContinuar));

                        Resource csv = csvResource(
                                        "probabilidade,nivel_risco,previsao,explicabilidade\n" +
                                                        "0.75,BAIXO,VAI_CONTINUAR,\"Age,Balance\"");

                        previsaoPersistenciaService.persistirCsv(csv);

                        ArgumentCaptor<List<Previsao>> captor = ArgumentCaptor.forClass(List.class);

                        verify(previsaoRepository).saveAll(captor.capture());

                        List<Previsao> previsoes = captor.getValue();
                        assertEquals(1, previsoes.size());

                        Previsao previsao = previsoes.get(0);
                        assertEquals(0.75, previsao.getProbabilidade());
                        assertEquals(nivelBaixo, previsao.getNivelRisco());
                        assertEquals(vaiContinuar, previsao.getPrevisao());
                        assertEquals(List.of("Age", "Balance"),
                                        previsao.getExplicabilidade());
                }
        }

        @Nested
        class NegativeCases {
                @Test
                void deveFalharQuandoNivelRiscoNaoExistir() {
                        when(nivelRiscoRepository.findAll())
                                        .thenReturn(List.of());

                        when(tipoPrevisaoRepository.findAll())
                                        .thenReturn(List.of(vaiContinuar));

                        Resource csv = csvResource(
                                        "probabilidade,nivel_risco,previsao,explicabilidade\n" +
                                                        "0.5,ALTO,VAI_CONTINUAR,\"Age,Balance\"");

                        RegraNegocioException exception = assertThrows(
                                        RegraNegocioException.class,
                                        () -> previsaoPersistenciaService.persistirCsv(csv));

                        assertTrue(exception.getCause().getMessage()
                                        .contains("Nível de risco não encontrado"));

                        verify(previsaoRepository, never()).saveAll(any());
                }

                @Test
                void deveFalharQuandoTipoPrevisaoNaoExistir() {
                        when(nivelRiscoRepository.findAll())
                                        .thenReturn(List.of(nivelBaixo));

                        when(tipoPrevisaoRepository.findAll())
                                        .thenReturn(List.of());

                        Resource csv = csvResource(
                                        "probabilidade,nivel_risco,previsao,explicabilidade\n" +
                                                        "0.5,BAIXO,VAI_CANCELAR,Age");

                        RegraNegocioException exception = assertThrows(
                                        RegraNegocioException.class,
                                        () -> previsaoPersistenciaService.persistirCsv(csv));

                        assertTrue(exception.getCause().getMessage()
                                        .contains("Tipo de previsão não encontrado"));

                        verify(previsaoRepository, never()).saveAll(any());
                }

                @Test
                void deveFalharQuandoCsvForInvalido() {
                        when(nivelRiscoRepository.findAll())
                                        .thenReturn(List.of(nivelBaixo));

                        when(tipoPrevisaoRepository.findAll())
                                        .thenReturn(List.of(vaiContinuar));

                        Resource csv = csvResource(
                                        "probabilidade,nivel_risco\n" +
                                                        "0.5,BAIXO,EXTRA");

                        RegraNegocioException exception = assertThrows(
                                        RegraNegocioException.class,
                                        () -> previsaoPersistenciaService.persistirCsv(csv));

                        assertEquals("Erro ao processar CSV", exception.getMessage());
                        assertNotNull(exception.getCause());

                        verify(previsaoRepository, never()).saveAll(any());
                }
        }

        private Resource csvResource(String conteudo) {
                return new ByteArrayResource(conteudo.getBytes(StandardCharsets.UTF_8)) {
                        @Override
                        public String getFilename() {
                                return "teste.csv";
                        }
                };
        }

}
