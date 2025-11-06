; Atividade 8: Exercícios do Capítulo 3 do Livro Base
; Paradigmas de Linguagens de Programação
; Aula: 18-09-2025

; 5) Escreva a definição de uma função que conte, a partir do número informado como parâmetro, até chegar a zero. 
; Enquanto não chegar a zero, mostrar o número correspondente. Quando a contagem chegar a zero, mostrar a mensagem "Encerrado"

(define (conta n)
    (if (= n 0)
        (display "Encerrado")
        (begin
        (display n) (newline)
        (conta (- n 1)))))

(display "Contagem regressiva até zero:")
(conta 10)
