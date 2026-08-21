import java.util.Scanner;

public class LP_Atividade_N1_1_08_2040482612024 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o valor total da compra em reais: ");
        double valorTotalReais = scanner.nextDouble();

        System.out.print("Informe o número de parcelas: ");
        int numeroParcelas = scanner.nextInt();

        long centavosTotais = Math.round(valorTotalReais * 100);
        long parcelaCentavos = centavosTotais / numeroParcelas;
        double parcelaExibicao = parcelaCentavos / 100.0;

        String valorOriginalStr = String.format("%.2f", valorTotalReais).replace(",", ".");
        String parcelaExibicaoStr = String.format("%.2f", parcelaExibicao).replace(",", ".");

        System.out.println("--- Calculadora Financeira Escalar (Mapeamento em Centavos) ---");
        System.out.println("Valor total convertido: " + centavosTotais + " centavos.");
        System.out.println("Divisão de R$" + valorOriginalStr + " por " + numeroParcelas + " em centavos: " + parcelaCentavos + " centavos por parcela.");
        System.out.println("Valor convertido para exibição: R$ " + parcelaExibicaoStr);

        scanner.close();
    }
}