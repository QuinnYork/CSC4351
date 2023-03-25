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
    if (e != null)
      System.out.println(e.getClass());

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
    ExpTy type = transVar(e.var);
    Type etype = e.type;
    if (!etype.coerceTo(type.ty))
      throw new Error("incompatible types");
    return new ExpTy(null, e.exp.type); // shouldn't have to bind the type since it isn't a VarDec
    // Only throw error for incompatible types
  }

  ExpTy transExp(Absyn.IfExp e) {
    ExpTy test = transExp(e.test);
    checkInt(test, e.test.pos);
    ExpTy thenclause = transExp(e.thenclause);
    ExpTy elseclause = transExp(e.elseclause);

    if (e.elseclause == null) {
      return new ExpTy(null, elseclause.ty);
    } else {
      if (!thenclause.ty.coerceTo(elseclause.ty) && !elseclause.ty.coerceTo(thenclause.ty))
        error(e.pos, "type does not match");
      return elseclause;
    }
  }

  ExpTy transExp(Absyn.BreakExp e) {
    return new ExpTy(null, VOID); // check
    // need to know if we are in a loop or not. throw error if not
  }

  ExpTy transExp(Absyn.WhileExp e) {
    ExpTy tt = transExp(e.test);
    checkInt(tt, e.test.pos);
    // Loop (class) variable = new Loop(env)
    // state = variable.transExp
    // if (!state.coerceTo())
    // error
    // return new ExpTy
    return null;
  }

  ExpTy transExp(Absyn.CallExp e) { // function call expression
    // could potentially be calling a function inside of another function so
    // we should check the table and see if the function name is declared
    if (env.venv.get(e.func) == null) // not in table
      error(e.pos, "function " + e.func + " was not declared");
    return new ExpTy(null, e.type);
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
      System.out.println("name of function: " + fd.name.toString());
      if (f != null)
        System.out.println(" has a body: " + f.hasBody);
      if (f != null &&
          (f.hasBody || (!f.hasBody && fd.body == null)))
        error(fd.pos, "multiple functions of the same name cannot be declared");
      Type result = transTy(d.result); // gets return type of function
      Types.RECORD fields = transTypeFields(d.params);
      body = (fd.body != null);
      env.venv.put(d.name, new FunEntry(fields, result, body));
      Type ret = transExp(d.body).ty; // does this return the last expression in the function, aka the result?
      if (!ret.coerceTo(result))
        error(d.pos, "return type is different from function result needed");
    }

    for (fd = d; fd != null; fd = fd.next) { // add all params to scope and check if a func doesn't have a body
      if (!(env.venv.get(fd.name) instanceof FunEntry))
        error(fd.pos, "not a function entry in table");
      FunEntry fe = (FunEntry) env.venv.get(fd.name);
      if (!fe.hasBody)
        error(fd.pos, "function \"" + fd.name.toString() + "\" was not defined");
      fd.entry = fe;
      env.venv.beginScope();
      for (Absyn.FieldList param = d.params; param != null; param = param.tail)
        env.venv.put(param.name, new VarEntry((Type) env.venv.get(param.typ)));
      env.venv.endScope();
    }
    return null;
  }

  // private ExpTy transFields(Absyn.FieldList f) {
  // return null;
  // }

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
    return transTypeFields(t.fields);
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
    } else {
      error(v.pos, "variable is unrecognized");
      return new ExpTy(null, INT);
    }
  }

  ExpTy transVar(Absyn.FieldVar v) { // a.x, return type of x
    Type type = transVar(v.var).ty; // get type of variable that is calling a field
    // check if type is a RECORD. if it's not, throw error (can only reference a
    // field if variable is a record type)
    if (type instanceof Types.RECORD) {
      // get type of field
      type = (Type) env.tenv.get(v.field);
      return new ExpTy(null, type);
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
    return transVar(v.var);
  }

  // Exp transVar(Absyn.Var v) { might need this method. ref implementation has 5
  // transVar and right now we only have 4
  // return null;
  // }

}