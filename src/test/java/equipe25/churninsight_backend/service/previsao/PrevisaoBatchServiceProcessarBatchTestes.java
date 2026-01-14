package equipe25.churninsight_backend.service.previsao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;
import equipe25.churninsight_backend.application.api.dto.BatchJobResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoBatchAsyncService;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoBatchService;
import equipe25.churninsight_backend.exception.domain.RegraNegocioException;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PrevisaoBatchServiceProcessarBatchTestes {

    @Mock
    private PrevisaoClienteService previsaoClienteService;

    @Mock
    private PrevisaoBatchAsyncService previsaoBatchAsyncService;

    @InjectMocks
    private PrevisaoBatchService previsaoBatchService;

    @Mock
    private MultipartFile file;

    private BatchJobResponse jobResponse;

    @BeforeEach
    void setup() {
        jobResponse = new BatchJobResponse("job-123", "PROCESSANDO");
    }

    @Nested
    class PositiveCases {
        @Test
        void deveriaEnviarArquivoEIniciarProcessamentoAsync() throws Exception {
            byte[] conteudo = "col1,col2\n1,2".getBytes();

            when(file.isEmpty()).thenReturn(false);
            when(file.getOriginalFilename()).thenReturn("arquivo.csv");
            when(file.getBytes()).thenReturn(conteudo);

            when(previsaoClienteService.enviarBatch(any(Resource.class)))
                    .thenReturn(jobResponse);

            BatchJobResponse result = previsaoBatchService.processarBatch(file);

            assertNotNull(result);
            assertEquals(jobResponse, result);

            verify(previsaoClienteService)
                    .enviarBatch(any(Resource.class));

            verify(previsaoBatchAsyncService)
                    .processarBatchAsync("job-123");
        }
    }

    @Nested
    class NegativeCases {
        @Test
        void deveFalharQuandoArquivoForNuloOuVazio() {
            when(file.isEmpty()).thenReturn(true);

            RegraNegocioException exception = assertThrows(
                    RegraNegocioException.class,
                    () -> previsaoBatchService.processarBatch(file));

            assertEquals("Arquivo CSV vazio", exception.getMessage());

            verify(previsaoClienteService, never()).enviarBatch(any());
            verify(previsaoBatchAsyncService, never()).processarBatchAsync(any());
        }

        @Test
        void deveFalharQuandoArquivoNaoForCsv() {
            when(file.isEmpty()).thenReturn(false);
            when(file.getOriginalFilename()).thenReturn("arquivo.txt");

            RegraNegocioException exception = assertThrows(
                    RegraNegocioException.class,
                    () -> previsaoBatchService.processarBatch(file));

            assertEquals("Arquivo deve ser CSV", exception.getMessage());

            verify(previsaoClienteService, never()).enviarBatch(any());
            verify(previsaoBatchAsyncService, never()).processarBatchAsync(any());
        }

        @Test
        void deveFalharQuandoErroAoLerArquivo() throws Exception {
            when(file.isEmpty()).thenReturn(false);
            when(file.getOriginalFilename()).thenReturn("arquivo.csv");
            when(file.getBytes()).thenThrow(new RuntimeException("IO error"));

            RegraNegocioException exception = assertThrows(
                    RegraNegocioException.class,
                    () -> previsaoBatchService.processarBatch(file));

            assertEquals("Erro ao ler arquivo CSV", exception.getMessage());

            verify(previsaoClienteService, never()).enviarBatch(any());
            verify(previsaoBatchAsyncService, never()).processarBatchAsync(any());
        }
    }

}
