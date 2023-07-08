public class JogoDaVelha {
    private char[][] tabuleiro;

    public JogoDaVelha(int tamanhoTabuleiro) {
        tabuleiro = new char[tamanhoTabuleiro][tamanhoTabuleiro];
    }

    public boolean realizaJogada(int linha, int coluna, char jogada) {
        if (tabuleiro[linha][coluna] != '\0') {
            return false;
        }
        tabuleiro[linha][coluna] = jogada;
        return true;
    }

    public boolean verificaGanhador() {
        // Verifica as diagonais principais e secundárias
        char diagonalPrincipal = tabuleiro[0][0];
        char diagonalSecundaria = tabuleiro[0][tabuleiro.length - 1];
        for (int i = 1; i < tabuleiro.length; i++) {
            if (tabuleiro[i][i] != diagonalPrincipal) {
                diagonalPrincipal = '\0';
            }
            if (tabuleiro[i][tabuleiro.length - 1 - i] != diagonalSecundaria) {
                diagonalSecundaria = '\0';
            }
        }
        if (diagonalPrincipal != '\0') {
            return true;
        }
        if (diagonalSecundaria != '\0') {
            return true;
        }

        // Verifica as linhas
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            char primeiroElemento = tabuleiro[linha][0];
            if (primeiroElemento == '\0') {
                continue;
            }
            boolean linhaCompleta = true;
            for (int coluna = 1; coluna < tabuleiro.length; coluna++) {
                if (tabuleiro[linha][coluna] != primeiroElemento) {
                    linhaCompleta = false;
                    break;
                }
            }
            if (linhaCompleta) {
                return true;
            }
        }

        // Verifica as colunas
        for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
            char primeiroElemento = tabuleiro[0][coluna];
            if (primeiroElemento == '\0') {
                continue;
            }
            boolean colunaCompleta = true;
            for (int linha = 1; linha < tabuleiro.length; linha++) {
                if (tabuleiro[linha][coluna] != primeiroElemento) {
                    colunaCompleta = false;
                    break;
                }
            }
            if (colunaCompleta) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
                sb.append(tabuleiro[linha][coluna]);
                if (coluna < tabuleiro.length - 1) {
                    sb.append('|');
                }
            }
            sb.append('\n');
            if (linha < tabuleiro.length - 1) {
                sb.append("-".repeat(tabuleiro.length * 2 - 1));
                sb.append('\n');
            }
        }
        return sb.toString();
    }

	public void resetTabuleiro() {
		
	}

	public boolean tabuleiroCompleto() {
		return false;
	}

}


