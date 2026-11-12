; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; EXERCÍCIO 5 - TESTE DE SHR E SHL

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

ORG 0
JMP INICIO

ORG 20
INICIO: CLO

        MOV DL,16h     ; Valor inicial = 00010110b

        SHR DL,1       ; Desloca 1 bit à direita → divide por 2
        ; DL = 00001011b = 0Bh

        SHR DL,1       ; Desloca novamente → divide por 2 de novo
        ; DL = 00000101b = 05h

        SHL DL,1       ; Desloca à esquerda → multiplica por 2
        ; DL = 00001010b = 0Ah

END
