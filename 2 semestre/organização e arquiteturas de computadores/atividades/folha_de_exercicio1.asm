; 1) Atribuir FA, FB, FC e FD a AL, BL, CL e DL

ORG 0
JMP INICIO

ORG 20
INICIO:  CLO
         MOV  AL,FAh
         MOV  BL,FBh
         MOV  CL,FCh
         MOV  DL,FDh
END

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

; 4) Faça um programa que apresente no display de sete segmentos (porta 02) o valor 88.

ORG 0
JMP INICIO

ORG 20
INICIO:  CLO
; Ajuste EIGHT de acordo com seu hardware:
EIGHT   EQU 7Fh        ; se common-cathode
;EIGHT  EQU 80h        ; se common-anode (ativo baixo)

         MOV  AL,EIGHT
         OUT  02,AL     ; escreve '8' no 7-seg
         ; caso precise do segundo '8', repita a escrita / selecione o outro dígito conforme sua multiplexação
END

; 5) Mostrar seu nome no VDU: ida e volta

ORG 0
JMP INICIO

ORG 10
DB "LUCAS"      ; 5 caracteres

ORG 20
INICIO:  CLO
         MOV  CL,0C0h   ; início da memória de vídeo (destino)
         MOV  DL,10h    ; início da string (origem)
         MOV  BL,5      ; tamanho do nome

; --- FORWARD (início -> fim) ---
FWD:     MOV  AL,[DL]
         MOV  [CL],AL
         INC  CL
         INC  DL
         DEC  BL
         JNZ  FWD

; --- BACKWARD (fim -> início) ---
; DL está após o último char; volte 1
         DEC  DL
; Reposiciona VDU uma linha abaixo (ex: C0h + 16) ou no mesmo lugar, como preferir
         MOV  CL,0D0h   ; ex.: próxima linha do VDU
         MOV  BL,5

BWD:     MOV  AL,[DL]
         MOV  [CL],AL
         INC  CL
         DEC  DL
         DEC  BL
         JNZ  BWD

END

; 6) Operação lógica de bytes: ((DB & FD) ^ (~3E)) | (DA) → resultado em AL

ORG 0
JMP INICIO

ORG 20
INICIO:  CLO
         MOV  AL,DBh        ; AL = DB
         AND  AL,FDh        ; AL = (DB & FD)
         MOV  BL,3Eh
         NOT  BL            ; BL = ~3E
         XOR  AL,BL         ; AL = (DB & FD) ^ (~3E)
         OR   AL,DAh        ; AL = ((DB & FD) ^ (~3E)) | DA
END

; 7) Operação lógica de 16 bits:

((7732h ^ 88CDh) | (FDCAh & 7235h)) ^ 1111h

ORG 0
JMP INICIO

ORG 20
INICIO:  CLO
; Carrega 7732h em AH:AL  (AH=77h, AL=32h)
         MOV  AH,77h
         MOV  AL,32h
; Carrega 88CDh em BH:BL  (BH=88h, BL=CDh)
         MOV  BH,88h
         MOV  BL,CDh

; Passo 1: (7732 ^ 88CD) por bytes
         XOR  AL,BL        ; AL = 32 ^ CD
         XOR  AH,BH        ; AH = 77 ^ 88

; Carrega FDCAh e 7235h para fazer (&)
         MOV  DL,CAh       ; low  byte de FDCA
         MOV  CL,35h       ; low  byte de 7235
         AND  DL,CL        ; DL = CA & 35

         MOV  DH,FDh       ; high byte de FDCA
         MOV  CH,72h       ; high byte de 7235
         AND  DH,CH        ; DH = FD & 72

; Passo 2: OR com o resultado anterior, por bytes
         OR   AL,DL        ; low  byte
         OR   AH,DH        ; high byte

; Passo 3: XOR com 1111h -> (11h high, 11h low)
         XOR  AL,11h       ; low  byte ^ 11h
         XOR  AH,11h       ; high byte ^ 11h

; Resultado final:
; AH = byte alto, AL = byte baixo do valor de 16 bits
END
