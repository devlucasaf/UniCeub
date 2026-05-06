#|
QUESTÃO RESOLVIDA A FINS DE ESTUDOS
Leia o capítulo da seção 2.1 à seção 2.5 do livro:
https://www.scheme.com/tspl4/start.html#./start:h5
Paradigmas de Linguagens de Programação
Aula: 18-09-2025
|#

;; Definições com compose
(define caar  (compose car car))
(define cdar  (compose cdr car))
(define caaar (compose car caar))
(define caadr (compose car cadr))
(define cadar (compose car cdar))
(define cdaar (compose cdr caar))
(define cdadr (compose cdr cadr))
(define cddar (compose cdr cdar))
(define cdddr (compose cdr cddr))

(define lst '( (1 2) (3 4) (5 6) ))

(caar lst)   
(cdar lst)   
(caadr lst)  
(cddar lst)  
