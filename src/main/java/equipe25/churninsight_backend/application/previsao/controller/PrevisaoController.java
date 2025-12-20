package equipe25.churninsight_backend.application.previsao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import equipe25.churninsight_backend.application.api.dto.ClientRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/previsao")
public class PrevisaoController {

    private final PrevisaoService previsaoService;

    @PostMapping
    public ResponseEntity<ClienteResponse> prever(@RequestBody ClientRequest request) {
        return ResponseEntity.ok(previsaoService.prever(request));
    }

}
