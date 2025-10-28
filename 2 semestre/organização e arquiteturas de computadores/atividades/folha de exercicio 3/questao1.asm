; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; EXERCÍCIO 1 - LER TECLA E ARMAZENAR EM 90h

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

ORG 0
JMP INICIO

ORG 20
INICIO: CLO

        IN  AL,00h      ; Lê o valor da tecla (porta 00)
        MOV [90h],AL    ; Armazena o valor no endereço 90h

FIM:     JMP FIM         ; Loop infinito
END