<h1 id="inicio" align="center">ChurnInsight — API Backend <br>
<img src="https://img.shields.io/badge/Status-Em%20desenvolvimento-yellow" width="180" height="30" />
</h1>

---

<h2 align="center">📑 Sumário</h2>

* [Visão Geral do Projeto](#visao-geral)
* [Propósito do Backend](#proposito)
* [Arquitetura e Abordagem Geral](#arquitetura)
* [Tecnologias Utilizadas](#tecnologias)
* [Integração com o Time de Data Science](#integracao)
* [Contrato Inicial de Comunicação](#contrato)
* [Primeiros Entregáveis do Squad](#entregaveis)
* [Estrutura do Projeto (proposta inicial)](#estrutura)
* [Como Executar o Projeto](#como-executar)
* [Contribuições](#contribuicoes)

---

<h2 id="visao-geral" align="center">Visão Geral do Projeto</h2>

O **ChurnInsight** é uma solução criada para prever o risco de **cancelamento (churn)** de clientes em serviços recorrentes.  
Este repositório contém o código do **Backend**, responsável por expor a previsão do modelo de Data Science através de uma API REST desenvolvida em **Spring Boot**.

O objetivo do backend é fornecer um endpoint capaz de receber informações de um cliente, acionar o modelo preditivo do time de Data Science e retornar a probabilidade de churn de forma clara e estruturada.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="proposito" align="center">Propósito do Backend</h2>

O backend tem como responsabilidades iniciais:

* Implementar uma **API REST** com rotas para previsão de churn.
* Validar os dados recebidos dos clientes.
* Enviar os dados ao microserviço Python do time de DS.
* Interpretar a resposta do modelo e retorná-la ao consumidor final.
* Garantir tratamento de erros, logs e respostas consistentes.

Como este é um projeto de hackathon, a prioridade é entregar **funcionalidade, clareza e integração**, evitando complexidade desnecessária.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="arquitetura" align="center">Arquitetura e Abordagem Geral</h2>

A arquitetura prevista para o backend é simples e direta:

* Camada de **Controller** para exposição de endpoints.
* Camada de **Service** para orquestrar validação, chamada ao serviço Data Science e montagem de respostas.
* Camada de **DTOs** para padronizar entrada e saída do endpoint.
* Cliente HTTP interno para comunicação com o microserviço Python.
* Modelagem orientada a **MVP**, com foco em previsões de churn.

Poderemos adicionar funcionalidades extras conforme avanço do projeto (ex.: logs detalhados, persistência, endpoint de estatísticas), mas inicialmente a prioridade é o endpoint `/predict`.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="tecnologias" align="center">Tecnologias Utilizadas</h2>

* ☕ **Java 17+**
* 🌱 **Spring Boot 3**
* 🌐 Spring Web
* 📦 Spring Validation (Bean Validation)
* 🔧 Lombok
* 📖 Documentação automatizada com Swagger (OpenAPI)
* 🧪 JUnit (para testes básicos)
* 🐘 PostgreSQL: Banco de dados

Ferramentas auxiliares:

* Visual Studio Code e IntelliJ IDEA  
* Insomnia / Postman para testes  
* GitHub para colaboração  

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="integracao" align="center">Integração com o Time de Data Science</h2>

O backend consumirá as previsões do modelo através de um **microserviço Python (FastAPI)** hospedado localmente durante o hackathon.

Fluxo geral:

1. O cliente envia um JSON ao backend Java.  
2. O backend valida o payload.  
3. O backend envia os dados ao serviço Data Science (`/predict-model`).  
4. O serviço Data Science retorna:
   - a previsão textual  
   - a probabilidade numérica  
5. O backend responde ao cliente no mesmo padrão.

Todo o contrato pode evoluir conforme o modelo do Data Science for sendo ajustado.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="contrato" align="center">Contrato Inicial de Comunicação</h2>

📥 **Entrada esperada pelo backend:**
```json
{
  "tempo_contrato_meses": 12,
  "atrasos_pagamento": 2,
  "uso_mensal": 14.5,
  "plano": "Premium"
}
```
📤 **Saída devolvida pelo backend ao cliente:**
```json
{
  "previsao": "Vai cancelar",
  "probabilidade": 0.81
}
```
O backend retornará exatamente o que o microserviço Data Science responder, mantendo padrão consistente.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="entregaveis" align="center">Primeiros Entregáveis do Squad</h2>

Rascunho dos entregáveis iniciais do backend:
* Projeto Spring Boot inicializado
* Endpoint /predict com DTO de entrada e saída
* Validações básicas das informações enviadas
* Comunicação HTTP com microserviço Data Science
* Retorno padronizado com previsão e probabilidade
* README com instruções de execução do projeto

Posteriormente poderão ser incluídos:
* Endpoint /stats
* Logs estruturados
* Documentação OpenAPI
* Persistência opcional para previsões

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="estrutura" align="center">Estrutura do Projeto (proposta inicial)</h2>

A estrutura abaixo representa uma sugestão de organização do código:
```plaintext
src/main/java/
 └── com.churninsight.backend/
      ├── controller/       # Endpoints REST
      ├── service/          # Regras de integração e orquestração
      ├── client/           # Comunicação com o microserviço DS
      ├── dto/              # Objetos de entrada e saída
      ├── config/           # Configurações gerais
      └── BackendApplication.java

src/main/resources/
 └── application.properties

src/test/java/
 └── ... (testes de unidade e integração)

README.md
```
A estrutura será expandida conforme o backend evoluir.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="como-executar" align="center">Como Executar o Projeto</h2>

Passos gerais (sujeitos a ajustes):

1. Clonar o repositório
```bash
git clone <url-do-repo>
```
2. Acessar o diretório
```bash
cd churn-backend
```
3. Executar o projeto
```bash
./mvnw spring-boot:run
```
4. Testar endpoint
```bash
POST http://localhost:8080/predict
```

⚠️ É necessário que a API Python do time Data Science esteja rodando para que o backend consiga retornar previsões reais.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---

<h2 id="contribuicoes" align="center">Contribuições</h2>

Contribuições do squad - Para colaborar:
1. Criar uma branch (git checkout -b feature/nova-funcionalidade)
2. Implementar a alteração
3. Abrir um Pull Request descrevendo o que foi desenvolvido

A comunicação entre os membros do squad será essencial para evitar retrabalho e garantir consistência no contrato com o time Data Science.

<p align="right"><a href="#inicio">⬆️ Voltar ao início</a></p>

---