package equipe25.churninsight_backend.application.previsao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import equipe25.churninsight_backend.application.previsao.dto.Dominio;
import equipe25.churninsight_backend.application.previsao.dto.DominioResponse;
import equipe25.churninsight_backend.application.previsao.service.DominioService;

@RestController
@RequestMapping("/dominios")
public class DominioController {

    private final DominioService dominioService;

    public DominioController(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    @GetMapping("/paises")
    public ResponseEntity<DominioResponse<Dominio>> listarPaises() {
        return ResponseEntity.ok(
                new DominioResponse<>(dominioService.listarPaises()));
    }

    @GetMapping("/generos")
    public ResponseEntity<DominioResponse<Dominio>> listarGeneros() {
        return ResponseEntity.ok(
                new DominioResponse<>(dominioService.listarGeneros()));
    }

}
