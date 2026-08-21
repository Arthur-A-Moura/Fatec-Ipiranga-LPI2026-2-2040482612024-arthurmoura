import java.util.Scanner;

public class LP_Atividade_N1_1_10_2040482612024 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o valor A: ");
        double a = scanner.nextDouble();

        System.out.print("Informe o valor B: ");
        double b = scanner.nextDouble();

        System.out.print("Informe o valor C: ");
        double c = scanner.nextDouble();

        System.out.print("Informe o valor D: ");
        double d = scanner.nextDouble();

        System.out.print("Informe a média do aluno: ");
        double media = scanner.nextDouble();

        System.out.print("Informe o percentual de presença: ");
        double presenca = scanner.nextDouble();

        double semParenteses = a + b * c / d;
        double comParenteses = a + ((b * c) / d);

        System.out.println("--- Demonstração de Precedência Aritmética ---");
        System.out.println("Resultado Sem parênteses: " + semParenteses);
        System.out.println("Resultado Com parênteses explícitos: " + comParenteses);
        System.out.println("Nota: Ambos dão o mesmo resultado pela precedência natural (*, / depois +), mas a segunda forma é mais legível.");

        boolean aprovadoDireto = (media >= 6.0) && (presenca >= 75.0);

        String mediaStr = (media == (long) media) ? String.format("%d", (long) media) : String.valueOf(media);
        String presencaStr = (presenca == (long) presenca) ? String.format("%d", (long) presenca) : String.valueOf(presenca);

        System.out.println("--- Validação do Critério de Aprovação ---");
        System.out.println("Entrada -> Média: " + mediaStr + " | Presença: " + presencaStr + "%");
        System.out.println("Resultado da avaliação (aprovadoDireto): " + aprovadoDireto);

        scanner.close();
    }
}