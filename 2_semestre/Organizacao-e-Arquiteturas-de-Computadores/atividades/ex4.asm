; ----------------------------------------

; EXERCÍCIO TEÓRICO 4 - MicSim

; ----------------------------------------

ORG 0
    JMP INICIO       ; C0 20

ORG 10
    DB 54            ; 'T'
    DB 55            ; 'U'
    DB 52            ; 'R'
    DB 4D            ; 'M'
    DB 41            ; 'A'
    DB 20            ; ' '
    DB 44            ; 'D'
    DB 41            ; 'A'
    DB 20            ; ' '
    DB 58            ; 'X'
    DB 55            ; 'U'
    DB 58            ; 'X'
    DB 41            ; 'A'

ORG 20
INICIO: 
    MOV AL, [02A0h]  ; D0 02 A0
    MOV AL, [030Dh]  ; D0 03 0D
    MOV AL, [0110h]  ; D0 01 10
PROX:
    CMP [0090h], 01h ; D3 00 01
    MOV DL, [9002h]  ; D2 90 02
    CMP AL, [0020h]  ; BB 00 20
    MOV [0290h], AL  ; D1 02 90
    ADD [0200h], AL  ; D4 02 00
    INC [02h]        ; A4 02
    INC [01h]        ; A4 01
    DEC [03h]        ; A5 03
    JNZ PROX         ; C2 EB
    JMP FIM          ; C0 02
FIM:
    END
