package equipe25.churninsight_backend.infra.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .addSecurityItem(new SecurityRequirement().addList("ApiKeyAuth"))
                                .components(new Components()
                                                .addSecuritySchemes("ApiKeyAuth",
                                                                new SecurityScheme()
                                                                                .type(SecurityScheme.Type.APIKEY)
                                                                                .in(SecurityScheme.In.HEADER)
                                                                                .name("X-API-KEY")))
                                .addServersItem(new Server()
                                                .url("http://localhost:8080")
                                                .description("Ambiente local"))
                                .addServersItem(new Server()
                                                .url("https://api.churninsight.renantech.com.br")
                                                .description("Ambiente de produção (OCI)"))
                                .info(new Info()
                                                .title("ChurnInsight API — Plataforma de Previsão de Churn")
                                                .version("1.0.0")
                                                .description("""
                                                                A ChurnInsight API é o backend da plataforma ChurnInsight, responsável por
                                                                orquestrar previsões de churn de clientes em serviços recorrentes, como
                                                                bancos digitais, plataformas de assinatura e soluções SaaS.

                                                                Esta API disponibiliza funcionalidades para:
                                                                - Previsão unitária de churn a partir de dados de clientes
                                                                - Processamento assíncrono de previsões em lote (batch)
                                                                - Consulta de status e download de resultados de batch
                                                                - Análises estatísticas agregadas por nível de risco
                                                                - Integração com um microserviço de Data Science em Python

                                                                O backend atua como camada de validação, padronização de respostas,
                                                                aplicação de regras de negócio e persistência histórica das previsões,
                                                                garantindo baixo acoplamento entre frontend, modelo preditivo e infraestrutura.

                                                                Projeto desenvolvido como MVP durante o Hackathon da Alura,
                                                                com arquitetura preparada para evolução e deploy em ambientes cloud.
                                                                """)
                                                .contact(new Contact()
                                                                .name("Equipe ChurnInsight")
                                                                .url("https://github.com/renancvitor/churninsight-backend-h12-25b"))
                                                .license(new License()
                                                                .name("MIT License")
                                                                .url("https://opensource.org/licenses/MIT")));
        }

}
