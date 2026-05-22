#|
Leia o capítulo da seção 2.1 à seção 2.5 do livro:
https://www.scheme.com/tspl4/start.html#./start:h5
Paradigmas de Linguagens de Programação
Aula: 18-09-2025
|#

#|
2.2.1) Convert the following arithmetic expressions into Scheme expressions and 
evaluate them.

    a.	1.2 × (2 - 1/3) + -8.7
    b.	(2/3 + 4/9) ÷ (5/11 - 4/3)
    c.	1 + 1 ÷ (2 + 1 ÷ (1 + 1/2))
    d.	1 × -2 × 3 × -4 × 5 × -6 × 7

|#

; a. 
(display "a.")
(+ (* 1.2 (- 2 (/ 1 3))) -8.7)

; b.
(display "b.")
(/ (+ (/ 2 3) (/ 4 9)) (- (/ 5 11) (/ 4 3)))

; c.
(display "c.")
(+ 1 (/ 1 (+ 2 (/ 1 (+ 1 (/ 1 2))))))

;d.
(display "d.")
