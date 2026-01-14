; Paradigmas de Linguagens de Programação
; Atividade 7 - Utilizar a linguagem Scheme para definir as seguintes funções
; Aula: 11-09-2025

; 6) Fatorial de um número natural:

(define (fatorial n) (if (= n 0)1 (* n (fatorial (- n 1)))))
(display "Fatorial de um número natural 6")
(fatorial 6)
