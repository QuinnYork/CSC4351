PROCEDURE tigermain
# Before canonicalization: 
SEQ(
 SEQ(
  SEQ(
   SEQ(
    SEQ(
     MOVE(
      MEM(
       BINOP(PLUS,
        TEMP $fp,
        CONST -4)),
      CONST 8),
     MOVE(
      MEM(
       BINOP(PLUS,
        TEMP $fp,
        CONST -8)),
      CALL(
       NAME _initArray,
        MEM(
         BINOP(PLUS,
          TEMP $fp,
          CONST -4)),
        CONST 0))),
    MOVE(
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -12)),
     CALL(
      NAME _initArray,
       MEM(
        BINOP(PLUS,
         TEMP $fp,
         CONST -4)),
       CONST 0))),
   MOVE(
    MEM(
     BINOP(PLUS,
      TEMP $fp,
      CONST -16)),
    CALL(
     NAME _initArray,
      BINOP(MINUS,
       BINOP(PLUS,
        MEM(
         BINOP(PLUS,
          TEMP $fp,
          CONST -4)),
        MEM(
         BINOP(PLUS,
          TEMP $fp,
          CONST -4))),
       CONST 1),
      CONST 0))),
  MOVE(
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST -20)),
   CALL(
    NAME _initArray,
     BINOP(MINUS,
      BINOP(PLUS,
       MEM(
        BINOP(PLUS,
         TEMP $fp,
         CONST -4)),
       MEM(
        BINOP(PLUS,
         TEMP $fp,
         CONST -4))),
      CONST 1),
     CONST 0))),
 EXP(
  CALL(
   NAME tigermain.try.1,
    TEMP $fp,
    CONST 0)))
# After canonicalization: 
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP $fp,
   CONST -4)),
 CONST 8)
MOVE(
 TEMP t65,
 BINOP(PLUS,
  TEMP $fp,
  CONST -8))
MOVE(
 TEMP t64,
 CALL(
  NAME _initArray,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST -4)),
   CONST 0))
MOVE(
 MEM(
  TEMP t65),
 TEMP t64)
MOVE(
 TEMP t67,
 BINOP(PLUS,
  TEMP $fp,
  CONST -12))
MOVE(
 TEMP t66,
 CALL(
  NAME _initArray,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST -4)),
   CONST 0))
MOVE(
 MEM(
  TEMP t67),
 TEMP t66)
MOVE(
 TEMP t69,
 BINOP(PLUS,
  TEMP $fp,
  CONST -16))
MOVE(
 TEMP t68,
 CALL(
  NAME _initArray,
   BINOP(MINUS,
    BINOP(PLUS,
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4)),
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4))),
    CONST 1),
   CONST 0))
MOVE(
 MEM(
  TEMP t69),
 TEMP t68)
MOVE(
 TEMP t71,
 BINOP(PLUS,
  TEMP $fp,
  CONST -20))
MOVE(
 TEMP t70,
 CALL(
  NAME _initArray,
   BINOP(MINUS,
    BINOP(PLUS,
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4)),
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4))),
    CONST 1),
   CONST 0))
MOVE(
 MEM(
  TEMP t71),
 TEMP t70)
EXP(
 CALL(
  NAME tigermain.try.1,
   TEMP $fp,
   CONST 0))
# Basic Blocks: 
#
LABEL L50
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP $fp,
   CONST -4)),
 CONST 8)
MOVE(
 TEMP t65,
 BINOP(PLUS,
  TEMP $fp,
  CONST -8))
MOVE(
 TEMP t64,
 CALL(
  NAME _initArray,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST -4)),
   CONST 0))
MOVE(
 MEM(
  TEMP t65),
 TEMP t64)
MOVE(
 TEMP t67,
 BINOP(PLUS,
  TEMP $fp,
  CONST -12))
MOVE(
 TEMP t66,
 CALL(
  NAME _initArray,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST -4)),
   CONST 0))
MOVE(
 MEM(
  TEMP t67),
 TEMP t66)
MOVE(
 TEMP t69,
 BINOP(PLUS,
  TEMP $fp,
  CONST -16))
MOVE(
 TEMP t68,
 CALL(
  NAME _initArray,
   BINOP(MINUS,
    BINOP(PLUS,
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4)),
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4))),
    CONST 1),
   CONST 0))
MOVE(
 MEM(
  TEMP t69),
 TEMP t68)
MOVE(
 TEMP t71,
 BINOP(PLUS,
  TEMP $fp,
  CONST -20))
MOVE(
 TEMP t70,
 CALL(
  NAME _initArray,
   BINOP(MINUS,
    BINOP(PLUS,
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4)),
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4))),
    CONST 1),
   CONST 0))
MOVE(
 MEM(
  TEMP t71),
 TEMP t70)
EXP(
 CALL(
  NAME tigermain.try.1,
   TEMP $fp,
   CONST 0))
JUMP(
 NAME L49)
LABEL L49
# Trace Scheduled: 
LABEL L50
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP $fp,
   CONST -4)),
 CONST 8)
MOVE(
 TEMP t65,
 BINOP(PLUS,
  TEMP $fp,
  CONST -8))
MOVE(
 TEMP t64,
 CALL(
  NAME _initArray,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST -4)),
   CONST 0))
MOVE(
 MEM(
  TEMP t65),
 TEMP t64)
MOVE(
 TEMP t67,
 BINOP(PLUS,
  TEMP $fp,
  CONST -12))
MOVE(
 TEMP t66,
 CALL(
  NAME _initArray,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST -4)),
   CONST 0))
MOVE(
 MEM(
  TEMP t67),
 TEMP t66)
MOVE(
 TEMP t69,
 BINOP(PLUS,
  TEMP $fp,
  CONST -16))
MOVE(
 TEMP t68,
 CALL(
  NAME _initArray,
   BINOP(MINUS,
    BINOP(PLUS,
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4)),
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4))),
    CONST 1),
   CONST 0))
MOVE(
 MEM(
  TEMP t69),
 TEMP t68)
MOVE(
 TEMP t71,
 BINOP(PLUS,
  TEMP $fp,
  CONST -20))
MOVE(
 TEMP t70,
 CALL(
  NAME _initArray,
   BINOP(MINUS,
    BINOP(PLUS,
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4)),
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST -4))),
    CONST 1),
   CONST 0))
MOVE(
 MEM(
  TEMP t71),
 TEMP t70)
EXP(
 CALL(
  NAME tigermain.try.1,
   TEMP $fp,
   CONST 0))
JUMP(
 NAME L49)
