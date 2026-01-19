#|
Leia o capítulo da seção 2.1 à seção 2.5 do livro:
https://www.scheme.com/tspl4/start.html#./start:h5
Paradigmas de Linguagens de Programação
Aula: 18-09-2025
|#

;2.4.1. Rewrite the following expressions, using let to remove common subexpressions and to improve the structure of the code. Do not perform any algebraic simplifications.

; a.	(+ (- (* 3 a) b) (+ (* 3 a) b))
; b.	(cons (car (list a b c)) (cdr (list a b c)))

(define a 2)
(define b 5)
(define c 9)
; a. 
(display "a.")
(let ((triplo-a (* 3 a)))       
    (+ (- triplo-a b)             
        (+ triplo-a b)))           

;b.
(display "b.")
(let ((lista (list a b c)))     
    (cons (car lista)             
        (cdr lista)))  
