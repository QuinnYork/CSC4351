package FindEscape;

public class FindEscape {
  Symbol.Table escEnv = new Symbol.Table(); // escEnv maps Symbol to Escape
  Absyn.FunctionDec isLeafFunc;

  public FindEscape(Absyn.Exp e) {
    traverseExp(0, e);
  }

  private void traverseVar(int depth, Absyn.Var v) {
    if (v instanceof Absyn.FieldVar)
      traverseVar(depth, (Absyn.FieldVar) v);
    else if (v instanceof Absyn.SimpleVar)
      traverseVar(depth, (Absyn.SimpleVar) v);
    else if (v instanceof Absyn.SubscriptVar)
      traverseVar(depth, (Absyn.SubscriptVar) v);
    else
      System.out.println("error in traverseVar");
  }

  private void traverseVar(int depth, Absyn.FieldVar v) {
    traverseVar(depth, v.var);
  }

  private void traverseVar(int depth, Absyn.SubscriptVar v) {
    traverseVar(depth, v.var);
  }

  private void traverseVar(int depth, Absyn.SimpleVar v) {
    Escape escape = (Escape) escEnv.get(v.name);
    if ((escape == null) || (depth < escape.depth)) {
      return;
    }
    escape.setEscape();
  }

  private void traverseExp(int depth, Absyn.Exp e) {
    if (e instanceof Absyn.VarExp)
      traverseExp(depth, (Absyn.VarExp) e);
    else if (e instanceof Absyn.ArrayExp)
      traverseExp(depth, (Absyn.ArrayExp) e);
    else if (e instanceof Absyn.AssignExp)
      traverseExp(depth, (Absyn.AssignExp) e);
    else if (e instanceof Absyn.CallExp)
      traverseExp(depth, (Absyn.CallExp) e);
    else if (e instanceof Absyn.IfExp)
      traverseExp(depth, (Absyn.IfExp) e);
    else if (e instanceof Absyn.ForExp)
      traverseExp(depth, (Absyn.ForExp) e);
    else if (e instanceof Absyn.LetExp)
      traverseExp(depth, (Absyn.LetExp) e);
    else if (e instanceof Absyn.OpExp)
      traverseExp(depth, (Absyn.OpExp) e);
    else if (e instanceof Absyn.RecordExp)
      traverseExp(depth, (Absyn.RecordExp) e);
    else if (e instanceof Absyn.SeqExp)
      traverseExp(depth, (Absyn.SeqExp) e);
    else if (e instanceof Absyn.WhileExp)
      traverseExp(depth, (Absyn.WhileExp) e);
    else if (e instanceof Absyn.NilExp
        || e instanceof Absyn.IntExp
        || e instanceof Absyn.BreakExp
        || e instanceof Absyn.StringExp)
      return;
  }

  private void traverseExp(int depth, Absyn.VarExp e) {
    traverseVar(depth, e.var);
  }

  private void traverseExp(int depth, Absyn.ArrayExp e) {
    traverseExp(depth, e.size);
    traverseExp(depth, e.init);
  }

  private void traverseExp(int depth, Absyn.AssignExp e) {
    traverseVar(depth, e.var);
    traverseExp(depth, e.exp);
  }

  private void traverseExp(int depth, Absyn.CallExp e) {
    if (isLeafFunc != null) {
      isLeafFunc.leaf = false;
    }

    Absyn.ExpList arg = e.args;
    while (arg != null) {
      traverseExp(depth, arg.head);
      arg = arg.tail;
    }
  }

  private void traverseExp(int depth, Absyn.IfExp e) {
    traverseExp(depth, e.test);
    traverseExp(depth, e.thenclause);

    if (e.elseclause != null) {
      traverseExp(depth, e.elseclause);
    }
  }

  private void traverseExp(int depth, Absyn.ForExp e) {
    if (e.var instanceof Absyn.AssignExp)
      traverseExp(depth, (Absyn.AssignExp) e.var);
    else if (e.var instanceof Absyn.VarDec)
      traverseDec(depth, (Absyn.VarDec) e.var);
    else
      System.out.println("error in traverseForExp: loop var is not of a correct class");
    traverseExp(depth, e.hi);
    traverseExp(depth, e.body);
  }

  private void traverseExp(int depth, Absyn.LetExp e) {
    escEnv.beginScope();

    Absyn.DecList decl = e.decs;
    while (decl != null) {
      traverseDec(depth, decl.head);
      decl = decl.tail;
    }
    traverseExp(depth, e.body);
    escEnv.endScope();
  }

  private void traverseExp(int depth, Absyn.OpExp e) {
    traverseExp(depth, e.left);
    traverseExp(depth, e.right);
  }

  private void traverseExp(int depth, Absyn.RecordExp e) {
    Absyn.FieldExpList field = e.fields;
    while (field != null) {
      traverseExp(depth, field.init);
      field = field.tail;
    }
  }

  private void traverseExp(int depth, Absyn.SeqExp e) {
    Absyn.ExpList l = e.list;
    while (l != null) {
      traverseExp(depth, l.head);
      l = l.tail;
    }

  }

  private void traverseExp(int depth, Absyn.WhileExp e) {
    traverseExp(depth, e.test);
    traverseExp(depth, e.body);
  }

  private void traverseDec(int depth, Absyn.Dec d) {
    if (d instanceof Absyn.VarDec)
      traverseDec(depth, (Absyn.VarDec) d);
    else if (d instanceof Absyn.FunctionDec)
      traverseDec(depth, (Absyn.FunctionDec) d);
    else if (d instanceof Absyn.TypeDec)
      return;
  }

  private void traverseDec(int depth, Absyn.VarDec d) {
    traverseExp(depth, d.init);
    escEnv.put(d.name, new VarEscape(depth, d));
  }

  private void traverseDec(int depth, Absyn.FunctionDec d) {
    Absyn.FunctionDec func = d;
    while (func != null) {
      escEnv.beginScope();
      Absyn.FieldList functionParam = func.params;
      while (functionParam != null) {
        escEnv.put(functionParam.name, new FormalEscape(depth + 1, functionParam));
        functionParam = functionParam.tail;
      }
      escEnv.endScope();
      traverseExp(depth + 1, func.body);
      func = func.next;
    }
  }

}