LABEL L49
# Instructions: 
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
# Before canonicalization: 
SEQ(
 SEQ(
  CJUMP(EQ,
   TEMP t40,
   MEM(
    BINOP(PLUS,
     MEM(
      BINOP(PLUS,
       TEMP $fp,
       CONST 0)),
     CONST -4)),
   L46,L47),
  SEQ(
   SEQ(
    SEQ(
     LABEL L46,
     EXP(
      CALL(
       NAME tigermain.printboard.0,
        MEM(
         BINOP(PLUS,
          TEMP $fp,
          CONST 0))))),
    JUMP(
     NAME L48)),
   SEQ(
    SEQ(
     LABEL L47,
     SEQ(
      SEQ(
       SEQ(
        SEQ(
         MOVE(
          TEMP t41,
          CONST 0),
         MOVE(
          TEMP t62,
          BINOP(MINUS,
           MEM(
            BINOP(PLUS,
             MEM(
              BINOP(PLUS,
               TEMP $fp,
               CONST 0)),
             CONST -4)),
           CONST 1))),
        CJUMP(LE,
         TEMP t41,
         TEMP t62,
         L44,L14)),
       SEQ(
        SEQ(
         SEQ(
          LABEL L44,
          SEQ(
           SEQ(
            SEQ(
             SEQ(
              CJUMP(EQ,
               ESEQ(
                SEQ(
                 MOVE(
                  TEMP t42,
                  MEM(
                   BINOP(PLUS,
                    MEM(
                     BINOP(PLUS,
                      TEMP $fp,
                      CONST 0)),
                    CONST -8))),
                 SEQ(
                  MOVE(
                   TEMP t43,
                   TEMP t41),
                  SEQ(
                   CJUMP(LT,
                    TEMP t43,
                    CONST 0,
                    _BADSUB,L15),
                   SEQ(
                    LABEL L15,
                    SEQ(
                     CJUMP(GT,
                      TEMP t43,
                      MEM(
                       BINOP(PLUS,
                        TEMP t42,
                        CONST -4)),
                      _BADSUB,L16),
                     LABEL L16))))),
                MEM(
                 BINOP(PLUS,
                  TEMP t42,
                  BINOP(MUL,
                   TEMP t43,
                   CONST 4)))),
               CONST 0,
               L19,L42),
              SEQ(
               LABEL L19,
               CJUMP(EQ,
                ESEQ(
                 SEQ(
                  MOVE(
                   TEMP t44,
                   MEM(
                    BINOP(PLUS,
                     MEM(
                      BINOP(PLUS,
                       TEMP $fp,
                       CONST 0)),
                     CONST -16))),
                  SEQ(
                   MOVE(
                    TEMP t45,
                    BINOP(PLUS,
                     TEMP t41,
                     TEMP t40)),
                   SEQ(
                    CJUMP(LT,
                     TEMP t45,
                     CONST 0,
                     _BADSUB,L17),
                    SEQ(
                     LABEL L17,
                     SEQ(
                      CJUMP(GT,
                       TEMP t45,
                       MEM(
                        BINOP(PLUS,
                         TEMP t44,
                         CONST -4)),
                       _BADSUB,L18),
                      LABEL L18))))),
                 MEM(
                  BINOP(PLUS,
                   TEMP t44,
                   BINOP(MUL,
                    TEMP t45,
                    CONST 4)))),
                CONST 0,
                L24,L42))),
             SEQ(
              LABEL L24,
              CJUMP(EQ,
               ESEQ(
                SEQ(
                 MOVE(
                  TEMP t46,
                  MEM(
                   BINOP(PLUS,
                    MEM(
                     BINOP(PLUS,
                      TEMP $fp,
                      CONST 0)),
                    CONST -20))),
                 SEQ(
                  MOVE(
                   TEMP t47,
                   BINOP(MINUS,
                    BINOP(PLUS,
                     TEMP t41,
                     CONST 7),
                    TEMP t40)),
                  SEQ(
                   CJUMP(LT,
                    TEMP t47,
                    CONST 0,
                    _BADSUB,L22),
                   SEQ(
                    LABEL L22,
                    SEQ(
                     CJUMP(GT,
                      TEMP t47,
                      MEM(
                       BINOP(PLUS,
                        TEMP t46,
                        CONST -4)),
                      _BADSUB,L23),
                     LABEL L23))))),
                MEM(
                 BINOP(PLUS,
                  TEMP t46,
                  BINOP(MUL,
                   TEMP t47,
                   CONST 4)))),
               CONST 0,
               L41,L42))),
            SEQ(
             SEQ(
              SEQ(
               LABEL L41,
               SEQ(
                SEQ(
                 SEQ(
                  SEQ(
                   SEQ(
                    SEQ(
                     SEQ(
                      MOVE(
                       ESEQ(
                        SEQ(
                         MOVE(
                          TEMP t48,
                          MEM(
                           BINOP(PLUS,
                            MEM(
                             BINOP(PLUS,
                              TEMP $fp,
                              CONST 0)),
                            CONST -8))),
                         SEQ(
                          MOVE(
                           TEMP t49,
                           TEMP t41),
                          SEQ(
                           CJUMP(LT,
                            TEMP t49,
                            CONST 0,
                            _BADSUB,L27),
                           SEQ(
                            LABEL L27,
                            SEQ(
                             CJUMP(GT,
                              TEMP t49,
                              MEM(
                               BINOP(PLUS,
                                TEMP t48,
                                CONST -4)),
                              _BADSUB,L28),
                             LABEL L28))))),
                        MEM(
                         BINOP(PLUS,
                          TEMP t48,
                          BINOP(MUL,
                           TEMP t49,
                           CONST 4)))),
                       CONST 1),
                      MOVE(
                       ESEQ(
                        SEQ(
                         MOVE(
                          TEMP t50,
                          MEM(
                           BINOP(PLUS,
                            MEM(
                             BINOP(PLUS,
                              TEMP $fp,
                              CONST 0)),
                            CONST -16))),
                         SEQ(
                          MOVE(
                           TEMP t51,
                           BINOP(PLUS,
                            TEMP t41,
                            TEMP t40)),
                          SEQ(
                           CJUMP(LT,
                            TEMP t51,
                            CONST 0,
                            _BADSUB,L29),
                           SEQ(
                            LABEL L29,
                            SEQ(
                             CJUMP(GT,
                              TEMP t51,
                              MEM(
                               BINOP(PLUS,
                                TEMP t50,
                                CONST -4)),
                              _BADSUB,L30),
                             LABEL L30))))),
                        MEM(
                         BINOP(PLUS,
                          TEMP t50,
                          BINOP(MUL,
                           TEMP t51,
                           CONST 4)))),
                       CONST 1)),
                     MOVE(
                      ESEQ(
                       SEQ(
                        MOVE(
                         TEMP t52,
                         MEM(
                          BINOP(PLUS,
                           MEM(
                            BINOP(PLUS,
                             TEMP $fp,
                             CONST 0)),
                           CONST -20))),
                        SEQ(
                         MOVE(
                          TEMP t53,
                          BINOP(MINUS,
                           BINOP(PLUS,
                            TEMP t41,
                            CONST 7),
                           TEMP t40)),
                         SEQ(
                          CJUMP(LT,
                           TEMP t53,
                           CONST 0,
                           _BADSUB,L31),
                          SEQ(
                           LABEL L31,
                           SEQ(
                            CJUMP(GT,
                             TEMP t53,
                             MEM(
                              BINOP(PLUS,
                               TEMP t52,
                               CONST -4)),
                             _BADSUB,L32),
                            LABEL L32))))),
                       MEM(
                        BINOP(PLUS,
                         TEMP t52,
                         BINOP(MUL,
                          TEMP t53,
                          CONST 4)))),
                      CONST 1)),
                    MOVE(
                     ESEQ(
                      SEQ(
                       MOVE(
                        TEMP t54,
                        MEM(
                         BINOP(PLUS,
                          MEM(
                           BINOP(PLUS,
                            TEMP $fp,
                            CONST 0)),
                          CONST -12))),
                       SEQ(
                        MOVE(
                         TEMP t55,
                         TEMP t40),
                        SEQ(
                         CJUMP(LT,
                          TEMP t55,
                          CONST 0,
                          _BADSUB,L33),
                         SEQ(
                          LABEL L33,
                          SEQ(
                           CJUMP(GT,
                            TEMP t55,
                            MEM(
                             BINOP(PLUS,
                              TEMP t54,
                              CONST -4)),
                            _BADSUB,L34),
                           LABEL L34))))),
                      MEM(
                       BINOP(PLUS,
                        TEMP t54,
                        BINOP(MUL,
                         TEMP t55,
                         CONST 4)))),
                     TEMP t41)),
                   EXP(
                    CALL(
                     NAME tigermain.try.1,
                      MEM(
                       BINOP(PLUS,
                        TEMP $fp,
                        CONST 0)),
                      BINOP(PLUS,
                       TEMP t40,
                       CONST 1)))),
                  MOVE(
                   ESEQ(
                    SEQ(
                     MOVE(
                      TEMP t56,
                      MEM(
                       BINOP(PLUS,
                        MEM(
                         BINOP(PLUS,
                          TEMP $fp,
                          CONST 0)),
                        CONST -8))),
                     SEQ(
                      MOVE(
                       TEMP t57,
                       TEMP t41),
                      SEQ(
                       CJUMP(LT,
                        TEMP t57,
                        CONST 0,
                        _BADSUB,L35),
                       SEQ(
                        LABEL L35,
                        SEQ(
                         CJUMP(GT,
                          TEMP t57,
                          MEM(
                           BINOP(PLUS,
                            TEMP t56,
                            CONST -4)),
                          _BADSUB,L36),
                         LABEL L36))))),
                    MEM(
                     BINOP(PLUS,
                      TEMP t56,
                      BINOP(MUL,
                       TEMP t57,
                       CONST 4)))),
                   CONST 0)),
                 MOVE(
                  ESEQ(
                   SEQ(
                    MOVE(
                     TEMP t58,
                     MEM(
                      BINOP(PLUS,
                       MEM(
                        BINOP(PLUS,
                         TEMP $fp,
                         CONST 0)),
                       CONST -16))),
                    SEQ(
                     MOVE(
                      TEMP t59,
                      BINOP(PLUS,
                       TEMP t41,
                       TEMP t40)),
                     SEQ(
                      CJUMP(LT,
                       TEMP t59,
                       CONST 0,
                       _BADSUB,L37),
                      SEQ(
                       LABEL L37,
                       SEQ(
                        CJUMP(GT,
                         TEMP t59,
                         MEM(
                          BINOP(PLUS,
                           TEMP t58,
                           CONST -4)),
                         _BADSUB,L38),
                        LABEL L38))))),
                   MEM(
                    BINOP(PLUS,
                     TEMP t58,
                     BINOP(MUL,
                      TEMP t59,
                      CONST 4)))),
                  CONST 0)),
                MOVE(
                 ESEQ(
                  SEQ(
                   MOVE(
                    TEMP t60,
                    MEM(
                     BINOP(PLUS,
                      MEM(
                       BINOP(PLUS,
                        TEMP $fp,
                        CONST 0)),
                      CONST -20))),
                   SEQ(
                    MOVE(
                     TEMP t61,
                     BINOP(MINUS,
                      BINOP(PLUS,
                       TEMP t41,
                       CONST 7),
                      TEMP t40)),
                    SEQ(
                     CJUMP(LT,
                      TEMP t61,
                      CONST 0,
                      _BADSUB,L39),
                     SEQ(
                      LABEL L39,
                      SEQ(
                       CJUMP(GT,
                        TEMP t61,
                        MEM(
                         BINOP(PLUS,
                          TEMP t60,
                          CONST -4)),
                        _BADSUB,L40),
                       LABEL L40))))),
                  MEM(
                   BINOP(PLUS,
                    TEMP t60,
                    BINOP(MUL,
                     TEMP t61,
                     CONST 4)))),
                 CONST 0))),
              JUMP(
               NAME L43)),
             SEQ(
              SEQ(
               LABEL L42,
               EXP(
                CONST 0)),
              JUMP(
               NAME L43)))),
           LABEL L43)),
         CJUMP(LT,
          TEMP t41,
          TEMP t62,
          L45,L14)),
        SEQ(
         SEQ(
          LABEL L45,
          MOVE(
           TEMP t41,
           BINOP(PLUS,
            TEMP t41,
            CONST 1))),
         JUMP(
          NAME L44)))),
      LABEL L14)),
    JUMP(
     NAME L48)))),
 LABEL L48)
