package equipe25.churninsight_backend.infra.config;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    private final Set<String> validKeys;

    public ApiKeyInterceptor(
            @Value("${api.key.default}") String defaultKey,
            @Value("${api.key.frontend}") String frontendKey) {

        this.validKeys = Stream.of(defaultKey, frontendKey)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (this.validKeys.isEmpty()) {
            throw new IllegalStateException("Nenhuma API Key configurada");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String requestKey = request.getHeader("X-API-KEY");

        if (requestKey == null || !validKeys.contains(requestKey)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        return true;
    }
}
