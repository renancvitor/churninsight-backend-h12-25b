package equipe25.churninsight_backend.service.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import equipe25.churninsight_backend.application.api.dto.BatchStatusResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoBatchAsyncService;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoBatchService;
import equipe25.churninsight_backend.exception.domain.ResultadoAindaNaoDisponivelException;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoBatchServiceBaixarResultadoFinalizadoTestes {

        @Mock
        private PrevisaoClienteService previsaoClienteService;

        @Mock
        private PrevisaoBatchAsyncService previsaoBatchAsyncService;

        @InjectMocks
        private PrevisaoBatchService previsaoBatchService;

        @Mock
        private MultipartFile file;

        @Nested
        class PositiveCases {
                @Test
                void deveriaBaixarResultadoQuandoStatusForFinalizado() {
                        BatchStatusResponse status = new BatchStatusResponse("FINALIZADO");

                        Resource resource = mock(Resource.class);

                        when(previsaoClienteService.consultarStatus("job-123"))
                                        .thenReturn(status);

                        when(previsaoClienteService.baixarResultado("job-123"))
                                        .thenReturn(resource);

                        Resource result = previsaoBatchService.baixarResultadoFinalizado("job-123");

                        assertNotNull(result);
                        assertEquals(resource, result);

                        verify(previsaoClienteService)
                                        .consultarStatus("job-123");

                        verify(previsaoClienteService)
                                        .baixarResultado("job-123");
                }
        }

        @Nested
        class NegativeCases {
                @Test
                void deveFalharQuandoStatusNaoForFinalizado() {
                        BatchStatusResponse status = new BatchStatusResponse("PROCESSANDO");

                        when(previsaoClienteService.consultarStatus("job-123"))
                                        .thenReturn(status);

                        ResultadoAindaNaoDisponivelException exception = assertThrows(
                                        ResultadoAindaNaoDisponivelException.class,
                                        () -> previsaoBatchService.baixarResultadoFinalizado("job-123"));

                        assertTrue(exception.getMessage()
                                        .contains("Resultado ainda não disponível"));

                        verify(previsaoClienteService)
                                        .consultarStatus("job-123");

                        verify(previsaoClienteService, never())
                                        .baixarResultado(any());
                }
        }

}
