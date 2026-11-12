    mov	    al,f0
    out	    02
    mov	    al,f5
    out	    02
    end	

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

end
