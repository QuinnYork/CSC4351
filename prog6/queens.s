PROCEDURE tigermain
LABEL L49
L50:
	li t72 8
	sw t72 -4+tigermain_framesize($sp)
	add t73 $sp -8+tigermain_framesize
	move t65 t73
	lw t74 -4+tigermain_framesize($sp)
	move $a0 t74
	move $a1 $0
	jal _initArray
	move t64 $v0
	sw t64 (t65)
	add t75 $sp -12+tigermain_framesize
	move t67 t75
	lw t76 -4+tigermain_framesize($sp)
	move $a0 t76
	move $a1 $0
	jal _initArray
	move t66 $v0
	sw t66 (t67)
	add t77 $sp -16+tigermain_framesize
	move t69 t77
	lw t80 -4+tigermain_framesize($sp)
	lw t81 -4+tigermain_framesize($sp)
	add t79 t80 t81
	sub t78 t79 1
	move $a0 t78
	move $a1 $0
	jal _initArray
	move t68 $v0
	sw t68 (t69)
	add t82 $sp -20+tigermain_framesize
	move t71 t82
	lw t85 -4+tigermain_framesize($sp)
	lw t86 -4+tigermain_framesize($sp)
	add t84 t85 t86
	sub t83 t84 1
	move $a0 t83
	move $a1 $0
	jal _initArray
	move t70 $v0
	sw t70 (t71)
	addu t87 $sp tigermain_framesize
	move $a0 t87
	move $a1 $0
	jal tigermain.try.1
	b L49
L49:
END tigermain
PROCEDURE tigermain.try.1
LABEL L51
L52:
	lw t89 0+tigermain.try.1_framesize($sp)
	lw t88 -4(t89)
	beq t40 t88 L46
L47:
	move t41 $0
	lw t92 0+tigermain.try.1_framesize($sp)
	lw t91 -4(t92)
	sub t90 t91 1
	move t62 t90
	ble t41 t62 L44
L14:
L48:
	b L51
L46:
	lw t93 0+tigermain.try.1_framesize($sp)
	move $a0 t93
	jal tigermain.printboard.0
	b L48
L44:
	lw t95 0+tigermain.try.1_framesize($sp)
	lw t94 -8(t95)
	move t42 t94
	move t43 t41
	blt t43 0 _BADSUB
L15:
	lw t96 -4(t42)
	bgt t43 t96 _BADSUB
L16:
	sll t99 t43 2
	add t98 t42 t99
	lw t97 (t98)
	beq t97 0 L19
L42:
L43:
	bge t41 t62 L14
L45:
	add t100 t41 1
	move t41 t100
	b L44
L19:
	lw t102 0+tigermain.try.1_framesize($sp)
	lw t101 -16(t102)
	move t44 t101
	add t103 t41 t40
	move t45 t103
	blt t45 0 _BADSUB
L17:
	lw t104 -4(t44)
	bgt t45 t104 _BADSUB
L18:
	sll t107 t45 2
	add t106 t44 t107
	lw t105 (t106)
	bne t105 0 L42
L24:
	lw t109 0+tigermain.try.1_framesize($sp)
	lw t108 -20(t109)
	move t46 t108
	add t111 t41 7
	sub t110 t111 t40
	move t47 t110
	blt t47 0 _BADSUB
L22:
	lw t112 -4(t46)
	bgt t47 t112 _BADSUB
L23:
	sll t115 t47 2
	add t114 t46 t115
	lw t113 (t114)
	bne t113 0 L42
L41:
	lw t117 0+tigermain.try.1_framesize($sp)
	lw t116 -8(t117)
	move t48 t116
	move t49 t41
	blt t49 0 _BADSUB
L27:
	lw t118 -4(t48)
	bgt t49 t118 _BADSUB
L28:
	sll t121 t49 2
	add t120 t48 t121
	lw t119 (t120)
	li t122 1
	sw t122 (t119)
	lw t124 0+tigermain.try.1_framesize($sp)
	lw t123 -16(t124)
	move t50 t123
	add t125 t41 t40
	move t51 t125
	blt t51 0 _BADSUB
