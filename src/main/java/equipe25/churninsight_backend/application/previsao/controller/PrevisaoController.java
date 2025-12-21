package equipe25.churninsight_backend.application.previsao.controller;

import equipe25.churninsight_backend.application.previsao.dto.PrevisaoListagem;
import equipe25.churninsight_backend.application.previsao.dto.PrevisaoPorNivelRisco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import equipe25.churninsight_backend.application.api.dto.ClienteRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import equipe25.churninsight_backend.application.previsao.service.PrevisaoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/previsao")
public class PrevisaoController {

    private final PrevisaoService previsaoService;

    @PostMapping
    public ResponseEntity<ClienteResponse> prever(@RequestBody ClienteRequest request) {
        return ResponseEntity.ok(previsaoService.prever(request));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<PrevisaoListagem>> listar(Pageable paginacao) {

        var page = previsaoService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/obterGrafico")
    public ResponseEntity<List<PrevisaoPorNivelRisco>> obterGrafico() {

        var lista = previsaoService.obterGrafico();
        return ResponseEntity.ok(lista);
    }
}