# After canonicalization: 
CJUMP(EQ,
 TEMP t40,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -4)),
 L46,L47)
LABEL L46
EXP(
 CALL(
  NAME tigermain.printboard.0,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0))))
JUMP(
 NAME L48)
LABEL L47
MOVE(
 TEMP t41,
 CONST 0)
MOVE(
 TEMP t62,
 BINOP(MINUS,
  MEM(
   BINOP(PLUS,
    MEM(
     BINOP(PLUS,
      TEMP $fp,
      CONST 0)),
    CONST -4)),
  CONST 1))
CJUMP(LE,
 TEMP t41,
 TEMP t62,
 L44,L14)
LABEL L44
MOVE(
 TEMP t42,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -8)))
MOVE(
 TEMP t43,
 TEMP t41)
CJUMP(LT,
 TEMP t43,
 CONST 0,
 _BADSUB,L15)
LABEL L15
CJUMP(GT,
 TEMP t43,
 MEM(
  BINOP(PLUS,
   TEMP t42,
   CONST -4)),
 _BADSUB,L16)
LABEL L16
CJUMP(EQ,
 MEM(
  BINOP(PLUS,
   TEMP t42,
   BINOP(MUL,
    TEMP t43,
    CONST 4))),
 CONST 0,
 L19,L42)
LABEL L19
MOVE(
 TEMP t44,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -16)))
MOVE(
 TEMP t45,
 BINOP(PLUS,
  TEMP t41,
  TEMP t40))
CJUMP(LT,
 TEMP t45,
 CONST 0,
 _BADSUB,L17)
LABEL L17
CJUMP(GT,
 TEMP t45,
 MEM(
  BINOP(PLUS,
   TEMP t44,
   CONST -4)),
 _BADSUB,L18)
LABEL L18
CJUMP(EQ,
 MEM(
  BINOP(PLUS,
   TEMP t44,
   BINOP(MUL,
    TEMP t45,
    CONST 4))),
 CONST 0,
 L24,L42)
LABEL L24
MOVE(
 TEMP t46,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -20)))
MOVE(
 TEMP t47,
 BINOP(MINUS,
  BINOP(PLUS,
   TEMP t41,
   CONST 7),
  TEMP t40))
CJUMP(LT,
 TEMP t47,
 CONST 0,
 _BADSUB,L22)
LABEL L22
CJUMP(GT,
 TEMP t47,
 MEM(
  BINOP(PLUS,
   TEMP t46,
   CONST -4)),
 _BADSUB,L23)
