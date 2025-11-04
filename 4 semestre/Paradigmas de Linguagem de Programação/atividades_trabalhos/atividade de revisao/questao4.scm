; Paradigmas de Linguagem de Programação
; Atividade de Revisão

; Questão 4. Escreva 3 funções usando o paradigma funcional. E avalie as suas funções com alguns valores de entrada.

; Função 1:

;; Função que dobra cada elemento de uma lista
(define (dobrar-lista lista)
    (map (lambda (x) (* 2 x)) lista))

;; Avaliação
(display "1️⃣ Dobrar lista: ")
(display (dobrar-lista '(1 2 3 4 5)))
(newline)

; Função 2:

;; Função que retorna apenas os números pares
(define (filtrar-pares lista)
    (filter (lambda (x) (= (modulo x 2) 0)) lista))

;; Avaliação
(display "2️⃣ Filtrar pares: ")
(display (filtrar-pares '(10 15 22 33 40 55)))
(newline)

; Função 3:

;; Função que calcula o produto de todos os elementos da lista
(define (produto-lista lista)
  (apply * lista))

;; Avaliação
(display "3️⃣ Produto da lista: ")
(display (produto-lista '(2 3 4)))
(newline)

