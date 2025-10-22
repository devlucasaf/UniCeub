; --------------------------------------------------------------
; Press  ALT+R  to tun this program.

; A program to turn on the heater
; --------------------------------------------------------------
	CLO		; Close unwanted windows
	MOV	AL,80	; Move 10000000 into AL
	OUT	03	; Send AL to port 03
	END
; --------------------------------------------------------------
