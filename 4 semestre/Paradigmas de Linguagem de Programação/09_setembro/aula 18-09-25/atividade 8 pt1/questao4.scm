; Atividade 8: Exercícios do Capítulo 3 do Livro Base
; Paradigmas de Linguagens de Programação
; Aula: 18-09-2025

#| 
4) Escreva a definição de funções que, a partir de um parâmetro representando 
a o tempo necessário para fabricar um produto expresso em segundos, 
mostre o expresso em horas, minutos e segundos (dica: utilize as primitivas quotient para extrair a parte inteira de uma divisão 
e remainder para extrair o resto da divisão).
|#

(define (converte-tempo s)
    (let* ((h (quotient s 3600))
            (resto1 (remainder s 3600))
            (m (quotient resto1 60))
            (seg (remainder resto1 60)))
    (list h m seg)))

(display "Converter segundos em horas, minutos e segundos")
(converte-tempo 10000)
