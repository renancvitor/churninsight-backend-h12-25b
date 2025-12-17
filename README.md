<h1 id="inicio" align="center">
ChurnInsight — Plataforma de Previsão de Churn<br>
<img src="https://img.shields.io/badge/Status-Em%20desenvolvimento-yellow" width="180" height="30" />
</h1>

---

<h2 align="center">🔗 Repositório do Time de Data Science</h2>

O **ChurnInsight** utiliza um modelo preditivo desenvolvido pelo squad de **Data Science**, responsável pela análise dos dados, treinamento do modelo e disponibilização das previsões via API Python.

O código do modelo, experimentos, notebooks e a API de inferência estão disponíveis no repositório abaixo:

👉 [**ChurnInsight — Data Science**](https://github.com/LeticiaPaesano/Churn_Hackathon_ONE-Data_Science)

---

<h2 align="center">📑 Sumário</h2>

- [Visão Geral do Projeto](#visao-geral)
- [Problema de Negócio](#problema)
- [Solução Desenvolvida](#solucao)
- [Arquitetura Geral](#arquitetura)
- [Abordagem de Data Science](#data-science)
- [Tecnologias Utilizadas](#tecnologias)
- [Contrato de Comunicação](#contrato)
- [Estrutura do Projeto](#estrutura)
- [Como Executar o Projeto](#como-executar)
- [Primeiros Entregáveis](#entregaveis)
- [Próximos Passos](#proximos-passos)
- [Equipe](#equipe)
- [Contribuições](#contribuicoes)

---

<h2 id="visao-geral" align="center">Visão Geral do Projeto</h2>

O **ChurnInsight** é uma solução desenvolvida durante o **Hackathon da Alura** com o objetivo de prever o risco de **cancelamento de clientes (churn)** em serviços recorrentes, como bancos digitais, plataformas de assinatura e soluções SaaS.

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

- **Microserviço de Data Science (Python)**  
  Responsável pela análise dos dados, treinamento do modelo e geração das previsões.

- **API Backend (Java / Spring Boot)**  
  Responsável por expor uma API REST, validar dados, consumir o modelo preditivo e padronizar as respostas ao cliente final.

Essa separação garante baixo acoplamento, clareza de responsabilidades e facilidade de manutenção.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="arquitetura" align="center">Arquitetura Geral</h2>

Fluxo de funcionamento da plataforma:

1. O cliente envia os dados do cliente via JSON.
2. O Backend valida o payload recebido.
3. O Backend chama a API Python do time de Data Science.
4. O modelo preditivo executa a inferência.
5. A previsão e a probabilidade são retornadas ao Backend.
6. O Backend responde ao cliente de forma padronizada.

Durante o hackathon, os serviços são executados localmente, mas a arquitetura é compatível com deploy em ambientes cloud.

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
- ☕ Java 17+
- 🌱 Spring Boot 3
- 🌐 Spring Web
- 📦 Bean Validation
- 🔧 Lombok
- 📖 Swagger / OpenAPI
- 🧪 JUnit
- 🐘 PostgreSQL

### Data Science
- 🐍 Python 3
- 📊 Pandas, NumPy, Matplotlib, Seaborn
- 🤖 Scikit-learn
- 🌐 FastAPI
- 🔧 Uvicorn
- 📓 Jupyter Notebook / Google Colab

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
  "recomendacao": "Cliente estável - manutenção padrão"
}
```
O contrato pode evoluir conforme ajustes no modelo e nas regras de negócio.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="estrutura" align="center">Estrutura do Projeto</h2>

### Backend

```plaintext
src/main/java/
 └── com.churninsight.backend/
      ├── controller/
      ├── service/
      ├── client/
      ├── dto/
      ├── config/
      └── ChurnInsightBackendApplication.java
```
### Data Science

```plaintext
api/
 ├── model/
 │ └── model.joblib
 ├── main.py
 ├── requirements.txt
```

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="como-executar" align="center">Como Executar o Projeto</h2>

### API de Data Science

```bash
pip install -r api/requirements.txt
uvicorn api.main:app --reload
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

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---


<h2 id="entregaveis" align="center">Primeiros Entregáveis</h2>

- Modelo preditivo treinado e validado
- Pipeline serializado
- API FastAPI funcional
- API Backend integrada
- Contrato JSON definido
- Documentação unificada do projeto

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="proximos-passos" align="center">Próximos Passos</h2>

Como evolução natural da plataforma, são considerados os seguintes aprimoramentos:

- Interface frontend para visualização das previsões
- Persistência do histórico de previsões
- Monitoramento de métricas do modelo
- Deploy em ambiente cloud
- Evolução das regras de recomendação de retenção

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="equipe" align="center">Equipe</h2>

Projeto desenvolvido durante o Hackathon da Alura, com dois squads integrados:

**Data Science**: análise de dados, modelagem e API Python

**Backend**: API REST, integração e padronização de respostas

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---
