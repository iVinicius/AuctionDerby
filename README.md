# AuctionDerby

## SETUP
	- Rodar o programa 1x para criar o banco de dados
	- Fechar o programa
	- Conectar no banco com alguma software auxiliar (ex: RazorSQL)
	- Rodar os scripts contidos no source do programa, na ordem:
		1: sql_scripts
		2: rodar todos os sql_populate_* em qualquer ordem
	- Commitar e fechar a conex�o com o banco
	
## OBSERVA��ES
	- Rodar o programa sem as tabelas criadas ir� buga-lo
	- Rodar o programa enquanto conectado no banco por outra fonte ir� buga-lo
	- As primeiras querys podem demorar um pouco
	- Ap�s se registrar e fechar o programa, basta lembrar do ID gerado e fazer login
	- Os DaoTests est�o mais para testes de integra��o com o banco que unit tests .-.

## Coment�rios
	- A solu��o n�o foi completamente implementada, por�m todo o "core" da solu��o permite adicionar/alterar funcionalidades de forma f�cil
	- O que n�o � f�cil, nem r�pido � fazer o Swing/AWT (al�m de chato)
	- Os scripts permitem visualizar um conjunto de leil�es e seus respectivos lances