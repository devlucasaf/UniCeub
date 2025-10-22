	org 0
	jmp	inicio
	org	10
	db	"miguel archanjo"
		
	org	40	;define inicio	
inicio:	MOV	CL,c0
	MOV	DL,0f
	mov	bl,10
							
prox:	MOV	AL,[bl]
	MOV	[CL],AL
	INC	CL		
	inc	bl
	dec	dl
	JNZ	PROX	
	mov	cl,e0
	MOV	DL,0f
	mov	bl,1e
prox2:	MOV	AL,[bl]
	MOV	[CL],AL
	INC	CL		
	dec	bl
	dec	dl
	JNZ	PROX2
	END