; Paradigmas de Linguagens de Programação
; Atividade 7 - Utilizar a linguagem Scheme para definir as seguintes funções
; Aula: 11-09-2025

;2) A área de um círculo em função do raio:

(define (area-circulo r)
    (* pi r r))
(display "Área do círculo em função do raio 5:")
(area-circulo 5)
