package equipe25.churninsight_backend.application.previsao.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, Object> root() {
        return Map.of(
                "status", "online",
                "service", "ChurnInsight API",
                "version", "1.0.0",
                "docs", "/swagger-ui.html",
                "health", "/health");
    }

}
