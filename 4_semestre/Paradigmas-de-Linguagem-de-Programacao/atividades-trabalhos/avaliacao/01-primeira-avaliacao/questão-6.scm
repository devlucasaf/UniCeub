; Paradigmas de Linguagens de Programação
; Avaliação 1
; Data: 02-10-25

; Questão 6

; 6.1. calcule o n-ésimo número de Fibonacci F(n). Dê um exemplo de chamada.

(define (fibonacci n)
  (if (< n 2)
      n
      (+ (fibonacci (- n 1)) (fibonacci (- n 2)))))

(fibonacci 10)

; 6.2. calcule a área de um círculo. Dê um exemplo de chamada.

(define (area-circulo r)
  (* pi (* r r)))

(area-circulo 5)