package equipe25.churninsight_backend.application.previsao.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import equipe25.churninsight_backend.application.api.dto.ClienteRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import equipe25.churninsight_backend.application.previsao.dto.FatorCount;
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoListagem;
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoPorNivelRisco;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/previsao")
public class PrevisaoController {

    private final PrevisaoService previsaoService;

    @PostMapping
    public ResponseEntity<ClienteResponse> prever(@Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(previsaoService.prever(request));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<PrevisaoListagem>> listar(Pageable paginacao) {
        return ResponseEntity.ok(previsaoService.listar(paginacao));
    }

    @GetMapping("/total")
    public ResponseEntity<Long> total() {
        return ResponseEntity.ok(previsaoService.total());
    }

    @GetMapping("/obterGrafico")
    public ResponseEntity<List<PrevisaoPorNivelRisco>> obterGrafico() {
        return ResponseEntity.ok(previsaoService.obterGrafico());
    }

    @GetMapping("/top3Fatores")
    public ResponseEntity<List<FatorCount>> top3Fatores() {
        return ResponseEntity.ok(previsaoService.top3Fatores());
    }

}
