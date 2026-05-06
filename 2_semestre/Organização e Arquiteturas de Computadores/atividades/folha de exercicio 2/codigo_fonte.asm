    MOV AL,77       ; [00]  D0 00 77 
    MOV BL,88       ; [03] D0 00 88 
    MOV CL,11       ; [06]  D0 02 11 
    MOV DL,EE       ; [09]  D0 03 EE 
    OR DL,CL        ; [0C]  AB 03 02 
    XOR DL,DF       ; [0F]   AC 03 DF 
    AND AL,BL       ; [12] AA 00 01 
    NOT BL          ; [15] AD 01 
    NOT AL          ; [17] AD 00 
    PUSH DL         ; [19] E0 03 
    PUSH AL         ; [1B] E0 00 
    PUSH BL         ; [1D] E0 01 
END                 ; [1F]  00
