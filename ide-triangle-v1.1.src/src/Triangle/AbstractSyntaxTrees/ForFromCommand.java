package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * @description Clase para representar un AST de la iteraci�n en un loop for
 * @author Joseph
 * @codigo J.15
 */
public class ForFromCommand extends AST {

  public ForFromCommand (Identifier iAST, Expression eAST, SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    E = eAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitForFromCommand(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitForFromCommand(this, o);
  }
  
  public Identifier I;
  public Expression E;
}