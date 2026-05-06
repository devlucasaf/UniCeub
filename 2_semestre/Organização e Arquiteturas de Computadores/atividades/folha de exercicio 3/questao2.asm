
; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; EXERCÍCIO 2 - ACEITAR SOMENTE LETRAS MINÚSCULAS

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

ORG 0
JMP INICIO

ORG 20
INICIO: CLO

LER:    IN  AL,00h          ; Lê uma tecla
        CMP AL,61h          ; Comparar com 'a'
        JC  LER             ; Se menor, lê novamente
        CMP AL,7Ah          ; Comparar com 'z'
        JNC LER             ; Se maior, lê novamente
        MOV [90h],AL        ; Guarda a tecla válida em 90h

FIM:     JMP FIM
END
