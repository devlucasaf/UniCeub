    MOV AL,2F       [00]  D0 00 2F 
    MOV CL,90       [03]  D0 02 90 
    MOV BL,AA       [06]  D0 01 AA 
    MOV DL,55       [09]  D0 03 55 
    MOV [DL],CL     [0C]  D4 03 02 
    NOT DL          [0F]  AD 03  
    AND AL,BL       [11]  AA 00 01 
    PUSH CL         [14]  E0 02  
    END             [16]  00  
; SUCCESS : No errors found. 