L29:
	lw t126 -4(t50)
	bgt t51 t126 _BADSUB
L30:
	sll t129 t51 2
	add t128 t50 t129
	lw t127 (t128)
	li t130 1
	sw t130 (t127)
	lw t132 0+tigermain.try.1_framesize($sp)
	lw t131 -20(t132)
	move t52 t131
	add t134 t41 7
	sub t133 t134 t40
	move t53 t133
	blt t53 0 _BADSUB
L31:
	lw t135 -4(t52)
	bgt t53 t135 _BADSUB
L32:
	sll t138 t53 2
	add t137 t52 t138
	lw t136 (t137)
	li t139 1
	sw t139 (t136)
	lw t141 0+tigermain.try.1_framesize($sp)
	lw t140 -12(t141)
	move t54 t140
	move t55 t40
	blt t55 0 _BADSUB
L33:
	lw t142 -4(t54)
	bgt t55 t142 _BADSUB
L34:
	sll t145 t55 2
	add t144 t54 t145
	lw t143 (t144)
	sw t41 (t143)
	lw t146 0+tigermain.try.1_framesize($sp)
	move $a0 t146
	add t147 t40 1
	move $a1 t147
	jal tigermain.try.1
	lw t149 0+tigermain.try.1_framesize($sp)
	lw t148 -8(t149)
	move t56 t148
	move t57 t41
	blt t57 0 _BADSUB
L35:
	lw t150 -4(t56)
	bgt t57 t150 _BADSUB
L36:
	sll t153 t57 2
	add t152 t56 t153
	lw t151 (t152)
	sw $0 (t151)
	lw t155 0+tigermain.try.1_framesize($sp)
	lw t154 -16(t155)
	move t58 t154
	add t156 t41 t40
	move t59 t156
	blt t59 0 _BADSUB
L37:
	lw t157 -4(t58)
	bgt t59 t157 _BADSUB
L38:
	sll t160 t59 2
	add t159 t58 t160
	lw t158 (t159)
	sw $0 (t158)
	lw t162 0+tigermain.try.1_framesize($sp)
	lw t161 -20(t162)
	move t60 t161
	add t164 t41 7
	sub t163 t164 t40
	move t61 t163
	blt t61 0 _BADSUB
L39:
	lw t165 -4(t60)
	bgt t61 t165 _BADSUB
L40:
	sll t168 t61 2
	add t167 t60 t168
	lw t166 (t167)
	sw $0 (t166)
	b L43
L51:
END tigermain.try.1
PROCEDURE tigermain.printboard.0
LABEL L53
L54:
	move t33 $0
	lw t171 0+tigermain.printboard.0_framesize($sp)
	lw t170 -4(t171)
	sub t169 t170 1
	move t39 t169
	ble t33 t39 L12
L0:
	la t172 L11
	move $a0 t172
	jal _print
	b L53
L12:
	move t34 $0
	lw t175 0+tigermain.printboard.0_framesize($sp)
	lw t174 -4(t175)
	sub t173 t174 1
	move t38 t173
	ble t34 t38 L9
L1:
	la t176 L11
	move $a0 t176
	jal _print
	bge t33 t39 L0
L13:
	add t177 t33 1
	move t33 t177
	b L12
L9:
	lw t179 0+tigermain.printboard.0_framesize($sp)
	lw t178 -12(t179)
	move t35 t178
	move t36 t33
	blt t36 0 _BADSUB
L2:
	lw t180 -4(t35)
	bgt t36 t180 _BADSUB
L3:
	sll t183 t36 2
	add t182 t35 t183
	lw t181 (t182)
	beq t181 t34 L6
L7:
	la t184 L5
	move t37 t184
L8:
	move $a0 t37
	jal _print
	bge t34 t38 L1
L10:
	add t185 t34 1
	move t34 t185
	b L9
L6:
	la t186 L4
	move t37 t186
	b L8
L53:
END tigermain.printboard.0
	.data
	.word 1
L11:	.asciiz	"\n"
	.data
	.word 2
L5:	.asciiz	" ."
	.data
	.word 2
L4:	.asciiz	" O"
