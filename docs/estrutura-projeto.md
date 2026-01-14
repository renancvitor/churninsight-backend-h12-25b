<h1 align="center"> Organização completa do Projeto</h1>

## 🎯 Objetivo desta estrutura

Esta organização foi pensada para:

- Facilitar manutenção e evolução
- Isolar regras de negócio de infraestrutura
- Permitir escalabilidade de novos domínios
- Tornar o projeto compreensível para novos desenvolvedores

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
 │    │    │    │    ├── BatchJobResponse.java
 │    │    │    │    ├── BatchStatusResponse.java
 │    │    │    │    ├── ClienteRequest.java
 │    │    │    │    ├── ClienteResponse.java
 │    │    │    │    └── PrevisaoBatchCsv.java
 │    │    │    └── service
 │    │    │         └── PrevisaoClienteService.java
 │    │    ├── nivelrisco
 │    │    │    └── NivelRiscoRepository.java
 │    │    ├── previsao
 │    │    │    ├── controller
 │    │    │    │    ├── DominioController.java
 │    │    │    │    ├── HealthController.java
 │    │    │    │    ├── PrevisaoBatchController.java
 │    │    │    │    ├── PrevisaoController.java
 │    │    │    │    └── RootController.java
 │    │    │    ├── dto
 │    │    │    │    ├── Dominio.java
 │    │    │    │    ├── DominioResponse.java
 │    │    │    │    ├── EstatisticasResponse.java
 │    │    │    │    ├── FatorCountAnalytics.java
 │    │    │    │    ├── FatorCountResponse.java
 │    │    │    │    ├── PrevisaoListagem.java
 │    │    │    │    └── PrevisaoPorNivelRisco.java
 │    │    │    ├── repository
 │    │    │    │    └── PrevisaoRepository.java
 │    │    │    └── service
 │    │    │         ├── DominioService.java
 │    │    │         ├── PrevisaoBatchAsyncService.java
 │    │    │         ├── PrevisaoBatchService.java
 │    │    │         ├── PrevisaoPersistenciaService.java
 │    │    │         └── PrevisaoService.java
 │    │    └── tipoprevisao
 │    │         └── TipoPrevisaoRepository.java
 │    ├── exception
 │    │    ├── domain
 │    │    │    ├── IntegracaoExternaException.java
 │    │    │    ├── RecursoNaoEncontradoException.java
 │    │    │    ├── RegraNegocioException.java
 │    │    │    └── ResultadoAindaNaoDisponivelException.java
 │    │    ├── dto
 │    │    │    ├── DadosErro.java
 │    │    │    └── DadosErroValidacao.java
 │    │    └── handler
 │    │         └── TratadorDeErros.java
 │    ├── infra
 │    │    ├── config
 │    │    │    ├── ApiKeyInterceptor.java
 │    │    │    ├── JpaWarmup.java
 │    │    │    └── RestTemplateConfig.java
 │    │    ├── documentation
 │    │    │    └── SpringDocConfigurations.java
 │    │    └── security
 │    │         ├── CorsConfig.java
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
 │    ├── controller/previsao
 │    │    ├── PrevisaoBatchControllerDownloadResultadoTestes.java
 │    │    ├── PrevisaoBatchControllerStatusBatchTestes.java
 │    │    ├── PrevisaoBatchControllerTestes.java
 │    │    ├── PrevisaoControllerEstatisticasTestes.java
 │    │    ├── PrevisaoControllerObterGraficoTestes.java
 │    │    ├── PrevisaoControllerPreverTestes.java
 │    │    └── PrevisaoControllerTop3FatoresTestes.java
 │    ├── service
 │    │    ├── api
 │    │    │    ├── PrevisaoClienteServiceBaixarResultadoTestes.java
 │    │    │    ├── PrevisaoClienteServiceConsultarStatusTestes.java
 │    │    │    ├── PrevisaoClienteServiceEnviarBatchTestes.java
 │    │    │    └── PrevisaoClienteServicePreverTestes.java
 │    │    └── previsao
 │    │         ├── PrevisaoBatchAsyncServiceTestes.java
 │    │         ├── PrevisaoBatchServiceBaixarResultadoFinalizadoTestes.java
 │    │         ├── PrevisaoBatchServiceProcessarBatchTestes.java
 │    │         ├── PrevisaoPersistenciaServicePersistirCsvTestes.java
 │    │         ├── PrevisaoServiceEstatisticasTestes.java
 │    │         ├── PrevisaoServiceObterGraficoTestes.java
 │    │         ├── PrevisaoServicePreverTestes.java
 │    │         ├── PrevisaoServiceTop3FatoresResponseTestes.java
 │    │         └── PrevisaoServiceTop3FatoresTestes.java
 │    ├── utils
 │    │    └── FabricaObjetosTeste.java
 │    └── ChurninsightBackendApplicationTests.java
 ├── resources
 │    ├── payload
 │    │    ├── previsao_batch_input_1000.csv
 │    │    └── request-unitario.json
 │    └── application-test.properties
 ├── Dockerfile
 ├── LICENSE
 └── README.md
```

> Estrutura atualizada em: Janeiro/2026

 <p align="right"><a href="../README.md">🔄 Voltar para a documentação completa</a></p>
