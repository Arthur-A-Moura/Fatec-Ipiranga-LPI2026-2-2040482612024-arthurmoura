import java.util.Scanner;

public class LP_Atividade_N2_02_2040482612024 {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double totalVendido = 0.0;
        int quantidadeVendas = 0;

        System.out.print("Valor da venda (ou 0 para encerrar): ");
        double valorVenda = scanner.nextDouble();

        while (valorVenda != 0) {
            totalVendido += valorVenda;
            quantidadeVendas++;

            System.out.print("Valor da venda (ou 0 para encerrar): ");
            valorVenda = scanner.nextDouble();
        }

        if (quantidadeVendas > 0) {
            double mediaVendas = totalVendido / quantidadeVendas;
            System.out.println("Total vendido no turno: R$ " + String.format("%.2f", totalVendido).replace(",", "."));
            System.out.println("Quantidade de vendas: " + quantidadeVendas);
            System.out.println("Valor médio por venda: R$ " + String.format("%.2f", mediaVendas).replace(",", "."));
        } else {
            System.out.println("Nenhuma venda registrada nesta sessão de caixa.");
        }

        scanner.close();
    }
}