# Api listar Titulos (17/04/2020)

Api de listagem de titulos

# Instruções

Utilizar o WildFly para fazer deploy do arquivo .war

Endpoints:

GET_LISTAR_TODAS_REGIÕES: http://127.0.0.1:8080/CSIG-GlobalAPI-web/rest/v1/premiosPagosPorRegiao/json/porAno?ano=2021
GET_LISTAR_TODAS_REGIÃO_ANO: http://127.0.0.1:8080/CSIG-GlobalAPI-web/rest/v1/premiosPagosPorRegiao/json/porAno?ano=2019&regiao=sul&regiao=Sudeste


### CT01 - Listar por ano

Modalidade do titulo: TRADICIONAL

### CT02 - Listar acima de 2 anos

Mensagem de erro: Intervalo de Anos não pode ser maior que 2 anos!

### CT03 - URL Invalida

Mensagem de erro: URL Inválida

### CT04 - Listar por Ano e região 

Total: [SUL] R$ [2.8077702E7]

### CT05 - Região Invalida

Mensagem de erro: Região inválida!

### CT06 - Ano futuro

Mensagem de erro: Ano inválido!