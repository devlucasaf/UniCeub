	mov	al,0
	out	06
loop:
	in	06	; Test for lift at top
	push	al
	pop	bl
	and	bl,5	; Test for MotorUp AND TopSwitch
	cmp	bl,5
	jz	StopTop

	in	06	; Test lift at bottom
	push	al
	pop	bl
	and	bl,A	; Test for MotorDown AND BottomSwitch
	cmp	bl,A
	jz	StopBot

	push	al
	pop	bl
	and	bl,10	; test for the Call Down button
	jnz	CallDown

	push	al
	pop	bl
	and	bl,20	; test for the Call Up button
	jnz	CallUp

	jmp	loop

StopTop:
	and	al,DE	; Switch off motor bits and call button
	out	06
	jmp	loop

StopBot:
	and	al,ED	; Switch off motor bits and call button
	out	06
	jmp	loop

CallDown:
	in	06	; Is lift already down
	and	al,8
	cmp	al,8
	jz	errDown	; Don't turn on the motor
	Mov	al,12
	Out	06
	jmp	loop
errDown:
	Mov	al,0
	Out	06
	jmp	loop

CallUp:
	in	06	; Is lift already up
	and	al,4
	cmp	al,4
	jz	errUp	; Don't turn on the motor
	Mov	al,21
	Out	06
	jmp	loop
errUp:
	Mov	al,0
	Out	06
	jmp	loop

	end
