; --------------------------------------------------------------
; Press  ALT+R  to tun this program.

; Keyboard input and VDU Output.  Run the program.  When 
; prompted, type some text, slowly, key press by key press.
; The text you entered will be displayed on the VDU screen.
; Pressing Enter stops the program run.
; --------------------------------------------------------------
	Mov	BL,C0	; Address of top left corner of VDU
Start:
	IN	00	; Keyboard Input goes into AL register.
	CMP	AL,0D	; Was it the Enter Key ? 
	JZ	Stop	; If so then jump to Stop.
	MOV	[BL],AL	; Copy text character in AL to the
			; address that BL points to. ( VDU )
	INC	BL	; BL now points to next screen location.
	JMP	Start	; Jump back to the start.
Stop:
	End		; Program Ends.
; --------------------------------------------------------------