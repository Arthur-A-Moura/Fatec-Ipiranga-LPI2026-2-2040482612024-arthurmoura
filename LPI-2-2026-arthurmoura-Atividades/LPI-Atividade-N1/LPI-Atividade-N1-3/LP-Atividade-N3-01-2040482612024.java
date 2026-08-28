import java.util.Scanner;

public class PgmN13 {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        // 1. DECLARAÇÃO E INSTANCIAÇÃO
        // O tamanho do vetor é definido pelo usuário em tempo de execução
        System.out.print("Quantidade de leituras: ");
        int quantidadeLeituras = leitor.nextInt();
        double[] leituras = new double[quantidadeLeituras];

        // 2. ACESSO (Escrita)
        // Preenchimento do vetor com dados via teclado
        for (int i = 0; i < leituras.length; i++) {
            System.out.print("Temperatura (°C): ");
            leituras[i] = leitor.nextDouble();
        }

        // 3. PERCURSO
        // Exibição de todos os elementos utilizando um laço for tradicional
        System.out.println("-- Leituras Registradas --");
        for (int i = 0; i < leituras.length; i++) {
            System.out.println("Leitura [" + i + "]: " + leituras[i] + "°C");
        }

        // 4. ACESSO DIRETO
        // Obtendo o primeiro e o último elemento sem a necessidade de laços
        System.out.println("-- Acesso Direto --");
        System.out.println("Primeira leitura (indice 0): " + leituras[0] + "°C");
        int ultimoIndice = leituras.length - 1;
        System.out.println("Ultima leitura (indice " + ultimoIndice + "): " + leituras[ultimoIndice] + "°C");

        // 5. BUSCA LINEAR (Extremos)
        // Varredura para encontrar o maior e o menor valor simultaneamente
        System.out.println("-- Busca Linear: Extremos --");
        double maior = leituras[0];
        double menor = leituras[0];
        int indMaior = 0;
        int indMenor = 0;

        for (int i = 1; i < leituras.length; i++) {
            if (leituras[i] > maior) {
                maior = leituras[i];
                indMaior = i;
            }
            if (leituras[i] < menor) {
                menor = leituras[i];
                indMenor = i;
            }
        }
        System.out.println("Maior temperatura: " + maior + "°C (indice " + indMaior + ")");
        System.out.println("Menor temperatura: " + menor + "°C (indice " + indMenor + ")");

        // 6. BUSCA LINEAR (Alerta)
        // Verificação da primeira ocorrência que atinge ou ultrapassa o valor crítico
        System.out.print("Temperatura crítica de alerta (°C): ");
        double temperaturaCritica = leitor.nextDouble();
        System.out.println("-- Busca Linear: Alerta --");

        boolean encontrouCritica = false;
        for (int i = 0; i < leituras.length; i++) {
            if (leituras[i] >= temperaturaCritica) {
                System.out.println("Alerta: temperatura critica atingida na leitura de indice " + i + " (" + leituras[i] + "°C).");
                encontrouCritica = true;
                break; // Interrompe na primeira ocorrência conforme solicitado
            }
        }

        if (!encontrouCritica) {
            System.out.println("Nenhuma leitura atingiu ou ultrapassou a temperatura critica informada.");
        }

        leitor.close();
    }
}