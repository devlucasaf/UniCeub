; Atividade 8: Exercícios do Capítulo 3 do Livro Base
; Paradigmas de Linguagens de Programação
; Aula: 18-09-2025

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
