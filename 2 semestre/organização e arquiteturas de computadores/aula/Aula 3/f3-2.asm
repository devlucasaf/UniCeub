    e:	in	00
	cmp	al,60
	jz	le
	js	le
	cmp	al,7b
	jns	le
	mov	[90],al
	end