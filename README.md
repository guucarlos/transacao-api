🚀 Transação API
    API REST desenvolvida com Java 21 e Spring Boot para gerenciamento de transações financeiras e cálculo de estatísticas em tempo real.
    O sistema registra transações e calcula métricas considerando apenas as transações realizadas nos últimos 60 segundos (ou intervalo customizado).
📌 Funcionalidades
    Registrar transações
    Remover todas as transações
    Calcular estatísticas (count, sum, avg, min, max)
    Validação de dados
    Tratamento de exceções
    Documentação Swagger
🧰 Tecnologias Utilizadas
    Java 21
    Spring Boot
    Spring Validation
    Gradle
    JUnit 5
    Mockito
    OpenAPI (Swagger)
    Docker (opcional)
🏗️ Arquitetura
    O projeto segue arquitetura em camadas:
    Controller → Service → DTO → Exception Handler
    Controller: Recebe requisições HTTP
    Service: Contém regras de negócio
    DTOs: Objetos de transferência de dados
    Exception Handler: Tratamento centralizado de erros
⚙️ Como Executar
    Pré-requisitos
    Java 21 ou superior
    Gradle 8 ou superior
    Git

Clone o repositório
    git clone https://github.com/seu-usuario/transacao-api.git
    cd transacao-api

Build do projeto
    ./gradlew clean build

Executar aplicação
    ./gradlew bootRun

🐳 Executando com Docker

    docker build -t transacao-api .

Executar container
    docker run -p 8080:8080 transacao-api

Endpoints
    ➕ Registrar Transação
    POST /transacao
    Body (JSON)
    {
        "valor": 100.50,
        "dataHora": "2025-02-18T14:30:00Z"
    }
Respostas
    Status	Descrição
    201	Criado com sucesso
    422	Transação inválida

🗑️ Limpar Transações
    DELETE /transacao
    Status	Descrição
    200	Transações removidas com sucesso
📊 Estatísticas
    GET /estatistica
    Query Param (opcional)
    intervaloSegundos=30
    Exemplo 
    GET /estatistica?intervaloSegundos=30
    Resposta
    {
        "count": 5,
        "sum": 350.00,
        "avg": 70.00,
        "min": 10.00,
        "max": 150.00
    }
📖 Documentação Swagger
    Após iniciar a aplicação:
    http://localhost:8080/swagger-ui/index.html
🧪 Executar Teste
    ./gradlew test
