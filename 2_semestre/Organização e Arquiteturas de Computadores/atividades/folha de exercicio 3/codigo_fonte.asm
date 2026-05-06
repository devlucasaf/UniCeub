; LIST FILE : SUCCESS : No errors found. 
; 
; This shows the machine code that was generated and 
; the addresses at which the codes were stored. 
; 
MOV   AL,55     ; [00]  D0 00 55 
MOV  BL,AA  ; [03]  D0 01 AA 
PUSH  AL           ; [06]  E0 00  
PUSH  BL          ; [08]  E0 01  
POP  DL          ; [0A]  E1 03  
POP  CL          ; [0C]  E1 02  
XOR  DL,CL  [0E]  AC 03 02 
NOT  CL           ; [11]  AD 02  
AND  AL,BL ; [13]  AA 00 01 
OR  AL,DL ; [16]  AB 00 03 
END           ; [19]  00  
; 
; SUCCESS : No errors found.
