import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/* Arthur Alencar De Sousa Moura - RA: 2040482612024 | LPi - Fatec Ipiranga (Tema 4) */
public class SistemaBiblioteca {

    static final int MAX_LIV = 200, MAX_LEI = 100, MAX_EMP = 500, ANO = 2026;
    static final double MULTA_DIA = 0.50;
    static final String[] CATS = {"Literatura", "Didatico", "Tecnico", "Infantojuvenil", "Outros"};
    static final String[] TIPOS = {"Estudante", "Professor", "Comunidade"};
    static final int[] PRAZOS = {7, 14, 7}, LIMITES = {2, 4, 1};

    static int[] codLiv = new int[MAX_LIV], catLiv = new int[MAX_LIV], anoLiv = new int[MAX_LIV], totLiv = new int[MAX_LIV], dispLiv = new int[MAX_LIV];
    static String[] titLiv = new String[MAX_LIV], autLiv = new String[MAX_LIV];
    static int qtdLiv = 0;

    static int[] codLei = new int[MAX_LEI], tipoLei = new int[MAX_LEI], abertosLei = new int[MAX_LEI];
    static String[] nomeLei = new String[MAX_LEI];
    static int qtdLei = 0;

    static int[] codEmp = new int[MAX_EMP], empLiv = new int[MAX_EMP], empLei = new int[MAX_EMP], empDia = new int[MAX_EMP], empPrev = new int[MAX_EMP], empDev = new int[MAX_EMP];
    static double[] empMulta = new double[MAX_EMP];
    static int qtdEmp = 0, proxEmp = 1;

