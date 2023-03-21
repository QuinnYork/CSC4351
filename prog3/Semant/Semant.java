package Semant;

import Translate.Exp;
import Types.Type;

public class Semant {
  Env env;

  public Semant(ErrorMsg.ErrorMsg err) {
    this(new Env(err));
  }

  Semant(Env e) {
    env = e;
  }

  public void transProg(Absyn.Exp exp) {
    transExp(exp);
  }

  private void error(int pos, String msg) {
    env.errorMsg.error(pos, msg);
  }

  static final Types.VOID VOID = new Types.VOID();
  static final Types.INT INT = new Types.INT();
  static final Types.STRING STRING = new Types.STRING();
  static final Types.NIL NIL = new Types.NIL();

  private Exp checkInt(ExpTy et, int pos) {
    if (!INT.coerceTo(et.ty))
      error(pos, "integer required");
    return et.exp;
  }

  // check that left and right are the same type
  private boolean checkComparable(ExpTy left, ExpTy right, int pos) {
    if (!left.ty.coerceTo(right.ty))
      error(pos, "not equal types");
    return true;
  }

  // check that left and right are either both INT or both STRING
  private boolean checkOrderable(ExpTy left, ExpTy right, int pos) {
    if ((INT.coerceTo(left.ty) && INT.coerceTo(right.ty)) ||
        (STRING.coerceTo(left.ty) && STRING.coerceTo(right.ty))) {
      return true;
    }
    error(pos, "not equal types");
    return false;
  }

  ExpTy transExp(Absyn.Exp e) {
    ExpTy result;

    if (e == null)
      return new ExpTy(null, VOID);
    else if (e instanceof Absyn.OpExp)
      result = transExp((Absyn.OpExp) e);
    else if (e instanceof Absyn.LetExp)
      result = transExp((Absyn.LetExp) e);
    else if (e instanceof Absyn.IntExp)
      result = transExp((Absyn.IntExp) e);
    else if (e instanceof Absyn.SeqExp)
      result = transExp((Absyn.SeqExp) e);
    else if (e instanceof Absyn.StringExp)
      result = transExp((Absyn.StringExp) e);
    else if (e instanceof Absyn.ArrayExp)
      result = transExp((Absyn.ArrayExp) e);
    else if (e instanceof Absyn.NilExp)
      result = transExp((Absyn.NilExp) e);
    else if (e instanceof Absyn.RecordExp)
      result = transExp((Absyn.RecordExp) e);
    else if (e instanceof Absyn.VarExp)
      result = transExp((Absyn.VarExp) e);
    else if (e instanceof Absyn.AssignExp)
      result = transExp((Absyn.AssignExp) e);
    else if (e instanceof Absyn.IfExp)
      result = transExp((Absyn.IfExp) e);
    else if (e instanceof Absyn.BreakExp)
      result = transExp((Absyn.BreakExp) e);
    else if (e instanceof Absyn.WhileExp)
      result = transExp((Absyn.WhileExp) e);
    else if (e instanceof Absyn.CallExp)
      result = transExp((Absyn.CallExp) e);
    else if (e instanceof Absyn.ForExp)
      result = transExp((Absyn.ForExp) e);
    else
      throw new Error("Semant.transExp");
    e.type = result.ty;
    return result;
  }

  ExpTy transExp(Absyn.OpExp e) {
    ExpTy left = transExp(e.left);
    ExpTy right = transExp(e.right);

    switch (e.oper) {
      case Absyn.OpExp.PLUS:
        checkInt(left, e.left.pos);
        checkInt(right, e.right.pos);
        return new ExpTy(null, INT);
      case Absyn.OpExp.MINUS:
        checkInt(left, e.left.pos);
        checkInt(right, e.right.pos);
        return new ExpTy(null, INT);
      case Absyn.OpExp.MUL:
        checkInt(left, e.left.pos);
        checkInt(right, e.right.pos);
        return new ExpTy(null, INT);
      case Absyn.OpExp.DIV:
        checkInt(left, e.left.pos);
        checkInt(right, e.right.pos);
        return new ExpTy(null, INT);
      case Absyn.OpExp.EQ:
        checkComparable(left, right, e.left.pos);
        return new ExpTy(null, left.ty.actual()); // ty.actual()?
      case Absyn.OpExp.NE:
        checkComparable(left, right, e.left.pos);
        return new ExpTy(null, left.ty.actual());
      case Absyn.OpExp.LT:
        checkOrderable(left, right, e.left.pos);
        return new ExpTy(null, left.ty.actual());
      case Absyn.OpExp.LE:
        checkOrderable(left, right, e.left.pos);
        return new ExpTy(null, left.ty.actual());
      case Absyn.OpExp.GT:
        checkOrderable(left, right, e.left.pos);
        return new ExpTy(null, left.ty.actual());
      case Absyn.OpExp.GE:
        checkOrderable(left, right, e.left.pos);
        return new ExpTy(null, left.ty.actual());
      default:
        throw new Error("unknown operator");
    }
  }

  /*
   * Declarations can only be made between let and in (let ...decs... in ... end)
   * Do we begin and end the env scope somewhere else?
   */
  ExpTy transExp(Absyn.LetExp e) {
    env.venv.beginScope();
    env.tenv.beginScope();
    for (Absyn.DecList d = e.decs; d != null; d = d.tail) {
      transDec(d.head);
    }
    ExpTy body = transExp(e.body);
    env.venv.endScope();
    env.tenv.endScope();
    return new ExpTy(null, body.ty);
  }

  ExpTy transExp(Absyn.IntExp e) {
    return new ExpTy(null, INT);
  }

