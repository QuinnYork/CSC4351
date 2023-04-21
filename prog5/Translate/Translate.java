package Translate;

import Symbol.Symbol;
import Tree.BINOP;
import Tree.CJUMP;
import Tree.ESEQ;
import Tree.StmList;
import Tree.TEMP;
import Temp.Temp;
import Temp.Label;

public class Translate {
  public Frame.Frame frame;

  public Translate(Frame.Frame f) {
    frame = f;
  }

  private Frag frags;

  public void procEntryExit(Level level, Exp body) {
    Frame.Frame myframe = level.frame;
    Tree.Exp bodyExp = body.unEx();
    Tree.Stm bodyStm;
    if (bodyExp != null)
      bodyStm = MOVE(TEMP(myframe.RV()), bodyExp);
    else
      bodyStm = body.unNx();
    ProcFrag frag = new ProcFrag(myframe.procEntryExit1(bodyStm), myframe);
    frag.next = frags;
    frags = frag;
  }

  public Frag getResult() {
    return frags;
  }

  private static Tree.Exp CONST(int value) {
    return new Tree.CONST(value);
  }

  private static Tree.Exp NAME(Label label) {
    return new Tree.NAME(label);
  }

  private static Tree.Exp TEMP(Temp temp) {
    return new Tree.TEMP(temp);
  }

  private static Tree.Exp BINOP(int binop, Tree.Exp left, Tree.Exp right) {
    return new Tree.BINOP(binop, left, right);
  }

  private static Tree.Exp MEM(Tree.Exp exp) {
    return new Tree.MEM(exp);
  }

  private static Tree.Exp CALL(Tree.Exp func, Tree.ExpList args) {
    return new Tree.CALL(func, args);
  }

  private static Tree.Exp ESEQ(Tree.Stm stm, Tree.Exp exp) {
    if (stm == null)
      return exp;
    return new Tree.ESEQ(stm, exp);
  }

  private static Tree.Stm MOVE(Tree.Exp dst, Tree.Exp src) {
    return new Tree.MOVE(dst, src);
  }

  private static Tree.Stm UEXP(Tree.Exp exp) {
    return new Tree.UEXP(exp);
  }

  private static Tree.Stm JUMP(Label target) {
    return new Tree.JUMP(target);
  }

  private static Tree.Stm CJUMP(int relop, Tree.Exp l, Tree.Exp r, Label t, Label f) {
    return new Tree.CJUMP(relop, l, r, t, f);
  }

  private static Tree.Stm SEQ(Tree.Stm left, Tree.Stm right) {
    if (left == null)
      return right;
    if (right == null)
      return left;
    return new Tree.SEQ(left, right);
  }

  private static Tree.Stm LABEL(Label label) {
    return new Tree.LABEL(label);
  }

  private static Tree.ExpList ExpList(Tree.Exp head, Tree.ExpList tail) {
    return new Tree.ExpList(head, tail);
  }

  private static Tree.ExpList ExpList(Tree.Exp head) {
    return ExpList(head, null);
  }

  private static Tree.ExpList ExpList(ExpList exp) {
    if (exp == null)
      return null;
    return ExpList(exp.head.unEx(), ExpList(exp.tail));
  }

  public Exp Error() {
    return new Ex(CONST(0));
  }

  public Exp SimpleVar(Access access, Level level) {
    // if access to var is inside level, use MEM. else follow static links
    // do {
    // if (access.home.equals(level)) { // if in same level
    // return null;
    // }
    // } while (!access.home.equals(level));
    if (level.equals(access.home))
      return new Ex(access.acc.exp(new TEMP(access.home.frame.FP())));
    else
      return SimpleVar(access, level.parent);
  }

  public Exp FieldVar(Exp record, int index) {
    return Error();
  }

  public Exp SubscriptVar(Exp array, Exp index) {
    return Error();
  }

  public Exp NilExp() {
    return Error();
  }

  public Exp IntExp(int value) {
    return new Ex(CONST(value));
  }

  private java.util.Hashtable strings = new java.util.Hashtable();

  public Exp StringExp(String lit) {
    String u = lit.intern();
    Label lab = (Label) strings.get(u);
    if (lab == null) {
      lab = new Label();
      strings.put(u, lab);
      DataFrag frag = new DataFrag(frame.string(lab, u));
      frag.next = frags;
      frags = frag;
    }
    return new Ex(NAME(lab));
  }

  private Tree.Exp CallExp(Symbol f, ExpList args, Level from) {
    return frame.externalCall(f.toString(), ExpList(args));
  }

  private Tree.Exp CallExp(Level f, ExpList args, Level from) {
    // return new Tree.CALL(null, args);
    throw new Error("Translate.CallExp unimplemented");
  }

  public Exp FunExp(Symbol f, ExpList args, Level from) {
    return new Ex(CallExp(f, args, from));
  }

