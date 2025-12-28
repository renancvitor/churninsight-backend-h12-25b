package equipe25.churninsight_backend.infra.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyInterceptior implements HandlerInterceptor {

    private final Set<String> validKeys;

    public ApiKeyInterceptior(
            @Value("${api.key.default}") String defaultKey,
            @Value("${api.key.frontend}") String frontendKey) {
        this.validKeys = Set.of(defaultKey, frontendKey);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        String requestKey = request.getHeader("X-API-KEY");

        if (requestKey == null || !validKeys.contains(requestKey)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        return true;
    }

}
