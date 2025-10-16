; Atividade 8: Exercícios do Capítulo 3 do Livro Base
; Paradigmas de Linguagens de Programação
; Aula: 18-09-2025

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 1)  Escreva a definição de uma função que, a partir de dois números, escreva qual é o maior?

(define (maior x y)
  (if (> x y) x y))

(display "O maior número é:")
(maior 10 25)

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 2) Escreva a definição de uma função que, a partir de dois números, escreva se são ou não múltiplos?

(define (multiplos? x y)
  (or (= (remainder x y) 0)
      (= (remainder y x) 0)))

(display "Os números são ou não múltiplos:")
(display "Números: 15 e 25")
(multiplos? 15 25)
(display "Números: 2 e 6")
(multiplos? 2 6)

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 3) Um supermercado deseja reajustar os preços de seus produtos, para mais ou para menos, de acordo com os critérios mostrados na tabela abaixo. 
;Escrever uma definição de função que, a partir dos valores do preço atual e da venda média mensal do produto, calcule qual o preço reajustado?

(define (reajuste preco venda)
  (cond ((> venda 1000) (* preco 1.10)) 
        ((< venda 500) (* preco 0.95))  
        (else preco)))

(display "Preço reajustado do mercado:")
(reajuste 100 541)

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 4) Escreva a definição de funções que, a partir de um parâmetro representando a o tempo necessário para fabricar um produto 
; expresso em segundos, mostre o expresso em horas, minutos e segundos 
; (dica: utilize as primitivas quotient para extrair a parte inteira de uma divisão e remainder para extrair o resto da divisão).

(define (converte-tempo s)
  (let* ((h (quotient s 3600))
         (resto1 (remainder s 3600))
         (m (quotient resto1 60))
         (seg (remainder resto1 60)))
    (list h m seg)))

(display "Converter segundos em horas, minutos e segundos")
(converte-tempo 10000)

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 5) Escreva a definição de uma função que conte, a partir do número informado como parâmetro, até chegar a zero. 
; Enquanto não chegar a zero, mostrar o número correspondente. Quando a contagem chegar a zero, mostrar a mensagem "Encerrado"

(define (conta n)
  (if (= n 0)
      (display "Encerrado")
      (begin
        (display n) (newline)
        (conta (- n 1)))))

(display "Contagem regressiva até zero:")
(conta 10)

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 6) Escreva a definição de funções que, a partir de um parâmetro representando a idade de uma pessoa expressa em dias, 
; mostre-a expressa em anos, meses e dias (dica: utilize as primitivas quotient para extrair a parte inteira de uma divisão e remainder para extrair o resto da divisão).

(define (idade-em-anos dias)
  (let* ((anos (quotient dias 365))
         (resto1 (remainder dias 365))
         (meses (quotient resto1 30))
         (dias-resto (remainder resto1 30)))
    (list anos meses dias-resto)))

(display "Converter idade em dias → anos, meses e dias")
(idade-em-anos 7186)

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 7) Escreva a definição de uma função que, a partir de três parâmetros (dois números e um caracter representando a operação aritmética desejada + - * /) 
; calcula o resultado da operação aritmética (dica: utilize a primitiva cond).

(define (calc x y op)
  (cond ((eq? op '+) (+ x y))
        ((eq? op '-) (- x y))
        ((eq? op '*) (* x y))
        ((eq? op '/) (/ x y))
        (else "Operação inválida")))

(display "Calculadora básica com cond")
(display (calc 10 80 '+))

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 8) Escreva a definição de uma função que, a partir de um parâmetro numérico representando o salário bruto de uma pessoa, 
; calcule o valor a ser pago referente ao INSS, de acordo com as seguintes faixas de valores: até R$1200,00 2% do salário bruto, acima de R$1200,00 até R$2500,00 5% do salário bruto e acim de R$2500,00 8% do salário bruto 
; (dica: utilize a primitiva cond).

(define (inss salario)
  (cond ((<= salario 1200) (* salario 0.02))
        ((<= salario 2500) (* salario 0.05))
        (else (* salario 0.08))))

(display "INSS conforme faixas salariais")
(inss 15400)

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 9) Escreva a definição de uma função que calcule o fatorial de um número.

(define (fatorial n)
  (if (= n 0)
      1
      (* n (fatorial (- n 1)))))

(display "Fatorial")
(fatorial 8)
