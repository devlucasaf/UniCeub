; Paradigmas de Linguagens de Programação
; Aula: 11-09-2025

;1) O comprimento de um círculo em função do raio:
(define (comprimento-circulo r)
  (* 2 pi r))

(display "Comprimento do círculo de raio 10:")
(comprimento-circulo 10)

;2) A área de um círculo em função do raio:

(define (area-circulo r)
  (* pi r r))
(display "Área do círculo em função do raio 5:")
(area-circulo 5)

;3) A área de uma esfera em função do raio:

(define (area-esfera r)
  (* 4 pi r r))

(display "Área da esfera em função do raio 16")
(area-esfera 16)

;4) O volume de uma esfera em função do raio
(define (volume-esfera r)
  (* (/ 4 3) pi r r r))

(display "Volume da esfera em função do raio 22")
(volume-esfera 22)

;5) Soma de dois número reais:

(define (soma a b)
  (+ a b))
(display "Soma de dois números reais:")

(soma 49 23)

; 6) Fatorial de um número natural:

(define (fatorial n) (if (= n 0)1 (* n (fatorial (- n 1)))))
(display "Fatorial de um número natural 6")
(fatorial 6)

;7) Sequência de Fibonacci:
(define (fibonacci n)
  (if (<= n 1)
      n
      (+ (fibonacci (- n 1)) (fibonacci (- n 2)))))

(display "Sequência de Fibonacci 10:")
(fibonacci 10)

;8) Soma dos n primeiros números naturais:

(define (soma-naturais n)
  (if (= n 0)
      0
      (+ n (soma-naturais (- n 1)))))

(display "Soma dos n primeiros números naturais:")
(soma-naturais 3)

;9) Soma dos quadrados de dois números reais:

(define (soma-quadrados a b)
  (+ (* a a) (* b b)))

(display "Soma dos quadrados de 2 números reais:")
(soma-quadrados 15 10)
