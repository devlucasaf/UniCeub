#|
Leia o capítulo da seção 2.1 à seção 2.5 do livro:
https://www.scheme.com/tspl4/start.html#./start:h5
Paradigmas de Linguagens de Programação
Aula: 18-09-2025
|#

; 2.4.2. Determine the value of the following expression. Explain how you derived this value.

(let ([x 9])
    (* x
        (let ([x (/ x 3)])
            (+ x x))))  
