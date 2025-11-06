; Atividade 8: Exercícios do Capítulo 3 do Livro Base
; Paradigmas de Linguagens de Programação
; Aula: 18-09-2025

; 9) Escreva a definição de uma função que calcule o fatorial de um número.

(define (fatorial n)
  (if (= n 0)
      1
      (* n (fatorial (- n 1)))))

(display "Fatorial")
(fatorial 8)
