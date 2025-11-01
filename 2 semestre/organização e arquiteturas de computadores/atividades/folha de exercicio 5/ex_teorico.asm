; LIST FILE : SUCCESS : No errors found.
; ARQUIVO LISTADO : SUCESSO : Nenhum erro encontrado (traduzido)
;
; This shows the machine code that was generated and
; the addresses at which the codes were stored.
;
; Isto mostra o código de máquina que foi gerado e
; os endereços onde esses códigos foram armazenados.

    MOV     CL,A1       ; [00] D0 02 A1
    IN      00          ; [04] F0 00
    MOV     [A0],AL     ; [05] D2 A0 00
    IN      00          ; [09] F0 00
    MOV     [CL],AL     ; [0A] D4 02 00
    MOV     AL,[A0]     ; [0D] D1 00 A0
    AND     AL,0F       ; [10] BA 00 0F
    ROL     AL          ; [13] 9A 00
    ROL     AL          ; [15] 9A 00
    ROL     AL          ; [17] 9A 00
    ROL     AL          ; [19] 9A 00
    PUSH    AL          ; [1B] E0 00
    MOV     AL,[CL]     ; [1D] D3 00 02
    AND     AL,0F0      ; [20] BA 00 0F
    POP     BL          ; [23] E1 01
    OR      AL,BL       ; [25] AB 00 01
    END 
; 
; SUCESS : No errors found.
; SUCESSO : Nenhum erro encontrado.
