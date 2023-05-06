package Mips;

import Temp.Temp;
import Temp.TempList;
import Temp.LabelList;
import java.util.Hashtable;
import Assem.LABEL;

public class Codegen {
  MipsFrame frame;

  public Codegen(MipsFrame f) {
    frame = f;
  }

  private Assem.InstrList ilist = null, last = null;

  private void emit(Assem.Instr inst) {
    if (last != null)
      last = last.tail = new Assem.InstrList(inst, null);
    else {
      if (ilist != null)
        throw new Error("Codegen.emit");
      last = ilist = new Assem.InstrList(inst, null);
    }
  }

  Assem.InstrList codegen(Tree.Stm s) {
    munchStm(s);
    Assem.InstrList l = ilist;
    ilist = last = null;
    return l;
  }

  static Assem.Instr OPER(String a, TempList d, TempList s, LabelList j) {
    return new Assem.OPER("\t" + a, d, s, j);
  }

  static Assem.Instr OPER(String a, TempList d, TempList s) {
    return new Assem.OPER("\t" + a, d, s);
  }

  static Assem.Instr MOVE(String a, Temp d, Temp s) {
    return new Assem.MOVE("\t" + a, d, s);
  }

  static TempList L(Temp h) {
    return new TempList(h, null);
  }

  static TempList L(Temp h, TempList t) {
    return new TempList(h, t);
  }

  void munchStm(Tree.Stm s) {
    if (s instanceof Tree.MOVE)
      munchStm((Tree.MOVE) s);
    else if (s instanceof Tree.UEXP)
      munchStm((Tree.UEXP) s);
    else if (s instanceof Tree.JUMP)
      munchStm((Tree.JUMP) s);
    else if (s instanceof Tree.CJUMP)
      munchStm((Tree.CJUMP) s);
    else if (s instanceof Tree.LABEL)
      munchStm((Tree.LABEL) s);
    else
      throw new Error("Codegen.munchStm");
  }

