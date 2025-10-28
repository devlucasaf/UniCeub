; 2) Trocar AL↔DL e BL↔CL usando PUSH/POP

ORG 0
JMP INICIO

ORG 20
INICIO:  CLO
; ... (suponha AL,BL,CL,DL já carregados)

; swap AL <-> DL
        PUSH AL
        PUSH DL
        POP  AL   ; AL recebe antigo DL
        POP  DL   ; DL recebe antigo AL

; swap BL <-> CL
        PUSH BL
        PUSH CL
        POP  BL
        POP  CL

END