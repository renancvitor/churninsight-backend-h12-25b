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
4. Build da imagem Docker
5. Push da imagem para o Docker Hub
6. Deploy automático em produção (Oracle Cloud Infrastructure – VM via Docker)

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

A aplicação é compilada com Maven, gerando o JAR final.

- O build é executado com `-DskipTests`
- Decisão tomada visando velocidade e estabilidade no contexto de Hackathon
- Os testes automatizados existem no projeto, mas não bloqueiam o deploy neste pipeline

### 5️⃣ Build e publicação da imagem Docker

Após o build da aplicação:

- A imagem Docker é criada utilizando o Dockerfile do projeto
- A imagem recebe duas tags:
  - SHA do commit (imutável)
  - `latest`
- As imagens são publicadas no Docker Hub

Essa estratégia permite:

- Rastreabilidade por commit
- Rollback simples em caso de falha

### 6️⃣ Deploy em produção (OCI)

O deploy é realizado automaticamente em uma VM na Oracle Cloud Infrastructure (OCI) via SSH.

O pipeline executa:

- Pull da imagem Docker mais recente
- Parada do container anterior (se existir)
- Remoção do container antigo
- Inicialização de um novo container com:
  - Porta 8080 exposta apenas localmente na VM
  - Variáveis de ambiente via arquivo `.env`
  - Política de restart automático

---

## 🔐 Variáveis de Ambiente e Secrets

As seguintes variáveis são gerenciadas via GitHub Secrets:

- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`
- `DOCKER_USER`
- `DOCKER_PASSWORD`
- `OCI_VM_HOST`
- `OCI_VM_USER`
- `OCI_VM_SSH_KEY`

Além disso, a VM utiliza um arquivo `.env` local para configuração da aplicação em runtime.

---

## 🏗️ Ambiente de Produção

- Plataforma de deploy: Oracle Cloud Infrastructure (VM)
- Orquestração: Docker (container único)
- Registro de imagens: Docker Hub
- Banco de dados: PostgreSQL
- Migrações: Flyway
- Perfil ativo: prod

---

## 📌 Decisões Técnicas

- Uso do Flyway para versionamento do banco de dados
- Deploy automatizado para evitar inconsistências manuais
- Pipeline simples e direto, equilibrando rapidez e confiabilidade no contexto de Hackathon
- Integração contínua garantindo consistência entre código e banco
- Uso de Docker para padronizar o ambiente de execução
- Deploy via SSH em VM para maior controle da infraestrutura

---

## 🔮 Próximos Passos

- Expansão da cobertura de testes automatizados
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

> Documento atualizado em: Janeiro/2026

<p align="right"><a href="../README.md">🔄 Voltar para a documentação completa</a></p>
