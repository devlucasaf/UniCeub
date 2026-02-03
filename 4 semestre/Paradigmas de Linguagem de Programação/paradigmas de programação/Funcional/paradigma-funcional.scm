;; Demonstração de programação funcional em Scheme
;; Paradigma funcional: funções como valores, recursão, composição, imutabilidade

;; Definição de funções matemáticas simples
(define (quadrado x)
  (* x x))

(define (cubo x)
  (* x x x))

;; Função de ordem superior: aplica uma função a cada elemento de uma lista
(define (mapear f lista)
  (if (null? lista)
      '()
      (cons (f (car lista)) (mapear f (cdr lista)))))

;; Função de ordem superior: filtra elementos de uma lista
(define (filtrar pred lista)
  (cond
    ((null? lista) '())
    ((pred (car lista)) (cons (car lista) (filtrar pred (cdr lista))))
    (else (filtrar pred (cdr lista)))))

;; Função de ordem superior: reduz uma lista a um valor
(define (reduzir f inicial lista)
  (if (null? lista)
      inicial
      (reduzir f (f inicial (car lista)) (cdr lista))))

;; Exemplo de composição de funções
(define (compor f g)
    (lambda (x) (f (g x))))

;; Funções compostas
(define quadrado-do-cubo (compor quadrado cubo))
(define cubo-do-quadrado (compor cubo quadrado))

;; Função recursiva: soma de elementos de uma lista
(define (somar lista)
    (if (null? lista)
        0
        (+ (car lista) (somar (cdr lista)))))

;; Função recursiva: calcular fatorial
(define (fatorial n)
    (if (= n 0)
        1
        (* n (fatorial (- n 1)))))

;; Função recursiva: calcular Fibonacci
(define (fibonacci n)
    (if (< n 2)
        n
        (+ (fibonacci (- n 1)) (fibonacci (- n 2)))))

;; Demonstração prática
(define numeros '(1 2 3 4 5 6 7 8 9 10))

(display "Lista original: ") (display numeros) (newline)

(display "Quadrados: ") (display (mapear quadrado numeros)) (newline)

(display "Cubos: ") (display (mapear cubo numeros)) (newline)

(display "Números pares: ") (display (filtrar even? numeros)) (newline)

(display "Soma dos números: ") (display (somar numeros)) (newline)

(display "Produto dos números (reduzir): ")
(display (reduzir * 1 numeros)) (newline)

(display "Quadrado do cubo de 2: ")
(display ((quadrado-do-cubo) 2)) (newline)

(display "Cubo do quadrado de 2: ")
(display ((cubo-do-quadrado) 2)) (newline)

(display "Fatorial de 5: ") (display (fatorial 5)) (newline)

(display "Fibonacci de 10: ") (display (fibonacci 10)) (newline)