LABEL L23
CJUMP(EQ,
 MEM(
  BINOP(PLUS,
   TEMP t46,
   BINOP(MUL,
    TEMP t47,
    CONST 4))),
 CONST 0,
 L41,L42)
LABEL L41
MOVE(
 TEMP t48,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -8)))
MOVE(
 TEMP t49,
 TEMP t41)
CJUMP(LT,
 TEMP t49,
 CONST 0,
 _BADSUB,L27)
LABEL L27
CJUMP(GT,
 TEMP t49,
 MEM(
  BINOP(PLUS,
   TEMP t48,
   CONST -4)),
 _BADSUB,L28)
LABEL L28
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t48,
   BINOP(MUL,
    TEMP t49,
    CONST 4))),
 CONST 1)
MOVE(
 TEMP t50,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -16)))
MOVE(
 TEMP t51,
 BINOP(PLUS,
  TEMP t41,
  TEMP t40))
CJUMP(LT,
 TEMP t51,
 CONST 0,
 _BADSUB,L29)
LABEL L29
CJUMP(GT,
 TEMP t51,
 MEM(
  BINOP(PLUS,
   TEMP t50,
   CONST -4)),
 _BADSUB,L30)
LABEL L30
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t50,
   BINOP(MUL,
    TEMP t51,
    CONST 4))),
 CONST 1)
MOVE(
 TEMP t52,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -20)))
MOVE(
 TEMP t53,
 BINOP(MINUS,
  BINOP(PLUS,
   TEMP t41,
   CONST 7),
  TEMP t40))
CJUMP(LT,
 TEMP t53,
 CONST 0,
 _BADSUB,L31)
LABEL L31
CJUMP(GT,
 TEMP t53,
 MEM(
  BINOP(PLUS,
   TEMP t52,
   CONST -4)),
 _BADSUB,L32)
LABEL L32
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t52,
   BINOP(MUL,
    TEMP t53,
    CONST 4))),
 CONST 1)
MOVE(
 TEMP t54,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -12)))
MOVE(
 TEMP t55,
 TEMP t40)
CJUMP(LT,
 TEMP t55,
 CONST 0,
 _BADSUB,L33)
LABEL L33
CJUMP(GT,
 TEMP t55,
 MEM(
  BINOP(PLUS,
   TEMP t54,
   CONST -4)),
 _BADSUB,L34)
LABEL L34
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t54,
   BINOP(MUL,
    TEMP t55,
    CONST 4))),
 TEMP t41)
EXP(
 CALL(
  NAME tigermain.try.1,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   BINOP(PLUS,
    TEMP t40,
    CONST 1)))
MOVE(
 TEMP t56,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -8)))
MOVE(
 TEMP t57,
 TEMP t41)
CJUMP(LT,
 TEMP t57,
 CONST 0,
 _BADSUB,L35)
LABEL L35
CJUMP(GT,
 TEMP t57,
 MEM(
  BINOP(PLUS,
   TEMP t56,
   CONST -4)),
 _BADSUB,L36)
LABEL L36
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t56,
   BINOP(MUL,
    TEMP t57,
    CONST 4))),
 CONST 0)
MOVE(
 TEMP t58,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -16)))
MOVE(
 TEMP t59,
 BINOP(PLUS,
  TEMP t41,
  TEMP t40))
CJUMP(LT,
 TEMP t59,
 CONST 0,
 _BADSUB,L37)
LABEL L37
CJUMP(GT,
 TEMP t59,
 MEM(
  BINOP(PLUS,
   TEMP t58,
   CONST -4)),
 _BADSUB,L38)
LABEL L38
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t58,
   BINOP(MUL,
    TEMP t59,
    CONST 4))),
 CONST 0)
MOVE(
 TEMP t60,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -20)))
MOVE(
 TEMP t61,
 BINOP(MINUS,
  BINOP(PLUS,
   TEMP t41,
   CONST 7),
  TEMP t40))
CJUMP(LT,
 TEMP t61,
 CONST 0,
 _BADSUB,L39)
LABEL L39
CJUMP(GT,
 TEMP t61,
 MEM(
  BINOP(PLUS,
   TEMP t60,
   CONST -4)),
 _BADSUB,L40)
LABEL L40
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t60,
   BINOP(MUL,
    TEMP t61,
    CONST 4))),
 CONST 0)
JUMP(
 NAME L43)
LABEL L42
JUMP(
 NAME L43)
LABEL L43
CJUMP(LT,
 TEMP t41,
 TEMP t62,
 L45,L14)
LABEL L45
MOVE(
 TEMP t41,
 BINOP(PLUS,
  TEMP t41,
  CONST 1))
JUMP(
 NAME L44)
LABEL L14
JUMP(
 NAME L48)
LABEL L48
# Basic Blocks: 
#
LABEL L52
CJUMP(EQ,
 TEMP t40,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -4)),
 L46,L47)
#
LABEL L46
EXP(
 CALL(
  NAME tigermain.printboard.0,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0))))
JUMP(
 NAME L48)
#
LABEL L47
MOVE(
 TEMP t41,
 CONST 0)
MOVE(
 TEMP t62,
 BINOP(MINUS,
  MEM(
   BINOP(PLUS,
    MEM(
     BINOP(PLUS,
      TEMP $fp,
      CONST 0)),
    CONST -4)),
  CONST 1))
CJUMP(LE,
 TEMP t41,
 TEMP t62,
 L44,L14)
#
LABEL L44
MOVE(
 TEMP t42,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -8)))
MOVE(
 TEMP t43,
 TEMP t41)
CJUMP(LT,
 TEMP t43,
 CONST 0,
 _BADSUB,L15)
#
LABEL L15
CJUMP(GT,
 TEMP t43,
 MEM(
  BINOP(PLUS,
   TEMP t42,
   CONST -4)),
 _BADSUB,L16)
#
LABEL L16
CJUMP(EQ,
 MEM(
  BINOP(PLUS,
   TEMP t42,
   BINOP(MUL,
    TEMP t43,
    CONST 4))),
 CONST 0,
 L19,L42)
#
LABEL L19
MOVE(
 TEMP t44,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -16)))
MOVE(
 TEMP t45,
 BINOP(PLUS,
  TEMP t41,
  TEMP t40))
CJUMP(LT,
 TEMP t45,
 CONST 0,
 _BADSUB,L17)
#
LABEL L17
CJUMP(GT,
 TEMP t45,
 MEM(
  BINOP(PLUS,
   TEMP t44,
   CONST -4)),
 _BADSUB,L18)
#
LABEL L18
CJUMP(EQ,
 MEM(
  BINOP(PLUS,
   TEMP t44,
   BINOP(MUL,
    TEMP t45,
    CONST 4))),
 CONST 0,
 L24,L42)
#
LABEL L24
MOVE(
 TEMP t46,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -20)))
MOVE(
 TEMP t47,
 BINOP(MINUS,
  BINOP(PLUS,
   TEMP t41,
   CONST 7),
  TEMP t40))
CJUMP(LT,
 TEMP t47,
 CONST 0,
 _BADSUB,L22)
