; =========================
; MicSim Assembly - Solução
; =========================

        ORG 0000h
        JMP     INICIO          ; (linha 2) desvia para o código em 20h

; -------------------------
; Dados ASCII (linhas 4–16)
; -------------------------
        ORG 0010h
MSG:    DB      'TURMA DA XUXA' ; 54 55 52 4D 41 20 44 41 20 58 55 58 41

; -----------------------------------------
; Código (linhas 18–30) – endereço 20h+
; -----------------------------------------
        ORG 0020h

INICIO:
        ; linhas 18–20
        MOV     AL, M[02]       ; 18: AL <- M[02]
        MOV     BL, M[03]       ; 19: BL <- M[03]
        MOV     CL, M[01]       ; 20: CL <- M[01]

PROX:                           ; 21–29 (laço)
        MOV     M[00], AL       ; 21: escreve AL em M[00]
        ADD     AL, M[02]       ; 22: AL <- AL + M[02]
        CMP     AL, M[20]       ; 23: compara AL com M[20] (ajusta flags)
        MOV     M[02], AL       ; 24: M[02] <- AL
        SUB     AL, BL          ; 25: AL <- AL - BL
        INC     AL              ; 26: AL++
        INC     BL              ; 27: BL++
        DEC     CL              ; 28: CL--
        JNZ     PROX            ; 29: enquanto CL != 0, repete

FIM:
        JMP     FIM             ; 30: laço infinito (fim do programa)

        END
