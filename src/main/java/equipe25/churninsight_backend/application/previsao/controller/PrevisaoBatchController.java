package equipe25.churninsight_backend.application.previsao.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import equipe25.churninsight_backend.application.api.dto.BatchJobResponse;
import equipe25.churninsight_backend.application.api.dto.BatchStatusResponse;
import equipe25.churninsight_backend.application.api.service.PrevisaoClienteService;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoBatchService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/previsoes/batch")
@RequiredArgsConstructor
public class PrevisaoBatchController {

    private final PrevisaoBatchService previsaoBatchService;
    private final PrevisaoClienteService previsaoClienteService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BatchJobResponse> enviar(@RequestParam("file") MultipartFile file) {

        BatchJobResponse job = previsaoBatchService.processarBatch(file);

        return ResponseEntity.accepted().body(job);
    }

    @GetMapping("/{jobId}/download")
    public ResponseEntity<Resource> download(@PathVariable String jobId) {
        Resource csv = previsaoBatchService.baixarResultadoFinalizado(jobId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resultado.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }

    @GetMapping("/{jobId}/status")
    public ResponseEntity<BatchStatusResponse> status(@PathVariable String jobId) {
        BatchStatusResponse status = previsaoClienteService.consultarStatus(jobId);

        return ResponseEntity.ok(status);
    }

}
