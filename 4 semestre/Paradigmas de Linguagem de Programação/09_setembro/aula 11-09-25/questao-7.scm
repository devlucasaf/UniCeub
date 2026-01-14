; Paradigmas de Linguagens de Programação
; Atividade 7 - Utilizar a linguagem Scheme para definir as seguintes funções
; Aula: 11-09-2025

;7) Sequência de Fibonacci:
(define (fibonacci n)
    (if (<= n 1)
        n
        (+ (fibonacci (- n 1)) (fibonacci (- n 2)))))

(display "Sequência de Fibonacci 10:")
(fibonacci 10)
