; Atividade 8: Exercícios do Capítulo 3 do Livro Base
; Paradigmas de Linguagens de Programação
; Aula: 18-09-2025

; 2) Escreva a definição de uma função que, a partir de dois números, escreva se são ou não múltiplos?

(define (multiplos? x y)
    (or (= (remainder x y) 0)
        (= (remainder y x) 0)))

(display "Os números são ou não múltiplos:")
(display "Números: 15 e 25")
(multiplos? 15 25)
(display "Números: 2 e 6")
(multiplos? 2 6)
