; Paradigmas de Linguagens de Programação
; Atividade 7 - Utilizar a linguagem Scheme para definir as seguintes funções
; Aula: 11-09-2025

;4) O volume de uma esfera em função do raio
(define (volume-esfera r)
    (* (/ 4 3) pi r r r))

(display "Volume da esfera em função do raio 22")
(volume-esfera 22)

