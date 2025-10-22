	mov	al,88
	out	05
prox:	rol	al
	out	05
	jmp	prox
	end