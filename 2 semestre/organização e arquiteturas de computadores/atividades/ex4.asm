//  print brasil and brasil backwards
	ORG	0
	JMP	INICIO
	ORG	10
	DB	"BRASIL"
	ORG	20
INICIO: CLO	
	MOV	CL, C0
	MOV	BL,6
	MOV	DL,10
PROX:	MOV	AL,[DL]
	MOV	[CL],AL
	INC	CL
	INC	DL
	DEC	BL
	JNZ	PROX
##################################
	MOV	CL,e0
	MOV 	BL,6
	MOV	DL,15
PROX1:	MOV	AL,[DL]
	MOV	[CL],AL
	INC	CL
	DEC	DL
	DEC	BL
	JNZ	PROX1
	