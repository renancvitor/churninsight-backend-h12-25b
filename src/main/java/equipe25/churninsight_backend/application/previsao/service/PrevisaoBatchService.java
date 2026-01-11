package equipe25.churninsight_backend.application.previsao.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import equipe25.churninsight_backend.exception.domain.RegraNegocioException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrevisaoBatchService {

    private final PrevisaoBatchAsyncService asyncService;

    public void processarBatch(MultipartFile file) {
        validarArquivo(file);
        asyncService.processarBatchAsync(file);
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