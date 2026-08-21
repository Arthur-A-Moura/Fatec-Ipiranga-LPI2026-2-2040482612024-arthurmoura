import java.util.Scanner;

public class LP_Atividade_N2_01_2040482612024 {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quantidade de alunos na turma: ");
        int quantidadeAlunos = scanner.nextInt();

        double soma = 0.0;
        double maiorNota = 0.0;

        for (int i = 0; i < quantidadeAlunos; i++) {
            System.out.print("Nota do aluno: ");
            double nota = scanner.nextDouble();

            soma += nota;

            if (i == 0 || nota > maiorNota) {
                maiorNota = nota;
            }
        }

        double media = soma / quantidadeAlunos;

        System.out.println("Soma total das notas: " + soma);
        System.out.println("Média da turma: " + media);
        System.out.println("Maior nota da turma: " + maiorNota);

        scanner.close();
    }
}