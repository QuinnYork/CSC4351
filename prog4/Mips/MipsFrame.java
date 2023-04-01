package Mips;

import java.util.Hashtable;
import Symbol.Symbol;
import Temp.Temp;
import Temp.Label;
import Frame.Frame;
import Frame.Access;
import Frame.AccessList;

public class MipsFrame extends Frame {

  private int count = 0;
  private static final int MAX_REGISTERS = 32;

  public Frame newFrame(Symbol name, Util.BoolList formals) {
    Label label;
    if (name == null)
      label = new Label();
    else if (this.name != null)
      label = new Label(this.name + "." + name + "." + count++);
    else
      label = new Label(name);
    return new MipsFrame(label, formals);
  }

  public MipsFrame() {
  }

  private MipsFrame(Label n, Util.BoolList f) {
    name = n;
    this.formals = getAccess(f);
  }

  private AccessList getAccess(Util.BoolList f) {
    if (f == null)
      return null;
    return new AccessList(allocLocal(f.head), getAccess(f.tail));
  }

  private static final int wordSize = 4;

  public int wordSize() {
    return wordSize;
  }

  public Access allocLocal(boolean escape) {
    Temp t = new Temp();
    if (!escape && t.key() < MAX_REGISTERS) // room in temporary storage
      return new InReg(t);
    count -= 4;
    InFrame i = new InFrame(count);
    return i; // else allocate on stack
  }
}
