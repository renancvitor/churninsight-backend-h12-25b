package equipe25.churninsight_backend.service.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
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
import equipe25.churninsight_backend.application.api.dto.BatchStatusResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoBatchAsyncService;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoPersistenciaService;
import equipe25.churninsight_backend.exception.domain.RegraNegocioException;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoBatchAsyncServiceTestes {

    @Mock
    private PrevisaoClienteService previsaoClienteService;

    @Mock
    private PrevisaoPersistenciaService previsaoPersistenciaService;

    @InjectMocks
    private PrevisaoBatchAsyncService previsaoBatchAsyncService;

    @Nested
    class PositiveCases {
        @Test
        void deveriaPersistirCsvQuandoStatusFinalizar() {
            BatchStatusResponse processando = new BatchStatusResponse("PROCESSANDO");

            BatchStatusResponse finalizado = new BatchStatusResponse("FINALIZADO");

            Resource csv = mock(Resource.class);

            when(previsaoClienteService.consultarStatus("job-123"))
                    .thenReturn(processando)
                    .thenReturn(finalizado);

            when(previsaoClienteService.baixarResultado("job-123"))
                    .thenReturn(csv);

            previsaoBatchAsyncService.processarBatchAsync("job-123");

            verify(previsaoClienteService, atLeast(2))
                    .consultarStatus("job-123");

            verify(previsaoClienteService)
                    .baixarResultado("job-123");

            verify(previsaoPersistenciaService)
                    .persistirCsv(csv);
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveFalharQuandoStatusForErro() {
            BatchStatusResponse erro = new BatchStatusResponse("ERRO");

            when(previsaoClienteService.consultarStatus("job-123"))
                    .thenReturn(erro);

            RegraNegocioException exception = assertThrows(
                    RegraNegocioException.class,
                    () -> previsaoBatchAsyncService.processarBatchAsync("job-123"));

            assertEquals("Erro no processamento batch", exception.getMessage());

            verify(previsaoClienteService)
                    .consultarStatus("job-123");

            verify(previsaoPersistenciaService, never())
                    .persistirCsv(any());
        }

        @Test
        void deveFalharQuandoTimeoutAtingido() {
            BatchStatusResponse processando = new BatchStatusResponse("PROCESSANDO");

            when(previsaoClienteService.consultarStatus("job-123"))
                    .thenReturn(processando);

            RegraNegocioException exception = assertThrows(
                    RegraNegocioException.class,
                    () -> previsaoBatchAsyncService.processarBatchAsync("job-123"));

            assertEquals("Timeout no processamento batch", exception.getMessage());

            verify(previsaoClienteService, atLeast(30))
                    .consultarStatus("job-123");

            verify(previsaoPersistenciaService, never())
                    .persistirCsv(any());
        }

        @Test
        void deveFalharQuandoThreadForInterrompida() {
            BatchStatusResponse processando = new BatchStatusResponse("PROCESSANDO");

            when(previsaoClienteService.consultarStatus("job-123"))
                    .thenReturn(processando);

            Thread.currentThread().interrupt();

            RegraNegocioException exception = assertThrows(
                    RegraNegocioException.class,
                    () -> previsaoBatchAsyncService.processarBatchAsync("job-123"));

            assertEquals("Thread interrompida", exception.getMessage());

            Thread.interrupted();
        }
    }

}
