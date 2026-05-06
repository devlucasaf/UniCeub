; Atividade 8: Exercícios do Capítulo 3 do Livro Base
; Paradigmas de Linguagens de Programação
; Aula: 18-09-2025

; 1)  Escreva a definição de uma função que, a partir de dois números, escreva qual é o maior?

(define (maior x y)
    (if (> x y) x y))

(display "O maior número é:")
(maior 10 25)
