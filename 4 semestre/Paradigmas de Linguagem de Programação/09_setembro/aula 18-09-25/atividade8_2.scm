; Paradigmas de Linguagens de Programação
; Aula: 18-09-2025


; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 2.2.1) Convert the following arithmetic expressions into Scheme expressions and evaluate them.

; a.	1.2 × (2 - 1/3) + -8.7
; b.	(2/3 + 4/9) ÷ (5/11 - 4/3)
; c.	1 + 1 ÷ (2 + 1 ÷ (1 + 1/2))
; d.	1 × -2 × 3 × -4 × 5 × -6 × 7

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

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

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

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

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
(car (cdr (car (cdr '((a b) (c d))))))

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 2.3.1) Write down the steps necessary to evaluate the expression below.

((car (cdr (list + - * /))) 17 5)

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

2.4.1. Rewrite the following expressions, using let to remove common subexpressions and to improve the structure of the code. Do not perform any algebraic simplifications.

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

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 2.4.2. Determine the value of the following expression. Explain how you derived this value.

(let ([x 9])
  (* x
    (let ([x (/ x 3)])
      (+ x x))))  

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; 2.5.1.Determine the values of the expressions below.

; a.
(display "a.")
(let ([f (lambda (x) x)])
  (f 'a))
