package FindEscape;

public class FindEscape {
  Symbol.Table escEnv = new Symbol.Table(); // escEnv maps Symbol to Escape
  Absyn.FunctionDec isLeafFunc;

  public FindEscape(Absyn.Exp e) {
    traverseExp(0, e);
  }

  void traverseVar(int depth, Absyn.Var v) {
    if (v.getClass().equals(Absyn.FieldVar.class)) {
      traverseVar(depth, (Absyn.FieldVar) v);
    }

    else if (v.equals(Absyn.SimpleVar.class)) {
      traverseVar(depth, (Absyn.SimpleVar) v);
    }

    else if (v.getClass().equals(Absyn.SubscriptVar.class)) {
      traverseVar(depth, (Absyn.SubscriptVar) v);
    }

    else {
      System.out.println("error in transvar");
    }
  }

  void traverseVar(int depth, Absyn.FieldVar v) {
    traverseVar(depth, v.var);
  }

  void traverseVar(int depth, Absyn.SubscriptVar v) {
    traverseVar(depth, v.var);
  }

  void traverseVar(int depth, Absyn.SimpleVar v) {
    Escape escape = (Escape) escEnv.get(v.name);
    if ((escape == null) || (depth < escape.depth)) {
      return;
    }
    escape.setEscape();

  }

  void traverseExp(int depth, Absyn.Exp e) {
    Class a = e.getClass();

    if (a.equals(Absyn.VarExp.class)) {
      traverseExp(depth, (Absyn.VarExp) e);
    }

    else if (a.equals(Absyn.ArrayExp.class)) {
      traverseExp(depth, (Absyn.ArrayExp) e);
    }

    else if (a.equals(Absyn.AssignExp.class)) {
      traverseExp(depth, (Absyn.AssignExp) e);
    }

    else if (a.equals(Absyn.CallExp.class)) {
      traverseExp(depth, (Absyn.CallExp) e);
    }

    else if (a.equals(Absyn.IfExp.class)) {
      traverseExp(depth, (Absyn.IfExp) e);
    }

    else if (a.equals(Absyn.ForExp.class)) {
      traverseExp(depth, (Absyn.ForExp) e);
    }

    else if (a.equals(Absyn.LetExp.class)) {
      traverseExp(depth, (Absyn.LetExp) e);
    }

    else if (a.equals(Absyn.OpExp.class)) {
      traverseExp(depth, (Absyn.OpExp) e);
    }

    else if (a.equals(Absyn.RecordExp.class)) {
      traverseExp(depth, (Absyn.RecordExp) e);
    }

    else if (a.equals(Absyn.SeqExp.class)) {
      traverseExp(depth, (Absyn.SeqExp) e);
    }

    else if (a.equals(Absyn.WhileExp.class)) {
      traverseExp(depth, (Absyn.WhileExp) e);
    }

    if (a.equals(Absyn.NilExp.class) || a.equals(Absyn.IntExp.class) || a.equals(Absyn.BreakExp.class)
        || a.equals(Absyn.StringExp.class)) {
      return;
    }

  }

  void traverseExp(int depth, Absyn.VarExp e) {
    traverseVar(depth, e.var);
  }

  void traverseExp(int depth, Absyn.ArrayExp e) {
    traverseExp(depth, e.size);
    traverseExp(depth, e.init);
  }

  void traverseExp(int depth, Absyn.AssignExp e) {
    traverseVar(depth, e.var);
    traverseExp(depth, e.exp);
  }

  void traverseExp(int depth, Absyn.CallExp e) {
    if (isLeafFunc != null) {
      isLeafFunc.leaf = false;
    }

    Absyn.ExpList arg = e.args;
    while (arg != null) {
      traverseExp(depth, arg.head);
      arg = arg.tail;
    }
  }

  void traverseExp(int depth, Absyn.IfExp e) {
    traverseExp(depth, e.test);
    traverseExp(depth, e.thenclause);

    if (e.elseclause != null) {
      traverseExp(depth, e.elseclause);
    }
  }

  void traverseExp(int depth, Absyn.ForExp e) {
    // kind of very confused for this one :(
  }

  void traverseExp(int depth, Absyn.LetExp e) {
    escEnv.beginScope();

    Absyn.DecList decl = e.decs;
    while (decl != null) {
      traverseDec(depth, decl.head);
      decl = decl.tail;
    }
  }

  void traverseExp(int depth, Absyn.OpExp e) {
    traverseExp(depth, e.left);
    traverseExp(depth, e.right);
  }

  void traverseExp(int depth, Absyn.RecordExp e) {
    Absyn.FieldExpList field = e.fields;
    while (field != null) {
      traverseExp(depth, field.init);
      field = field.tail;
    }

  }

  void traverseExp(int depth, Absyn.SeqExp e) {
    Absyn.ExpList l = e.list;
    while (l != null) {
      traverseExp(depth, l.head);
      l = l.tail;
    }

  }

  void traverseExp(int depth, Absyn.WhileExp e) {
    traverseExp(depth, e.test);
    traverseExp(depth, e.body);
  }

  void traverseDec(int depth, Absyn.Dec d) {
    if (d.getClass().equals(Absyn.VarDec.class)) {
      traverseDec(depth, (Absyn.VarDec) d);
    }

    else if (d.getClass().equals(Absyn.FunctionDec.class)) {
      traverseDec(depth, (Absyn.FunctionDec) d);
    }

    else if (d.getClass().equals(Absyn.TypeDec.class)) {
      return;
    }
  }

  void traverseDec(int depth, Absyn.VarDec d) {
    traverseExp(depth, d.init);
    escEnv.put(d.name, new VarEscape(depth, d));
  }

  void traverseDec(int depth, Absyn.FunctionDec d) {
    Absyn.FunctionDec func = d;
    while (func != null) {
      escEnv.beginScope();
      func = func.next;
      Absyn.FieldList functionParam = func.params;
      while (functionParam != null) {
        escEnv.put(functionParam.name, new FormalEscape(depth + 1, functionParam));
        functionParam = functionParam.tail;
      }
      escEnv.endScope();
      traverseExp(depth + 1, func.body);
    }
  }

}
