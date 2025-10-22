; --------------------------------------------------------------
; Press  ALT+R  to tun this program.

; Stepper motor.

; This shows how it can be made to rotate.
; The numbers controlling the motor should be put into a 
; data table.  By altering the numbers in the table, the 
; motor can be made to perform complex moves such as might 
; be found in a printer, plotter or robot.
; --------------------------------------------------------------
start:
	mov	al,1
	out	05
	mov	al,3
	out	05
	jmp	start

	end
; --------------------------------------------------------------