#
LABEL L22
CJUMP(GT,
 TEMP t47,
 MEM(
  BINOP(PLUS,
   TEMP t46,
   CONST -4)),
 _BADSUB,L23)
#
LABEL L23
CJUMP(EQ,
 MEM(
  BINOP(PLUS,
   TEMP t46,
   BINOP(MUL,
    TEMP t47,
    CONST 4))),
 CONST 0,
 L41,L42)
#
LABEL L41
MOVE(
 TEMP t48,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -8)))
MOVE(
 TEMP t49,
 TEMP t41)
CJUMP(LT,
 TEMP t49,
 CONST 0,
 _BADSUB,L27)
#
LABEL L27
CJUMP(GT,
 TEMP t49,
 MEM(
  BINOP(PLUS,
   TEMP t48,
   CONST -4)),
 _BADSUB,L28)
#
LABEL L28
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t48,
   BINOP(MUL,
    TEMP t49,
    CONST 4))),
 CONST 1)
MOVE(
 TEMP t50,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -16)))
MOVE(
 TEMP t51,
 BINOP(PLUS,
  TEMP t41,
  TEMP t40))
CJUMP(LT,
 TEMP t51,
 CONST 0,
 _BADSUB,L29)
#
LABEL L29
CJUMP(GT,
 TEMP t51,
 MEM(
  BINOP(PLUS,
   TEMP t50,
   CONST -4)),
 _BADSUB,L30)
#
LABEL L30
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t50,
   BINOP(MUL,
    TEMP t51,
    CONST 4))),
 CONST 1)
MOVE(
 TEMP t52,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -20)))
MOVE(
 TEMP t53,
 BINOP(MINUS,
  BINOP(PLUS,
   TEMP t41,
   CONST 7),
  TEMP t40))
CJUMP(LT,
 TEMP t53,
 CONST 0,
 _BADSUB,L31)
#
LABEL L31
CJUMP(GT,
 TEMP t53,
 MEM(
  BINOP(PLUS,
   TEMP t52,
   CONST -4)),
 _BADSUB,L32)
#
LABEL L32
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t52,
   BINOP(MUL,
    TEMP t53,
    CONST 4))),
 CONST 1)
MOVE(
 TEMP t54,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -12)))
MOVE(
 TEMP t55,
 TEMP t40)
CJUMP(LT,
 TEMP t55,
 CONST 0,
 _BADSUB,L33)
#
LABEL L33
CJUMP(GT,
 TEMP t55,
 MEM(
  BINOP(PLUS,
   TEMP t54,
   CONST -4)),
 _BADSUB,L34)
#
LABEL L34
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t54,
   BINOP(MUL,
    TEMP t55,
    CONST 4))),
 TEMP t41)
EXP(
 CALL(
  NAME tigermain.try.1,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   BINOP(PLUS,
    TEMP t40,
    CONST 1)))
MOVE(
 TEMP t56,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -8)))
MOVE(
 TEMP t57,
 TEMP t41)
CJUMP(LT,
 TEMP t57,
 CONST 0,
 _BADSUB,L35)
#
LABEL L35
CJUMP(GT,
 TEMP t57,
 MEM(
  BINOP(PLUS,
   TEMP t56,
   CONST -4)),
 _BADSUB,L36)
#
LABEL L36
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t56,
   BINOP(MUL,
    TEMP t57,
    CONST 4))),
 CONST 0)
MOVE(
 TEMP t58,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -16)))
MOVE(
 TEMP t59,
 BINOP(PLUS,
  TEMP t41,
  TEMP t40))
CJUMP(LT,
 TEMP t59,
 CONST 0,
 _BADSUB,L37)
#
LABEL L37
CJUMP(GT,
 TEMP t59,
 MEM(
  BINOP(PLUS,
   TEMP t58,
   CONST -4)),
 _BADSUB,L38)
#
LABEL L38
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t58,
   BINOP(MUL,
    TEMP t59,
    CONST 4))),
 CONST 0)
MOVE(
 TEMP t60,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -20)))
MOVE(
 TEMP t61,
 BINOP(MINUS,
  BINOP(PLUS,
   TEMP t41,
   CONST 7),
  TEMP t40))
CJUMP(LT,
 TEMP t61,
 CONST 0,
 _BADSUB,L39)
#
LABEL L39
CJUMP(GT,
 TEMP t61,
 MEM(
  BINOP(PLUS,
   TEMP t60,
   CONST -4)),
 _BADSUB,L40)
#
LABEL L40
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t60,
   BINOP(MUL,
    TEMP t61,
    CONST 4))),
 CONST 0)
JUMP(
 NAME L43)
#
LABEL L42
JUMP(
 NAME L43)
#
LABEL L43
CJUMP(LT,
 TEMP t41,
 TEMP t62,
 L45,L14)
#
LABEL L45
MOVE(
 TEMP t41,
 BINOP(PLUS,
  TEMP t41,
  CONST 1))
JUMP(
 NAME L44)
#
LABEL L14
JUMP(
 NAME L48)
#
LABEL L48
JUMP(
 NAME L51)
LABEL L51
# Trace Scheduled: 
LABEL L52
CJUMP(EQ,
 TEMP t40,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -4)),
 L46,L47)
LABEL L47
MOVE(
 TEMP t41,
 CONST 0)
MOVE(
 TEMP t62,
 BINOP(MINUS,
  MEM(
   BINOP(PLUS,
    MEM(
     BINOP(PLUS,
      TEMP $fp,
      CONST 0)),
    CONST -4)),
  CONST 1))
CJUMP(LE,
 TEMP t41,
 TEMP t62,
 L44,L14)
LABEL L14
LABEL L48
JUMP(
 NAME L51)
LABEL L46
EXP(
 CALL(
  NAME tigermain.printboard.0,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0))))
JUMP(
 NAME L48)
LABEL L44
MOVE(
 TEMP t42,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -8)))
MOVE(
 TEMP t43,
 TEMP t41)
CJUMP(LT,
 TEMP t43,
 CONST 0,
 _BADSUB,L15)
LABEL L15
CJUMP(GT,
 TEMP t43,
 MEM(
  BINOP(PLUS,
   TEMP t42,
   CONST -4)),
 _BADSUB,L16)
LABEL L16
CJUMP(EQ,
 MEM(
  BINOP(PLUS,
   TEMP t42,
   BINOP(MUL,
    TEMP t43,
    CONST 4))),
 CONST 0,
 L19,L42)
LABEL L42
LABEL L43
CJUMP(GE,
 TEMP t41,
 TEMP t62,
 L14,L45)
LABEL L45
MOVE(
 TEMP t41,
 BINOP(PLUS,
  TEMP t41,
  CONST 1))
JUMP(
 NAME L44)
LABEL L19
MOVE(
 TEMP t44,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -16)))
MOVE(
 TEMP t45,
 BINOP(PLUS,
  TEMP t41,
  TEMP t40))
CJUMP(LT,
 TEMP t45,
 CONST 0,
 _BADSUB,L17)
LABEL L17
CJUMP(GT,
 TEMP t45,
 MEM(
  BINOP(PLUS,
   TEMP t44,
   CONST -4)),
 _BADSUB,L18)
