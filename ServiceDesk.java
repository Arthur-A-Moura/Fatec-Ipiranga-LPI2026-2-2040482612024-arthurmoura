import java.io.*;
import java.util.Scanner;

/*ARTHUR ALENCAR DE SOUSA MOURA - RA: 2040482612024
 * Trabalho de LPi - Sistema de Biblioteca */

public class ServiceDesk {

    public static void main(String[] args) {
        int[] cods = new int[20];
        String[] nomes = new String[20];
        int[] contagem = new int[20]; 
        
        int totalCategorias = carregarDepartamentos("categorias.txt", cods, nomes);
        
        if (totalCategorias == 0) {
            System.out.println("Nao achei o arquivo categorias.txt!");
            return;
        }

        Scanner teclado = new Scanner(System.in);
        int numProtocolo = proximoProtocolo("emprestimos.txt");

        while (true) {
            System.out.println("\n--- LISTA DE LIVROS ---");
            for (int i = 0; i < totalCategorias; i++) {
                System.out.println(cods[i] + " - " + nomes[i]);
            }

            System.out.print("Qual o codigo? (0 sai): ");
            String texto = teclado.nextLine();
            
            int c;
            try {
                c = Integer.parseInt(texto);
            } catch (Exception e) {
                System.out.println("Digita um numero valido!");
                continue;
            }

            if (c == 0) break;

            int posicao = -1;
            for (int i = 0; i < totalCategorias; i++) {
                if (cods[i] == c) {
                    posicao = i;
                    break;
                }
            }

            if (posicao == -1) {
                System.out.println("Codigo nao existe!");
                continue;
            }

            System.out.println("Voce escolheu: " + nomes[posicao]);
            System.out.print("Nome do Aluno: ");
            String aluno = teclado.nextLine().replace(";", ","); 
            System.out.print("Titulo do Livro: ");
            String livro = teclado.nextLine().replace(";", ",");

            try {
                FileWriter f = new FileWriter("emprestimos.txt", true);
                PrintWriter p = new PrintWriter(f);
                p.println(numProtocolo + ";" + aluno + ";" + nomes[posicao] + ";" + livro);
                p.close();
                
                System.out.println("Gravado! Protocolo: " + numProtocolo);
                contagem[posicao]++; 
                numProtocolo++;
            } catch (Exception e) {
                System.out.println("Erro ao salvar.");
            }
        }

        try {
            PrintWriter p2 = new PrintWriter(new FileWriter("resumo_biblioteca.txt"));
            p2.println("CATEGORIA;TOTAL");
            for (int i = 0; i < totalCategorias; i++) {
                if (contagem[i] > 0) {
                    p2.println(nomes[i] + ";" + contagem[i]);
                }
            }
            p2.close();
        } catch (Exception e) { }

        System.out.println("\n--- Resumo Final ---");
        exibirArquivo("resumo_biblioteca.txt");
        teclado.close();
    }

    public static int carregarDepartamentos(String arq, int[] c, String[] n) {
        int conta = 0;
        try {
            Scanner s = new Scanner(new File(arq));
            s.nextLine(); 
            while (s.hasNextLine() && conta < 20) {
                String l = s.nextLine();
                try {
                    String[] p = l.split(";");
                    c[conta] = Integer.parseInt(p[0]);
                    n[conta] = p[1];
                    conta++;
                } catch (Exception e) {
                    System.out.println("Linha zoada ignorada: " + l);
                }
            }
            s.close();
        } catch (Exception e) { }
        return conta;
    }

    public static int proximoProtocolo(String arq) {
        int qtdLinhas = 0;
        try {
            Scanner s = new Scanner(new File(arq));
            while (s.hasNextLine()) {
                s.nextLine();
                qtdLinhas++;
            }
            s.close();
        } catch (Exception e) { 
            return 1; 
        }
        return qtdLinhas + 1;
    }

    public static void exibirArquivo(String arq) {
        try {
            Scanner s = new Scanner(new File(arq));
            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
            s.close();
        } catch (Exception e) { }
    }
}