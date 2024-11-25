
# Projeto de Xadrez de Terminal em Java

Este projeto é uma implementação de um jogo de xadrez em terminal, desenvolvido em Java com suporte ao Maven.

## Pré-requisitos

- **Java:** Certifique-se de ter o Java JDK versão **21.0.3** instalado.
- **Maven:** Certifique-se de ter o Maven versão **3.9.9** configurado em seu ambiente.

## Como executar o projeto

1. **Clone o repositório do projeto:**

   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_DIRETORIO>
   ```

2. **Compile o projeto:**

   Use o comando a seguir para compilar o projeto com Maven:

   ```bash
   mvn compile
   ```

3. **Execute o projeto:**

   Após a compilação, execute o projeto com o seguinte comando:

   ```bash
   mvn exec:java
   ```

## Funcionalidades

- Jogo de xadrez jogável diretamente pelo terminal.
- Todas as jogadas são formadas de "posição da peça" + "posição de movimento
  - Exemplo: e2e4 (Peça em e2 se move para e4)
- Verificação de movimentos válidos e regras do jogo.
- Exibição do tabuleiro no terminal.