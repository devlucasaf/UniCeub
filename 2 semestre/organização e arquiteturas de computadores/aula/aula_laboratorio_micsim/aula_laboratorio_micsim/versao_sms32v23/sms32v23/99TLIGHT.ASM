; ----- Control the traffic lights -----------------------------
; Press  ALT+R  to tun this program.

; This simple example does not step the lights realistically
; It lacks a time delay facility.
; It would be better to use a data table.
; It works otherwise!  
; Solving this properly is one of the learning tasks.
; --------------------------------------------------------------
	CLO		; Close unwanted windows
rep:
	mov	al,84	; Red		Green
	out	01
	mov	al,c8	; Red+Amber	Amber
	out	01
	mov	al,30	; Green		Red
	out	01
	mov	al,58	; Amber		Red+Amber
	out	01
	jmp	rep
	end
; --------------------------------------------------------------
