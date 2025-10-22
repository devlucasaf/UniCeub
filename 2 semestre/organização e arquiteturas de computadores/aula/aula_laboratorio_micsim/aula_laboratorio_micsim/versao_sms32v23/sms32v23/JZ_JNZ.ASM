; A program to test JZ and JNZ.

; The 'Z' or zero flag in the status register (SR) is set
; whenever a calculation gives a zero result.  The JZ
; and JNZ commands work in conjunction with the 'Z' flag.
; JZ causes the program to jump if the 'Z' flag is set.
; JNZ causes the program to jump if the 'Z' flag is not set.

; A count down loop that terminates when AL becomes zero.
; This loop exercises the JZ (jump zero) command.

start:
	mov	al,5	; Initialise AL register counter to 5.
foo:
	dec	al	; Decrement AL.
	jz	bar	; Jump out of loop if zero bit is set.
	jmp	foo	; Jump back and repeat the loop.


; A count down loop that terminates when AL becomes zero.
; This loop exercises the JNZ (jump not zero) command.

bar:
	mov	al,5	; Initialise AL register counter to 5.
puppy:
	dec	al	; Decrement AL.
	jnz	puppy	; Jump back and repeat the loop 
			; if the zero bit is not set.

	jmp	start	; PRESS ESCAPE TO STOP THE PROGRAM

	end