LABEL L18
CJUMP(NE,
 MEM(
  BINOP(PLUS,
   TEMP t44,
   BINOP(MUL,
    TEMP t45,
    CONST 4))),
 CONST 0,
 L42,L24)
LABEL L24
MOVE(
 TEMP t46,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -20)))
MOVE(
 TEMP t47,
 BINOP(MINUS,
  BINOP(PLUS,
   TEMP t41,
   CONST 7),
  TEMP t40))
CJUMP(LT,
 TEMP t47,
 CONST 0,
 _BADSUB,L22)
LABEL L22
CJUMP(GT,
 TEMP t47,
 MEM(
  BINOP(PLUS,
   TEMP t46,
   CONST -4)),
 _BADSUB,L23)
LABEL L23
CJUMP(NE,
 MEM(
  BINOP(PLUS,
   TEMP t46,
   BINOP(MUL,
    TEMP t47,
    CONST 4))),
 CONST 0,
 L42,L41)
LABEL L41
MOVE(
 TEMP t48,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -8)))
MOVE(
 TEMP t49,
 TEMP t41)
CJUMP(LT,
 TEMP t49,
 CONST 0,
 _BADSUB,L27)
LABEL L27
CJUMP(GT,
 TEMP t49,
 MEM(
  BINOP(PLUS,
   TEMP t48,
   CONST -4)),
 _BADSUB,L28)
LABEL L28
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t48,
   BINOP(MUL,
    TEMP t49,
    CONST 4))),
 CONST 1)
MOVE(
 TEMP t50,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -16)))
MOVE(
 TEMP t51,
 BINOP(PLUS,
  TEMP t41,
  TEMP t40))
CJUMP(LT,
 TEMP t51,
 CONST 0,
 _BADSUB,L29)
LABEL L29
CJUMP(GT,
 TEMP t51,
 MEM(
  BINOP(PLUS,
   TEMP t50,
   CONST -4)),
 _BADSUB,L30)
LABEL L30
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t50,
   BINOP(MUL,
    TEMP t51,
    CONST 4))),
 CONST 1)
MOVE(
 TEMP t52,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -20)))
MOVE(
 TEMP t53,
 BINOP(MINUS,
  BINOP(PLUS,
   TEMP t41,
   CONST 7),
  TEMP t40))
CJUMP(LT,
 TEMP t53,
 CONST 0,
 _BADSUB,L31)
LABEL L31
CJUMP(GT,
 TEMP t53,
 MEM(
  BINOP(PLUS,
   TEMP t52,
   CONST -4)),
 _BADSUB,L32)
LABEL L32
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t52,
   BINOP(MUL,
    TEMP t53,
    CONST 4))),
 CONST 1)
MOVE(
 TEMP t54,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -12)))
MOVE(
 TEMP t55,
 TEMP t40)
CJUMP(LT,
 TEMP t55,
 CONST 0,
 _BADSUB,L33)
LABEL L33
CJUMP(GT,
 TEMP t55,
 MEM(
  BINOP(PLUS,
   TEMP t54,
   CONST -4)),
 _BADSUB,L34)
LABEL L34
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t54,
   BINOP(MUL,
    TEMP t55,
    CONST 4))),
 TEMP t41)
EXP(
 CALL(
  NAME tigermain.try.1,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   BINOP(PLUS,
    TEMP t40,
    CONST 1)))
MOVE(
 TEMP t56,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -8)))
MOVE(
 TEMP t57,
 TEMP t41)
CJUMP(LT,
 TEMP t57,
 CONST 0,
 _BADSUB,L35)
LABEL L35
CJUMP(GT,
 TEMP t57,
 MEM(
  BINOP(PLUS,
   TEMP t56,
   CONST -4)),
 _BADSUB,L36)
LABEL L36
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t56,
   BINOP(MUL,
    TEMP t57,
    CONST 4))),
 CONST 0)
MOVE(
 TEMP t58,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -16)))
MOVE(
 TEMP t59,
 BINOP(PLUS,
  TEMP t41,
  TEMP t40))
CJUMP(LT,
 TEMP t59,
 CONST 0,
 _BADSUB,L37)
LABEL L37
CJUMP(GT,
 TEMP t59,
 MEM(
  BINOP(PLUS,
   TEMP t58,
   CONST -4)),
 _BADSUB,L38)
LABEL L38
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t58,
   BINOP(MUL,
    TEMP t59,
    CONST 4))),
 CONST 0)
MOVE(
 TEMP t60,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -20)))
MOVE(
 TEMP t61,
 BINOP(MINUS,
  BINOP(PLUS,
   TEMP t41,
   CONST 7),
  TEMP t40))
CJUMP(LT,
 TEMP t61,
 CONST 0,
 _BADSUB,L39)
LABEL L39
CJUMP(GT,
 TEMP t61,
 MEM(
  BINOP(PLUS,
   TEMP t60,
   CONST -4)),
 _BADSUB,L40)
LABEL L40
MOVE(
 MEM(
  BINOP(PLUS,
   TEMP t60,
   BINOP(MUL,
    TEMP t61,
    CONST 4))),
 CONST 0)
JUMP(
 NAME L43)
LABEL L51
# Instructions: 
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
	lw t94 0+tigermain.try.1_framesize($sp)
	lw t42 -8(t94)
	move t43 t41
	blt t43 0 _BADSUB
L15:
	lw t95 -4(t42)
	bgt t43 t95 _BADSUB
L16:
	sll t98 t43 2
	add t97 t42 t98
	lw t96 (t97)
	beq t96 0 L19
L42:
L43:
	bge t41 t62 L14
L45:
	add t99 t41 1
	move t41 t99
	b L44
L19:
	lw t100 0+tigermain.try.1_framesize($sp)
	lw t44 -16(t100)
	add t101 t41 t40
	move t45 t101
	blt t45 0 _BADSUB
L17:
	lw t102 -4(t44)
	bgt t45 t102 _BADSUB
L18:
	sll t105 t45 2
	add t104 t44 t105
	lw t103 (t104)
	bne t103 0 L42
L24:
	lw t106 0+tigermain.try.1_framesize($sp)
	lw t46 -20(t106)
	add t108 t41 7
	sub t107 t108 t40
	move t47 t107
	blt t47 0 _BADSUB
L22:
	lw t109 -4(t46)
	bgt t47 t109 _BADSUB
L23:
	sll t112 t47 2
	add t111 t46 t112
	lw t110 (t111)
	bne t110 0 L42
L41:
	lw t113 0+tigermain.try.1_framesize($sp)
	lw t48 -8(t113)
	move t49 t41
	blt t49 0 _BADSUB
L27:
	lw t114 -4(t48)
	bgt t49 t114 _BADSUB
L28:
	li t115 1
	sll t117 t49 2
	add t116 t48 t117
	sw t115 (t116)
	lw t118 0+tigermain.try.1_framesize($sp)
	lw t50 -16(t118)
	add t119 t41 t40
	move t51 t119
	blt t51 0 _BADSUB
L29:
	lw t120 -4(t50)
	bgt t51 t120 _BADSUB
L30:
	li t121 1
	sll t123 t51 2
	add t122 t50 t123
	sw t121 (t122)
	lw t124 0+tigermain.try.1_framesize($sp)
	lw t52 -20(t124)
	add t126 t41 7
	sub t125 t126 t40
	move t53 t125
	blt t53 0 _BADSUB
