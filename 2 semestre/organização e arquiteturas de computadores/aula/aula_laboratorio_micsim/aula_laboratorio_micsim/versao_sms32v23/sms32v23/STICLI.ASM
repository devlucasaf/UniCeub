; --------------------------------------------------------------
; Use CLI and STI to clear and set the interrupt flag.
; This tests the function of the hardware interrupt 02.
; --------------------------------------------------------------
	JMP	Start
	DB	40	; Interrupt vector at address 02
Start:
	nop
	nop
	nop
	cli
	nop
	nop
	nop
	sti
	jmp	Start
; --------------------------------------------------------------
	org	40
	NOP
	NOP
	NOP
	NOP
	NOP
	IRET
; --------------------------------------------------------------
	end
; --------------------------------------------------------------
