# 🚀 Boas Práticas Backend

Este documento consolida diretrizes essenciais para manter a qualidade, segurança e consistência do backend.

---

## 🔒 Segurança
- Nunca expor stacktrace em respostas.
- Usar validações do Bean Validation (`@NotNull`, `@Email`, etc.).
- CORS configurado apenas para domínios necessários.

---

## 📡 API
- Usar DTOs para entrada/saída.
- Utilizar ResponseEntity nos Controllers.
- Padronizar mensagens de erro.
- Utilizar paginação (`Pageable`) para endpoints com listas grandes.

---

## 🛠️ Código
- Evitar variáveis globais.
- Preferir injeção por construtor.
- Criar interfaces apenas se forem realmente necessárias.

---

## 📌 Regras Gerais
- Não adicionar lógica de negócio no *Controller*.
- *ServiceImpl* somente quando existir interface.
- DTOs nunca devem ser usados como entidades.
- Classes utilitárias devem ser final e ter construtor privado.

---

# 🧹 Clean Code — Guia Interno

Este documento reúne boas práticas aplicadas pelo time para manter o código limpo e sustentável.

---

## 🎯 Princípios Gerais
- Métodos pequenos (máx. 20–30 linhas).
- Uma responsabilidade por classe (SRP).
- Evitar comentários desnecessários — prefira código claro.
- Preferir nomes expressivos.
- Evitar duplicação de código (*DRY*).
- Evitar *magic numbers*.
- Evitar retornos `null` → use `Optional` quando apropriado.

---

## 🚫 Coisas que NÃO fazemos
- Colocar regra de negócio no Controller.
- Expor entidades diretamente no Controller.
- Criar endpoints sem DTOs.
- Colocar múltiplas responsabilidades na mesma classe.

---

# 🔤 Padrões de Nomenclatura

Padronizamos nomes para manter consistência no código, facilitar leitura e evitar ambiguidades.

---

## 📁 Pacotes (sempre minúsculos)
controller, service, repository, config, exception, dto, model

---

## 🧱 Classes (PascalCase)
UsuarioController
ProdutoService
AuthRequestDTO
EmailValidator

---

## 🔡 Variáveis e Métodos (camelCase)
numeroPedido
emailUsuario
calcularTotal()
buscarPorId()

---

## 🔠 Constantes (UPPER_SNAKE_CASE)
TOKEN_EXPIRATION_HOURS
MAX_LOGIN_ATTEMPTS

---

## 🌐 Padrões de Endpoints REST
- Utilizar plural
- Sem verbos
- Identificadores via path params

GET /usuarios
POST /usuarios
GET /usuarios/{id}
PUT /usuarios/{id}
DELETE /usuarios/{id}

---

# 📝 Convenção de Commits (PT-BR)

Adotamos uma convenção simples para padronizar commits e facilitar leitura, histórico e versionamento.

---

## 🧱 Estrutura do Commit
<tipo>: <descrição curta>

---

## 📌 Tipos Permitidos
- **feat** — Nova funcionalidade
- **fix** — Correção de bug
- **refactor** — Refatoração sem mudança funcional
- **docs** — Atualização de documentação
- **test** — Adição/alteração de testes
- **style** — Formatação, identação, sem alterar lógica
- **perf** — Melhorias de performance
- **chore** — Tarefas gerais (deps, configs, build)

---

## 📝 Exemplos:
feat: adicionar endpoint de criação de usuário
fix: corrigir validação de CPF
refactor: extrair serviço de autenticação para classe separada
docs: adicionar instruções do Swagger