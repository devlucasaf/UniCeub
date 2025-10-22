; ----- Test all instructions in the instruction set --------
	org	A0
	DB	"HELLO"
	DB	0

	org	48
	db	"world"
	db	0

	org	0
	MOV	CL,A0
	MOV	BL,C0
rep:
	MOV	AL,[CL]
	CMP	AL,0
	JZ	foo
	MOV	[BL],AL
	INC	BL
	INC	CL
	JMP	rep
foo:
	MOV	CL,[A0]
	MOV	BL,C6
poo:
	MOV	AL,[CL]
	CMP	AL,[A5]
	JZ	azz
	MOV	[BL],AL
	INC	BL
	INC	CL
	JMP	poo
azz:
	MOV	AL,5A
	MOV	[C0],AL

	mov	DL,78
	mov	AL,8F
ree:
	call	62
	Add	DL,1
	CMP	DL,AL
	JNZ	ree
	JMP	LAA

	org	4E
laa:
	mov	bl,10
	mov	al,88
goo:
	out	05
	ror	al
	sub	bl,1
	cmp	bl,f0
	jnz	goo
	jmp	here

; ----- Sent a byte to the traffic lights -----
	org	62
	push	al
	push	dl
	pop	al
	out	01
	pop	al
	nop
	ret	

here:
	int	06
	in	00
	mov	cl,f0	; 11110000
	mov	dl,ff	; 11111111

	AND	CL,DL	; 11110000
	OR	CL,DL	; 11111111
	XOR	AL,BL
	NOT	BL
	ROL	AL
	SHL	CL
	SHR	DL
	AND	AL,0F
	OR	CL,F0
	XOR	AL,AA

	halt

; ----- Test INT -----
	org	D3

	pushf
	push	al

	mov	al,8
	mov	bl,2
	SUB	al,bl
	MUL	al,bl
	DIV	al,bl
	MOD	al,bl

	DEC	AL
	ADD	AL,11
	SUB	AL,2
	MUL	AL,03
	DIV	AL,6
	MOD	AL,5

	pop	al
	popf

	iret

	end

Assembler	Machine Code  Explanation
JS     STOP     C3 09         Increase IP by 9 if S flag is set
                C3 E1         Decrease IP by 31 if S flag is set

JNS    START    C4 04         Increase IP by 4 if S flag not set
                C4 E0         Decrease IP by 32 if S flag not set

JO     REPEAT   C5 09         Increase IP by 9 if O flag is set

                C5 DF         Decrease IP by 33 if O flag is set

JNO    AGAIN    C6 04         Increase IP by 4 if O flag not set
                C6 FB         Decrease IP by 5 if O flag not set



Procedures and Interrupts.  Flags NOT set.

CALL, RET, INT and IRET are available only in the registered version.

Assembler	Machine Code  Explanation
INT    01       CC 01         Run code starting at address in 01
                              and save return address on the stack.

IRET            CD            Return from interrupt and continue
                              from address saved on the stack.




Input Output Instructions.  Flags NOT set.

Assembler	Machine Code  Explanation
IN     0C       F0 0C         Data input  from I/O port 0C to AL



Miscellaneous Instructions.  CLI and STI set I flag.

Assembler	Machine Code  Explanation
CLO             FE            Close all visible peripheral windows.
STI             FC            Set Interrupts Flag - Enabled
CLI             FD            Clear Interrupts Flag - Disabled


