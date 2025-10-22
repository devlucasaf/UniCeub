; _____ FIBONACCI - A solution to task 04 ______________________

	Jmp	Start

; _____ INITIALISE MEMORY LOCATIONS ____________________________

	DB	0	; RAM Location [02] used as a variable
	DB	1	; RAM Location [03] used as a variable
Start:
	MOV	BL,[02]	; Prevoius value into BL
	MOV	CL,[03]	; Previous value into CL
	ADD	BL,CL	; Calculate new value
	MOV	[02],CL	; Prevoius value moved back one place
	MOV	[03],BL	; Answer becomes new previous value

	JMP	Start	; Do next fibonacci number.

	End
; ______________________________________________________________