  void munchStm(Tree.MOVE s) {
    // make optimizations when moving into or from a MEM tree
    if (s.dst instanceof Tree.TEMP) {
      if (s.src instanceof Tree.TEMP) {
        emit(MOVE("move `d0 `s0", munchExp(s.dst), munchExp(s.src)));
        return;
      } else if (s.src instanceof Tree.CONST) {
        emit(MOVE("move `d0 `s0", munchExp(s.dst), munchExp(s.src)));
        return;
      } else if (s.src instanceof Tree.MEM) {
        // look inside MEM for a BINOP
        Tree.MEM m = (Tree.MEM) s.src;
        if (m.exp instanceof Tree.BINOP
            && (((Tree.BINOP) m.exp).left instanceof Tree.TEMP)
            && ((Tree.BINOP) m.exp).right instanceof Tree.CONST) {
          Tree.TEMP t = (Tree.TEMP) ((Tree.BINOP) m.exp).left;
          if (t.temp != frame.FP) {
            emit(MOVE("move `d0 `s0", munchExp(s.dst), munchExp(s.src)));
            return;
          }
          Tree.CONST c = (Tree.CONST) ((Tree.BINOP) m.exp).right;
          emit(MOVE("move `d0 " + c.value + "+" + frame.name + "_framesize(`s0)", munchExp(s.dst), frame.SP));
          return;
        } else if (m.exp instanceof Tree.BINOP
            && (((Tree.BINOP) m.exp).left instanceof Tree.CONST)
            && ((Tree.BINOP) m.exp).right instanceof Tree.TEMP) {
          Tree.TEMP t = (Tree.TEMP) ((Tree.BINOP) m.exp).right;
          if (t.temp != frame.FP) {
            emit(MOVE("move `d0 `s0", munchExp(s.dst), munchExp(s.src)));
            return;
          }
          Tree.CONST c = (Tree.CONST) ((Tree.BINOP) m.exp).left;
          emit(MOVE("move `d0 " + c.value + "+" + frame.name + "_framesize(`s0)", munchExp(s.dst), frame.SP));
          return;
        }
        emit(MOVE("move `d0 `s0", munchExp(s.dst), munchExp(s.src)));
        return;
      } else if (s.src instanceof Tree.CALL) {
        emit(MOVE("move `d0 `s0", munchExp(s.dst), munchExp(s.src)));
        return;
      } else if (s.src instanceof Tree.BINOP) {
        // // change later TODO
        // Tree.BINOP bp = (Tree.BINOP) s.src;
        // if (bp.left instanceof Tree.TEMP
        // && bp.right instanceof Tree.CONST) {
        // Tree.TEMP t = (Tree.TEMP) bp.left;
        // if (t.temp != frame.FP) {
        // emit(MOVE("move `d0 `s0", munchExp(s.dst), munchExp(s.src)));
        // return;
        // }
        // Tree.CONST c = (Tree.CONST) bp.right;
        // emit(MOVE("move `d0 " + c.value + "+" + frame.name + "_framesize(`s0)",
        // munchExp(s.dst), frame.SP));
        // return;
        // } else if (bp.left instanceof Tree.CONST
        // && bp.right instanceof Tree.TEMP) {
        // Tree.TEMP t = (Tree.TEMP) bp.right;
        // if (t.temp != frame.FP) {
        // emit(MOVE("move `d0 `s0", munchExp(s.dst), munchExp(s.src)));
        // return;
        // }
        // Tree.CONST c = (Tree.CONST) bp.left;
        // emit(MOVE("move `d0 " + c.value + "+" + frame.name + "_framesize(`s0)",
        // munchExp(s.dst), frame.SP));
        // return;
        // }
        emit(MOVE("move `d0 `s0", munchExp(s.dst), munchExp(s.src)));
        return;
      } else if (s.src instanceof Tree.NAME) {
        emit(MOVE("move `d0 `s0", munchExp(s.dst), munchExp(s.src)));
        return;
      }
    } else if (s.dst instanceof Tree.MEM) {
      // store has source first then dest
      String binop_str = "";
      Temp mem_exp = null;
      if (((Tree.MEM) s.dst).exp instanceof Tree.BINOP) {
        Tree.BINOP bp = (Tree.BINOP) ((Tree.MEM) s.dst).exp;
        if (bp.left instanceof Tree.TEMP && bp.right instanceof Tree.CONST
            && ((Tree.TEMP) bp.left).temp == frame.FP)
          binop_str += ((Tree.CONST) bp.right).value + "+" + frame.name + "_framesize(`d0)";
        else if (bp.right instanceof Tree.TEMP && bp.left instanceof Tree.CONST
            && ((Tree.TEMP) bp.right).temp == frame.FP) {
          binop_str += ((Tree.CONST) bp.left).value + "+" + frame.name + "_framesize(`d0)";
        }
      } else if (((Tree.MEM) s.dst).exp instanceof Tree.TEMP) {
        mem_exp = ((Tree.TEMP) ((Tree.MEM) s.dst).exp).temp;
      }

      if (s.src instanceof Tree.TEMP) {
        if (binop_str != "")
          emit(MOVE("sw `s0 " + binop_str, frame.SP, munchExp(s.src)));
        else if (mem_exp != null)
          emit(MOVE("sw `s0 (`d0)", mem_exp, munchExp(s.src)));
        else
          emit(MOVE("sw `s0 (`d0)", munchExp(s.dst), munchExp(s.src)));
        return;
      } else if (s.src instanceof Tree.CONST) {
        if (binop_str != "")
          emit(MOVE("sw `s0 " + binop_str, frame.SP, munchExp(s.src)));
        else if (mem_exp != null)
          emit(MOVE("sw `s0 (`d0)", mem_exp, munchExp(s.src)));
        else
          emit(MOVE("sw `s0 (`d0)", munchExp(s.dst), munchExp(s.src)));
        return;
      } else if (s.src instanceof Tree.MEM) {
        // do more here TODO
        if (binop_str != "")
          emit(MOVE("sw `s0 " + binop_str, frame.SP, munchExp(s.src)));
        else if (mem_exp != null)
          emit(MOVE("sw `s0 (`d0)", mem_exp, munchExp(s.src)));
        else
          emit(MOVE("sw `s0 (`d0)", munchExp(s.dst), munchExp(s.src)));
        return;
      } else if (s.src instanceof Tree.CALL) {
        if (binop_str != "")
          emit(MOVE("sw `s0 " + binop_str, frame.SP, munchExp(s.src)));
        else if (mem_exp != null)
          emit(MOVE("sw `s0 (`d0)", mem_exp, munchExp(s.src)));
        else
          emit(MOVE("sw `s0 (`d0)", munchExp(s.dst), munchExp(s.src)));
        return;
      } else if (s.src instanceof Tree.BINOP) {
        // change later TODO
        if (binop_str != "")
          emit(MOVE("sw `s0 " + binop_str, frame.SP, munchExp(s.src)));
        else if (mem_exp != null)
          emit(MOVE("sw `s0 (`d0)", mem_exp, munchExp(s.src)));
        else
          emit(MOVE("sw `s0 (`d0)", munchExp(s.dst), munchExp(s.src)));
        return;
      } else if (s.src instanceof Tree.NAME) {
        if (binop_str != "")
          emit(MOVE("sw `s0 " + binop_str, frame.SP, munchExp(s.src)));
        else if (mem_exp != null)
          emit(MOVE("sw `s0 (`d0)", mem_exp, munchExp(s.src)));
        else
          emit(MOVE("sw `s0 (`d0)", munchExp(s.dst), munchExp(s.src)));
        return;
      }
    } else
      throw new Error("munchStm(MOVE)");
  }

