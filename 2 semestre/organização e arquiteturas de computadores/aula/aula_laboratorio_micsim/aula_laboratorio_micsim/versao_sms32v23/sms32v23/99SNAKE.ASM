	CLO
Start:
	MOV	AL,FF	; Reset the maze
	OUT	04	; Send control data to port 04
	MOV	AL,27	; 2 means left.  7 distance units
	OUT	04	; Send control data to port 04
	MOV	AL,4F	; 4 means down.  Down 15 units.
	OUT	04	; Send control data to port 04
	OUT	04	; Do it twice to move further
	MOV	AL,1E	; 1 means right.  E means 14.
	OUT	04	; Send control data to port 04
	MOV	AL,8F	; 8 means up.  15 distance units.
	OUT	04	; Send control data to port 04
	OUT	04	; Send control data to port 04
	MOV	AL,27	; 2 means left.  7 distance units
	OUT	04	; Send control data to port 04
	JMP	Start	; Repeat the whole sequence
	END
