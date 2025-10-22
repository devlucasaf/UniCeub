; Stepper motor moving in half steps.

; It would be better to use a data table.  It is not a 
; good idea to mix code and data.

start:
	mov	al,1
	out	05
	mov	al,3
	out	05
	mov	al,2
	out	05
	mov	al,06
	out	05
	mov	al,04
	out	05
	mov	al,0C
	out	05
	mov	al,08
	out	05
	mov	al,09
	out	05	

	jmp	start

	end