L31:
	lw t127 -4(t52)
	bgt t53 t127 _BADSUB
L32:
	li t128 1
	sll t130 t53 2
	add t129 t52 t130
	sw t128 (t129)
	lw t131 0+tigermain.try.1_framesize($sp)
	lw t54 -12(t131)
	move t55 t40
	blt t55 0 _BADSUB
L33:
	lw t132 -4(t54)
	bgt t55 t132 _BADSUB
L34:
	sll t134 t55 2
	add t133 t54 t134
	sw t41 (t133)
	lw t135 0+tigermain.try.1_framesize($sp)
	move $a0 t135
	add t136 t40 1
	move $a1 t136
	jal tigermain.try.1
	lw t137 0+tigermain.try.1_framesize($sp)
	lw t56 -8(t137)
	move t57 t41
	blt t57 0 _BADSUB
L35:
	lw t138 -4(t56)
	bgt t57 t138 _BADSUB
L36:
	sll t140 t57 2
	add t139 t56 t140
	sw $0 (t139)
	lw t141 0+tigermain.try.1_framesize($sp)
	lw t58 -16(t141)
	add t142 t41 t40
	move t59 t142
	blt t59 0 _BADSUB
L37:
	lw t143 -4(t58)
	bgt t59 t143 _BADSUB
L38:
	sll t145 t59 2
	add t144 t58 t145
	sw $0 (t144)
	lw t146 0+tigermain.try.1_framesize($sp)
	lw t60 -20(t146)
	add t148 t41 7
	sub t147 t148 t40
	move t61 t147
	blt t61 0 _BADSUB
L39:
	lw t149 -4(t60)
	bgt t61 t149 _BADSUB
L40:
	sll t151 t61 2
	add t150 t60 t151
	sw $0 (t150)
	b L43
L51:
END tigermain.try.1
PROCEDURE tigermain.printboard.0
# Before canonicalization: 
SEQ(
 SEQ(
  SEQ(
   SEQ(
    SEQ(
     MOVE(
      TEMP t33,
      CONST 0),
     MOVE(
      TEMP t39,
      BINOP(MINUS,
       MEM(
        BINOP(PLUS,
         MEM(
          BINOP(PLUS,
           TEMP $fp,
           CONST 0)),
         CONST -4)),
       CONST 1))),
    CJUMP(LE,
     TEMP t33,
     TEMP t39,
     L12,L0)),
   SEQ(
    SEQ(
     SEQ(
      LABEL L12,
      SEQ(
       SEQ(
        SEQ(
         SEQ(
          SEQ(
           MOVE(
            TEMP t34,
            CONST 0),
           MOVE(
            TEMP t38,
            BINOP(MINUS,
             MEM(
              BINOP(PLUS,
               MEM(
                BINOP(PLUS,
                 TEMP $fp,
                 CONST 0)),
               CONST -4)),
             CONST 1))),
          CJUMP(LE,
           TEMP t34,
           TEMP t38,
           L9,L1)),
         SEQ(
          SEQ(
           SEQ(
            LABEL L9,
            EXP(
             CALL(
              NAME _print,
               ESEQ(
                SEQ(
                 SEQ(
                  CJUMP(EQ,
                   ESEQ(
                    SEQ(
                     MOVE(
                      TEMP t35,
                      MEM(
                       BINOP(PLUS,
                        MEM(
                         BINOP(PLUS,
                          TEMP $fp,
                          CONST 0)),
                        CONST -12))),
                     SEQ(
                      MOVE(
                       TEMP t36,
                       TEMP t33),
                      SEQ(
                       CJUMP(LT,
                        TEMP t36,
                        CONST 0,
                        _BADSUB,L2),
                       SEQ(
                        LABEL L2,
                        SEQ(
                         CJUMP(GT,
                          TEMP t36,
                          MEM(
                           BINOP(PLUS,
                            TEMP t35,
                            CONST -4)),
                          _BADSUB,L3),
                         LABEL L3))))),
                    MEM(
                     BINOP(PLUS,
                      TEMP t35,
                      BINOP(MUL,
                       TEMP t36,
                       CONST 4)))),
                   TEMP t34,
                   L6,L7),
                  SEQ(
                   SEQ(
                    LABEL L6,
                    SEQ(
                     MOVE(
                      TEMP t37,
                      NAME L4),
                     JUMP(
                      NAME L8))),
                   SEQ(
                    LABEL L7,
                    SEQ(
                     MOVE(
                      TEMP t37,
                      NAME L5),
                     JUMP(
                      NAME L8))))),
                 LABEL L8),
                TEMP t37)))),
           CJUMP(LT,
            TEMP t34,
            TEMP t38,
            L10,L1)),
          SEQ(
           SEQ(
            LABEL L10,
            MOVE(
             TEMP t34,
             BINOP(PLUS,
              TEMP t34,
              CONST 1))),
           JUMP(
            NAME L9)))),
        LABEL L1),
       EXP(
        CALL(
         NAME _print,
          NAME L11)))),
     CJUMP(LT,
      TEMP t33,
      TEMP t39,
      L13,L0)),
    SEQ(
     SEQ(
      LABEL L13,
      MOVE(
       TEMP t33,
       BINOP(PLUS,
        TEMP t33,
        CONST 1))),
     JUMP(
      NAME L12)))),
  LABEL L0),
 EXP(
  CALL(
   NAME _print,
    NAME L11)))
# After canonicalization: 
MOVE(
 TEMP t33,
 CONST 0)
MOVE(
 TEMP t39,
 BINOP(MINUS,
  MEM(
   BINOP(PLUS,
    MEM(
     BINOP(PLUS,
      TEMP $fp,
      CONST 0)),
    CONST -4)),
  CONST 1))
CJUMP(LE,
 TEMP t33,
 TEMP t39,
 L12,L0)
LABEL L12
MOVE(
 TEMP t34,
 CONST 0)
MOVE(
 TEMP t38,
 BINOP(MINUS,
  MEM(
   BINOP(PLUS,
    MEM(
     BINOP(PLUS,
      TEMP $fp,
      CONST 0)),
    CONST -4)),
  CONST 1))
CJUMP(LE,
 TEMP t34,
 TEMP t38,
 L9,L1)
LABEL L9
MOVE(
 TEMP t35,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -12)))
MOVE(
 TEMP t36,
 TEMP t33)
CJUMP(LT,
 TEMP t36,
 CONST 0,
 _BADSUB,L2)
LABEL L2
CJUMP(GT,
 TEMP t36,
 MEM(
  BINOP(PLUS,
   TEMP t35,
   CONST -4)),
 _BADSUB,L3)
LABEL L3
CJUMP(EQ,
 MEM(
  BINOP(PLUS,
   TEMP t35,
   BINOP(MUL,
    TEMP t36,
    CONST 4))),
 TEMP t34,
 L6,L7)
LABEL L6
MOVE(
 TEMP t37,
 NAME L4)
JUMP(
 NAME L8)
LABEL L7
MOVE(
 TEMP t37,
 NAME L5)
JUMP(
 NAME L8)
LABEL L8
EXP(
 CALL(
  NAME _print,
   TEMP t37))