  void munchStm(Tree.UEXP s) {
    munchExp(s.exp);
  }

  void munchStm(Tree.JUMP s) {
    // munchExp(s.exp); // do we need return value of this
    if (s.exp instanceof Tree.NAME) {
      Tree.NAME n = (Tree.NAME) s.exp;
      emit(OPER("b " + n.label.toString(), null, null, s.targets));
    } else
      throw new Error("munchStm(JUMP)");
  }

  private static String[] CJUMP = new String[10];
  static {
    CJUMP[Tree.CJUMP.EQ] = "beq";
    CJUMP[Tree.CJUMP.NE] = "bne";
    CJUMP[Tree.CJUMP.LT] = "blt";
    CJUMP[Tree.CJUMP.GT] = "bgt";
    CJUMP[Tree.CJUMP.LE] = "ble";
    CJUMP[Tree.CJUMP.GE] = "bge";
    CJUMP[Tree.CJUMP.ULT] = "bltu";
    CJUMP[Tree.CJUMP.ULE] = "bleu";
    CJUMP[Tree.CJUMP.UGT] = "bgtu";
    CJUMP[Tree.CJUMP.UGE] = "bgeu";
  }

  void munchStm(Tree.CJUMP s) {
    LabelList ll = new LabelList(s.iftrue, new LabelList(s.iffalse, null));
    String str = CJUMP[s.relop];
    if (s.left instanceof Tree.CONST
        && s.right instanceof Tree.CONST) {
      emit(OPER(
          str + " " + ((Tree.CONST) s.left).value + " " + ((Tree.CONST) s.right).value + " " + s.iftrue.toString(),
          null, L(munchExp(s.right)), ll));
      return;
    } else if (s.left instanceof Tree.CONST) {
      emit(OPER(str + " " + ((Tree.CONST) s.left).value + " `s1 " + s.iftrue.toString(),
          null, L(munchExp(s.right)), ll));
      return;
    } else if (s.right instanceof Tree.CONST) {
      emit(OPER(str + " `s0 " + ((Tree.CONST) s.right).value + " " + s.iftrue.toString(),
          null, L(munchExp(s.left)), ll));
      return;
    }

    Temp l = munchExp(s.left);
    Temp r = munchExp(s.right);

    // we might not need a false label since we just fall through
    emit(OPER(str + " `s0 `s1 " + s.iftrue.toString(),
        null, L(l, L(r)), ll));
  }

