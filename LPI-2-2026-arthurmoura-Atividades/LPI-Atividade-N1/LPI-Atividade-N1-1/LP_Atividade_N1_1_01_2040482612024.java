import java.util.Scanner;

public class LP_Atividade_N1_1_01_2040482612024 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Idade do Aluno: ");
        byte idade = scanner.nextByte();

        System.out.print("Número de Faltas: ");
        short faltas = scanner.nextShort();

        System.out.print("Matrícula ID: ");
        int matriculaId = scanner.nextInt();

        System.out.print("Código Nacional do Estudante: ");
        long codigoNacional = scanner.nextLong();

        System.out.print("Nota do Trabalho: ");
        float notaTrabalho = scanner.nextFloat();

        System.out.print("Nota da Prova Final: ");
        double notaProvaFinal = scanner.nextDouble();

        System.out.print("Conceito Final do Aluno: ");
        char conceitoFinal = scanner.next().charAt(0);

        System.out.print("Aluno está Aprovado (true/false): ");
        boolean aprovado = scanner.nextBoolean();

        System.out.println("--- Dados do Aluno Fictício ---");
        System.out.println("Idade do Aluno: " + idade + " anos");
        System.out.println("Número de Faltas: " + faltas);
        System.out.println("Matrícula ID: " + matriculaId);
        System.out.println("Código Nacional do Estudante: " + codigoNacional);
        System.out.println("Nota do Trabalho: " + notaTrabalho);
        System.out.println("Nota da Prova Final: " + notaProvaFinal);
        System.out.println("Conceito Final do Aluno: " + conceitoFinal);
        System.out.println("Aluno está Aprovado? " + aprovado);

        scanner.close();
    }
}