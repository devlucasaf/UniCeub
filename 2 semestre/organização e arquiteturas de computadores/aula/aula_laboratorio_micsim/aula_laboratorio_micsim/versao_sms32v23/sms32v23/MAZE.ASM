; A program to run the snake through the maze.

START:
	MOV	AL,FF
	OUT	04
	MOV	CL,30
	MOV	DL,40
MORE:
	MOV	AL,[CL]
	MOV	BL,[DL]
	CMP	AL,0
	JZ	START
REP:
	OUT	04
	DEC	BL
	JNZ	REP
	INC	CL
	INC	DL
	JMP	MORE
		

	org	30	; DATA TABLE TO RUN MAZE : Steering
	db	4F	; Down 0B
	db	1D	; Right 3
	db	8B	; Up 8
	db	1D	; Right 3
	db	4E	; Down 6
	db	1E	; Right 3
	db	8E	; Up 9
	db	2F	; Left 5
	db	8C	; Up 3
	db	1F	; Right 8
	db	4E	; Down 0C

	org	40	; DATA TABLE TO RUN MAZE : Distances
	db	0B	; Down 0B
	db	3	; Right 3
	db	8	; Up 8	
	db	3	; Right 3
	db	6	; Down 6
	db	3	; Right 3
	db	9	; Up 9
	db	5	; Left 5
	db	3	; Up 3
	db	8	; Right 8
	db	0C	; Down 0C

	END
