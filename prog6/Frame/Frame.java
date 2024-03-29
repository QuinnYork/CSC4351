package Frame;

public abstract class Frame implements Temp.TempMap {
    public Temp.Label name;
    public AccessList formals;

    abstract public Frame newFrame(Symbol.Symbol name, Util.BoolList formals);

    abstract public Access allocLocal(boolean escape);

    abstract public Temp.Temp FP();

    abstract public int wordSize();

    abstract public Tree.Exp externalCall(String func, Tree.ExpList args);

    abstract public Temp.Temp RV();

    abstract public Tree.Stm procEntryExit1(Tree.Stm body);

    abstract public String string(Temp.Label label, String value);

    abstract public Temp.Label badPtr();

    abstract public Temp.Label badSub();

    abstract public Assem.InstrList procEntryExit2(Assem.InstrList body);

    abstract public Proc procEntryExit3(Assem.InstrList body);

    abstract public String tempMap(Temp.Temp temp);

    abstract public Assem.InstrList codegen(Tree.Stm stm);
}