    static int[][] uso = new int[3][5];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        carregarDados();
        int op;
        do {
            System.out.println("\n=== BIBLIOTECA VILA LEITURA ===");
            System.out.println("1-Cad.Livro  2-Add.Exemplar  3-Listar Acervo  4-Cons.Livro  5-Por Categoria");
            System.out.println("6-Cad.Leitor 7-Listar Leitor 8-Emprestar      9-Devolver    10-Em Aberto");
            System.out.println("11-Circulacao 12-Multas      13-Perfil Uso   14-Exportar    0-Sair");
            op = lerInt("Opcao: ", 0, 14);
            switch (op) {
                case 1: cadLivro(); break;
                case 2: addExemplares(); break;
                case 3: listarLivros(0); break;
                case 4: consLivro(); break;
                case 5: listarLivros(lerInt("Categoria (1-Lit 2-Did 3-Tec 4-Inf 5-Out): ", 1, 5)); break;
                case 6: cadLeitor(); break;
                case 7: listarLeitores(); break;
                case 8: emprestar(); break;
                case 9: devolver(); break;
                case 10: emAberto(); break;
                case 11: relCirculacao(); break;
                case 12: relMultas(); break;
                case 13: relPerfil(); break;
                case 14: exportarAtrasos(); break;
                case 0: salvarLivros(); salvarLeitores(); salvarEmp(); System.out.println("Dados salvos. Saindo..."); break;
            }
        } while (op != 0);
        sc.close();
    }

    static int lerInt(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v >= min && v <= max) return v;
                System.out.println("Digite entre " + min + " e " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas numeros.");
            }
        }
    }

    static String lerTxt(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (!s.isEmpty() && !s.contains(";")) return s;
            System.out.println("Campo obrigatorio e sem ';'.");
        }
    }

    static int buscar(int[] vet, int qtd, int cod) {
        for (int i = 0; i < qtd; i++) if (vet[i] == cod) return i;
        return -1;
    }

    static int atraso(int prev, int dia) {
        return dia > prev ? dia - prev : 0;
    }

    static void cadLivro() {
        if (qtdLiv >= MAX_LIV) { System.out.println("Limite de livros atingido."); return; }
        int cod = lerInt("Codigo (100-9999): ", 100, 9999);
        if (buscar(codLiv, qtdLiv, cod) != -1) { System.out.println("Codigo ja existe."); return; }
        titLiv[qtdLiv] = lerTxt("Titulo: ");
        autLiv[qtdLiv] = lerTxt("Autor: ");
        catLiv[qtdLiv] = lerInt("Categoria (1-Lit 2-Did 3-Tec 4-Inf 5-Out): ", 1, 5);
        anoLiv[qtdLiv] = lerInt("Ano (1450-" + ANO + "): ", 1450, ANO);
        totLiv[qtdLiv] = lerInt("Exemplares (1-50): ", 1, 50);
        dispLiv[qtdLiv] = totLiv[qtdLiv];
        codLiv[qtdLiv++] = cod;
        if (salvarLivros()) System.out.println("Livro cadastrado!");
    }

    static void addExemplares() {
        int i = buscar(codLiv, qtdLiv, lerInt("Codigo do livro: ", 100, 9999));
        if (i == -1) { System.out.println("Livro nao encontrado."); return; }
        int n = lerInt("Novos exemplares (1-50): ", 1, 50);
        totLiv[i] += n; dispLiv[i] += n;
        if (salvarLivros()) System.out.println("Atualizado! Total: " + totLiv[i] + " | Disp: " + dispLiv[i]);
    }

    static void listarLivros(int cat) {
        int achados = 0, soma = 0;
        for (int i = 0; i < qtdLiv; i++) {
            if (cat == 0 || catLiv[i] == cat) {
                if (achados == 0) System.out.printf("%-5s %-25s %-18s %-14s %-5s %-4s %-4s%n", "Cod", "Titulo", "Autor", "Categoria", "Ano", "Tot", "Disp");
                System.out.printf("%-5d %-25s %-18s %-14s %-5d %-4d %-4d%n", codLiv[i], titLiv[i], autLiv[i], CATS[catLiv[i] - 1], anoLiv[i], totLiv[i], dispLiv[i]);
                achados++; soma += totLiv[i];
            }
        }
        if (achados == 0) System.out.println("Nenhum livro encontrado.");
        else System.out.println("Titulos: " + achados + (cat == 0 ? " | Exemplares: " + soma : ""));
    }

    static void consLivro() {
        int cod = lerInt("Codigo do livro: ", 100, 9999), i = buscar(codLiv, qtdLiv, cod);
        if (i == -1) { System.out.println("Livro nao encontrado."); return; }
        int emp = totLiv[i] - dispLiv[i];
        System.out.printf("%d - %s (%s, %d) | Cat: %s | Tot: %d | Disp: %d | Empr: %d%n", codLiv[i], titLiv[i], autLiv[i], anoLiv[i], CATS[catLiv[i] - 1], totLiv[i], dispLiv[i], emp);
        for (int e = 0; e < qtdEmp; e++) {
            if (empLiv[e] == cod && empDev[e] == 0) {
                int k = buscar(codLei, qtdLei, empLei[e]);
                System.out.println(" Emprestado #" + codEmp[e] + " para " + (k != -1 ? nomeLei[k] : "?") + " (Prev: dia " + empPrev[e] + ")");
            }
        }
    }

    static void cadLeitor() {
        if (qtdLei >= MAX_LEI) { System.out.println("Limite de leitores atingido."); return; }
        int cod = lerInt("Codigo (1-9999): ", 1, 9999);
        if (buscar(codLei, qtdLei, cod) != -1) { System.out.println("Codigo ja existe."); return; }
        nomeLei[qtdLei] = lerTxt("Nome: ");
        int t = lerInt("Tipo (1-Estudante 2-Professor 3-Comunidade): ", 1, 3);
        tipoLei[qtdLei] = t; abertosLei[qtdLei] = 0; codLei[qtdLei++] = cod;
        if (salvarLeitores()) System.out.println("Leitor cadastrado! Prazo: " + PRAZOS[t - 1] + "d | Limite: " + LIMITES[t - 1]);
    }

    static void listarLeitores() {
        if (qtdLei == 0) { System.out.println("Nenhum leitor cadastrado."); return; }
        System.out.printf("%-5s %-25s %-12s %-10s%n", "Cod", "Nome", "Tipo", "Abertos");
        for (int i = 0; i < qtdLei; i++) {
            int t = tipoLei[i] - 1;
            System.out.printf("%-5d %-25s %-12s %d de %d%n", codLei[i], nomeLei[i], TIPOS[t], abertosLei[i], LIMITES[t]);
        }
    }

    static void emprestar() {
        if (qtdEmp >= MAX_EMP) { System.out.println("Limite de emprestimos atingido."); return; }
        int cLei = lerInt("Cod Leitor: ", 1, 9999), iK = buscar(codLei, qtdLei, cLei);
        if (iK == -1) { System.out.println("Leitor nao encontrado."); return; }
        int t = tipoLei[iK] - 1;
        if (abertosLei[iK] >= LIMITES[t]) { System.out.println("Limite atingido (" + LIMITES[t] + ")."); return; }
        int cLiv = lerInt("Cod Livro: ", 100, 9999), iL = buscar(codLiv, qtdLiv, cLiv);
        if (iL == -1 || dispLiv[iL] <= 0) { System.out.println("Livro inexistente ou indisponivel."); return; }
        int dia = lerInt("Dia do emprestimo (1-365): ", 1, 365);
        for (int e = 0; e < qtdEmp; e++) {
            if (empLei[e] == cLei && empDev[e] == 0) {
                if (empLiv[e] == cLiv) { System.out.println("Leitor ja possui este livro em aberto."); return; }
                if (empPrev[e] < dia) { System.out.println("Leitor com emprestimo atrasado (#" + codEmp[e] + ")."); return; }
            }
        }
        codEmp[qtdEmp] = proxEmp; empLiv[qtdEmp] = cLiv; empLei[qtdEmp] = cLei;
        empDia[qtdEmp] = dia; empPrev[qtdEmp] = dia + PRAZOS[t]; empDev[qtdEmp] = 0; empMulta[qtdEmp] = 0.0;
        dispLiv[iL]--; abertosLei[iK]++; uso[t][catLiv[iL] - 1]++; qtdEmp++; proxEmp++;
        if (salvarLivros() && salvarEmp()) System.out.println("Emprestimo #" + (proxEmp - 1) + " registrado. Previsto: dia " + empPrev[qtdEmp - 1]);
    }

    static void devolver() {
        int iE = buscar(codEmp, qtdEmp, lerInt("Cod Emprestimo: ", 1, 999999));
        if (iE == -1 || empDev[iE] != 0) { System.out.println("Emprestimo nao encontrado ou ja devolvido."); return; }
        int dia;
        do {
            dia = lerInt("Dia devolucao (1-365): ", 1, 365);
            if (dia < empDia[iE]) System.out.println("Nao pode ser menor que o dia do emprestimo (" + empDia[iE] + ").");
        } while (dia < empDia[iE]);
        int atr = atraso(empPrev[iE], dia);
        empDev[iE] = dia; empMulta[iE] = atr * MULTA_DIA;
        int iL = buscar(codLiv, qtdLiv, empLiv[iE]), iK = buscar(codLei, qtdLei, empLei[iE]);
        if (iL != -1) dispLiv[iL]++;
        if (iK != -1 && abertosLei[iK] > 0) abertosLei[iK]--;
        if (salvarLivros() && salvarEmp()) System.out.printf(atr > 0 ? "Atraso: %d dia(s). Multa: R$ %.2f%n" : "Devolvido no prazo.%n", atr, empMulta[iE]);
    }

    static void emAberto() {
        int dia = lerInt("Dia de referencia (1-365): ", 1, 365), ab = 0, atr = 0;
        for (int e = 0; e < qtdEmp; e++) {
            if (empDev[e] == 0) {
                int d = atraso(empPrev[e], dia), iL = buscar(codLiv, qtdLiv, empLiv[e]), iK = buscar(codLei, qtdLei, empLei[e]);
                if (d > 0) atr++;
                System.out.printf("#%-3d %-22s %-18s Emp:%-3d Prev:%-3d %s%n", codEmp[e], iL != -1 ? titLiv[iL] : "?", iK != -1 ? nomeLei[iK] : "?", empDia[e], empPrev[e], d > 0 ? "ATRASADO " + d + "d" : "NO PRAZO");
                ab++;
            }
        }
        System.out.println(ab == 0 ? "Nenhum emprestimo em aberto." : "Em aberto: " + ab + " | Atrasados: " + atr);
    }

    static void relCirculacao() {
        if (qtdLiv == 0) { System.out.println("Acervo vazio."); return; }
        int tot = 0, disp = 0, emp = 0;
        for (int i = 0; i < qtdLiv; i++) {
            int em = totLiv[i] - dispLiv[i]; tot += totLiv[i]; disp += dispLiv[i]; emp += em;
            System.out.printf("%-5d %-25s Tot:%-3d Disp:%-3d Empr:%-3d%n", codLiv[i], titLiv[i], totLiv[i], dispLiv[i], em);
        }
        System.out.printf("Disponiveis: %d | Emprestados: %d (%.1f%%)%nSem exemplares: ", disp, emp, tot > 0 ? emp * 100.0 / tot : 0.0);
        for (int i = 0; i < qtdLiv; i++) if (dispLiv[i] == 0) System.out.print("[" + codLiv[i] + " " + titLiv[i] + "] ");
        System.out.println();
    }

    static void relMultas() {
        int dia = lerInt("Dia de referencia (1-365): ", 1, 365);
        double arr = 0, prev = 0;
        System.out.println("(a) Devolucoes com multa:");
        for (int e = 0; e < qtdEmp; e++) {
            if (empDev[e] != 0 && empMulta[e] > 0) {
                int iK = buscar(codLei, qtdLei, empLei[e]);
                System.out.printf(" #%-3d %-18s %dd R$ %.2f%n", codEmp[e], iK != -1 ? nomeLei[iK] : "?", atraso(empPrev[e], empDev[e]), empMulta[e]);
                arr += empMulta[e];
            }
        }
        System.out.printf("Total arrecadado: R$ %.2f%n(b) Atrasos em aberto no dia %d:%n", arr, dia);
        for (int e = 0; e < qtdEmp; e++) {
            int d = empDev[e] == 0 ? atraso(empPrev[e], dia) : 0;
            if (d > 0) {
                int iK = buscar(codLei, qtdLei, empLei[e]);
                System.out.printf(" #%-3d %-18s %dd R$ %.2f%n", codEmp[e], iK != -1 ? nomeLei[iK] : "?", d, d * MULTA_DIA);
                prev += d * MULTA_DIA;
            }
        }
        System.out.printf("Total previsto: R$ %.2f%n", prev);
    }

    static void relPerfil() {
        int[] col = new int[5];
        int totG = 0, maxCol = -1, idxG = 0;
        System.out.printf("%-11s %-10s %-8s %-8s %-10s %-6s %-5s%n", "Tipo", "Lit.", "Did.", "Tec.", "Inf.", "Out.", "Tot");
        for (int t = 0; t < 3; t++) {
            int lin = 0, maxL = -1, idxL = 0;
            System.out.printf("%-11s ", TIPOS[t]);
            for (int c = 0; c < 5; c++) {
                int v = uso[t][c]; lin += v; col[c] += v; totG += v;
                if (v > maxL) { maxL = v; idxL = c; }
                System.out.printf("%-8d ", v);
            }
            System.out.printf("%-5d (Mais: %s)%n", lin, lin > 0 ? CATS[idxL] : "-");
        }
        System.out.printf("%-11s ", "Total");
        for (int c = 0; c < 5; c++) {
            System.out.printf("%-8d ", col[c]);
            if (col[c] > maxCol) { maxCol = col[c]; idxG = c; }
        }
        System.out.println(totG + (totG > 0 ? " | Mais procurada: " + CATS[idxG] : ""));
    }

    static void exportarAtrasos() {
        int dia = lerInt("Dia de referencia (1-365): ", 1, 365), qtd = 0;
        double tot = 0;
        for (int e = 0; e < qtdEmp; e++) if (empDev[e] == 0 && atraso(empPrev[e], dia) > 0) qtd++;
        if (qtd == 0) { System.out.println("Nenhum atraso no dia " + dia + ". Arquivo nao gerado."); return; }
        String arq = "atrasos_dia" + dia + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arq, StandardCharsets.UTF_8))) {
            bw.write("BIBLIOTECA VILA LEITURA - ATRASOS (DIA " + dia + ")"); bw.newLine();
            for (int e = 0; e < qtdEmp; e++) {
                int d = empDev[e] == 0 ? atraso(empPrev[e], dia) : 0;
                if (d > 0) {
                    int iL = buscar(codLiv, qtdLiv, empLiv[e]), iK = buscar(codLei, qtdLei, empLei[e]);
                    double m = d * MULTA_DIA; tot += m;
                    bw.write(String.format("#%d;%s;%s;%s;Prev:%d;%dd;R$%.2f", codEmp[e], iL != -1 ? titLiv[iL] : "?", iK != -1 ? nomeLei[iK] : "?", iK != -1 ? TIPOS[tipoLei[iK] - 1] : "?", empPrev[e], d, m));
                    bw.newLine();
                }
            }
            bw.write(String.format("Total atrasos: %d | Multas previstas: R$ %.2f", qtd, tot)); bw.newLine();
            System.out.println("Exportado para " + arq + " (" + qtd + " atrasos).");
        } catch (IOException e) { System.out.println("Erro ao exportar: " + e.getMessage()); }
    }

    static void carregarDados() {
        carregarLivros(); carregarLeitores(); carregarEmp();
        int maxCod = 0;
        for (int e = 0; e < qtdEmp; e++) {
            if (codEmp[e] > maxCod) maxCod = codEmp[e];
            int iL = buscar(codLiv, qtdLiv, empLiv[e]), iK = buscar(codLei, qtdLei, empLei[e]);
            if (iK != -1 && empDev[e] == 0) abertosLei[iK]++;
            if (iL != -1 && iK != -1) uso[tipoLei[iK] - 1][catLiv[iL] - 1]++;
        }
        proxEmp = maxCod + 1;
        for (int i = 0; i < qtdLiv; i++) {
            int ab = 0;
            for (int e = 0; e < qtdEmp; e++) if (empLiv[e] == codLiv[i] && empDev[e] == 0) ab++;
            int calc = totLiv[i] - ab;
            if (calc >= 0 && dispLiv[i] != calc) {
                System.out.println("AVISO livro " + codLiv[i] + ": disponiveis corrigido de " + dispLiv[i] + " para " + calc);
                dispLiv[i] = calc;
            }
        }
    }

    static void carregarLivros() {
        int ok = 0, ign = 0, nl = 1;
        try (BufferedReader br = new BufferedReader(new FileReader("livros.txt", StandardCharsets.UTF_8))) {
            br.readLine();
            for (String l; (l = br.readLine()) != null; ) {
                nl++; if (l.trim().isEmpty()) continue;
                if (qtdLiv >= MAX_LIV) { ign++; continue; }
                try {
                    String[] p = l.split(";");
                    int c = Integer.parseInt(p[0].trim()), cat = Integer.parseInt(p[3].trim()), ano = Integer.parseInt(p[4].trim()), tot = Integer.parseInt(p[5].trim()), disp = Integer.parseInt(p[6].trim());
                    String tit = p[1].trim(), aut = p[2].trim();
                    if (c < 100 || c > 9999 || buscar(codLiv, qtdLiv, c) != -1 || tit.isEmpty() || aut.isEmpty() || cat < 1 || cat > 5 || ano < 1450 || ano > ANO || tot < 1 || tot > 50 || disp < 0 || disp > tot) {
                        System.out.println("livros.txt linha " + nl + " ignorada."); ign++; continue;
                    }
                    codLiv[qtdLiv] = c; titLiv[qtdLiv] = tit; autLiv[qtdLiv] = aut; catLiv[qtdLiv] = cat; anoLiv[qtdLiv] = ano; totLiv[qtdLiv] = tot; dispLiv[qtdLiv++] = disp; ok++;
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) { System.out.println("livros.txt linha " + nl + " ignorada: " + e.getMessage()); ign++; }
            }
            System.out.println("livros.txt: " + ok + " carregado(s), " + ign + " ignorada(s).");
        } catch (FileNotFoundException e) { System.out.println("livros.txt nao encontrado."); } catch (IOException e) { System.out.println("Erro livros.txt: " + e.getMessage()); }
    }

    static void carregarLeitores() {
        int ok = 0, ign = 0, nl = 1;
        try (BufferedReader br = new BufferedReader(new FileReader("leitores.txt", StandardCharsets.UTF_8))) {
            br.readLine();
            for (String l; (l = br.readLine()) != null; ) {
                nl++; if (l.trim().isEmpty()) continue;
                if (qtdLei >= MAX_LEI) { ign++; continue; }
                try {
                    String[] p = l.split(";");
                    int c = Integer.parseInt(p[0].trim()), t = Integer.parseInt(p[2].trim());
                    String nm = p[1].trim();
                    if (c < 1 || c > 9999 || buscar(codLei, qtdLei, c) != -1 || nm.isEmpty() || t < 1 || t > 3) {
                        System.out.println("leitores.txt linha " + nl + " ignorada."); ign++; continue;
                    }
                    codLei[qtdLei] = c; nomeLei[qtdLei] = nm; tipoLei[qtdLei++] = t; ok++;
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) { System.out.println("leitores.txt linha " + nl + " ignorada: " + e.getMessage()); ign++; }
            }
            System.out.println("leitores.txt: " + ok + " carregado(s), " + ign + " ignorada(s).");
        } catch (FileNotFoundException e) { System.out.println("leitores.txt nao encontrado."); } catch (IOException e) { System.out.println("Erro leitores.txt: " + e.getMessage()); }
    }

    static void carregarEmp() {
        int ok = 0, ign = 0, nl = 1;
        try (BufferedReader br = new BufferedReader(new FileReader("emprestimos.txt", StandardCharsets.UTF_8))) {
            br.readLine();
            for (String l; (l = br.readLine()) != null; ) {
                nl++; if (l.trim().isEmpty()) continue;
                if (qtdEmp >= MAX_EMP) { ign++; continue; }
                try {
                    String[] p = l.split(";");
                    int c = Integer.parseInt(p[0].trim()), cl = Integer.parseInt(p[1].trim()), ck = Integer.parseInt(p[2].trim()), d = Integer.parseInt(p[3].trim()), pr = Integer.parseInt(p[4].trim()), dv = Integer.parseInt(p[5].trim());
                    double m = Double.parseDouble(p[6].trim());
                    if (c <= 0 || buscar(codEmp, qtdEmp, c) != -1 || buscar(codLiv, qtdLiv, cl) == -1 || buscar(codLei, qtdLei, ck) == -1 || d < 1 || d > 365 || (dv != 0 && (dv < d || dv > 365)) || m < 0) {
                        System.out.println("emprestimos.txt linha " + nl + " ignorada."); ign++; continue;
                    }
                    codEmp[qtdEmp] = c; empLiv[qtdEmp] = cl; empLei[qtdEmp] = ck; empDia[qtdEmp] = d; empPrev[qtdEmp] = pr; empDev[qtdEmp] = dv; empMulta[qtdEmp++] = m; ok++;
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) { System.out.println("emprestimos.txt linha " + nl + " ignorada: " + e.getMessage()); ign++; }
            }
            System.out.println("emprestimos.txt: " + ok + " carregado(s), " + ign + " ignorada(s).");
        } catch (FileNotFoundException e) { System.out.println("emprestimos.txt nao encontrado."); } catch (IOException e) { System.out.println("Erro emprestimos.txt: " + e.getMessage()); }
    }

    static boolean salvarLivros() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("livros.txt", StandardCharsets.UTF_8))) {
            bw.write("CODIGO;TITULO;AUTOR;CATEGORIA;ANO;TOTAL;DISPONIVEIS"); bw.newLine();
            for (int i = 0; i < qtdLiv; i++) { bw.write(codLiv[i] + ";" + titLiv[i] + ";" + autLiv[i] + ";" + catLiv[i] + ";" + anoLiv[i] + ";" + totLiv[i] + ";" + dispLiv[i]); bw.newLine(); }
            return true;
        } catch (IOException e) { System.out.println("Erro ao salvar livros.txt: " + e.getMessage()); return false; }
    }

    static boolean salvarLeitores() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("leitores.txt", StandardCharsets.UTF_8))) {
            bw.write("CODIGO;NOME;TIPO"); bw.newLine();
            for (int i = 0; i < qtdLei; i++) { bw.write(codLei[i] + ";" + nomeLei[i] + ";" + tipoLei[i]); bw.newLine(); }
            return true;
        } catch (IOException e) { System.out.println("Erro ao salvar leitores.txt: " + e.getMessage()); return false; }
    }

    static boolean salvarEmp() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("emprestimos.txt", StandardCharsets.UTF_8))) {
            bw.write("CODIGO;CODIGO_LIVRO;CODIGO_LEITOR;DIA_EMPRESTIMO;DIA_PREVISTO;DIA_DEVOLUCAO;MULTA"); bw.newLine();
            for (int i = 0; i < qtdEmp; i++) { bw.write(codEmp[i] + ";" + empLiv[i] + ";" + empLei[i] + ";" + empDia[i] + ";" + empPrev[i] + ";" + empDev[i] + ";" + empMulta[i]); bw.newLine(); }
            return true;
        } catch (IOException e) { System.out.println("Erro ao salvar emprestimos.txt: " + e.getMessage()); return false; }
    }
}