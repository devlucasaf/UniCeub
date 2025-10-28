	org 0
	jmp inicio

	org 10
	db "lucas freitas" 
	org 30 

inicio:
    mov cl, c0           
        mov bl, 11         
        mov dl, 10           
prox:
        mov al, [dl]         
    mov [cl], al        
        inc cl               
        inc dl              
        dec bl                
        jnz prox              

;########################################

	mov cl, e0           
        mov bl, 11         
        mov dl, 20           
prox1:
        mov al, [dl]         
    mov [cl], al        
        inc cl               
        dec dl              
        dec bl                
        jnz prox1     
end
