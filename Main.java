import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
        private static final int MAXIMO_JOGADORES = 6;
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<Imovel> imoveis = new ArrayList<>();
    private static double dinheiroInicial = 50000.0;
    private static double ganhoPorVolta = 5000.0;
    private static int maximoRodadas = 15;
    private static int rodadaAtual = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("=== Painel de Controle ===");
            System.out.println("1. Controle de Jogadores");
            System.out.println("2. Gestão de Imóveis");
            System.out.println("3. Preferências do Sistema");
            System.out.println("4. Iniciar Sessão de Jogo");
            System.out.println("0. Sair do Sistema");
            System.out.print("Selecione uma opção: ");


            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> controleJogadores(scanner);
                case 2 -> gestaoImoveis(scanner);
                case 3 -> prefSistemas(scanner);
                case 4 -> {
                    if (inicioJogo()) {
                        iniciarJogo(scanner);
                    }
                }
                case 0 -> {
                    System.out.println("Programa finalizado com sucesso. Obrigado por utilizar o sistema.");
                    sair = true;
                }
                default -> System.out.println("Entrada inválida. Por favor, selecione uma opção válida.");

            }
        }
    }


    private static void controleJogadores(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("--- Gerenciamento de Jogadores ---");
            System.out.println("(Jogadores cadastrados: " + players.size() + "/6)");
            System.out.println("1. Adicionar Jogador");
            System.out.println("2. Ver Lista de Jogadores");
            System.out.println("3. Editar Informações de um Jogador");
            System.out.println("4. Excluir Jogador");
            System.out.println("5. Retornar ao Menu Principal");
            System.out.print("Selecione uma opção: ");


            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    if (players.size() < 6) {
                        System.out.print("Informe o nome do novo jogador: ");
                        String nome = scanner.nextLine();
                        players.add(new Player(nome, dinheiroInicial));
                        System.out.println("Jogador adicionado com sucesso!");
                    } else {
                        System.out.println("⚠Limite de 6 jogadores atingido. Não é possível adicionar mais.");
                    }
                }
                case 2 -> {
                    System.out.println("=== Lista de Jogadores ===");
                    if (players.isEmpty()) {
                        System.out.println("Nenhum jogador cadastrado até o momento.");
                    } else {
                        for (int i = 0; i < players.size(); i++) {
                            Player player = players.get(i);
                            System.out.printf("%d. %s - Saldo: R$ %.2f%n", (i + 1), player.getNome(), player.getSaldo());
                        }
                    }
                }
                case 3 -> {
                    System.out.println("=== Atualização de Jogador ===");
                    for (int i = 0; i < players.size(); i++) {
                        System.out.printf("%d. %s%n", (i + 1), players.get(i).getNome());
                    }
                    System.out.print("Digite o número correspondente ao jogador que deseja editar: ");
                    int indice = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (indice >= 0 && indice < players.size()) {
                        Player player = players.get(indice);
                        System.out.print("Novo nome (pressione Enter para manter o atual): ");
                        String novoNome = scanner.nextLine();
                        if (!novoNome.isEmpty()) player.setNome(novoNome);

                        System.out.print("Novo saldo (digite -1 para não alterar): ");
                        double novoSaldo = scanner.nextDouble();
                        if (novoSaldo >= 0) player.setSaldo(novoSaldo);

                        System.out.println(" Dados do jogador atualizados com sucesso!");
                    } else {
                        System.out.println(" Jogador não encontrado. Verifique o número informado.");
                    }
                }
                case 4 -> {
                    System.out.println("=== Remoção de Jogador ===");
                    for (int i = 0; i < players.size(); i++) {
                        System.out.printf("%d. %s%n", (i + 1), players.get(i).getNome());
                    }
                    System.out.print("Digite o número do jogador que deseja remover: ");
                    int indice = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (indice >= 0 && indice < players.size()) {
                        System.out.println("🗑️ Jogador '" + players.get(indice).getNome() + "' removido com sucesso.");
                        players.remove(indice);
                    } else {
                        System.out.println(" Opção inválida. Nenhum jogador foi removido.");
                    }
                }
                case 5 -> {
                    System.out.println(" Retornando ao menu principal...");
                    voltar = true;
                }
                default -> System.out.println(" Opção inválida. Por favor, tente novamente.");
            }

        }
    }

    private static void gestaoImoveis(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("=== Gerenciamento de Imóveis ===");
            System.out.println("(Total: " + imoveis.size() + " de 40 imóveis cadastrados)");
            System.out.println("1. Adicionar Novo Imóvel");
            System.out.println("2. Exibir Imóveis Cadastrados");
            System.out.println("3. Editar Imóvel Existente");
            System.out.println("4. Remover Imóvel");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    if (imoveis.size() < 40) {
                        System.out.print("Nome do novo imóvel: ");
                        String nome = scanner.nextLine();
                        System.out.print("Preço do imóvel: ");
                        double preco = scanner.nextDouble();
                        System.out.print("Valor do aluguel: ");
                        double aluguel = scanner.nextDouble();
                        imoveis.add(new Imovel(nome, preco, aluguel));
                        System.out.println(" Imóvel cadastrado com sucesso!");
                    } else {
                        System.out.println(" Limite máximo de 40 imóveis atingido.");
                    }
                }
                case 2 -> {
                    System.out.println("=== Lista de Imóveis ===");
                    if (imoveis.isEmpty()) {
                        System.out.println("Nenhum imóvel cadastrado até o momento.");
                    } else {
                        for (int i = 0; i < imoveis.size(); i++) {
                            Imovel imovel = imoveis.get(i);
                            System.out.printf("%d. %s | Preço: R$ %.2f | Aluguel: R$ %.2f%n",
                                    (i + 1), imovel.getNome(), imovel.getPreco(), imovel.getAluguel());
                        }
                    }
                }
                case 3 -> {
                    System.out.println("=== Atualizar Imóvel ===");
                    for (int i = 0; i < imoveis.size(); i++) {
                        System.out.printf("%d. %s%n", (i + 1), imoveis.get(i).getNome());
                    }
                    System.out.print("Digite o número do imóvel a ser atualizado: ");
                    int indice = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (indice >= 0 && indice < imoveis.size()) {
                        Imovel imovel = imoveis.get(indice);

                        System.out.print("Novo nome (Enter para manter): ");
                        String novoNome = scanner.nextLine();
                        if (!novoNome.isEmpty()) imovel.setNome(novoNome);

                        System.out.print("Novo preço (-1 para manter atual): ");
                        double novoPreco = scanner.nextDouble();
                        if (novoPreco >= 0) imovel.setPreco(novoPreco);

                        System.out.print("Novo valor de aluguel (-1 para manter atual): ");
                        double novoAluguel = scanner.nextDouble();
                        if (novoAluguel >= 0) imovel.setAluguel(novoAluguel);

                        System.out.println(" Imóvel atualizado com sucesso!");
                    } else {
                        System.out.println(" Número inválido. Nenhuma alteração feita.");
                    }
                }
                case 4 -> {
                    System.out.println("=== Remoção de Imóvel ===");
                    for (int i = 0; i < imoveis.size(); i++) {
                        System.out.printf("%d. %s%n", (i + 1), imoveis.get(i).getNome());
                    }
                    System.out.print("Informe o número do imóvel que deseja remover: ");
                    int indice = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (indice >= 0 && indice < imoveis.size()) {
                        System.out.println("🗑 Imóvel '" + imoveis.get(indice).getNome() + "' removido com sucesso.");
                        imoveis.remove(indice);
                    } else {
                        System.out.println(" Opção inválida. Nenhum imóvel foi removido.");
                    }
                }
                case 5 -> {
                    System.out.println(" Retornando ao menu principal...");
                    voltar = true;
                }
                default -> System.out.println(" Opção inválida. Tente novamente.");
            }
        }
    }


    private static void prefSistemas(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("=== Ajustes da Partida ===");
            System.out.printf("1. Alterar Saldo Inicial (Atual: R$ %.2f)%n", dinheiroInicial);
            System.out.printf("2. Alterar Salário por Volta (Atual: R$ %.2f)%n", ganhoPorVolta);
            System.out.printf("3. Definir Limite de Rodadas (Atual: %d)%n", maximoRodadas);
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Selecione uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Informe o novo valor de saldo inicial: ");
                    dinheiroInicial = scanner.nextDouble();
                    System.out.printf(" Saldo inicial atualizado para R$ %.2f%n", dinheiroInicial);

                    for (Player player : players) {
                        player.setSaldo(dinheiroInicial);
                    }
                }
                case 2 -> {
                    System.out.print("Informe o novo valor de salário por volta: ");
                    ganhoPorVolta = scanner.nextDouble();
                    System.out.printf(" Salário por volta definido para R$ %.2f%n", ganhoPorVolta);
                }
                case 3 -> {
                    System.out.print("Defina o número máximo de rodadas: ");
                    maximoRodadas = scanner.nextInt();
                    System.out.printf(" Máximo de rodadas ajustado para %d%n", maximoRodadas);
                }
                case 4 -> {
                    System.out.println(" Retornando ao menu principal...");
                    voltar = true;
                }
                default -> System.out.println(" Opção inválida. Tente novamente.");
            }
        }
    }

    private static boolean inicioJogo() {
        if (players.size() < 2) {
            System.out.println("️ É necessário pelo menos 2 jogadores para iniciar a partida.");
            return false;
        }
        if (imoveis.size() < 10) {
            System.out.println("⚠ Cadastre ao menos 10 imóveis antes de começar o jogo.");
            return false;
        }
        return true;
    }

    private static void iniciarJogo(Scanner scanner) {
        System.out.println(" Iniciando a partida...");
        Tabuleiro tabuleiro = criarTabuleiro();
        inicializarPosicoes(tabuleiro);

        while (rodadaAtual <= maximoRodadas) {
            System.out.printf("=== Rodada %d ===%n", rodadaAtual);

            for (Player player : players) {
                if (player.getSaldo() <= 0) {
                    System.out.printf(" Jogador %s está sem saldo e ficará fora desta rodada.%n", player.getNome());
                    continue;
                }
                menuTurnoPlayer(player, scanner, tabuleiro);
            }

            rodadaAtual++;

            if (verificarFimDeJogo()) {
                exibirFimDeJogo(" Jogo encerrado: Condições de término alcançadas.");
                break;
            }
        }

        if (rodadaAtual > maximoRodadas) {
            exibirFimDeJogo(" Jogo encerrado: Limite de rodadas atingido.");
        }
    }


    private static Tabuleiro criarTabuleiro() {
        Tabuleiro tabuleiro = new Tabuleiro();
        for (Imovel imovel : imoveis) {
            tabuleiro.adicionarCasa(new Node("Imóvel", imovel));
        }
        tabuleiro.adicionarCasa(new Node("Imposto", null));
        tabuleiro.adicionarCasa(new Node("Restituição", null));
        return tabuleiro;
    }

    private static void inicializarPosicoes(Tabuleiro tabuleiro) {
        Node inicio = tabuleiro.getInicio();
        for (Player player : players) {
            player.setPosicaoAtual(inicio);
        }
    }

    private static void menuTurnoPlayer(Player player, Scanner scanner, Tabuleiro tabuleiro) {
        boolean turnoFinalizado = false;

        while (!turnoFinalizado) {
            System.out.println("=========================================");
            System.out.printf("== RODADA %d de %d | Jogador: %s ==%n", rodadaAtual, maximoRodadas, player.getNome());
            System.out.println("=========================================");
            System.out.printf("Local atual: %s%n", player.getPosicaoAtual().getImovel() != null
                    ? player.getPosicaoAtual().getImovel().getNome()
                    : "Casa Especial");
            System.out.printf("Saldo disponível: R$ %.2f%n", player.getSaldo());

            System.out.println("Escolha uma ação:");
            System.out.println("1. Jogar os dados e avançar");
            System.out.println("2. Ver status completo");
            System.out.println("3. Propor troca com outro jogador");
            System.out.println("4. Ver ranking geral");
            System.out.println("0. Desistir do jogo");
            System.out.print("Digite sua opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    lancarDadosEMover(player, scanner, tabuleiro);
                    turnoFinalizado = true;
                }
                case 2 -> verStatusCompleto(player);
                case 3 -> System.out.println("Função de troca ainda não implementada.");
                case 4 -> verRankingJogadores();
                case 0 -> {
                    System.out.printf("O jogador %s decidiu sair da partida.%n", player.getNome());
                    player.setSaldo(0);
                    turnoFinalizado = true;
                }
                default -> System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private static void lancarDadosEMover(Player player, Scanner scanner, Tabuleiro tabuleiro) {
        int dado1 = (int) (Math.random() * 6) + 1;
        int dado2 = (int) (Math.random() * 6) + 1;
        int total = dado1 + dado2;

        System.out.printf("Você rolou %d e %d. Total: %d%n", dado1, dado2, total);
        System.out.printf("Avançando %d casas no tabuleiro...%n", total);

        moverPlayer(player, total, tabuleiro);

        Node casaAtual = player.getPosicaoAtual();
        System.out.printf("Você parou na casa: '%s'%n", casaAtual.getImovel() != null
                ? casaAtual.getImovel().getNome()
                : casaAtual.getTipo());

        if ("Imposto".equals(casaAtual.getTipo())) {
            double imposto = player.getSaldo() * 0.10;
            player.setSaldo(player.getSaldo() - imposto);
            System.out.printf("Você caiu na casa de Imposto e perdeu R$ %.2f.%n", imposto);
        } else if ("Restituição".equals(casaAtual.getTipo())) {
            double restit = player.getSaldo() * 0.10;
            player.setSaldo(player.getSaldo() + restit);
            System.out.printf("Você recebeu uma restituição de R$ %.2f!%n", restit);
        } else if (casaAtual.getImovel() != null) {
            Imovel imovel = casaAtual.getImovel();
            if (imovel.getProprietario() == null) {
                System.out.printf("O imóvel '%s' está à venda por R$ %.2f.%n", imovel.getNome(), imovel.getPreco());
                System.out.print("Deseja comprar este imóvel? (s/n): ");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("s")) {
                    if (player.getSaldo() >= imovel.getPreco()) {
                        player.setSaldo(player.getSaldo() - imovel.getPreco());
                        player.adicionarPropriedade(imovel);
                        imovel.setProprietario(player);
                        System.out.printf("Parabéns! Você comprou '%s'.%n", imovel.getNome());
                    } else {
                        System.out.println("Você não tem saldo suficiente para essa compra.");
                    }
                }
            } else if (!imovel.getProprietario().equals(player)) {
                double aluguel = imovel.getAluguel();
                if (player.getSaldo() >= aluguel) {
                    player.setSaldo(player.getSaldo() - aluguel);
                    imovel.getProprietario().setSaldo(imovel.getProprietario().getSaldo() + aluguel);
                    System.out.printf("Você pagou R$ %.2f de aluguel para %s.%n", aluguel, imovel.getProprietario().getNome());
                } else {
                    System.out.printf("Você não tem saldo para pagar o aluguel de R$ %.2f. Você foi à falência.%n", aluguel);
                    player.setSaldo(0);
                }
            } else {
                System.out.println("Este imóvel já pertence a você.");
            }
        } else {
            System.out.println("Você caiu em uma casa neutra. Nada acontece.");
        }


        System.out.println("Pressione Enter para finalizar o turno...");
        scanner.nextLine();
    }


    private static void moverPlayer(Player player, int dado, Tabuleiro tabuleiro) {
        Node posicaoAtual = player.getPosicaoAtual();
        Node inicio = tabuleiro.getInicio();

        for (int i = 0; i < dado; i++) {
            posicaoAtual = posicaoAtual.getProximo();
            if (posicaoAtual == inicio) {
                player.setSaldo(player.getSaldo() + ganhoPorVolta);
                System.out.printf("%s completou uma volta no tabuleiro e recebeu R$ %.2f como salário.%n", player.getNome(), ganhoPorVolta);
            }
        }

        player.setPosicaoAtual(posicaoAtual);
        System.out.printf("%s está agora na casa: %s.%n", player.getNome(),
                posicaoAtual.getImovel() != null ? posicaoAtual.getImovel().getNome() : posicaoAtual.getTipo());
    }

    private static void verStatusCompleto(Player player) {
        System.out.printf("=== Status de %s ===%n", player.getNome());
        System.out.printf("Saldo atual: R$ %.2f%n", player.getSaldo());
        System.out.println("Imóveis adquiridos:");
        for (Imovel imovel : player.getPropriedades()) {
            System.out.printf("- %s | Valor: R$ %.2f | Aluguel: R$ %.2f%n", imovel.getNome(), imovel.getPreco(), imovel.getAluguel());
        }
    }

    private static void verRankingJogadores() {
        Collections.sort(players, (j1, j2) -> Double.compare(calcularPatrimonio(j2), calcularPatrimonio(j1)));

        System.out.println("=== Ranking dos Jogadores ===");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.printf("%dº lugar: %s - Total em patrimônio: R$ %.2f%n", i + 1, player.getNome(), calcularPatrimonio(player));
        }
    }

    private static double calcularPatrimonio(Player player) {
        double patrimonio = player.getSaldo();
        for (Imovel imovel : player.getPropriedades()) {
            patrimonio += imovel.getPreco();
        }
        return patrimonio;
    }

    private static boolean verificarFimDeJogo() {
        int playersAtivos = 0;
        for (Player player : players) {
            if (player != null && player.getSaldo() > 0) {
                playersAtivos++;
            }
        }
        return playersAtivos <= 1;
    }


    private static void reiniciarEstadoDoJogo() {
        rodadaAtual = 1;
        players.clear();
        imoveis.clear();
    }

    private static void exibirFimDeJogo(String motivo) {
        System.out.println("=========================================");
        System.out.println("======         PARTIDA ENCERRADA       ======");
        System.out.println("=========================================");
        System.out.printf("Motivo do encerramento: %s%n", motivo);
        System.out.println("--- Classificação Final ---");
        verRankingJogadores();
        System.out.println("--- O que você gostaria de fazer agora? ---");
        System.out.println("1. Jogar novamente (mantendo as configurações atuais)");
        System.out.println("2. Retornar ao Menu Principal (para alterar as configurações)");
        System.out.println("0. Sair do jogo");

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao < 0 || opcao > 2) {
            System.out.print("Informe sua escolha: ");
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Opção inválida. Digite um número entre 0 e 2.");
                scanner.nextLine();
            }
        }

        switch (opcao) {
            case 1 -> {
                reiniciarEstadoDoJogo();
                iniciarJogo(scanner);
            }
            case 2 -> {
                reiniciarEstadoDoJogo();
                Main.main(null);
            }
            case 0 -> System.out.println("Jogo finalizado. Até breve!");
        }
    }

}