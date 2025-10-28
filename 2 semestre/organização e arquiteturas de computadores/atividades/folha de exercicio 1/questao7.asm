; 7) Operação lógica de 16 bits:

((7732h ^ 88CDh) | (FDCAh & 7235h)) ^ 1111h

ORG 0
JMP INICIO

ORG 20
INICIO:  CLO
; Carrega 7732h em AH:AL  (AH=77h, AL=32h)
        MOV  AH,77h
        MOV  AL,32h
; Carrega 88CDh em BH:BL  (BH=88h, BL=CDh)
        MOV  BH,88h
        MOV  BL,CDh

; Passo 1: (7732 ^ 88CD) por bytes
        XOR  AL,BL        ; AL = 32 ^ CD
        XOR  AH,BH        ; AH = 77 ^ 88

; Carrega FDCAh e 7235h para fazer (&)
        MOV  DL,CAh       ; low  byte de FDCA
        MOV  CL,35h       ; low  byte de 7235
        AND  DL,CL        ; DL = CA & 35

        MOV  DH,FDh       ; high byte de FDCA
        MOV  CH,72h       ; high byte de 7235
        AND  DH,CH        ; DH = FD & 72

; Passo 2: OR com o resultado anterior, por bytes
        OR   AL,DL        ; low  byte
        OR   AH,DH        ; high byte

; Passo 3: XOR com 1111h -> (11h high, 11h low)
        XOR  AL,11h       ; low  byte ^ 11h
        XOR  AH,11h       ; high byte ^ 11h

; Resultado final:
; AH = byte alto, AL = byte baixo do valor de 16 bits
END