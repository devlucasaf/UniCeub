; Atividade 8: Exercícios do Capítulo 3 do Livro Base
; Paradigmas de Linguagens de Programação
; Aula: 18-09-2025

; 8) Escreva a definição de uma função que, a partir de um parâmetro numérico representando o salário bruto de uma pessoa, 
; calcule o valor a ser pago referente ao INSS, de acordo com as seguintes faixas de valores: até R$1200,00 2% do salário bruto, acima de R$1200,00 até R$2500,00 5% do salário bruto e acim de R$2500,00 8% do salário bruto 
; (dica: utilize a primitiva cond).

(define (inss salario)
    (cond ((<= salario 1200) (* salario 0.02))
        ((<= salario 2500) (* salario 0.05))
        (else (* salario 0.08))))

(display "INSS conforme faixas salariais")
(inss 15400)
