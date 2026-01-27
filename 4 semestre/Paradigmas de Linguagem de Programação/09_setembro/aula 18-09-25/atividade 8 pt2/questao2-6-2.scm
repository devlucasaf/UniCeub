#|
QUESTÃO RESOLVIDA A FINS DE ESTUDOS
Leia o capítulo da seção 2.1 à seção 2.5 do livro:
https://www.scheme.com/tspl4/start.html#./start:h5
Paradigmas de Linguagens de Programação
Aula: 18-09-2025
|#

;; Exercise 2.6.1
; What would happen if you were to type

; (double-any double-any double-any)

; given the definition of double-any from the beginning of this section?

; Definição de compose
(define (compose p1 p2)
    ((lambda (x) p1 (p2 x))))

; Usando compose para definir cadr e cddr
(define cadr (compose car cdr))
(define cddr (compose car cdr))
