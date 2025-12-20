package equipe25.churninsight_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import equipe25.churninsight_backend.dto.DataPredictResposta;
import equipe25.churninsight_backend.dto.EntradaCliente;
import equipe25.churninsight_backend.service.PredictionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/predictions")
public class PredictController {

    private final PredictionService predictionService;

    @PostMapping
    public ResponseEntity<DataPredictResposta> prever(@RequestBody EntradaCliente request) {
        return ResponseEntity.ok(predictionService.prever(request));
    }

}