CJUMP(LT,
 TEMP t34,
 TEMP t38,
 L10,L1)
LABEL L10
MOVE(
 TEMP t34,
 BINOP(PLUS,
  TEMP t34,
  CONST 1))
JUMP(
 NAME L9)
LABEL L1
EXP(
 CALL(
  NAME _print,
   NAME L11))
CJUMP(LT,
 TEMP t33,
 TEMP t39,
 L13,L0)
LABEL L13
MOVE(
 TEMP t33,
 BINOP(PLUS,
  TEMP t33,
  CONST 1))
JUMP(
 NAME L12)
LABEL L0
EXP(
 CALL(
  NAME _print,
   NAME L11))
# Basic Blocks: 
#
LABEL L54
MOVE(
 TEMP t33,
 CONST 0)
MOVE(
 TEMP t39,
 BINOP(MINUS,
  MEM(
   BINOP(PLUS,
    MEM(
     BINOP(PLUS,
      TEMP $fp,
      CONST 0)),
    CONST -4)),
  CONST 1))
CJUMP(LE,
 TEMP t33,
 TEMP t39,
 L12,L0)
#
LABEL L12
MOVE(
 TEMP t34,
 CONST 0)
MOVE(
 TEMP t38,
 BINOP(MINUS,
  MEM(
   BINOP(PLUS,
    MEM(
     BINOP(PLUS,
      TEMP $fp,
      CONST 0)),
    CONST -4)),
  CONST 1))
CJUMP(LE,
 TEMP t34,
 TEMP t38,
 L9,L1)
#
LABEL L9
MOVE(
 TEMP t35,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -12)))
MOVE(
 TEMP t36,
 TEMP t33)
CJUMP(LT,
 TEMP t36,
 CONST 0,
 _BADSUB,L2)
#
LABEL L2
CJUMP(GT,
 TEMP t36,
 MEM(
  BINOP(PLUS,
   TEMP t35,
   CONST -4)),
 _BADSUB,L3)
#
LABEL L3
CJUMP(EQ,
 MEM(
  BINOP(PLUS,
   TEMP t35,
   BINOP(MUL,
    TEMP t36,
    CONST 4))),
 TEMP t34,
 L6,L7)
#
LABEL L6
MOVE(
 TEMP t37,
 NAME L4)
JUMP(
 NAME L8)
#
LABEL L7
MOVE(
 TEMP t37,
 NAME L5)
JUMP(
 NAME L8)
#
LABEL L8
EXP(
 CALL(
  NAME _print,
   TEMP t37))
CJUMP(LT,
 TEMP t34,
 TEMP t38,
 L10,L1)
#
LABEL L10
MOVE(
 TEMP t34,
 BINOP(PLUS,
  TEMP t34,
  CONST 1))
JUMP(
 NAME L9)
#
LABEL L1
EXP(
 CALL(
  NAME _print,
   NAME L11))
CJUMP(LT,
 TEMP t33,
 TEMP t39,
 L13,L0)
#
LABEL L13
MOVE(
 TEMP t33,
 BINOP(PLUS,
  TEMP t33,
  CONST 1))
JUMP(
 NAME L12)
#
LABEL L0
EXP(
 CALL(
  NAME _print,
   NAME L11))
JUMP(
 NAME L53)
LABEL L53
# Trace Scheduled: 
LABEL L54
MOVE(
 TEMP t33,
 CONST 0)
MOVE(
 TEMP t39,
 BINOP(MINUS,
  MEM(
   BINOP(PLUS,
    MEM(
     BINOP(PLUS,
      TEMP $fp,
      CONST 0)),
    CONST -4)),
  CONST 1))
CJUMP(LE,
 TEMP t33,
 TEMP t39,
 L12,L0)
LABEL L0
EXP(
 CALL(
  NAME _print,
   NAME L11))
JUMP(
 NAME L53)
LABEL L12
MOVE(
 TEMP t34,
 CONST 0)
MOVE(
 TEMP t38,
 BINOP(MINUS,
  MEM(
   BINOP(PLUS,
    MEM(
     BINOP(PLUS,
      TEMP $fp,
      CONST 0)),
    CONST -4)),
  CONST 1))
CJUMP(LE,
 TEMP t34,
 TEMP t38,
 L9,L1)
LABEL L1
EXP(
 CALL(
  NAME _print,
   NAME L11))
CJUMP(GE,
 TEMP t33,
 TEMP t39,
 L0,L13)
LABEL L13
MOVE(
 TEMP t33,
 BINOP(PLUS,
  TEMP t33,
  CONST 1))
JUMP(
 NAME L12)
LABEL L9
MOVE(
 TEMP t35,
 MEM(
  BINOP(PLUS,
   MEM(
    BINOP(PLUS,
     TEMP $fp,
     CONST 0)),
   CONST -12)))
MOVE(
 TEMP t36,
 TEMP t33)
CJUMP(LT,
 TEMP t36,
 CONST 0,
 _BADSUB,L2)
LABEL L2
CJUMP(GT,
 TEMP t36,
 MEM(
  BINOP(PLUS,
   TEMP t35,
   CONST -4)),
 _BADSUB,L3)
LABEL L3
CJUMP(EQ,
 MEM(
  BINOP(PLUS,
   TEMP t35,
   BINOP(MUL,
    TEMP t36,
    CONST 4))),
 TEMP t34,
 L6,L7)
LABEL L7
MOVE(
 TEMP t37,
 NAME L5)
LABEL L8
EXP(
 CALL(
  NAME _print,
   TEMP t37))
CJUMP(GE,
 TEMP t34,
 TEMP t38,
 L1,L10)
LABEL L10
MOVE(
 TEMP t34,
 BINOP(PLUS,
  TEMP t34,
  CONST 1))
JUMP(
 NAME L9)
LABEL L6
MOVE(
 TEMP t37,
 NAME L4)
JUMP(
 NAME L8)
LABEL L53
# Instructions: 
L54:
	move t33 $0
	lw t154 0+tigermain.printboard.0_framesize($sp)
	lw t153 -4(t154)
	sub t152 t153 1
	move t39 t152
	ble t33 t39 L12
L0:
	la t155 L11
	move $a0 t155
	jal _print
	b L53
L12:
	move t34 $0
	lw t158 0+tigermain.printboard.0_framesize($sp)
	lw t157 -4(t158)
	sub t156 t157 1
	move t38 t156
	ble t34 t38 L9
L1:
	la t159 L11
	move $a0 t159
	jal _print
	bge t33 t39 L0
L13:
	add t160 t33 1
	move t33 t160
	b L12
L9:
	lw t161 0+tigermain.printboard.0_framesize($sp)
	lw t35 -12(t161)
	move t36 t33
	blt t36 0 _BADSUB
L2:
	lw t162 -4(t35)
	bgt t36 t162 _BADSUB
L3:
	sll t165 t36 2
	add t164 t35 t165
	lw t163 (t164)
	beq t163 t34 L6
L7:
	la t166 L5
	move t37 t166
L8:
	move $a0 t37
	jal _print
	bge t34 t38 L1
L10:
	add t167 t34 1
	move t34 t167
	b L9
L6:
	la t168 L4
	move t37 t168
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
