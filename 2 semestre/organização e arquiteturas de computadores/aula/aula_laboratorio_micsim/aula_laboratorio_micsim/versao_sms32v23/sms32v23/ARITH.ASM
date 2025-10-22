; Arithmetic.  	This program is intended to fail with a divide 
;		by zero error.

;   DIRECT	is where arithmetic is done with registers only.

;   IMMEDIATE	is where arithmetic is done with a register
;		and a numeric value

; Addition
	MOV	AL,2	; Copy 2 Hex into AL
	MOV	BL,2	; Copy 2 Hex into BL
	ADD	AL,BL	; AL becomes AL + BL	DIRECT
	ADD	AL,2	; AL begomes AL + 2	IMMEDIATE
	INC	AL	; Increment or add one to AL

; Addition that causes an overflow
	MOV	AL,7F	; Copy 7F Hex into AL
	MOV	BL,1	; Copy 1 Hex into BL
	ADD	AL,BL	; AL becomes AL + BL	DIRECT
			; Answer is wrong becuase of the overflow

; Subtraction
	MOV	AL,8	; Copy 8 Hex into AL
	MOV	BL,5	; Copy 5 Hex into BL
	SUB	AL,BL	; AL becomes AL - BL	DIRECT
	SUB	AL,2	; AL begomes AL - 2	IMMEDIATE
	DEC	AL	; Decrement or subtract one from AL

; Subtraction that cuases the sign bit to be set
	MOV	AL,5	; Copy 5 Hex into AL
	MOV	BL,8	; Copy 5 Hex into BL
	SUB	AL,BL	; AL becomes AL + BL	DIRECT

; Multiplication
	MOV	AL,2	; Copy 2 Hex into AL
	MOV	BL,2	; Copy 2 Hex into BL
	MUL	AL,BL	; AL becomes AL * BL	DIRECT
	MUL	AL,2	; AL begomes AL * 2	IMMEDIATE

; Multiplication that causes an overflow
	MOV	AL,55	; Copy 55 Hex into AL
	MOV	BL,2	; Copy 2 Hex into BL
	MUL	AL,BL	; AL becomes AL * BL	DIRECT
			; Answer is wrong becuase of the overflow

; MOD  This is the remainder left ofer after doing a division.
	MOV	AL,55	; Copy 55 Hex into AL
	MOD	AL,10	; AL becones the remainder after 
			; dividing by 10	IMMEDIATE

	MOV	AL,76	; Copy 76 Hex into AL
	MOV	DL,20	; Copy 20 Hex into AL
	MOD	AL,DL	; AL becones the remainder after 
			; dividing by DL	DIRECT

; Division
	MOV	AL,10	; Copy 10 Hex into AL
	MOV	BL,2	; Copy 2 Hex into BL
	DIV	AL,BL	; AL becomes AL div BL	DIRECT
	DIV	AL,2	; AL begomes AL div 2	IMMEDIATE

; Division by zero ERROR
	MOV	AL,10	; Copy 10 Hex into AL
	MOV	BL,0	; Copy 0 Hex into BL
	DIV	AL,BL	; AL becomes AL div BL	FAILS

	MOV	AL,10	; Copy 10 Hex into AL
	DIV	AL,0	; AL becomes AL div 0	FAILS

	END		; End of the program
