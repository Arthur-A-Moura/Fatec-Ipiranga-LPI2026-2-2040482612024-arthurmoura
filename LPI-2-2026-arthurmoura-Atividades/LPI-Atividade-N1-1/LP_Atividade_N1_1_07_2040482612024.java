import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class LP_Atividade_N1_1_07_2040482612024 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o valor A (ex.: 1.00): ");
        String valorA = scanner.next();

        System.out.print("Informe o valor B (ex.: 0.90): ");
        String valorB = scanner.next();

        System.out.print("Informe o valor total da compra: ");
        String valorCompra = scanner.next();

        System.out.print("Informe o número de parcelas: ");
        int numeroParcelas = scanner.nextInt();

        double aDouble = Double.parseDouble(valorA);
        double bDouble = Double.parseDouble(valorB);
        double subDouble = aDouble - bDouble;

        System.out.println("--- Demonstração da Imprecisão do padrão IEEE 754 (double) ---");
        System.out.println("Resultado esperado de " + valorA + " - " + valorB + " seria 0.10");
        System.out.println("Resultado real obtido com double: " + subDouble);

        BigDecimal aBD = new BigDecimal(valorA);
        BigDecimal bBD = new BigDecimal(valorB);
        BigDecimal subBD = aBD.subtract(bBD);

        System.out.println("--- Correção exata utilizando java.math.BigDecimal ---");
        System.out.println("Resultado com BigDecimal (String Constructor): " + subBD);

        BigDecimal compra = new BigDecimal(valorCompra);
        BigDecimal parcelas = new BigDecimal(numeroParcelas);
        BigDecimal valorParcela = compra.divide(parcelas, 2, RoundingMode.HALF_UP);

        System.out.println("--- Divisão de parcelas com Escala e RoundingMode.HALF_UP ---");
        System.out.println("Compra de R$ " + valorCompra + " dividida em " + numeroParcelas + "x: R$ " + valorParcela + " por parcela.");

        scanner.close();
    }
}