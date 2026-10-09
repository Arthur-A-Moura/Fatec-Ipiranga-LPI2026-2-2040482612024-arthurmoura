# Especificação Técnica - Sistema de Biblioteca

**Estudante:** Arthur Alencar De Sousa Moura  
**RA:** 2040482612024  
**Curso:** ADS - FATEC Ipiranga (2º Semestre)  
**Disciplina:** Linguagem de Programação I  

---

## 1. Objetivo do Sistema
O sistema tem como objetivo gerenciar o acervo de uma biblioteca, permitindo o controle de livros, autores e a movimentação de empréstimos mensais. O projeto segue o modelo incremental proposto para a disciplina de LPI.

## 2. Requisitos Funcionais
- **Cadastro de Livros:** Registro de Título, Autor, Ano e ISBN.
- **Consulta:** Busca de obras por título para verificar disponibilidade.
- **Relatório Matricial (E2):** Controle de frequência de empréstimos por livro ao longo dos meses.
- **Gestão de Atrasos (E3):** Cálculo automático de multas (R$ 2,00/dia) para devoluções fora do prazo.

## 3. Estrutura de Dados (Planejamento)
Para atender aos requisitos da FATEC, o sistema utilizará:
- **Vetores (Arrays):** Para armazenar títulos, autores e ISBNs (Cadeias de caracteres).
- **Matrizes:** Para armazenar a contagem de empréstimos (Linhas = Livros / Colunas = Meses).
- **Modularização:** Métodos específicos para validação de ISBN e busca binária/linear.

## 4. Regras de Negócio e Validações
1. **ISBN:** Deve conter exatamente 13 dígitos numéricos.
2. **Ano:** Não são aceitos anos futuros (limite: 2026).
3. **Status:** O livro alterna entre "Disponível" e "Emprestado".
4. **Multa:** Aplicada apenas se os dias de atraso forem maiores que zero.

