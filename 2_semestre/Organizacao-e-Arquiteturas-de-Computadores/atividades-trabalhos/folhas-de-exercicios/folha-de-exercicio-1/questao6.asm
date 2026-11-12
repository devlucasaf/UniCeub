; 6) Operação lógica de bytes: ((DB & FD) ^ (~3E)) | (DA) → resultado em AL

ORG 0
JMP INICIO

ORG 20
INICIO:         CLO
                MOV  AL,DBh        ; AL = DB
                AND  AL,FDh        ; AL = (DB & FD)
                MOV  BL,3Eh
                NOT  BL            ; BL = ~3E
                XOR  AL,BL         ; AL = (DB & FD) ^ (~3E)
                OR   AL,DAh        ; AL = ((DB & FD) ^ (~3E)) | DA
END