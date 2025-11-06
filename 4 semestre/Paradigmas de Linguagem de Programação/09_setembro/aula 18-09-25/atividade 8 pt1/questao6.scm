; Atividade 8: Exercícios do Capítulo 3 do Livro Base
; Paradigmas de Linguagens de Programação
; Aula: 18-09-2025

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
