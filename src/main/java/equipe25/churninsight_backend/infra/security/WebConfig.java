package equipe25.churninsight_backend.infra.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import equipe25.churninsight_backend.infra.config.ApiKeyInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@Profile("!test")
public class WebConfig implements WebMvcConfigurer {

        private final ApiKeyInterceptor apiKeyInterceptor;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                                .allowedOrigins(
                                                "http://localhost:5173",
                                                "http://localhost:8080")
                                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                                .allowedHeaders("*")
                                .allowCredentials(false);
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(apiKeyInterceptor)
                                .addPathPatterns("/**")
                                .excludePathPatterns(
                                                "/",
                                                "/error",
                                                "/health",
                                                "/actuator/**");
        }
}
