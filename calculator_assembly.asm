.386
.model flat, stdcall


includelib msvcrt.lib
extern exit: proc
extern scanf: proc
extern sscanf: proc
extern fopen: proc
extern fclose: proc
extern printf: proc
extern gets: proc

public start
.data
		choose_base db ' Introduceti baza de numeratie:' , 13, 10, 0
		message db 'Introduceti o expresie:', 13, 10, 0;INPUT MESSAGE
		invalidMess db 'Invalid Input', 13, 10, 0
		formatc db "%c", 0
		formatd db "%d",13, 10, 0
		formats db "%s", 0
		formatH db "%X",13, 10, 0
		format_exp1 db "%d%c%d%c", 0
		format_exp2 db "%c%d%c", 0
		format_hexp1 db "%X %s %X %c", 13, 10, 0
		format_hexp2 db "%s %X %c", 13, 10, 0
	line db " 1  ",0
	line2 db " 2 ",0
		base db 0
		nr db 0
		op db 0
		egal db 0
		n1 dd 0
		n2 dd 0
		rez dd 0
		equal db 2  dup(0)
		sir db 20 dup(0)
		char db 0
		op_binar db 10 dup(0)
		first_gets db 1
	
			
.code
isExit proc
	cmp sir[0], 'e'
	jne continue
	cmp sir[1], 'x'
	je next
	call invalidMessage
	next:
	cmp sir[2], 'i'
	je next2
	call invalidMessage
	next2:
	cmp sir[3], 't'
	je finish
	continue:
	ret
isExit endp
	
	
isNum proc
cmp sir[0], 'n'
	jne _continue
	cmp sir[1], 'u'
	je _next
	call invalidMessage
	_next:
	cmp sir[2], 'm'
	je start
	call invalidMessage
	_continue: 
	ret
isNum endp

invalidMessage proc
	push offset invalidMess
	call printf
	add esp, 4
	jmp finish
	ret
invalidMessage endp

isDigit proc
			cmp char,'0'
			jl exp2	;if( (buffer>=48) && (buffer<=57) )
			cmp char, '9'	;then continue
			jg exp2
			 ret
isDigit endp

isDigitH proc
			cmp char,'0'
			jl exp2h	;if( (buffer>=48) && (buffer<=57) )
			cmp char, '9'	;then continue
			jg exp2h
			 ret
isDigitH endp

operatie proc 

		cmp op, '+'
	jne l_sub
	add eax, ebx
	ret
	l_sub:
	cmp op, '-'
	jne l_div
	sub eax, ebx
	ret
	l_div:
	cmp op, '/'
	jne l_mul
	mov edx, 0
	div ebx
	ret
	l_mul:
	cmp op, '*'
	je here
	call invalidMessage
	here:
	mov edx, 0
	mul n2
	ret

operatie endp

operatie_hexa proc 

		cmp op_binar[0], '+'
	jne l_subA
	add eax, ebx
	ret
	l_subA:
	cmp op_binar[0], '-'
	jne l_divA
	sub eax, ebx
	ret
	l_divA:
	cmp op_binar[0], '/'
	jne l_mulA
	mov edx, 0
	div ebx
	ret
	l_mulA:
	cmp op_binar[0], '*'
	jne l_and_
	mov edx, 0
	mul n2
	ret
	l_and_:
	cmp op_binar[0], 'A'
	jne l_or_
	cmp op_binar[1], 'N'
	je checkD
	call invalidMessage
	checkD:
	cmp op_binar[2], 'D'
	je do_and
	call invalidMessage
	do_and:
	and eax, ebx
	ret
	l_or_:
	cmp op_binar[0], 'O'
	jne l_xor_
	cmp op_binar[1], 'R'
	je do_or
	call invalidMessage
	do_or:
	or eax, ebx
	ret
	l_xor_: 
		cmp op_binar[0], 'X'
	jne l_not_
	cmp op_binar[1], 'O'
	je checkR_xor
	call invalidMessage
	checkR_xor:
	cmp op_binar[2], 'R'
	je do_xor
	call invalidMessage
	do_xor:
	xor eax, ebx
	ret
	l_not_: 
	cmp sir[0], 'N'
	jne quit
	cmp op_binar[0], 'N'
	jne quit
	cmp op_binar[1], 'O'
	jne quit
	cmp op_binar[2], 'T'
	jne quit
	mov eax, n2
	not eax
	ret
	quit:
	call invalidMessage
	ret
operatie_hexa endp

getbase proc
push offset base
push offset formatc
call scanf
add esp, 8
mov rez, 0

cmp base, 'D'
je again1

cmp base, 'H'
je again2
ret
getbase endp
;--------------------------------------------
start:

push offset choose_base
call printf
add esp, 4


mov first_gets, 1
call getbase


again1:

mov equal, 0
push offset message
call printf
add esp, 4

;push offset sir
;push offset formats
;call scanf
;add esp, 12

cmp first_gets, 1
jne skip1

push offset sir
call gets
add esp, 4
mov first_gets, 0

skip1:
push offset sir
call gets
add esp, 4
mov first_gets, 0

mov edi, 0
mov al, sir[edi]
mov char, al
call isExit
call isNum
call isDigit

exp1:
push offset equal
push offset n2
push offset op
push offset n1
push offset format_exp1
push offset sir
call sscanf
add esp, 36

	mov eax,n1
	mov ebx,n2
	
call operatie

	jmp print
	
exp2:
push offset equal
push offset n2
push offset op
push offset format_exp2
push offset sir
call sscanf
add esp, 32

mov eax,rez
	mov ebx,n2
	
call operatie
print:


mov rez, eax
mov eax, 0
mov ebx, 0
cmp equal, '='

jne again1
	push  rez
	push offset formatd
	call printf
	add esp,8
jmp again1

;----------

again2:

mov equal, 0
push offset message
call printf
add esp, 4

cmp first_gets, 1
jne skip

push offset sir
call gets
add esp, 4
mov first_gets, 0

skip:
push offset sir
call gets
add esp, 4

mov edi, 0
mov al, sir[edi]
mov char, al

call isExit
call isNum
call isDigitH

exp1h:
mov rez, 0

push offset equal
push offset n2
push offset op_binar
push offset n1
push offset format_hexp1
push offset sir
call sscanf
add esp, 28


	mov eax,n1
	mov ebx,n2
	
call operatie_hexa

jmp print2
	
exp2h:
push offset equal
push offset n2
push offset op_binar
push offset format_hexp2
push offset sir
call sscanf
add esp, 32

mov eax,rez
	mov ebx,n2
	
call operatie_hexa

print2:

mov rez, eax
mov eax, 0
mov ebx, 0

cmp equal, '='

jne again2
	push  rez
	push offset formatH
	call printf
	add esp,8
jmp again2
finish:
		push 0
		call exit
		end start