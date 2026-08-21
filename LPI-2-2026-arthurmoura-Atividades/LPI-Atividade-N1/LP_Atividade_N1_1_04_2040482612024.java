import java.util.Scanner;

public class LP_Atividade_N1_1_04_2040482612024 {
    public static final String INSTITUICAO = "FATEC Ipiranga";
    public static final int PRAZO_MAXIMO = 14;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o número de dias do empréstimo: ");
        final int diasEmprestimo = scanner.nextInt();

        exibirRelatorio(diasEmprestimo);

        scanner.close();
    }

    public static void exibirRelatorio(final int dias) {
        System.out.println("Instituição: " + INSTITUICAO);
        System.out.println("Prazo Máximo Padrão: " + PRAZO_MAXIMO + " dias.");
        System.out.println("Dias calculados para devolução: " + dias);
    }
}