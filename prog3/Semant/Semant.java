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

  // static boolean that will only change to true if inside of a loop and once
  // body is translated, boolean will be false again
  static boolean inLoop = false;

  private Exp checkInt(ExpTy et, int pos) {
    if (!INT.coerceTo(et.ty))
      error(pos, "integer required");
    return et.exp;
  }

  // check that left and right are the same type
  private boolean checkComparable(ExpTy left, ExpTy right, int pos) {
    if (!left.ty.coerceTo(right.ty))
      error(pos, "not equal types");
    else if (left.ty == NIL || right.ty == NIL)
      error(pos, "can not compare to nil");
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
        return new ExpTy(null, left.ty.actual());
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
      ty = new ExpTy(null, VOID);

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
    VarEntry ve = (VarEntry) env.venv.get(((Absyn.SimpleVar) e.var).name);
    if (ve != null && ve instanceof LoopVarEntry)
      error(e.pos, "loop variable can not be redeclared inside of loop"); // scope ends after loop ends so there
                                                                          // shouldn't be an instance of a LoopVarEntry
                                                                          // outside of a loop
    ExpTy type = transVar(e.var);
    ExpTy etype = transExp(e.exp);
    if (!etype.ty.coerceTo(type.ty))
      error(e.pos, "incompatible types");
    return etype; // shouldn't have to bind the type since it isn't a VarDec
    // Only throw error for incompatible types
  }

  ExpTy transExp(Absyn.IfExp e) {
    Type t = transExp(e.test).ty;
    if (!t.coerceTo(INT))
      error(e.pos, "condition test can not return a non-Integer");
    t = transExp(e.thenclause).ty;
    if (e.elseclause == null && !t.coerceTo(VOID)) // no else, then should not return a result
      error(e.pos, "then without an else can not return a result");
    else if (e.elseclause != null) {
      Type else_type = transExp(e.elseclause).ty;
      if (!else_type.coerceTo(t))
        error(e.elseclause.pos, "then and else clause have to have same result");
      return new ExpTy(null, else_type);
    }
    return new ExpTy(null, t);
  }

  ExpTy transExp(Absyn.BreakExp e) {
    if (inLoop == false)
      error(e.pos, "not inside of loop, break is invalid here");
    return new ExpTy(null, VOID);
  }

  ExpTy transExp(Absyn.WhileExp e) {
    Type t = transExp(e.test).ty;
    if (!t.coerceTo(INT))
      error(e.test.pos, "condition test can not return a non-Integer");
    inLoop = true;
    t = transExp(e.body).ty;
    if (!t.coerceTo(VOID))
      error(e.body.pos, "body can not return a value");
    inLoop = false;
    return new ExpTy(null, t);
  }

  ExpTy transExp(Absyn.CallExp e) { // function call expression
    ExpTy arg_type;
    FunEntry func = (FunEntry) env.venv.get(e.func);
    // could potentially be calling a function inside of another function so
    // we should check the table and see if the function name is declared
    if (func == null) // not in table
      error(e.pos, "function " + e.func + " was not declared");
    Types.RECORD field = func.formals;
    for (Absyn.ExpList el = e.args; el != null; el = el.tail) {
      if (field == null) {
        error(e.pos, "count on params vs args is not equal");
        break;
      }
      arg_type = transArgs(el.head);
      if (!arg_type.ty.coerceTo(field.fieldType))
        error(e.pos, "mismatched type: " + arg_type.ty + " cannot be coerced to -> " + field.fieldType);
      field = field.tail;
    }
    if (field != null)
      error(e.pos, "count on params vs args is not equal");

    return new ExpTy(null, func.result);
  }

  private ExpTy transArgs(Absyn.Exp e) {
    return transExp(e);
  }

  ExpTy transExp(Absyn.ForExp e) {
    // when initializing loop variable, create a LoopVarEntry for it so if inside of
    // the for loop the var is redeclared, report an error
    LoopVarEntry lve;
    env.venv.beginScope();
    if (e.var instanceof Absyn.AssignExp) {
      Absyn.AssignExp ae = (Absyn.AssignExp) e.var;
      ExpTy assign_type = transExp(ae);
      checkInt(assign_type, ae.pos);
      lve = new LoopVarEntry(assign_type.ty);
      env.venv.put(((Absyn.SimpleVar) ae.var).name, lve);
    } else if (e.var instanceof Absyn.VarExp)
      transLoopVarDec((Absyn.VarDec) e.var);
    else {
      error(e.var.pos, "variable initialization missing");
      return null;
    }
    // loop var has been translated
    inLoop = true;
    transExp(e.hi); // is there limitation on what type this can be?
    // transBody of loop
    // if body contains a VarDec or AssignExp and the variable is the loop variable,
    // throw an error
    Type t = transExp(e.body).ty;
    if (!t.coerceTo(VOID))
      error(e.body.pos, "body of loop can not return a result");
    env.venv.endScope();
    inLoop = false;
    if (!e.type.coerceTo(VOID))
      error(e.body.pos, "loop can not return a result");
    return new ExpTy(null, t);
  }

  private Exp transLoopVarDec(Absyn.VarDec d) {
    ExpTy init = transExp(d.init);
    if (init.ty == NIL)
      error(d.pos, "variable can not be initialized to nil");
    Type type;
    if (d.typ == null) {
      type = init.ty;
    } else {
      // if type is given, we need to look up type in table and make sure
      // the exp (RHS of :=) can be coerced onto that type
      type = (Type) env.tenv.get(d.typ.name);
      if (type == null)
        error(d.pos, "type could not be found"); // replace throw with error func later
      else if (!type.coerceTo(init.ty))
        error(d.pos, "types are not compatible");
      // |||||||
    } // vvvvvvv FALL THROUGH
    d.entry = new LoopVarEntry(type);
    env.venv.put(d.name, d.entry);
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
    VarEntry ve = (VarEntry) env.venv.get(d.name);
    if (ve != null && ve instanceof LoopVarEntry)
      error(d.pos, "loop variable can not be redeclared inside of loop"); // scope ends after loop ends so there
                                                                          // shouldn't be an instance of a LoopVarEntry
                                                                          // outside of a loop
    ExpTy init = transExp(d.init);
    if (init.ty == NIL)
      error(d.pos, "variable can not be initialized to nil");
    Type type;
    if (d.typ == null) {
      type = init.ty;
    } else {
      // if type is given, we need to look up type in table and make sure
      // the exp (RHS of :=) can be coerced onto that type
      type = (Type) env.tenv.get(d.typ.name);
      if (type == null)
        error(d.pos, "type could not be found"); // replace throw with error func later
      else if (!type.coerceTo(init.ty))
        error(d.pos, "types are not compatible");
      // |||||||
    } // vvvvvvv FALL THROUGH
    d.entry = new VarEntry(type);
    env.venv.put(d.name, d.entry);
    return null;
  }

  Exp transDec(Absyn.TypeDec d) {
    Types.NAME ty;
    Type bind_;

    // possibly also need to check that the type isn't NIL
    Absyn.TypeDec td;
    for (td = d; td != null; td = td.next) { // enter types into table and check for redeclarations
      bind_ = transTy(td.ty);
      ty = new Types.NAME(td.name);
      if (env.tenv.get(ty.name) != null)
        error(td.pos, "type can not be redeclared");
      env.tenv.put(ty.name, ty);
    }

    for (td = d; td != null; td = td.next) { // loop through each type declaration in a row (>= 1)
      // bind type-id to the type of ty (typedef ty type-id)
      bind_ = transTy(td.ty);
      ty = (Types.NAME) env.tenv.get(td.name);
      ty.bind(bind_);
      td.entry = ty;
    }

    // after adding all types to table, check for a loop within types
    Types.NAME check;
    for (td = d; td != null; td = td.next) {
      check = (Types.NAME) env.tenv.get(td.name);
      if (check.isLoop())
        error(td.pos, "looping error in type declarations");
    }
    return null;
  }

  Exp transDec(Absyn.FunctionDec d) {
    // do we have to type check the return value of the body with the result type?
    // do we have to parse the body of the function and if it recursively calls a
    // function,
    // check to make sure the function called was defined in the same place as the
    // caller?
    Absyn.FunctionDec fd;
    boolean body;
    for (fd = d; fd != null; fd = fd.next) { // add functions to table and type-check bodies
      FunEntry f = (FunEntry) env.venv.get(fd.name);
      fd.entry = f;
      System.out.println("\nfunction \"" + fd.name + "\" is being declared/defined:");
      if (f != null &&
          (f.hasBody || (!f.hasBody && fd.body == null)))
        error(fd.pos, "multiple functions of the same name cannot be declared");

      Type result = transTy(fd.result); // gets return type of function
      Types.RECORD fields = transTypeFields(fd.params);
      body = (fd.body != null);
      FunEntry fe = new FunEntry(fields, result, body);
      env.venv.put(fd.name, fe);
      env.venv.beginScope();
      for (Absyn.FieldList param = fd.params; param != null; param = param.tail) {
        System.out.println("parameter \"" + param.name + "\" is being entered into table with type " + param.typ);
        VarEntry ve = new VarEntry((Type) env.tenv.get(param.typ));
        env.venv.put(param.name, ve);
      }
      System.out.println("translating function body of \"" + fd.name + "\"...");
      ExpTy ret = transExp(fd.body);
      System.out.println("return type of body was: \"" + ret.ty + "\"");
      if (!ret.ty.coerceTo(result))
        error(fd.pos, "return type is different from function result needed");
      env.venv.endScope();
    }

    for (fd = d; fd != null; fd = fd.next) { // add all params to scope and check if a func doesn't have a body
      if (!(env.venv.get(fd.name) instanceof FunEntry))
        error(fd.pos, "not a function entry in table");
      FunEntry fe = (FunEntry) env.venv.get(fd.name);
      if (!fe.hasBody)
        error(fd.pos, "function \"" + fd.name.toString() + "\" was not defined");
    }
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

  Type transTy(Absyn.NameTy t) { // do we only use Ty's when declaring a type?
    Types.NAME type;
    type = (Types.NAME) env.tenv.get(t.name); // gets Name Type from table
    if (type == null) {// not declared yet
      error(t.pos, "type was not declared");
      type = new Types.NAME(t.name);
    }
    return type;
  }

  Type transTy(Absyn.RecordTy t) {
    /*
     * Go through each symbol in field list and create it's type
     * as a record type. First one will be the head then use
     * field list's tail field to get the next record.
     * Keep looping through each field until null
     * Add on the next field to the previous one when
     * constructing the RECORD type
     * Will have to use recursion so we can wait until tail is null
     * and then construct each Types.RECORD before the previous
     * Types.RECORD
     */
    return transTypeFields(t.fields); // make a different method for record types
  }

  private Types.RECORD transTypeFields(Absyn.FieldList f) {
    Types.RECORD rec;
    if (f == null) { // base case
      return null;
    }
    Type type = (Type) env.tenv.get(f.typ);
    rec = new Types.RECORD(f.name, type, transTypeFields(f.tail));
    return rec;
  }

  Type transTy(Absyn.ArrayTy t) {
    Type type;
    type = (Type) env.tenv.get(t.typ); // gets type from table
    if (type == null)// not declared yet
      throw new Error("type doesn't exist");
    return new Types.ARRAY(type);
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
    } else if (x instanceof FunEntry) {
      FunEntry ent = (FunEntry) x;
      return new ExpTy(null, ent.result);
    } else {
      error(v.pos, "variable \"" + v.name + "\" is unrecognized");
      return new ExpTy(null, INT);
    }
  }

  ExpTy transVar(Absyn.FieldVar v) { // a.x, return type of x
    Type type = transVar(v.var).ty; // get type of variable that is calling a field
    if (type == NIL)
      error(v.pos, "can not access a field of a record initialized to be nil");
    // check if type is a RECORD. if it's not, throw error (can only reference a
    // field if variable is a record type)
    if (type instanceof Types.RECORD) {
      // get type of field
      for (Types.RECORD rec = (Types.RECORD) type; rec != null; rec = rec.tail) {
        if (rec.fieldName == v.field) { // if field in record = FieldVar's field, return type of that field
          type = (Type) rec.fieldType;
          return new ExpTy(null, type);
        }
      }
      // field wasn't found
      error(v.pos, "field specified is not in record");
      return new ExpTy(null, INT); // type of anything, doesn't matter
    } else {
      error(v.pos, "can't access a field of a non-record type");
      return new ExpTy(null, INT); // type of anything, doesn't matter
    }
  }

  ExpTy transVar(Absyn.SubscriptVar v) { // a[x], return type of subscript (instead of an ARRAY type with a type field)
    // make sure variable is in table
    // return type of variable
    // should we be able to know what type a[x] would return and in turn check
    // it with the type of a?
    // Also do we check if x is in bounds of the array?
    Type type = transExp(v.index).ty;
    if (!type.coerceTo(INT))
      error(v.index.pos, "index of array has to be of type int");
    return transVar(v.var);
  }

  // Exp transVar(Absyn.Var v) { might need this method. ref implementation has 5
  // transVar and right now we only have 4
  // return null;
  // }

}