  public Exp FunExp(Level f, ExpList args, Level from) {
    return new Ex(CallExp(f, args, from));
  }

  public Exp ProcExp(Symbol f, ExpList args, Level from) {
    return new Nx(UEXP(CallExp(f, args, from)));
  }

  public Exp ProcExp(Level f, ExpList args, Level from) {
    return new Nx(UEXP(CallExp(f, args, from)));
  }

  public Exp OpExp(int op, Exp left, Exp right) {
    return new Ex(BINOP(op, left.unEx(), right.unEx()));
  }

  public Exp StrOpExp(int op, Exp left, Exp right) {
    // don't know if this is right
    return new RelCx(op, left.unEx(), right.unEx());
  }

  public Exp RecordExp(ExpList init) {
    return init.head;
  }

  public Exp SeqExp(ExpList e) {
    Tree.Stm stm;
    Tree.Exp ex;
    if (e.tail != null)
      stm = e.head.unNx();
    else {
      if (e.head.unEx() == null)
        return new Nx(e.head.unNx());
      return new Ex(e.head.unEx());
    }
    while (e.tail != null) {
      stm = SEQ(stm, e.head.unNx());
      e = e.tail;
    }
    ex = e.head.unEx();
    if (ex == null)
      return new Nx(SEQ(stm, e.head.unNx()));
    return new Ex(ESEQ(stm, ex));
  }

  public Exp AssignExp(Exp lhs, Exp rhs) {
    return new Nx(MOVE(lhs.unEx(), rhs.unEx()));
  }

  public Exp IfExp(Exp cc, Exp aa, Exp bb) {
    return Error();
  }

  public Exp WhileExp(Exp test, Exp body, Label done) {
    Tree.Stm stm = body.unNx();
    Label start = new Label(); // L1
    Label body_jump = new Label(); // L2
    System.out.println(test.unEx());
    stm = SEQ(
        SEQ(
            SEQ(LABEL(start), test.unCx(body_jump, done)),
            SEQ(
                SEQ(LABEL(body_jump), stm), JUMP(start))),
        LABEL(done));
    return new Nx(stm);
  }

  public Exp ForExp(Access i, Exp lo, Exp hi, Exp body, Label done) {
    // AssignExp used
    // get initial tree for loop var dec
    // do something with hi so we can put it in a temp
    // and use during CJUMP
    // translate body and add loop var + 1 after
    Exp from_lo = VarDec(i, lo);
    Exp to_hi = new Ex(TEMP(new Temp())); // create temp for hi number
    // get far left SEQ tree
    Tree.Stm stm = SEQ(from_lo.unNx(), MOVE(to_hi.unEx(), hi.unEx()));
    Tree.MOVE move = (Tree.MOVE) from_lo.unNx();
    RelCx rel_init = new RelCx(4, move.dst, to_hi.unEx());
    RelCx rel_post = new RelCx(2, move.dst, to_hi.unEx());
    Label first = new Label();
    Label second = new Label();

    // Configures the body with incrementing the loop var
    Tree.Stm bod;
    System.out.println(body.getClass());
    if (body == null || body instanceof Ex)
      bod = MOVE(move.dst, BINOP(0, move.dst, CONST(1)));
    else
      bod = SEQ(body.unNx(), MOVE(move.dst, BINOP(0, move.dst, CONST(1))));

    stm = SEQ(
        SEQ(
            SEQ(stm, rel_init.unCx(first, done)),
            SEQ(
                SEQ(LABEL(first), rel_post.unCx(second, done)),
                SEQ(
                    SEQ(LABEL(second),
                        bod),
                    JUMP(first)))),
        LABEL(done));

    return new Nx(stm);
  }

  public Exp ForExp(Exp id, Exp lo, Exp hi, Exp body, Label done) {
    // VarDec used? (since we know where it was declared)

    return Error();
  }

  public Exp BreakExp(Label done) {
    return Error();
  }

  public Exp LetExp(ExpList lets, Exp body) {
    Tree.Stm stm = lets.head.unNx(); // build this tree with each dec inside of lets
    lets = lets.tail;
    while (lets != null) {
      stm = SEQ(stm, lets.head.unNx());
      lets = lets.tail;
    }
    // stm holds Stm tree
    // attach to tree (either seq or eseq) of let body
    if (body != null) {
      Tree.Exp exp = body.unEx();
      if (exp == null) {
        stm = SEQ(stm, body.unNx());
        return new Nx(stm);
      }

      return new Ex(ESEQ(stm, exp));
    }
    return new Nx(stm);
  }

  public Exp ArrayExp(Exp size, Exp init) {
    return Error();
  }

  public Exp VarDec(Access a, Exp init) {
    Tree.Exp exp = a.acc.exp(init.unEx());
    return new Nx(MOVE(exp, init.unEx()));
  }

  public Exp TypeDec() {
    return new Nx(null);
  }

  public Exp FunctionDec() {
    return new Nx(null);
  }
}
