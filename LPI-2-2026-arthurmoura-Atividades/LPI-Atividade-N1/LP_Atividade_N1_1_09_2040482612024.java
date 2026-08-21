import java.util.Scanner;

public class LP_Atividade_N1_1_09_2040482612024 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe a nota final do aluno: ");
        double notaFinal = scanner.nextDouble();

        System.out.println("--- Abordagem 1: Estrutura Condicional Tradicional (if-else) ---");
        String situacaoIf;
        if (notaFinal >= 6.0) {
            situacaoIf = "Aprovado";
        } else {
            situacaoIf = "Reprovado";
        }
        System.out.println("Situação (if-else): " + situacaoIf);

        System.out.println("--- Abordagem 2: Operador Ternário Simples ---");
        String situacaoTernario = (notaFinal >= 6.0) ? "Aprovado" : "Reprovado";
        System.out.println("Situação (Ternário): " + situacaoTernario);

        scanner.close();
    }
}