; --------------------------------------------------------------
; Press  ALT+R  to tun this program.

; A program to turn off the heater
; --------------------------------------------------------------
	CLO		; Close unwanted windows
	MOV	AL,0	; Move 00000000 into AL
	OUT	03	; Send AL to port 03
	END
; --------------------------------------------------------------