  void munchStm(Tree.LABEL l) {
    emit(new LABEL(l.label.toString() + ":", l.label));
  }

  Temp munchExp(Tree.Exp s) {
    if (s instanceof Tree.CONST)
      return munchExp((Tree.CONST) s);
    else if (s instanceof Tree.NAME)
      return munchExp((Tree.NAME) s);
    else if (s instanceof Tree.TEMP)
      return munchExp((Tree.TEMP) s);
    else if (s instanceof Tree.BINOP)
      return munchExp((Tree.BINOP) s);
    else if (s instanceof Tree.MEM)
      return munchExp((Tree.MEM) s);
    else if (s instanceof Tree.CALL)
      return munchExp((Tree.CALL) s);
    else
      throw new Error("Codegen.munchExp");
  }

  Temp munchExp(Tree.CONST e) {
    if (e.value == 0)
      return frame.ZERO;
    Temp d = new Temp();
    emit(OPER("li `d0 " + e.value + "", L(d), null));
    return d;
  }

  Temp munchExp(Tree.NAME e) {
    Temp r = new Temp();
    emit(OPER("la `d0 " + e.label.toString(), L(r), null));
    return r;
  }

  Temp munchExp(Tree.TEMP e) {
    if (e.temp == frame.FP) {
      Temp t = new Temp();
      emit(OPER("addu `d0 `s0 " + frame.name + "_framesize",
          L(t), L(frame.SP)));
      return t;
    }
    return e.temp;
  }

  private static String[] BINOP = new String[10];
  static {
    BINOP[Tree.BINOP.PLUS] = "add";
    BINOP[Tree.BINOP.MINUS] = "sub";
    BINOP[Tree.BINOP.MUL] = "mulo";
    BINOP[Tree.BINOP.DIV] = "div";
    BINOP[Tree.BINOP.AND] = "and";
    BINOP[Tree.BINOP.OR] = "or";
    BINOP[Tree.BINOP.LSHIFT] = "sll";
    BINOP[Tree.BINOP.RSHIFT] = "srl";
    BINOP[Tree.BINOP.ARSHIFT] = "sra";
    BINOP[Tree.BINOP.XOR] = "xor";
  }

  // use for optimization of multiplication
  private static int shift(int i) {
    int shift = 0;
    if ((i >= 2) && ((i & (i - 1)) == 0)) {
      while (i > 1) {
        shift += 1;
        i >>= 1;
      }
    }
    return shift;
  }

