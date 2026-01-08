package equipe25.churninsight_backend.application.previsao.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import equipe25.churninsight_backend.application.previsao.service.PrevisaoBatchService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/previsoes/batch")
@RequiredArgsConstructor
public class PrevisaoBatchController {

    private final PrevisaoBatchService previsaoBatchService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> enviar(@RequestParam("File") MultipartFile file) {
        previsaoBatchService.processarBatch(file);

        return ResponseEntity.accepted().build();
    }
}
