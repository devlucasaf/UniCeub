; ---------------------------------------------------
; A program to turn the heater on and off.

; The system starts with the heater off allowing it to cool.
; The heater is then turned on until about 22 degrees is reached.
; This temperature is maintained for a period.
; The temperature is then forced up for a period before being 
; returned to 22 degrees.

; The above is then repeated.
; ---------------------------------------------------
	MOV	BL,80
	PUSH	BL
main:
			; HEAT/COOL SYSTEM
	POP	AL
	XOR	AL,80
	PUSH	AL
	OUT	03	; Send data to heater

	MOV	AL,30	; 30 time units delay
	CALL	90	; Call time delay procedure
; ---------------------------------------------------
			; BANG BANG CONTROLLER
			; Run this loop 20 times
	MOV	CL,20	; Use CL as a counter
Bang:
	DEC	CL
	JZ	Main
	IN	03	;
	CMP	AL,80	
	JZ	turnOn
	CMP	AL,81
	JZ	turnOff
	CMP	AL,0
	JZ	turnOn

turnOff:
	MOV	AL,0
	OUT	03
	jmp	Bang

turnOn:
	MOV	AL,80
	OUT	03
	jmp	Bang
; ---------------------------------------------------
	ORG	90	; Time Delay.  Determined by 
			; the value passed in AL.
	PUSH	AL
delay:
	DEC	AL
	JNZ	delay

	POP	AL
	RET	
; ---------------------------------------------------
	END
; ---------------------------------------------------
