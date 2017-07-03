# AuctionDerby

## SETUP
	- Rodar o programa 1x para criar o banco de dados
	- Fechar o programa
	- Conectar no banco com alguma software auxiliar (ex: RazorSQL)
	- Rodar os scripts contidos no source do programa, na ordem:
		1: sql_scripts
		2: rodar todos os sql_populate_* em qualquer ordem
	- Commitar e fechar a conexão com o banco
	
## OBSERVAÇÕES
	- Rodar o programa sem as tabelas criadas irá buga-lo
	- Rodar o programa enquanto conectado no banco por outra fonte irá buga-lo
	- As primeiras querys podem demorar um pouco
	- Após se registrar e fechar o programa, basta lembrar do ID gerado e fazer login
	- Os DaoTests estão mais para testes de integração com o banco que unit tests .-.

## Comentários
	- A solução não foi completamente implementada, porém todo o "core" da solução permite adicionar/alterar funcionalidades de forma fácil
	- O que não é fácil, nem rápido é fazer o Swing/AWT (além de chato)
	- Os scripts permitem visualizar um conjunto de leilões e seus respectivos lances