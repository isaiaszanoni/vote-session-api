## Link do projeto na web
O projeto está disponível na internet utilizando os serviços
da Railway:
- URL do projeto: https://vote-session-api-production.up.railway.app
- URL do Swagger do projeto: https://vote-session-api-production.up.railway.app/swagger-ui.html

## Como rodar este projeto localmente
Depois de clonar o projeto para a sua máquina, você pode executá-lo facilmente
utilizando o Docker Compose.

Você vai precisar do docker e do docker-compose instalado em sua máquina

Altere o arquivo /vote-session-api/src/main/resources/application.yml. Basta copiar o yml abaixo e colar no lugar:

```
spring:
application:
name: vote-session-api

datasource:
url: jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_SCHEMA:vote_session_api}?allowPublicKeyRetrieval=true&useSSL=false&zeroDateTimeBehavior=convertToNull
username: ${DB_USER:root}
password: ${DB_PASS:root}
driver-class-name: com.mysql.cj.jdbc.Driver
hikari:
connection-timeout: 24000

jpa:
hibernate:
ddl-auto: update
show-sql: false
properties:
hibernate:
jdbc:
time_zone: UTC
dialect: org.hibernate.dialect.MySQL8Dialect

server:
port: ${PORT:8081}
```
Na raiz do projeto, dê o comando `docker-compose up -d` para rodar o projeto em modo deamon
Caso queira ver os logs da aplicação, utilize o comando `docker-compose logs -f`. O comando
`docker-compose ps` vai te mostrar os serviços em execução.

Uma vez que a sua API está rodando, basta bater no localhost:8080 para acessar a aplicação.
O Swagger UI estará no endereço http://localhost:8080/swagger-ui.html.
Já o banco de dados, caso você queira se conectar a ele com alguma ferramenta como DBeaver, por exemplo,
estará na porta localhost:3307.

Dentro da pasta vote-session-api/assets você encontrará uma collection do Postman com os endpoints da aplicação.
Basta importar para o seu Postman ou Insomnia e utilizar!

