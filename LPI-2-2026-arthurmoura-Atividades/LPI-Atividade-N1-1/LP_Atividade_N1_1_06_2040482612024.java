import java.util.Scanner;

public class LP_Atividade_N1_1_06_2040482612024 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o nome de usuário: ");
        String texto = scanner.nextLine();

        if (texto != null && !texto.isEmpty()) {
            System.out.println("Usuário válido fornecido: " + texto);
        } else {
            System.out.println("Entrada rejeitada: O texto está nulo ou vazio.");
        }

        scanner.close();
    }
}