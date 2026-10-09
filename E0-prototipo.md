# E0 — Prototipação do Sistema de Gestão de Frota e Entregas

**Aluno:** Arthur Alencar De Sousa Moura 
**RA:** 2040482612024
**Disciplina:** Linguagem de Programação I — 2026 2  
**Tema 5:** Sistema de Biblioteca

## 1. Mapa do Menu Principal

O programa apresentará o menu abaixo. Após executar uma opção, retornará ao menu principal até que o usuário selecione `0 - Sair`.

```text
====================================================
          SISTEMA DE GESTÃO DE BIBLIOTECA
====================================================
1 - Cadastrar livro
2 - Listar acervo completo
3 - Buscar livro por título ou autor
4 - Registrar empréstimo de livro
5 - Registrar devolução de livro
6 - Relatório de empréstimos por categoria (E2)
7 - Relatório de livros com devolução atrasada (E3)
8 - Salvar acervo em arquivo (E5)
0 - Sair
====================================================
Escolha uma opção: _
```

**Fluxo:** Menu principal → Seleção da opção → Entrada de dados ou exibição do relatório → Retorno ao menu principal. A opção 0 encerra o sistema.
## PROTOTIPAGEM DO CONSOLE (Mockups):
## 2. Formulário simulado de cadastro de livro
```text
====================================================
                 CADASTRAR LIVRO
====================================================
Título: As Leis Dinâmicas da Prosperidade
Autor: Catherine Ponder
Ano de Publicação: 2011
Categoria: Desenvolvimento Pessoal
----------------------------------------------------
Livro cadastrado com sucesso!
```
### Relatório matricial de empréstimos por livro/mês (E2)

```text
==================================================================
                 RELATÓRIO MENSAL DE EMPRÉSTIMOS
==================================================================
TÍTULO                     AUTOR            JAN   FEV   MAR   TOTAL
------------------------------------------------------------------
As Leis Dinâmicas...       C. Ponder         5     8     4      17
Atitude Mental Positiva    Napoleon Hill     7     6     9      22
Quem Pensa Enriquece       Napoleon Hill     4     5     5      14
------------------------------------------------------------------
TOTAL POR MÊS                               16    19    18      53
==================================================================
Total de movimentações no período: 53 empréstimos
```

### Relatório de livros com devolução atrasada (E3)

```text
==================================================================
                 RELATÓRIO MENSAL DE QUILOMETRAGEM
==================================================================
PLACA      MODELO           JAN     FEV     MAR     TOTAL (km)
------------------------------------------------------------------
ABC1D23    Fiat Fiorino    1200    1500    1100       3800
DEF4G56    VW Saveiro       900    1300    1400       3600
GHI7J89    Fiat Strada     1800    1600    1700       5100
------------------------------------------------------------------
TOTAL POR MÊS             3900    4400    4200      12500
==================================================================
Total percorrido pela frota no período: 12500 km
```

A matriz é organizada com **linhas representando os veículos** e **colunas representando os meses**. O relatório apresenta a soma por veículo, por mês e o total do período.

### Regras de Validação de Entrada

```text
==================================================================
                    RELATÓRIO DE EXCESSO DE KM
==================================================================
Limite adotado para o período: 5000 km

PLACA      MODELO           KM NO PERÍODO    SITUAÇÃO
------------------------------------------------------------------
ABC1D23    Fiat Fiorino             3800     Normal
DEF4G56    VW Saveiro               3600     Normal
GHI7J89    Fiat Strada              5100     Excedido
------------------------------------------------------------------
Veículos acima do limite: 1
GHI7J89 - Fiat Strada: excesso de 100 km
==================================================================
```

**Critério do exemplo:** o veículo é sinalizado quando percorre **mais de 5000 km no período**. Esse limite é uma regra proposta para a prototipagem, não um valor fixado no enunciado.

## 3. Regras de Validação de Entrada

| Entrada | Regra de validação | Resposta a valor inválido |
|---|---|---|
| ISBN | Campo obrigatório; deve conter 13 dígitos numéricos; não permitir duplicidade no cadastro. | Exibir `ISBN inválido ou já cadastrado.` |
| Título | Texto não vazio e não composto apenas por espaços | Exibir `Informe o título do livro.` |
| Ano de Publicação |Número inteiro; não pode ser maior que o ano atual (2026). | Exibir `Ano de publicação inválido.` |
| Status | Apenas `Disponível, Emprestado ou Reservado` | Exibir `Status inválido.` |
| Busca por Título | O título precisa existir nos registros para consulta ou empréstimo. | Exibir `Livro não encontrado.` |
| Opção do menu | Apenas as opções `0` a `8`. | Exibir `Opção inválida.` e solicitar nova escolha. |

Para os testes de validação, utilizarei obras como "As Leis Dinâmicas da Prosperidade" (Catherine Ponder) e "Atitude Mental Positiva" (Napoleon Hill). Já li esses livros e recomendo muito, pois tratam de temas fundamentais como mentalidade e sucesso.

## 4. Assinaturas dos Métodos (Planejamento da E4)

As assinaturas abaixo representam métodos **planejados para implementação futura**, com os respectivos parâmetros e retornos.

### 4.1. Validar placa

```java
static boolean validarISBN(String isbn)
```

- **Parâmetro:** `isbn` — texto informado pelo usuário.
- **Retorno:** `true` para formato válido; (13 dígitos); `false` para inválido.

### 4.2. Buscar veículo por placa

```java
static int buscarLivro(String[] titulos, String tituloBuscado)
```

- **Parâmetros:** vetor com os títulos cadastrados e o título pesquisado.
- **Retorno:** índice do livro no vetor ou -1 se não for encontrado.

### 4.3. Gerar relatório mensal

```java
static void gerarRelatorioMensal(String[] titulos, String[] autores, int[][] emprestimosMensais)
```

- **Parâmetros:** vetores de títulos e autores e matriz de quantidade de empréstimos por livro/mês.
- **Retorno:** `void` — imprime o relatório no console, sem devolver valor.

### 4.4. Identificar excesso de quilometragem

```java
static void gerarRelatorioAtrasos(String[] titulos, String[] autores, int[] diasAtraso, double valorMultaDiaria)
```

- **Parâmetros:**  vetores de títulos e autores, vetor com dias de atraso e o valor da multa por dia.
- **Retorno:** `void` — imprime os livros que estão com a devolução pendente e o valor da multa.
### 4.5. Salvar histórico

```java
static void salvarAcervo(String nomeArquivo, String[] titulos, String[] autores, String[] isbns)
```

- **Parâmetros:** nome do arquivo e vetores com os dados dos livros.
- **Retorno:** `void` — planejado para gravar os dados do acervo em arquivo na E5.


