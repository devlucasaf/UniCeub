; Paradigmas de Linguagem de Programação
; Avaliação 1
; Data: 02-10-2025
; Questão 4. Selecione a alternativa que é a saída do código Scheme abaixo

(define (funcao lista)
    (if (null? lista)
        ()
        (append (funcao (cdr lista))
                (list (car lista)))))

(funcao (1 2 3 4 5))
