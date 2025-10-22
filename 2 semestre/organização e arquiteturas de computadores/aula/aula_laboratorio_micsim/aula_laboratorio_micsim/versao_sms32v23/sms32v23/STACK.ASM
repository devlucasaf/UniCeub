; ---------------------------------------------
;  TEST PUSH, POP, PUSHF and POPF

; This program fails in an unpredictable way.
; The popf command sets the CPU flags un an
; unplanned way.  The I flag is soon set, which 
; causes the hardware interrupt 02 to start 
; working. The address in RAM location 02 is 
; zero.  This causes the program to restart 
; without clearing up the stack.  The program 
; works correctly and then fails unexpectedly 
; when the I flag is set.
; ---------------------------------------------

	CLO		; Close unwanted windows
Start:

	mov	al,10	; Initialise counter
rep1:
	push	al	; test push
	push	al	; test push
	pop	bl	; test pop
	pushf		; test pushf
	dec	al	; decrement counter
	jnz	rep1	; repeat until al = 0

; ---------------------------------------------

	mov	al,10	; Initialise counter
rep2:
	pop	cl	; test pop
	popf		; test popf
	dec	al	; decrement counter
	jnz	rep2	; repeat until al = 0

	JMP	Start	; Press ESCAPE to stop the program

; ---------------------------------------------

	END

; ---------------------------------------------
