# Read Me
O objetivo desse projeto é resolver o desafio proposto para criar uma API REST para as seguintes funcionalidades abaixo. Para fins de exercício, a segurança das interfaces é abstraída e qualquer chamada para as interfaces é considerada como autorizada.

● Cadastrar uma nova pauta

● Abrir uma sessão de votação em uma pauta.

● Receber votos dos associados em pautas.

● Contabilizar os votos e dar o resultado da votação na pauta.


# Solução

Criado uma API REST com java e spring boot chamada voto-us para gerenciamento dos votos. Existem endpoints para gerenciamento dos recursos de votos, sessões, rateios e pautas. A API utiliza o swagger para documentação dos recursos. Para cada sessão de votação é vinculada uma pauta onde é possivel pessoas fisicas votarem. O encerramento da sessão é realizado por um scheduler parametrizavel (dias, horas e minutos), após o encerramento da sessão é realizado o rateio dos votos, gravando o resultado em uma tabela. Para o versionamento de base é utilizado o flyway.


# Tecnologias

● Spring Boot.
● MySQL.
● Flyway.


# Serviços


### Recurso VOTOS

### POST v1/votos
Cadastrar votos em sessões de votação. O CPF precisa ser valido, é realizada uma consulta no serviço GET https://user-info.herokuapp.com/users/{cpf} que valida o CPF, se o mesmo foi válido, aleatóriamente retorna se o CPF pode ou não votar.


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


###Recurso RATEIOS

### GET v1/rateios
Buscar rateios de votos


### Testes automatizados
TODO


### Documentação adicional
Para consultar mais projetos do autor, consulte o seu repositório no github.


* [GITHUB - Autor](https://github.com/KeomaPereira)


### Configuração
TODO


