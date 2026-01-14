package equipe25.churninsight_backend.application.previsao.service;

import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import equipe25.churninsight_backend.application.api.dto.BatchStatusResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.exception.domain.RegraNegocioException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrevisaoBatchAsyncService {

    private final PrevisaoClienteService previsaoClienteService;
    private final PrevisaoPersistenciaService previsaoPersistenciaService;

    private static final int MAX_TENTATIVAS = 30;
    private static final int INTERVALO_MS = 2000;

    @Async
    public void processarBatchAsync(String jobId) {

        int tentativas = 0;

        while (tentativas < MAX_TENTATIVAS) {
            BatchStatusResponse status = previsaoClienteService.consultarStatus(jobId);

            if ("FINALIZADO".equals(status.status())) {
                Resource csv = previsaoClienteService.baixarResultado(jobId);
                previsaoPersistenciaService.persistirCsv(csv);
                return;
            }

            if ("ERRO".equals(status.status())) {
                throw new RegraNegocioException("Erro no processamento batch");
            }

            try {
                Thread.sleep(INTERVALO_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RegraNegocioException("Thread interrompida");
            }

            tentativas++;
        }

        throw new RegraNegocioException("Timeout no processamento batch");
    }
}
