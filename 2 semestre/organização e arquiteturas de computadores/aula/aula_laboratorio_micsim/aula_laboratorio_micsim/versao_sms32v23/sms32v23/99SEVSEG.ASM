; --------------------------------------------------------------
; Press  ALT+R  to tun this program.

; A program to exercise the seven segment displays.  
; It is up to you to modify this program to get the displays 
; to step through recognisable sequences.  Try counting or 
; programming the segments to spell H E L L O.
; Use a data table.  It is poor practice to mix code and data
; as happens in this example.
; --------------------------------------------------------------
start:
	mov	al,FF	; Turn on all the right segments
	out	02	; Send data to port 02.
	mov	al,01	; Turn off all the right segments
	out	02
	mov	al,FE	; Turn on all the left segments
	out	02
	mov	al,0	; Turn off all the left segments
	out	02
	jmp	start	; Jump back to the start
	end
; --------------------------------------------------------------






