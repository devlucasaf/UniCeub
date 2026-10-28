; Paradigmas de Linguagem de Programação
; Atividade de Revisão

; Questão 8

; 8.1 - Crie a função fatorial coma linguagem Scheme

;; Função recursiva para calcular o fatorial de n
(define (fatorial n)
    (if (<= n 1)
        1
        (* n (fatorial (- n 1)))))

;; Avaliações de exemplo:
(display "8.1️⃣ Fatorial\n")
(display "(fatorial 0) = ") (display (fatorial 0)) (newline)
(display "(fatorial 5) = ") (display (fatorial 5)) (newline)
(display "(fatorial 7) = ") (display (fatorial 7)) (newline)


; 8.2. Crie a função que retorna a sequência de Fibonacci usando a linguagem Scheme.

;; Função recursiva que retorna o n-ésimo número de Fibonacci
(define (fibonacci n)
    (if (<= n 1)
        n
        (+ (fibonacci (- n 1)) (fibonacci (- n 2)))))

;; Avaliações de exemplo:
(display "\n8.2️⃣ Fibonacci\n")
(display "(fibonacci 0) = ") (display (fibonacci 0)) (newline)
(display "(fibonacci 1) = ") (display (fibonacci 1)) (newline)
(display "(fibonacci 6) = ") (display (fibonacci 6)) (newline)
(display "(fibonacci 10) = ") (display (fibonacci 10)) (newline)
