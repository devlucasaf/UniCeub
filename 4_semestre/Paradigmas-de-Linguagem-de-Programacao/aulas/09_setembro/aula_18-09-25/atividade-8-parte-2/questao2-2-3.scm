#|
Leia o capítulo da seção 2.1 à seção 2.5 do livro:
https://www.scheme.com/tspl4/start.html#./start:h5
Paradigmas de Linguagens de Programação
Aula: 18-09-2025
|#

; 2.2.3) Determine the values of the following expressions. Use your Scheme system to verify your answers.

;a.	
(display "a.")
(cons 'car 'cdr)

;b.
(display "b.")
(list 'this '(is silly))

;c.
(display "c.")
(cons 'is '(this silly?))

;d.
(display "d.")
(quote (+ 2 3))

;e.
(display "e.")
(cons '+ '(2 3))

;f.	
(display "f.")
(car '(+ 2 3))

;g.
(display "g.")
(cdr '(+ 2 3))

;h.
(display "h.")
cons

;i.
(display "i.")
(quote cons)

;j.
(display "j.")
(quote (quote cons))

;k.
(display "k.")
(car (quote (quote cons)))

;l.
(display "l.")
(+ 2 3)

;m.
(display "m.")
(+ '2 '3)

;n.
(display "n.")
(+ (car '(2 3)) (car (cdr '(2 3))))

;o.
(display "o.")
((car (list + - * /)) 2 3)
