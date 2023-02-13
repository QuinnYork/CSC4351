package Absyn;
import Symbol.Symbol;
public class ForExp extends Exp {
   public Absyn var;	// var is either a VarDec or an AssignExp
   public Exp hi, body;
   public ForExp(int p, Absyn v, Exp h, Exp b) {pos=p; var=v; hi=h; body=b;}
}
