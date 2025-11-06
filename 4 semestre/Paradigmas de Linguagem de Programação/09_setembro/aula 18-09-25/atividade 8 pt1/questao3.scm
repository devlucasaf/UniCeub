#| 
Atividade 8: Exercícios do Capítulo 3 do Livro Base
Paradigmas de Linguagens de Programação
Aula: 18-09-2025
|#

#| 
3) Um supermercado deseja reajustar os preços de seus produtos, para mais ou para menos, 
de acordo com os critérios mostrados na tabela abaixo. Escrever uma definição de 
função que, a partir dos valores do preço atual e da venda média mensal do produto, 
calcule qual o preço reajustado?
|#

(define (reajuste preco venda)
    (cond ((> venda 1000) (* preco 1.10)) 
        ((< venda 500) (* preco 0.95))  
        (else preco)))

(display "Preço reajustado do mercado:")
(reajuste 100 541)