  Temp munchExp(Tree.BINOP e) {
    Temp d = new Temp();
    // go through each instance
    // optimize possible left/right MEM nodes
    String s = BINOP[e.binop];
    if (e.left instanceof Tree.TEMP) {
      if (e.right instanceof Tree.TEMP)
        emit(OPER(s + " `d0 `s0 `s1", L(d), L(munchExp(e.left), L(munchExp(e.right)))));
      else if (e.right instanceof Tree.CONST) {
        Tree.CONST c = (Tree.CONST) e.right;
        int sh = shift(c.value);
        if (s.equals("mulo") && sh != 0) {
          s = BINOP[Tree.BINOP.LSHIFT];
          c.value = sh;
        } else if (s.equals("div") && sh != 0) {
          s = BINOP[Tree.BINOP.RSHIFT];
          c.value = sh;
        }
        if (((Tree.TEMP) e.left).temp == frame.FP)
          emit(OPER(s + " `d0 `s0 " + c.value + "+" + frame.name + "_framesize", L(d), L(frame.SP)));
        else
          emit(OPER(s + " `d0 `s0 " + c.value + "", L(d), L(munchExp(e.left))));
      } else if (e.right instanceof Tree.MEM)
        emit(OPER(s + " `d0 `s0 `s1", L(d), L(munchExp(e.left), L(munchExp(e.right))))); // fix string later for MEM
      else if (e.right instanceof Tree.BINOP)
        emit(OPER(s + " `d0 `s0 `s1", L(d), L(munchExp(e.left), L(munchExp(e.right))))); // maximal later
    } else if (e.left instanceof Tree.CONST) {
      Tree.CONST e1 = (Tree.CONST) e.left;
      if (e.right instanceof Tree.TEMP) {
        if (((Tree.TEMP) e.right).temp == frame.FP)
          emit(OPER(s + " `d0 `s0 " + e1.value + "+" + frame.name + "_framesize", L(d), L(frame.SP)));
        else
          emit(OPER(s + " `d0 " + e1.value + " `s0", L(d), L(munchExp(e.right))));
      } else if (e.right instanceof Tree.CONST) {
        Tree.CONST c = (Tree.CONST) e.right;
        int sh = shift(c.value);
        if (s.equals("mulo") && sh != 0) {
          s = BINOP[Tree.BINOP.LSHIFT];
          c.value = sh;
        } else if (s.equals("div") && sh != 0) {
          s = BINOP[Tree.BINOP.RSHIFT];
          c.value = sh;
        }
        emit(OPER(s + " `d0 " + e1.value + " " + c.value + "", L(d), null));
      } else if (e.right instanceof Tree.MEM)
        emit(OPER(s + " `d0 " + e1.value + " `s0", L(d), L(munchExp(e.left), L(munchExp(e.right)))));
      else if (e.right instanceof Tree.BINOP)
        emit(OPER(s + " `d0 `s0 `s1", L(d), L(munchExp(e.right)))); // maximal later
    } else if (e.left instanceof Tree.BINOP) {
      if (e.right instanceof Tree.TEMP)
        emit(OPER(s + " `d0 `s0 `s1", L(d), L(munchExp(e.left), L(munchExp(e.right)))));
      else if (e.right instanceof Tree.CONST) {
        Tree.CONST c = (Tree.CONST) e.right;
        emit(OPER(s + " `d0 `s0 " + c.value + "", L(d), L(munchExp(e.left))));
      } else if (e.right instanceof Tree.MEM)
        emit(OPER(s + " `d0 `s0 `s1", L(d), L(munchExp(e.left), L(munchExp(e.right))))); // fix string later for MEM
      else if (e.right instanceof Tree.BINOP)
        emit(OPER(s + " `d0 `s0 `s1", L(d), L(munchExp(e.left), L(munchExp(e.right))))); // maximal later
    } else if (e.left instanceof Tree.MEM) {
      if (e.right instanceof Tree.TEMP)
        emit(OPER(s + " `d0 `s0 `s1", L(d), L(munchExp(e.left), L(munchExp(e.right)))));
      else if (e.right instanceof Tree.CONST) {
        Tree.CONST c = (Tree.CONST) e.right;
        int sh = shift(c.value);
        if (s.equals("mulo") && sh != 0) {
          s = BINOP[Tree.BINOP.LSHIFT];
          c.value = sh;
        } else if (s.equals("div") && sh != 0) {
          s = BINOP[Tree.BINOP.RSHIFT];
          c.value = sh;
        }
        emit(OPER(s + " `d0 `s0 " + c.value + "", L(d), L(munchExp(e.left))));
      } else if (e.right instanceof Tree.MEM)
        emit(OPER(s + " `d0 `s0 `s1", L(d), L(munchExp(e.left), L(munchExp(e.right))))); // fix string later for MEM
      else if (e.right instanceof Tree.BINOP)
        emit(OPER(s + " `d0 `s0 `s1", L(d), L(munchExp(e.left), L(munchExp(e.right))))); // maximal later
    } else
      throw new Error("munchExp(BINOP)");
    return d;
  }

