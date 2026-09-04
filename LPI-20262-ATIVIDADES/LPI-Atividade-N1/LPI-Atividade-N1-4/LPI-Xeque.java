/*--
 * Disciplina: Linguagem de Programacao I
 * Prof. Verissimo
 *
 * Objetivo do Programa: Simular o Xeque Pastor em matriz bidimensional com modularizacao.
 *
 * Data: 03/09/2026
 * Autor: Arthur Alencar de Sousa Moura
 *
 */
public class LP_Atividade_N1_4_2040482612024 {

    private static final int LINHAS = 8;
    private static final int COLUNAS = 8;
    private static final String ORIGEM_VAZIA = "...";
    private static final String CASA_VAZIA = "";

    private static String[][] tabuleiro = new String[LINHAS][COLUNAS];

    public static void main(String[] args) {
        inicializarTabuleiro();
        exibirPosicaoInicial();

        // Jogada 1
        moverPeca(6, 4, 4, 4); // Brancas: e2-e4
        exibirTabuleiroComMensagem("-----------Brancas jogam e4-----------");
        moverPeca(1, 4, 3, 4); // Pretas: e7-e5
        exibirTabuleiroComMensagem("-----------Pretas jogam e5-----------");

        // Jogada 2
        moverPeca(7, 5, 4, 2); // Brancas: Bc4
        exibirTabuleiroComMensagem("-----------Brancas jogam Bc4-----------");
        moverPeca(0, 1, 2, 2); // Pretas: Cc6
        exibirTabuleiroComMensagem("-----------Pretas jogam Cc6-----------");

        // Jogada 3
        moverPeca(7, 3, 3, 7); // Brancas: Dh5
        exibirTabuleiroComMensagem("-----------Brancas jogam Dh5-----------");
        moverPeca(0, 6, 2, 5); // Pretas: Cf6
        exibirTabuleiroComMensagem("-----------Pretas jogam Cf6-----------");

        // Jogada 4 - Xeque Mate
        moverPeca(3, 7, 1, 5); // Brancas: Dxf7#
        exibirTabuleiroComMensagem("----Brancas capturam Peao PP6 em f7 e Xeque MATE (Dxf7#)--------");
    }

    // Inicializa a matriz com as pecas nas posicoes iniciais
    private static void inicializarTabuleiro() {
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                tabuleiro[i][j] = CASA_VAZIA;
            }
        }

        tabuleiro[0] = new String[]{"tP1", "cP2", "bP3", "dP4", "rP5", "bP6", "cP7", "tP8"};
        for (int j = 0; j < COLUNAS; j++) tabuleiro[1][j] = "PP" + (j + 1);
        for (int j = 0; j < COLUNAS; j++) tabuleiro[6][j] = "PB" + (j + 1);
        tabuleiro[7] = new String[]{"TB1", "CB2", "BB3", "DB4", "RB5", "BB6", "CB7", "TB8"};
    }

    // Mover peca na matriz
    private static void moverPeca(int linOrigem, int colOrigem, int linDestino, int colDestino) {
        tabuleiro[linDestino][colDestino] = tabuleiro[linOrigem][colOrigem];
        tabuleiro[linOrigem][colOrigem] = ORIGEM_VAZIA;
    }

    // Exibe tabuleiro inicial
    private static void exibirPosicaoInicial() {
        System.out.println("=================================================================");
        System.out.println("                     Posicao Incial do tabuleiro                 ");
        System.out.println("=================================================================");
        exibirTabuleiro();
        System.out.println();
    }

    // Exibe mensagem da jogada e o tabuleiro
    private static void exibirTabuleiroComMensagem(String mensagem) {
        System.out.println(mensagem);
        exibirTabuleiro();
        System.out.println();
    }

    // Imprime o tabuleiro formatado
    private static void exibirTabuleiro() {
        System.out.println("  -a-- -b-- -c-- -d-- -e-- -f-- -g-- -h-");
        for (int i = 0; i < LINHAS; i++) {
            System.out.print((LINHAS - i) + " ");
            for (int j = 0; j < COLUNAS; j++) {
                String casa = tabuleiro[i][j];
                if (casa.equals(CASA_VAZIA)) System.out.print("    ");
                else if (casa.equals(ORIGEM_VAZIA)) System.out.print("... ");
                else System.out.printf("%-4s", casa);
            }
            System.out.println();
        }
    }
}