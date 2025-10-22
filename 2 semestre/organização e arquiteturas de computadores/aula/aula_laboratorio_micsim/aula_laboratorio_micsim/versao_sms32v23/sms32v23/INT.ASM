; --------------------------------------------------------------
; This was used to check that interrupts worked properly
; and would co-exist with a procedure.  It does nothing 
; interesting.
; --------------------------------------------------------------
	jmp	start

	db	30
	db	50
	db	D0

start:
	int	02
	int	03
	int	04

	jmp	start

	org	30
	nop
	nop
	nop
	nop
	nop
	iret

	org	50
	nop
	nop
	nop
	nop
	nop
	iret

	org	D0
	nop
	nop
	call	E0
	nop
	nop
	iret

	org	E0
	nop
	nop
	int	02
	int	03
	nop
	nop
	ret
; --------------------------------------------------------------
	end
; --------------------------------------------------------------