  Temp munchExp(Tree.MEM e) {
    // ex: emit("lw `d0 " + ..., L(d), L(munchExp(e.exp)));
    // go through variances of exp
    /*
     * can be:
     * BINOP result
     * CONST
     * TEMP
     * MEM
     */
    // probably can optimize if e.exp is a BINOP
    Temp d = new Temp();
    String left = "";
    String right = "";
    Tree.CONST c_l = null;
    Tree.CONST c_r = null;
    if (e.exp instanceof Tree.BINOP) {
      // add left and right (represent in string)
      // if one is a temp and it's the frame ptr, add frame size to SP
      Tree.BINOP bp = (Tree.BINOP) e.exp;
      if (bp.left instanceof Tree.TEMP && ((Tree.TEMP) bp.left).temp == frame.FP) { // left is FP temp
        left += frame.name + "_framesize(`s0)";
      }
      if (bp.right instanceof Tree.TEMP && ((Tree.TEMP) bp.right).temp == frame.FP) {
        right += frame.name + "_framesize(`s0)";
      }

      if (bp.left instanceof Tree.CONST)
        c_l = (Tree.CONST) bp.left;
      else if (bp.right instanceof Tree.CONST) {
        c_r = (Tree.CONST) bp.right;
      }
      // MEM[BINOP]
      // need to get outcome of binop
      // if a temp is used but not the FP, see
      // if left/right is a const
      if ((bp.left instanceof Tree.TEMP || bp.right instanceof Tree.TEMP)
          && (left == "" && right == "")) {
        if (c_l != null) { // left is constant
          emit(OPER("lw `d0 " + c_l.value + "(`s0)", L(d), L(((Tree.TEMP) bp.right).temp)));
          return d;
        } else if (c_r != null) { // left is constant
          emit(OPER("lw `d0 " + c_r.value + "(`s0)", L(d), L(((Tree.TEMP) bp.left).temp)));
          return d;
        }
        emit(OPER("lw `d0 (`s0)", L(d), L(munchExp(e.exp))));
        return d;
      } else if (left != "" && right != "") { // left and right temp is FP
        emit(OPER("lw `d0 " + left + "+" + right, L(d), L(frame.SP, L(frame.SP))));
        return d;
      } else if (left != "" && bp.right instanceof Tree.CONST) {
        emit(OPER("lw `d0 " + ((Tree.CONST) bp.right).value + "+" + left, L(d), L(frame.SP)));
        return d;
      } else if (right != "" && bp.right instanceof Tree.CONST) {
        emit(OPER("lw `d0 " + ((Tree.CONST) bp.left).value + "+" + right, L(d), L(frame.SP)));
        return d;
      } else if (bp.left instanceof Tree.MEM
          && bp.right instanceof Tree.CONST) {
        emit(OPER("lw `d0 " + c_r.value + "(`s0)", L(d), L(munchExp(bp.left))));
        return d;
      }
      emit(OPER("lw `d0 (`s0)", L(d), L(munchExp(e.exp))));
      return d;
    }
    emit(OPER("lw `d0 (`s0)", L(d), L(munchExp(e.exp))));
    return d;
  }

  Temp munchExp(Tree.CALL s) {
    TempList src = munchArgs(0, s.args);
    Tree.NAME func = (Tree.NAME) s.func;
    emit(OPER("jal " + func.label.toString() + "", L(frame.V0), src));
    return frame.V0;
  }

  private TempList munchArgs(int i, Tree.ExpList args) {
    if (args == null)
      return null;
    Temp src = munchExp(args.head);
    if (i > frame.maxArgs)
      frame.maxArgs = i;
    switch (i) {
      case 0:
        emit(MOVE("move `d0 `s0", frame.A0, src));
        break;
      case 1:
        emit(MOVE("move `d0 `s0", frame.A1, src));
        break;
      case 2:
        emit(MOVE("move `d0 `s0", frame.A2, src));
        break;
      case 3:
        emit(MOVE("move `d0 `s0", frame.A3, src));
        break;
      default:
        emit(OPER("sw `s0 " + (i - 1) * frame.wordSize() + "(`s1)",
            null, L(src, L(frame.SP))));
        break;
    }
    return L(src, munchArgs(i + 1, args.tail));
  }
}
