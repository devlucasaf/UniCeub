    mov	bl,8F

le:	inc	bl
	in	00
	cmp	al,0D
	jz	meio
	cmp	al,60
	mov	[bl],al
	jz	le
	js	le
	cmp	al,7b
	mov	[bl],al
	jns	le
	cmp	al,40
	mov	[bl],al
	jz	le
	js	le
	mov	[bl],al
	cmp	al,5B
	mov	[bl],al
	jns	le

meio:	mov	bl,90
	mov	cl,C0
	
loop:	mov	dl,[bl]
	mov	[cl],dl
	inc	cl
	inc	bl
	cmp	bl,96
	jz	final
	js	loop	

final:	
	end