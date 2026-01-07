package equipe25.churninsight_backend.infra.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("churninsight")
                        .version("1.0.0")
                        .description("API RESTful para o ChurnInsight. " +
                                "Fornece endpoints para calcular a probabilidade de churn, " +
                                "consultar histórico de previsões e gerenciar regras de negócio.")
                );
    }
}
