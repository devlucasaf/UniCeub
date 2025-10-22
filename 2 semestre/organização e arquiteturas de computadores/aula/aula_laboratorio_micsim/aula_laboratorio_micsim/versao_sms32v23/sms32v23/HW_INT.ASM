; This program demonstrates a hardware interrupt.
; In the Configuration Tab, set a time of about 5 
; seconds.  Every 5 seconds, INT 02 is called by 
; the simulated hardware timer.  The program executes
; the interrupt code and continues where it left off.

	Jmp	Start
	DB	40
Start:
	STI
Rep:
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	JMP	Rep

	ORG	40
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	IRET

	END

; A TASK
; ======

; Write a program that causes the right seven segment
; display to count from 0 to 9 repeatedly.  On each 
; clock tick, increment the left display also from 0 to 9.
; Save and restore flags and registers on the stack or there
; will be no chance that this program will work.
