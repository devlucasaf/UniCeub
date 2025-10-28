    mov	bl,60
	mov	al,05

loop:	mov	[bl],al
	inc	bl
	add	al,02
	cmp	bl,70
	js	loop
	
	mov	al,61
	mov	dl,[60]

loop2:	mov	bl,[al]
	xor	dl,bl
	inc	al
	cmp	bl,[6F]
	js	loop2

	mov	[70],dl
	end
	