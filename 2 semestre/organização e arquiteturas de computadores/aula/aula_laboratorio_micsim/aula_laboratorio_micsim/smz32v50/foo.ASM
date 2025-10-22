; ============================================================= 
 mov bl,5 ; Use BL as a pointer to the data table 
 jmp start 
 
; ===== DATA TABLE ===== 
 db 1 
 db 3 
 db 2 
 db 6 
 db 4 
 db c 
 db 8 
 db 9 
; ====================== 
 
start: 
 call 50 
 call 50 
 call 50 
 call 50 
 call 50 
 call 70 
 call 70 
 call 70 
 jmp start 
 
; ===== A procedure to rotate 5 degrees clockwise ========== 
 org 50 ; 5 Degrees Clockwise 
 mov al,[bl] 
 out 05 
 sub bl,4 
 mod bl,8 
 add bl,5 
 ret 
 
; ===== A procedure to rotate 5 degrees anti-clockwise ===== 
 org 70 ; 5 Degrees Anti-clockwise 
 mov al,[bl] 
 out 05 
 sub bl,D 
 mod bl,8 
 add bl,C 
 ret 
 
 end 
; ============================================================= 
 
