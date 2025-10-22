; RATHER A LOT OF PROCEDURE CALLING HERE.
; THERE IS NO RECURSION.
; RECURSION IS POSSIBLE.

; ----- THE MAIN PROGRAM ---------------------------------------

rep:
	call	30	; Call the procedures at these addresses
	call	40
	call	50
	call	60
	call	70
	call	80
	call	90
	jmp	rep	; Jump back to the start

; ----- A PROCEDURE --------------------------------------------
	org	30	; Origin or start address of procedure
	call	40	; Call the procedures at these addresses
	call	50
	call	60
	call	70
	call	80
	call	90
	ret		; Return to address saved on stack

; ----- A PROCEDURE --------------------------------------------
	org	40	; Origin or start address of procedure
	call	50	; Call the procedures at these addresses
	call	60
	call	70
	call	80
	call	90
	ret		; Return to address saved on stack

; ----- A PROCEDURE --------------------------------------------
	org	50	; Origin or start address of procedure
	call	60	; Call the procedures at these addresses
	call	70
	call	80
	call	90
	ret		; Return to address saved on stack

; ----- A PROCEDURE --------------------------------------------
	org	60	; Origin or start address of procedure
	call	70	; Call the procedures at these addresses
	call	80
	call	90
	ret		; Return to address saved on stack

; ----- A PROCEDURE --------------------------------------------
	org	70	; Origin or start address of procedure
	call	80	; Call the procedures at these addresses
	call	90
	ret		; Return to address saved on stack

; ----- A PROCEDURE --------------------------------------------
	org	80	; Origin or start address of procedure
	call	90	; Call the procedures at these addresses
	ret		; Return to address saved on stack

; ----- A PROCEDURE --------------------------------------------
	org	90	; Origin or start address of procedure
	nop		; Do Nothing for one clock cycle
	pop	al
	push	al
	pop	bl
	push	bl
	pop	cl
	push	cl
	pop	dl
	push	dl	
	nop
	ret		; Return to address saved on stack

	end
; --------------------------------------------------------------
