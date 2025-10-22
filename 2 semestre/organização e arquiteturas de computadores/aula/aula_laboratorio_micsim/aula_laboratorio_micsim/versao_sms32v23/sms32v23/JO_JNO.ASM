; A program to test JO and JNO.

; An overflow occurs when a calculation gives a result
; that is too big to be stored in a register.  This 
; eight bit simulator can hold integer numbers between
; -128 and +127.  Any number outside this range causes an
; overflow and the 'O' flag is set in the status register (SR).

; The JO and JNO commands work in conjunction with the
; 'O' overflow flag.  JO causes the program to jump if the 'O' 
; flag is set.  JNO causes the program to jump if the 'O' flag
; is not set.

; A counting loop that terminates when AL overflows.
; This loop exercises the JO (jump overflow) command.

start:
	mov	al,75	; Initialise AL counter to 75 HEX.
			; 7F + 1 causes the overflow
foo:
	inc	al	; Increment AL.
	jo	bar	; Jump out of loop if overflowed.
	jmp	foo	; Jump back and repeat the loop.


; A counting loop that terminates when AL overflows.
; This loop exercises the JNO (jump not overflow) command.

bar:
	mov	al,75	; Initialise AL counter to 75 HEX.
puppy:
	inc	al	; Increment AL.
	jno	puppy	; Jump back and repeat the loop 
			; if the overflow bit is not set.

	jmp	start	; PRESS ESCAPE TO STOP THE PROGRAM

	end
