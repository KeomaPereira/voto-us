# Read Me
O objetivo desse projeto é resolver o desafio proposto para criar uma API REST para as seguintes funcionalidades abaixo. Para fins de exercício, a segurança das interfaces é abstraída e qualquer chamada para as interfaces é considerada como autorizada.

● Cadastrar uma nova pauta

● Abrir uma sessão de votação em uma pauta.

● Receber votos dos associados em pautas.

● Contabilizar os votos e dar o resultado da votação na pauta.


# Solução

Criado uma API REST com java e spring boot chamada voto-us para gerenciamento dos votos. Existem endpoints para gerenciamento dos recursos de votos, sessões, rateios e pautas. A API utiliza o swagger para documentação dos recursos. 
Para cada sessão de votação é vinculada uma pauta onde é possivel pessoas fisicas votarem. Para o versionamento de base é utilizado o flyway.
O encerramento da sessão é realizado por um scheduler parametrizavel (dias, horas e minutos), após o encerramento da sessão é realizado o rateio dos votos, gravando o resultado em uma tabela e após enviado para um tópico do kafka.


# Tecnologias

● Spring Boot.
● MySQL.
● Flyway.
● Java.
● Kafka.


# Serviços


### Recurso VOTOS

### POST v1/votos
Cadastrar votos em sessões de votação. O CPF precisa ser valido, é realizada uma consulta no serviço GET https://user-info.herokuapp.com/users/{cpf} que valida o CPF, se o mesmo foi válido, aleatóriamente retorna se o CPF pode ou não votar, é possível somente votar em uma sessão por CPF.


### Recurso PAUTAS

### POST v1/pautas
Cadastrar pautas para votação.

### GET v1/pautas
Buscar pautas.

### GET v1/pautas
Buscar uma pauta especifica.


### Recurso SESSÕES

### POST v1/sessoes
Cadastrar sessões de votação.

### GET v1/sessoes
Buscar pautas.

### GET v1/sessoes
Buscar uma pauta especifica.


### Recurso RATEIOS

### GET v1/rateios
Buscar rateios de votos


### Documentação adicional
Para consultar mais projetos do autor, consulte o seu repositório no github.


* [GITHUB - Autor](https://github.com/KeomaPereira)


### Configuração
Para rodar o projeto é necessário ter um servidor de bando de dados MySql com uma base chamada "voto" e também o kafka rodando para poder enviar para a fila. 
Para realizar o rateio, é necessário configurar o tempo do scheduler para execução no arquivo YML.  


