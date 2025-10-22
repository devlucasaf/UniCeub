; --------------------------------------------------------------
; An example of using hardware interrupts.
; This program spins the stepper motor continuously and 
; steps the traffic lights on each hardware interrupt.

; Uncheck the "Show only one peripheral at a time" box
; to enable both displays to appear simultaneously.

; --------------------------------------------------------------
	Jmp	Start	; Jump past table of interrupt vectors
	DB	50	; Vector at 02 pointing to address 50
Start:
	STI		; Set I flag. Enable hardware interrupts
	MOV	AL,11	;
Rep:
	OUT	05	; Stepper motor
	ROR	AL	; Rotate bits in AL right
	JMP	Rep
	JMP	Start
; --------------------------------------------------------------
	ORG	50

	push	al	; Save AL onto the stack.
	push	bl	; Save BL onto the stack.
	pushf		; Save flags onto the stack.

	Jmp	PastData
	DB	84	; Red		Green
	DB	c8	; Red+Amber	Amber
	DB	30	; Green		Red
	DB	58	; Amber		Red+Amber
	DB	57	; Used to track progress through table
PastData:
	MOV	BL,[5B]	; BL now points to the data table
	MOV	AL,[BL]	; Data from table goes into AL
	OUT	01	; Send AL data to traffic lights
	CMP	AL,58	; Last entry in the table
	JZ	Reset	; If last entry then reset pointer
	INC	BL	; BL points to next table entry
	MOV	[5B],BL	; Save pointer in RAM
	JMP	Stop
Reset:
	MOV	BL,57	; Pointer to data table start address
	MOV	[5B],BL	; Save pointer into RAM location 54
Stop:
	popf		; Restore flags to their previous value
	pop	bl	; Restore BL to its previous value
	pop	al	; Restore AL to its previous value

	IRET
; --------------------------------------------------------------
	END
; --------------------------------------------------------------

TASK
====	1)	Write a program that controls the heater and 
		thermostat whilst at the same time counting 
		from 0 to 9 repeatedly, displaying the result 
		on one of the seven segment displays.  If you 
		want a harder challenge, count from 0 to 99 
		repeatedly using both displays.  Use the 
		simulated hardware interrupt to control the 
		heater and thermostat.

	2)	A fiendish problem.  Solve the Tower of Hanoi 
		problem whilst steering the snake through the 
		maze.  Use the text characters A, B, C Etc to 
		represent the disks.  Use three of the four 
		rows on the screen to represent the pillars.
