; 1) Atribuir FA, FB, FC e FD a AL, BL, CL e DL

ORG 0
JMP INICIO

ORG 20
INICIO:         CLO
                MOV  AL,FAh
                MOV  BL,FBh
                MOV  CL,FCh
                MOV  DL,FDh
END