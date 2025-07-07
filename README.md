#  Jogo de Tabuleiro em Java

##  Visão Geral
Este projeto apresenta um jogo de tabuleiro desenvolvido em Java, onde os jogadores competem para acumular o maior patrimônio comprando e gerenciando imóveis, cobrando aluguéis, pagando impostos e negociando entre si. A proposta combina diversão com estratégia, utilizando conceitos sólidos de programação orientada a objetos e estruturas de dados.

##  Estruturas de Dados Utilizadas

###  Lista Ligada Circular
- **Finalidade**: Simula o movimento contínuo dos jogadores pelo tabuleiro.
- **Implementação**: A classe `Tabuleiro` utiliza nós (`Node`) para formar uma estrutura circular.

###  ArrayList
- **Finalidade**: Gerenciamento dinâmico de jogadores e imóveis.
- **Implementação**: Classes como `Player` e `Main` utilizam `ArrayList` para armazenar informações de forma eficiente.

###  Outros Recursos
- **Scanner**: Captura as entradas dos jogadores durante a partida.
- **Collections**: Ordena o ranking final com base no patrimônio de cada jogador.

##  Como Executar

###  Requisitos
- **Java JDK** (versão 11 ou superior)
- **IDE recomendada**: IntelliJ IDEA ou Eclipse (opcional, pois o jogo também pode ser executado via terminal)

### ▶ Passo a Passo
1. **Clone o repositório**
   ```bash
   git clone https://github.com/Igorwr/AS_ES_Igor.git
   cd AS_ES_Igor
