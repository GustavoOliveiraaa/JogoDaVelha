import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Digite o nome do primeiro jogador: ");
        String nomeJogador1 = s.nextLine();
        Jogador jogador1 = new Jogador(nomeJogador1);

        System.out.print("Digite o nome do segundo jogador: ");
        String nomeJogador2 = s.nextLine();
        Jogador jogador2 = new Jogador(nomeJogador2);

        System.out.print("Digite o tamanho do tabuleiro: ");
        int tamanhoTabuleiro = s.nextInt();
        s.nextLine(); // consumir o \n deixado pelo nextInt()

        JogoDaVelha jogo = new JogoDaVelha(tamanhoTabuleiro);

        int jogadorAtual = 1;
        boolean jogoContinua = true;

        while (jogoContinua) {
            System.out.println(jogo);
            System.out.println("É a vez do jogador " + jogadorAtual);

            System.out.print("Digite a linha da jogada: ");
            int linha = s.nextInt();

            System.out.print("Digite a coluna da jogada: ");
            int coluna = s.nextInt();
            s.nextLine(); // consumir o \n deixado pelo nextInt()

            char caractereJogadorAtual = (jogadorAtual == 1) ? 'X' : 'O';

            if (jogo.realizaJogada(linha, coluna, caractereJogadorAtual)) {
                if (jogo.verificaGanhador()) {
                    if (jogadorAtual == 1) {
                        jogador1.setPontos(jogador1.getPontos() + 1);
                        System.out.println("O jogador " + jogador1.getNome() + " venceu!");
                    } else {
                        jogador2.setPontos(jogador2.getPontos() + 1);
                        System.out.println("O jogador " + jogador2.getNome() + " venceu!");
                    }

                    System.out.println("Desejam jogar novamente? (S/N)");
                    String resposta = s.nextLine();
                    jogo.resetTabuleiro();

                    if (resposta.equalsIgnoreCase("N")) {
                        jogoContinua = false;
                    }
                } else if (jogo.tabuleiroCompleto()) {
                    System.out.println("Deu velha!");
                    System.out.println("Desejam jogar novamente? (S/N)");
                    String resposta = s.nextLine();
                    jogo.resetTabuleiro();

                    if (resposta.equalsIgnoreCase("N")) {
                        jogoContinua = false;
                    }
                } else {
                    jogadorAtual = (jogadorAtual == 1) ? 2 : 1;
                }
            } else {
                System.out.println("Posição já preenchida, tente novamente.");
            }
        }

        // Escrever resultado no arquivo resultado.txt
        FileWriter writer = null;
        try {
            writer = new FileWriter("resultado.txt");
            if (jogador1.getPontos() > jogador2.getPontos()) {
                writer.write("Vencedor: " + jogador1.getNome() + "\n");
                writer.write("Pontos: " + jogador1.getPontos());
            } else {
                writer.write("Vencedor: " + jogador2.getNome() + "\n");
                writer.write("Pontos: " + jogador2.getPontos());
            }
            System.out.println("Resultado gravado no arquivo resultado.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }
}
