# 🚀 Deploy & CI/CD

Este documento descreve o fluxo de deploy e integração contínua (CI/CD)
utilizado no backend do projeto **ChurnInsight**.

---

## 🧩 Visão Geral

O backend utiliza um pipeline automatizado de CI/CD com **GitHub Actions**.
Sempre que há um push na branch `main`, o workflow executa:

1. Preparação do ambiente
2. Execução das migrações do banco de dados (Flyway)
3. Build da aplicação
4. Deploy automático em produção (Render)

---

## 🔄 Gatilho do Pipeline

O pipeline é acionado automaticamente quando ocorre:

- `push` na branch `main`

```yaml
on:
  push:
    branches:
      - main
```

---

## 🛠️ Etapas do Workflow

### 1️⃣ Checkout do código

O código-fonte é obtido diretamente do repositório.

### 2️⃣ Configuração do ambiente Java

A aplicação utiliza Java 17, configurado via GitHub Actions.

### 3️⃣ Migrações de banco de dados (Flyway)

Antes do build, o Flyway executa automaticamente as migrações no banco de dados de produção.
- As credenciais são armazenadas como Secrets
- O perfil ativo é prod

Isso garante que o banco esteja sempre compatível com a versão da aplicação.

### 4️⃣ Build da aplicação

A aplicação é compilada com Maven, gerando o artefato final.
- Testes automatizados são executados durante o build
- Estratégia adotada visando maior confiabilidade, mesmo no contexto de Hackathon

### 5️⃣ Deploy em produção

O deploy é disparado automaticamente via Webhook do Render, integrando o pipeline de CI/CD à infraestrutura de produção.

---

## 🔐 Variáveis de Ambiente e Secrets

As seguintes variáveis são gerenciadas via GitHub Secrets:
- DB_URL
- DB_USER
- DB_PASSWORD
- RENDER_DEPLOY_HOOK

Isso garante segurança e evita exposição de dados sensíveis no repositório.

---

## 🏗️ Ambiente de Produção

- Plataforma de deploy: Render
- Banco de dados: (descrever, ex: PostgreSQL)
- Migrações: Flyway
- Perfil ativo: production

---

## 📌 Decisões Técnicas

- Uso do Flyway para versionamento do banco de dados
- Deploy automatizado para evitar inconsistências manuais
- Pipeline simples e direto, equilibrando rapidez e confiabilidade no contexto de Hackathon
- Integração contínua garantindo consistência entre código e banco

---

## 🔮 Próximos Passos

- Expansão da cobertura de testes automatizadoss
- Separação de ambientes (staging / production)
- Validações adicionais (lint, quality gates)

---

## 🏆 Por que isso é muito bom para Hackathon?

Porque você mostra que:
- Não faz deploy “na mão”
- Se preocupa com banco de dados
- Entende CI/CD de verdade
- Sabe justificar decisões técnicas

Mesmo que ninguém leia tudo, **o simples fato de existir** já pesa positivamente.

---

<p align="right"><a href="../README.md">🔄 Voltar para a documentação completa</a></p>
