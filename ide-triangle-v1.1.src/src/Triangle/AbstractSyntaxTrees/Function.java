package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * @description Clase para representar un AST de una Function
 * @author Joseph
 * @codigo J.34
 */
public class Function extends ProcFunc {

  public Function (Identifier iAST, FormalParameterSequence fpsAST, TypeDenoter tdAST,Expression eAST ,SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    FPS = fpsAST;
    TD = tdAST;
    E = eAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitFunction(this, o);
  }
  
  public Object visit2(Visitor v, Object o) {
    return v.visitFunction2(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitFunction(this, o);
  }
  
  public Identifier I;
  public FormalParameterSequence FPS;
  public TypeDenoter TD;
  public Expression E;
}
