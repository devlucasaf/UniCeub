; 5) Mostrar seu nome no VDU: ida e volta

ORG 0
JMP INICIO

ORG 10
DB "LUCAS"      ; 5 caracteres

ORG 20
INICIO:         CLO
                MOV  CL,0C0h   ; início da memória de vídeo (destino)
                MOV  DL,10h    ; início da string (origem)
                MOV  BL,5      ; tamanho do nome

; --- FORWARD (início -> fim) ---
FWD:            MOV  AL,[DL]
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

BWD:            MOV  AL,[DL]
                MOV  [CL],AL
                INC  CL
                DEC  DL
                DEC  BL
                JNZ  BWD

END