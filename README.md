<h1 id="inicio" align="center">
ChurnInsight — Plataforma de Previsão de Churn<br>
<img src="https://img.shields.io/badge/Status-Completed-brightgreen" width="180" height="30" />
</h1>

---

<h2 align="center">🔗 Repositórios Relacionados</h2>

O **ChurnInsight** é um projeto distribuído em múltiplos repositórios, cada um responsável por uma parte específica da solução.

Este repositório contém o **Backend da aplicação**, desenvolvido em **[Java](https://www.java.com/pt-BR/) com [Spring Boot](https://spring.io/projects/spring-boot)**, responsável pela orquestração da solução, regras de negócio, integrações e consumo das previsões do modelo.

Além dele, o projeto conta com os seguintes repositórios complementares:

- O repositório de **Data Science**, responsável pela análise dos dados, treinamento do modelo preditivo e disponibilização das previsões por meio de uma **API em [Python](https://www.python.org/)**.
- O repositório de **Frontend**, responsável pela interface visual da aplicação e pelo consumo das APIs expostas pelo backend.

Repositórios:

👉 [**ChurnInsight — Data Science**](https://github.com/LeticiaPaesano/Churn_Hackathon_ONE-Data_Science)  
👉 [**ChurnInsight — Frontend**](https://github.com/lucasns06/churninsight-frontend)

---

<h2 align="center">📑 Sumário</h2>

- [Visão Geral do Projeto](#visao-geral)
- [Problema de Negócio](#problema)
- [Solução Desenvolvida](#solucao)
- [Arquitetura Geral](#arquitetura)
- [Abordagem de Data Science](#data-science)
- [Tecnologias Utilizadas](#tecnologias)
- [Contrato de Comunicação](#contrato)
- [Documentação Visual](#documentação-visual)
  - [🌐 API - Swagger](#api-swagger)
  - [🗂️ Diagrama ER](#diagrama-er-banco-dados)
- [Estrutura do Projeto](#estrutura)
- [Como Executar o Projeto](#como-executar)
- [Deploy & CI/CD](#deploy)
- [Primeiros Entregáveis](#entregaveis)
- [Próximos Passos](#proximos-passos)
- [Equipe](#equipe)
- [Contribuições](#contribuicoes)
- [Licença](#licenca)

---

<h2 id="visao-geral" align="center">Visão Geral do Projeto</h2>

O **ChurnInsight** é uma solução desenvolvida durante o **Hackathon da [Alura](https://www.alura.com.br/)** com o objetivo de prever o risco de **cancelamento de clientes (churn)** em serviços recorrentes, como bancos digitais, plataformas de assinatura e soluções SaaS.

A plataforma integra **Data Science** e **Backend** para transformar dados de clientes em **insights acionáveis**, permitindo que empresas antecipem riscos de evasão e tomem decisões baseadas em dados.

O projeto foi concebido como um **MVP funcional**, com arquitetura simples, clara e preparada para evolução.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="problema" align="center">Problema de Negócio</h2>

A perda de clientes impacta diretamente a receita de negócios recorrentes.  
Identificar clientes com maior probabilidade de churn permite ações preventivas mais eficazes, reduzindo custos de aquisição e aumentando a retenção.

O ChurnInsight atua exatamente nesse ponto, oferecendo previsões claras e interpretáveis a partir de dados reais de clientes.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="solucao" align="center">Solução Desenvolvida</h2>

A solução é composta por dois componentes principais:

- **Microserviço de Data Science ([Python](https://www.python.org/))**  
  Responsável pela análise dos dados, treinamento do modelo e geração das previsões.

- **API Backend ([Java](https://www.java.com/pt-BR/) / [Spring Boot](https://spring.io/projects/spring-boot))**  
  Responsável por expor uma API REST, validar dados, consumir o modelo preditivo e padronizar as respostas ao cliente final.

Essa separação garante baixo acoplamento, clareza de responsabilidades e facilidade de manutenção.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="arquitetura" align="center">Arquitetura Geral</h2>

Fluxo de funcionamento da plataforma:

1. O cliente envia os dados via [JSON](https://www.json.org/json-en.html).
2. O Backend valida o payload recebido.
3. O Backend chama a API [Python](https://www.python.org/) do time de Data Science.
4. O modelo preditivo executa a inferência.
5. A previsão e a probabilidade são retornadas ao Backend.
6. O Backend responde ao cliente de forma padronizada.

Durante o hackathon, os serviços foram inicialmente executados localmente,
mas a arquitetura é compatível com deploy em ambientes cloud e atualmente suporta execução em VM via [Docker](https://www.docker.com/).

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="data-science" align="center">Abordagem de Data Science</h2>

A abordagem adotada pelo squad de Data Science para o MVP inclui:

- **Pré-processamento**

  - Remoção de colunas identificadoras
  - One-Hot Encoding para variáveis categóricas

- **Engenharia de Features**

  - `Age_Tenure`
  - `Balance_Salary_Ratio`
  - `High_Value_Customer` (calculada a partir das medianas do conjunto de treino)

- **Modelagem**

  - Random Forest Classifier (`n_estimators=200`)
  - Tratamento de desbalanceamento com `class_weight={0:1, 1:3}`
  - Threshold ajustado para maximizar o Recall da classe churn

- **Métricas finais (teste)**
  - ROC-AUC: **0.7669**
  - Acurácia: **79%**
  - Recall churn: **47.91%**

O pipeline completo foi serializado com `joblib` e exposto via API FastAPI.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="tecnologias" align="center">Tecnologias Utilizadas</h2>

### Backend

- ☕ [Java 17+](https://www.java.com/pt-BR/)
- 🌱 [Spring Boot 3](https://start.spring.io/)
- 🌐 [Spring Web](https://spring.io/projects/spring-web)
- 📦 [Bean Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)
- 🔄 [Spring Boot DevTools](https://docs.spring.io/spring-boot/reference/using/devtools.html)
- 🔧 Lombok
- 🧪 [JUnit 5](https://junit.org/) e 🔧 [Mockito](https://site.mockito.org/)
- 🛠️ [Flyway](https://flywaydb.org/)
- 🐘 [PostgreSQL](https://www.postgresql.org/)
- 📄 [Swagger (OpenAPI)](https://swagger.io/specification/)
- 📦 [Maven](https://maven.apache.org/)

### Data Science

- 🐍 [Python 3](https://www.python.org/)
- 📊 [Pandas](https://pandas.pydata.org/), [NumPy](https://numpy.org/), [Matplotlib](https://matplotlib.org/), [Seaborn](https://seaborn.pydata.org/)
- 🤖 [Scikit-learn](https://scikit-learn.org/stable/)
- 🌐 [FastAPI](https://fastapi.tiangolo.com/)
- 🔧 [Uvicorn](https://uvicorn.dev/)
- 📓 [Jupyter Notebook](https://jupyter.org/) / [Google Colab](https://colab.google/)
- 💾 [Joblib 1.5.3](https://joblib.readthedocs.io/en/stable/installing.html)
- 📦 [pyarrow 22.0.0](https://pypi.org/project/pyarrow/)
- 📌 [pydantic >=2.0,<3.0](https://pypi.org/project/pydantic/2.0.3/)
- 📌 [python-multipart](https://pypi.org/project/python-multipart/)
- 📌 [Requests 2.31.0](https://pypi.org/project/requests/)
- 📌 [HTTPX](https://www.python-httpx.org/)
- 📌 [pytest](https://docs.pytest.org/en/stable/)

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="contrato" align="center">Contrato de Comunicação</h2>

📥 **Entrada**

```json
{
  "CreditScore": 650,
  "Geography": "France",
  "Gender": "Male",
  "Age": 40,
  "Tenure": 5,
  "Balance": 60000,
  "EstimatedSalary": 80000
}
```

📤 **Saída**

```json
{
  "previsao": "Vai continuar",
  "probabilidade": 0.24,
  "nivel_risco": "BAIXO",
  "explicabilidade": ["Age", "Balance", "Germany"]
}
```

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="documentação-visual" align="center">Documentação Visual</h2>

<h3 id="api-swagger">🌐 <strong>API – Swagger</strong></h3>

A API do ChurnInsight é documentada utilizando o padrão  
[Swagger / OpenAPI](https://swagger.io/specification/).

Para visualizar a interface em funcionamento, acesse as  
[demonstrações visuais do Swagger](./docs/documentacao-swagger.md), com GIFs interativos mostrando os principais endpoints, fluxos de previsão e consultas disponíveis.

---

<h3 id="diagrama-er-banco-dados">🗂️ <strong>Diagrama ER do Banco de Dados (<a href="https://www.postgresql.org/" target="_blank">PostgreSQL</a>)</strong></h3>

A estrutura do banco de dados é representada por um diagrama entidade-relacionamento (ER), facilitando a compreensão das tabelas, relacionamentos e domínios utilizados pela aplicação.

👉 Acesse o diagrama completo aqui:  
[📊 Diagrama ER — Banco de Dados](./docs/er-diagrama.md)

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="estrutura" align="center">Estrutura do Projeto</h2>

### Backend

```plaintext
.github/workflows/                      # Pipelines de CI/CD: build, testes e validações automatizadas
docs/
 ├── diagrama-database/                 # Imagem do diagrama Entidade Relacionamento do banco de dados PostgreSQL
 ├── gifs/                              # Conjunto de gifs para gerar a documentação visual Swagger
 ├── boas-praticas-backend.md           # Guia completo de boas práticas em projetos Java/Spring Boot
 ├── DEPLOY_AND_CICD.md                 # Detalhes do Pipelina de CI/CD aplicado no projeto
 ├── documentacao-nocountry.md          # Documentação atualizada semanalmente na plataforma NoCountry
 ├── documentacao-swagger.md            # Documentação visual da API com GIFs demonstrativos
 ├── er-diagrama.md                     # Documentação sobre o diagrama ER do banco de dados PostgreSQL
 └── estrutura-projeto.md               # Estrutura detalhada do projeto e organização dos pacotes

src/main/java/
 └── equipe25/churninsight_backend/
      ├── application/                  # Camada de aplicação: orquestração dos casos de uso da API
      ├── config/                       # Configurações e integrações externas
      ├── exception/                    # Exceções globais e tratamento de erros da aplicação
      ├── model/                        # Entidades e enums específicas de cada agregado de domínio
      └── ChurnInsightBackendApplication.java

src/main/resources/
 ├── db/                                # Scripts Flyway (migrations e seeds)
 ├── application-*.properties           # Configurações específicas (prod, dev)
 └── application.properties             # Configuração padrão

src/test/java/
 └── equipe25/churninsight_backend/
      ├── service/                      # Testes unitários dos services, com alta cobertura por método
      ├── utils/                        # Fábrica de entidades e mocks reutilizáveis para testes
      └── ChurninsightBackendApplicationTests.java

 src/test/resources/
 ├── application-test.properties        # Configuração do ambiente de testes
 └── payload/                           # Dados auxiliares (JSON / JSONL) usados em testes e validações manuais
```

> 🔗 [Veja a estrutura completa do projeto aqui](./docs/estrutura-projeto.md)

### Data Science

```plaintext
app/
├── models/
│   ├── __init__.py
│   ├── model.joblib        # Modelo serializado
└── main.py                 # API FastAPI

data/
├── Churn.csv               # Dados brutos
└── dataset.parquet         # Dados tratados

docs/
└── Documentação Técnica de Visualizações.md  # Gráficos e análises

notebooks/
└── Churn_Hackathon.ipynb   # EDA e modelagem

tests/
├── integration/
│   ├── __init__.py
│   ├── test_integration_health.py
│   ├── test_integration_previsao.py
│   └── test_integration_root.py
└── unit/
    ├── __init__.py
    ├── test_unit_payload.py
    ├── test_unit_previsao_lote.py
    └── teste_unit_explicabilidade.py

__init__.py
.gitignore
Dockerfile
LICENSE
README.md
check_all.sh                # Script de validação total
conftest.py
docker-compose.yml
requirements.txt
stress_test.py
```

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="como-executar" align="center">Como Executar o Projeto Localmente</h2>

### API de Data Science

```bash
pip install -r requirements.txt
uvicorn app.main:app --reload
```

A documentação interativa estará disponível em:

```bash
http://localhost:8000/docs
```

### Backend

```bash
./mvnw spring-boot:run
```

Endpoint principal:

```bash
POST http://localhost:8080/previsao
```

⚠️ A API de Data Science deve estar em execução para previsões reais.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="deploy" align="center">Deploy & CI/CD</h2>

O backend utiliza deploy automatizado com [GitHub Actions](https://github.com/features/actions?locale=pt-BR),
incluindo migrações de banco via [Flyway](https://flywaydb.org/) e build automatizado da aplicação.

📄 [Detalhes do pipeline](docs/DEPLOY_AND_CICD.md)

A plataforma ChurnInsight possui deploy ativo para fins de demonstração e validação do MVP.

🔹 [**Data Science (API de Inferência)**](https://api-ds.duckdns.org/)  
👉 [Swagger](https://api-ds.duckdns.org/docs)

🔹 [**Backend (API REST)**](https://api.churninsight.renantech.com.br)  
👉 [Swagger](https://api.churninsight.renantech.com.br/swagger-ui/index.html#/)

🔹 [**Frontend (Interface Web)**](https://churninsight-frontend.vercel.app/)

⚠️ _Observação:_ Os ambientes estão configurados para fins de demonstração do MVP desenvolvido durante o Hackathon da Alura.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="equipe" align="center">Equipe</h2>

Projeto desenvolvido durante o Hackathon da [Alura](https://www.alura.com.br/), com dois squads integrados:

**Data Science**: análise de dados, modelagem e API [Python](https://www.python.org/)

**Backend**: API REST, integração e padronização de respostas

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="licenca" align="center">Licença</h2>

📌 Este projeto está licenciado sob a [Licença MIT](LICENSE), o que significa que você pode utilizá-lo, modificar, compartilhar e distribuir livremente, desde que mantenha o aviso de copyright e inclua uma cópia da licença original.  
Para mais detalhes, consulte o arquivo [LICENSE](LICENSE) ou a [licença MIT oficial](https://opensource.org/licenses/MIT).

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---
