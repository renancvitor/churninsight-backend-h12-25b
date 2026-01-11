package equipe25.churninsight_backend.application.previsao.service;

import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import equipe25.churninsight_backend.application.api.dto.BatchJobResponse;
import equipe25.churninsight_backend.application.api.dto.BatchStatusResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.exception.domain.RegraNegocioException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrevisaoBatchService {

    private final PrevisaoBatchService self;

    private final PrevisaoClienteService previsaoClienteService;
    private final PrevisaoPersistenciaService previsaoPersistenciaService;

    private static final Duration TIMEOUT_TOTAL = Duration.ofSeconds(30);
    private static final Duration POLLING_INTERVAL = Duration.ofSeconds(3);

    public void processarBatch(MultipartFile file) {
        validarArquivo(file);
        self.processarBatchAsync(file);
    }

    @Async
    void processarBatchAsync(MultipartFile file) {

        BatchJobResponse job = enviarCsvStreaming(file);

        aguardarConclusao(job.jobId());

        Resource csvResultado = previsaoClienteService.baixarResultado(job.jobId());

        previsaoPersistenciaService.persistirCsv(csvResultado);
    }

    private BatchJobResponse enviarCsvStreaming(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();

            Resource resource = new InputStreamResource(inputStream) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };

            return previsaoClienteService.enviarBatch(resource);

        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao enviar CSV para API Python", e);
        }
    }

    private void aguardarConclusao(String jobId) {

        Instant inicio = Instant.now();

        while (Duration.between(inicio, Instant.now()).compareTo(TIMEOUT_TOTAL) < 0) {

            BatchStatusResponse status = previsaoClienteService.consultarStatus(jobId);

            if ("FINALIZADO".equals(status.status())) {
                return;
            }

            if ("ERRO".equals(status.status())) {
                throw new RegraNegocioException("Erro no processamento batch");
            }

            dormir(POLLING_INTERVAL);
        }

        throw new RegraNegocioException("Timeout ao aguardar processamento batch");
    }

    private void dormir(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RegraNegocioException("Thread interrompida");
        }
    }

    private void validarArquivo(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RegraNegocioException("Arquivo CSV vazio");
        }

        if (file.getOriginalFilename() == null ||
                !file.getOriginalFilename().toLowerCase().endsWith(".csv")) {
            throw new RegraNegocioException("Arquivo deve ser CSV");
        }
    }

}
