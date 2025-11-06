#|
Leia o capítulo da seção 2.1 à seção 2.5 do livro:
https://www.scheme.com/tspl4/start.html#./start:h5
Paradigmas de Linguagens de Programação
Aula: 18-09-2025
|#

; 2.2.4) (car (car '((a b) (c d)))) yields a. Determine which compositions of car and cdr applied to ((a b) (c d)) yield b, c, and d.

; Para obter a.

(display "-->a")
(car (car '((a b) (c d)))) 

; Para obter b.

(display "-->b")
(car (cdr (car '((a b) (c d)))))

; Para obter c.

(display "-->c")
(car (car (cdr '((a b) (c d)))))

; Para obter d.

(display "-->d")
(car 
(cdr (car (cdr '((a b) (c d))))))
