Transação API

Este projeto é uma API REST para gerenciar transações e calcular estatísticas das transações realizadas nos últimos 60 segundos. A API foi desenvolvida com Java e Spring Boot.

Variáveis de Ambiente

Para rodar esta aplicação, você precisa de:

Java: JDK 21 ou superior. Maven: Versão 3.8.1 ou superior. Git: Para clonar o repositório. Docker (opcional): Caso queira rodar a aplicação em um container.

Como Configurar o Projeto

Clone o Repositório

Compile o Projeto

mvn clean install
Execute o Projeto
mvn spring-boot:run
Como Rodar em um Container (Opcional)
4.1. Crie a Imagem Docker Certifique-se de que o Docker está instalado e execute:

docker build -t api-transacoes
4.2. Execute o Container

docker run -p 8080:8080 api-transacoes
Documentação da API

Receber Transações

POST /transacao
Parâmetro	Tipo	Descrição
valor	BigDecimal	Obrigatório. O valor da transação
dataHora	OffsetDateTime	Obrigatório. O horário que a transação ocorreu
Limpar Transações

DELETE /transacao
Calcular Estatísticas

GET /estatistica
Parâmetro	Tipo	Descrição
intervaloSegundos	integer	Não Obrigatório O padrão default é60s