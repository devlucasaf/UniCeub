; Paradigmas de Linguagens de Programação
; Atividade 7 - Utilizar a linguagem Scheme para definir as seguintes funções
; Aula: 11-09-2025

;8) Soma dos n primeiros números naturais:

(define (soma-naturais n)
    (if (= n 0)
        0
        (+ n (soma-naturais (- n 1)))))

(display "Soma dos n primeiros números naturais:")
(soma-naturais 3)
