<h1 align="center"> Organização completa do Projeto</h1>

```plaintext
.github/workflows
 └── deploy.yml

 docs
 ├── diagrama-database/
 │    └── churnInsight-der.png
 ├── gifs/
 ├── boas-praticas-backend.md
 ├── DEPLOY_AND_CICD.md
 ├── documentacao-nocountry.md
 ├── documentacao-swagger.md
 ├── er-diagrama.md
 └── estrutura-projeto.md

src/main
 ├── java/equipe25/churninsight_backend
 │    ├── application
 │    │    ├── api
 │    │    │    ├── dto
 │    │    │    │    ├── ClienteRequest.java
 │    │    │    │    └── ClienteResponse.java
 │    │    │    └── service
 │    │    │         └── PrevisaoClienteService.java
 │    │    ├── nivelrisco
 │    │    │    └── NivelRiscoRepository.java
 │    │    ├── previsao
 │    │    │    ├── controller
 │    │    │    │    ├── HealthController.java
 │    │    │    │    └── PrevisaoController.java
 │    │    │    ├── dto
 │    │    │    │    ├── FatorCountAnalytics.java
 │    │    │    │    ├── FatorCountResponse.java
 │    │    │    │    ├── PrevisaoListagem.java
 │    │    │    │    └── PrevisaoPorNivelRisco.java
 │    │    │    ├── repository
 │    │    │    │    └── PrevisaoRepository.java
 │    │    │    └── service
 │    │    │         └── PrevisaoService.java
 │    │    └── tipoprevisao
 │    │         └── TipoPrevisaoRepository.java
 │    ├── exception
 │    │    ├── dto
 │    │    │    ├── DadosErro.java
 │    │    │    └── DadosErroValidacao.java
 │    │    └── TratadorDeErros.java
 │    ├── infra
 │    │    ├── config
 │    │    │    ├── ApiKeyInterceptor.java
 │    │    │    ├── JpaWarmup.java
 │    │    │    └── RestTemplateConfig.java
 │    │    ├── documentation
 │    │    │    └── SpringDocConfiguracoes.java
 │    │    └── security
 │    │    │    ├── CorsConfig.java
 │    │         └── WebConfig.java
 │    ├── model
 │    │    ├── explicabilidade
 │    │    │    └── ExplicabilidadeEnum.java
 │    │    ├── genero
 │    │    │    ├── enums
 │    │    |    │    └── GeneroEnum.java
 │    │    │    └── GeneroEntidade.java
 │    │    ├── nivelrisco
 │    │    │    ├── enums
 │    │    |    │    └── NivelRiscoEnum.java
 │    │    │    └── NivelRiscoEntidade.java
 │    │    ├── pais
 │    │    │    ├── enums
 │    │    |    │    └── PaisEnum.java
 │    │    │    └── PaisEntidade.java
 │    │    ├── previsao
 │    │    │    └── Previsao.java
 │    │    └── tipoprevisao
 │    │         ├── enums
 │    │         │    └── TipoPrevisaoEnum.java
 │    │         └── TipoPrevisaoEntidade.java
 │    └── ChurninsightBackendApplication.java
 ├── resources
 │    ├── db
 │    │    ├── V1__create_tables.sql
 │    │    ├── V2__insert_data.sql
 │    │    └── V3__create_table_explicabilidade_previsao.sql
 │    ├── application-dev.properties
 │    ├── application-prod.properties
 │    └── application.properties
 ├── test/java/equipe25/churninsight_backend
 │    ├── service
 │    │    └── PrevisaoServicePreverTestes.java
 │    ├── utils
 │    │    └── FabricaObjetosTeste.java
 │    └── ChurninsightBackendApplicationTests.java
 ├── resources
 │    ├── payload
 │    └── application-test.properties
 └── README.md
```

> Estrutura atualizada em: Dezembro/2025

 <p align="right"><a href="../README.md">🔄 Voltar para a documentação completa</a></p>
