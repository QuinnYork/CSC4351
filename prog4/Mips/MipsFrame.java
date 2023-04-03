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
    if (!escape) {// room in temporary storage
      count -= wordSize; // formal still has space allocated
      return new InReg(new Temp());
    }
    InFrame i = new InFrame(count);
    count -= wordSize;
    return i; // else allocate on stack
  }
}
