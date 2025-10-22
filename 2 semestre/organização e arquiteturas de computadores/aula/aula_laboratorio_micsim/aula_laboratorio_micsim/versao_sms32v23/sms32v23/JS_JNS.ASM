; A program to test JS and JNS.

; The 'S' or sign flag in the status register (SR) is set
; whenever a calculation gives a negative result.  The JS
; and JNS commands work in conjunction with the 'S' flag.
; JS causes the program to jump if the 'S' flag is set.
; JNS causes the program to jump if the 'S' flag is not set.

; A count down loop that terminates when AL becomes negative.
; This loop exercises the JS (jump sign) command.

start:
	mov	al,5	; Initialise AL register counter to 5.
foo:
	dec	al	; Decrement AL.
	js	bar	; Jump out of loop if sign bit is set.
	jmp	foo	; Jump back and repeat the loop.


; A count down loop that terminates when AL becomes negative.
; This loop exercises the JNS (jump not sign) command.

bar:
	mov	al,5	; Initialise AL register counter to 5.
puppy:
	dec	al	; Decrement AL.
	jns	puppy	; Jump back and repeat the loop 
			; if the sign bit is not set.

	jmp	start	; PRESS ESCAPE TO STOP THE PROGRAM

	end