  ExpTy transExp(Absyn.SeqExp e) {
    ExpTy ty = null;
    for (Absyn.ExpList l = e.list; l != null; l = l.tail) {
      ty = transExp(l.head);
    }
    if (ty == null)
      error(e.pos, "empty sequence of exp");

    return ty; // return type of last expression in sequence (error if ty == null)
  }

  ExpTy transExp(Absyn.StringExp e) {
    return new ExpTy(null, STRING);
  }

  ExpTy transExp(Absyn.ArrayExp e) {
    return new ExpTy(null, new Types.ARRAY(e.init.type));
  }

  ExpTy transExp(Absyn.NilExp e) {
    return new ExpTy(null, NIL);
  }

  ExpTy transExp(Absyn.RecordExp e) {
    for (Absyn.FieldExpList l = e.fields; l != null; l = l.tail) { // translate each part of the list
      transExp(l.init);
    }
    return new ExpTy(null, e.type); // then return the records type overall
  }

  ExpTy transExp(Absyn.VarExp e) {
    return transVar(e.var);
  }

  ExpTy transExp(Absyn.AssignExp e) {
    // e has a var and exp field
    // var := exp
    // left | right
    // want to type check the left with the right
    // do we actually change the values in the environment for this project?
    // if var type and exp type can be coerced to each other, return. else throw
    // error
    return new ExpTy(null, e.exp.type); // should e be null for construction?
  }

  ExpTy transExp(Absyn.IfExp e) {
    return null;
  }

  ExpTy transExp(Absyn.BreakExp e) {
    return new ExpTy(null, VOID); // check
    // need to know if we are in a loop or not. throw error if not
  }

  ExpTy transExp(Absyn.WhileExp e) {
    return null;
  }

  ExpTy transExp(Absyn.CallExp e) {
    return null;
  }

  ExpTy transExp(Absyn.ForExp e) {
    return null;
  }

  Exp transDec(Absyn.Dec d) {
    if (d instanceof Absyn.VarDec)
      return transDec((Absyn.VarDec) d);
    else if (d instanceof Absyn.TypeDec)
      return transDec((Absyn.TypeDec) d);
    else if (d instanceof Absyn.FunctionDec)
      return transDec((Absyn.FunctionDec) d);
    throw new Error("Semant.transDec");
  }

  Exp transDec(Absyn.VarDec d) {
    // NOTE: THIS IMPLEMENTATION IS INCOMPLETE
    // It is here to show you the general form of the transDec methods
    ExpTy init = transExp(d.init);
    Type type;
    if (d.typ == null) {
      type = init.ty;
    } else {
      // if type is given, we need to look up type in table and make sure
      // the exp (RHS of :=) can be coerced onto that type
      type = (Type) env.tenv.get(d.typ.name);
      if (type == null)
        throw new Error("type could not be found"); // replace throw with error func later
      else if (!type.coerceTo(init.ty))
        throw new Error("types are not compatible");
      // |||||||
    } // vvvvvvv FALL THROUGH
    d.entry = new VarEntry(type);
    env.venv.put(d.name, d.entry);
    return null;
  }

  Exp transDec(Absyn.TypeDec d) {
    Type type_;
    Types.NAME ty;
    // typedef ty type-id
    // transTy(ty) returns a type
    // need to bind ty (new type being made) to the type specified in type-id
    // if type-id can't be found in table, throw error

    // possibly also need to check that the type isn't NIL
    Absyn.TypeDec td;
    for (td = d; td != null; td = td.next) { // loop through each type declaration in a row (>= 1)
      ty = (Types.NAME) transTy(td.ty); // should we do this?
      type_ = (Type) env.tenv.get(td.name);
      if (!td.entry.coerceTo(type_)) // if entry type can't be coerced onto type_, error
        throw new Error("mismatching types");
      ty.bind(type_);
      env.tenv.put(ty.name, ty);
    }
    // after adding all types to table, check for a loop within types
    Types.NAME check;
    for (td = d; td != null; td = td.next) {
      check = (Types.NAME) env.tenv.get(td.name);
      if (check.isLoop())
        throw new Error("looping error in types");
    }
    return null;
  }

  Exp transDec(Absyn.FunctionDec d) {
    return null;
  }

  Type transTy(Absyn.Ty t) {
    if (t instanceof Absyn.NameTy)
      return transTy((Absyn.NameTy) t);
    else if (t instanceof Absyn.RecordTy)
      return transTy((Absyn.RecordTy) t);
    else if (t instanceof Absyn.ArrayTy)
      return transTy((Absyn.ArrayTy) t);
    throw new Error("Semant.transDec");
  }

  Type transTy(Absyn.NameTy t) {
    return null;
  }

  Type transTy(Absyn.RecordTy t) {
    return null;
  }

  Type transTy(Absyn.ArrayTy t) {
    return null;
  }

  ExpTy transVar(Absyn.Var v) {
    if (v instanceof Absyn.SimpleVar)
      return transVar((Absyn.SimpleVar) v);
    else if (v instanceof Absyn.FieldVar)
      return transVar((Absyn.FieldVar) v);
    else if (v instanceof Absyn.SubscriptVar)
      return transVar((Absyn.SubscriptVar) v);
    throw new Error("Semant.transVar");
  }

  ExpTy transVar(Absyn.SimpleVar v) {
    Entry x = (Entry) env.venv.get(v.name);
    if (x instanceof VarEntry) {
      VarEntry ent = (VarEntry) x;
      return new ExpTy(null, ent.ty);
    } else {
      error(v.pos, "variable is unrecognized");
      return new ExpTy(null, INT);
    }
  }

  ExpTy transVar(Absyn.FieldVar v) {
    return null;
  }

  ExpTy transVar(Absyn.SubscriptVar v) {
    return null;
  }

  // Exp transVar(Absyn.Var v) { might need this method. ref implementation has 5
  // transVar and right now we only have 4
  // return null;
  // }

}