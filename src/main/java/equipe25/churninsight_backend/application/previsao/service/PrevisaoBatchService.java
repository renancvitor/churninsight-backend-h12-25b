package equipe25.churninsight_backend.application.previsao.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import equipe25.churninsight_backend.application.api.dto.BatchJobResponse;
import equipe25.churninsight_backend.application.api.dto.BatchStatusResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.exception.domain.RegraNegocioException;
import equipe25.churninsight_backend.exception.domain.ResultadoAindaNaoDisponivelException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrevisaoBatchService {

    private final PrevisaoClienteService previsaoClienteService;
    private final PrevisaoBatchAsyncService previsaoBatchAsyncService;

    public BatchJobResponse processarBatch(MultipartFile file) {
        validarArquivo(file);

        byte[] conteudo;
        String nomeArquivo;

        try {
            conteudo = file.getBytes();
            nomeArquivo = file.getOriginalFilename();
        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao ler arquivo CSV", e);
        }

        Resource resource = new ByteArrayResource(conteudo) {
            @Override
            public String getFilename() {
                return nomeArquivo;
            }
        };

        BatchJobResponse job = previsaoClienteService.enviarBatch(resource);

        previsaoBatchAsyncService.processarBatchAsync(job.jobId());

        return job;
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

    public Resource baixarResultadoFinalizado(String jobId) {
        BatchStatusResponse status = previsaoClienteService.consultarStatus(jobId);

        if (!"FINALIZADO".equals(status.status())) {
            throw new ResultadoAindaNaoDisponivelException(
                    "Resultado ainda não disponível. Status atual: " + status.status());
        }

        return previsaoClienteService.baixarResultado(jobId);
    }

}
