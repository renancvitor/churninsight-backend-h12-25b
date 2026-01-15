# **ChurnInsight — Plataforma de Previsão de Churn**

---

## 🔗 Repositórios Relacionados

O **ChurnInsight** é um projeto distribuído em múltiplos repositórios, cada um responsável por uma parte específica da solução.

Este repositório contém o **Backend da aplicação**, desenvolvido em **Java com Spring Boot**, responsável pela orquestração da solução, regras de negócio, integrações e consumo das previsões do modelo.

Além dele, o projeto conta com os seguintes repositórios complementares:

- O repositório de **Data Science**, responsável pela análise dos dados, treinamento do modelo preditivo e disponibilização das previsões por meio de uma **API em Python**.
- O repositório de **Frontend**, responsável pela interface visual da aplicação e pelo consumo das APIs expostas pelo backend.

Repositórios:

👉 [**ChurnInsight — Data Science**](https://github.com/LeticiaPaesano/Churn_Hackathon_ONE-Data_Science)  
👉 [**ChurnInsight — Backend**](https://github.com/renancvitor/churninsight-backend-h12-25b)  
👉 [**ChurnInsight — Frontend**](https://github.com/lucasns06/churninsight-frontend)

---

## Deploy e Acesso à Plataforma

A plataforma ChurnInsight possui deploy ativo para fins de demonstração e validação do MVP.

🔹 [**Data Science (API de Inferência)**](https://api-ds.duckdns.org/)  
👉 [Swagger](https://api-ds.duckdns.org/docs)

🔹 [**Backend (API REST)**](https://api.churninsight.renantech.com.br)  
👉 [Swagger](https://api.churninsight.renantech.com.br/swagger-ui/index.html#/)

🔹 [**Frontend (Interface Web)**](https://api.churninsight.renantech.com.br/)

⚠️ _Observação:_ Os ambientes estão configurados para fins de demonstração do MVP desenvolvido durante o Hackathon da Alura.

---

## Visão Geral do Projeto

O **ChurnInsight** é uma solução desenvolvida durante o **Hackathon da Alura** com o objetivo de prever o risco de **cancelamento de clientes (churn)** em serviços recorrentes, como bancos digitais, plataformas de assinatura e soluções SaaS.

A plataforma integra **Data Science** e **Backend** para transformar dados de clientes em **insights acionáveis**, permitindo que empresas antecipem riscos de evasão e tomem decisões baseadas em dados.

O projeto foi concebido como um **MVP funcional**, com arquitetura simples, clara e preparada para evolução.

---

## Problema de Negócio

A perda de clientes impacta diretamente a receita de negócios recorrentes.  
Identificar clientes com maior probabilidade de churn permite ações preventivas mais eficazes, reduzindo custos de aquisição e aumentando a retenção.

O ChurnInsight atua exatamente nesse ponto, oferecendo previsões claras e interpretáveis a partir de dados reais de clientes.

---

## Solução Desenvolvida

A solução é composta por dois componentes principais:

**Microserviço de Data Science (Python)**  
 Responsável pela análise dos dados, treinamento do modelo e geração das previsões.

**API Backend (Java / Spring Boot)**  
 Responsável por expor uma API REST, validar dados, consumir o modelo preditivo e padronizar as respostas ao cliente final.

Essa separação garante baixo acoplamento, clareza de responsabilidades e facilidade de manutenção.

---

## Arquitetura Geral

Fluxo de funcionamento da plataforma:

1. O cliente envia os dados do cliente em formato JSON.
2. O Backend valida o payload recebido.
3. O Backend chama a API Python do time de Data Science.
4. O modelo preditivo executa a inferência.
5. A previsão e a probabilidade são retornadas ao Backend.
6. O Backend responde ao cliente de forma padronizada.

Durante o hackathon, os serviços são executados localmente, mas a arquitetura é compatível com deploy em ambientes cloud.

---

## Abordagem de Data Science

A abordagem adotada pelo squad de Data Science para o MVP inclui:

**Pré-processamento**

- Remoção de colunas identificadoras
- One-Hot Encoding para variáveis categóricas

**Engenharia de Features**

- `Age_Tenure`
- `Balance_Salary_Ratio`
- `High_Value_Customer` (calculada a partir das medianas do conjunto de treino)

**Modelagem**

- Random Forest Classifier (`n_estimators=200`)
- Tratamento de desbalanceamento com `class_weight={0:1, 1:3}`
- Threshold ajustado para maximizar o Recall da classe churn

**Métricas finais (teste)**

- ROC-AUC: **0.7669**
- Acurácia: **79%**
- Recall churn: **47.91%**

O pipeline completo foi serializado com `joblib` e exposto via API FastAPI.

---

## Tecnologias Utilizadas

### Data Science

- 🐍 Python 3
- 📊 Pandas, NumPy, Matplotlib, Seaborn
- 🤖 Scikit-learn
- 🌐 FastAPI
- 🔧 Uvicorn
- 📓 Jupyter Notebook / Google Colab
- 💾 Joblib 1.5.3
- 📦 pyarrow 22.0.0
- 📌 pydantic >=2.0,<3.0
- 📌 python-multipart
- 📌 Requests 2.31.0
- 📌 HTTPX
- 📌 pytest

### Backend

- ☕ Java 17+
- 🌱 Spring Boot 3
- 🌐 Spring Web
- 📦 Bean Validation
- 🔄 Spring Boot DevTools
- 🔧 Lombok
- 🧪 JUnit 5 e 🔧 Mockito
- 📖 Swagger / OpenAPI
- 🛠️ Flyway
- 🐘 PostgreSQL
- 📦 Maven

### Frontend

- ⚛️ React - v19.2.0
- #️⃣ TypeScript
- 🎨 Tailwind CSS - v4.1.18
- 🎨 Headlessui - v2.2.9
- 🎨 Heroicons - v2.2.0
- 🎨 Tsparticles - v3.0.0
- 🔌 Axios v1.13.2
- ⚡ Vite - v7.2.4

---

## Contrato de Comunicação

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

---

## Documentação API - Swagger

A API do ChurnInsight é documentada utilizando o padrão Swagger / OpenAPI  
👉 [Data Science - Swagger UI](https://api-ds.duckdns.org/docs)  
👉 [Backend - Swagger UI](https://api.churninsight.renantech.com.br/swagger-ui/index.html#/)

### 📌 Visão Geral

A interface Swagger UI permite:

- Explorar todos os endpoints disponíveis
- Testar requisições diretamente pelo navegador
- Visualizar contratos de entrada e saída (JSON / CSV)
- Compreender fluxos síncronos e assíncronos da API

---

## Diagrama Entidade Relacionamento

Foi elaborado um Diagrama Entidade-Relacionamento (DER) para representar a modelagem dos dados da aplicação.  
A partir desse diagrama, a estrutura do banco de dados foi implementada em PostgreSQL, utilizando o Supabase como sistema gerenciador de banco de dados (SGDB) online.

---

## Estrutura do Projeto

### Data Science

```plaintext
app/
├── models/
│   ├── model.joblib        # Modelo serializado
│   └── __init__.py
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

### Frontend

```plaintext
churninsight-frontend/
 └── src/
      ├── @types/         # Interfaces
      ├── assets/         # Imagens e ícones
      ├── components/     # Componentes reutilizáveis (UI)
      |     ├── effects/  # Particulas
      |     ├──  layout/  # Navbar, Footer
      ├── constants/      # Dados estáticos
      ├── pages/          # Páginas
      ├── services/       # Comunicação com a API
      ├── App.tsx         # Página princípal
```

---

## Como Executar o Projeto Localmente

### Clone os projetos nos rescpectivos repositórios

### Data Science

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
POST http://localhost:8080/predict
```

⚠️ A API de Data Science deve estar em execução para previsões reais.

### Frontend

```bash
npm install
```

```bash
npx vite
```

⚠️ A API do Backend e do Data Science devem estar em execução.

---
