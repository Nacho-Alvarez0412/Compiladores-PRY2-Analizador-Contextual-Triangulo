package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * @description Clase para representar un AST de un procedure
 * @author Joseph
 * @codigo J.33
 */
public class Procedure extends ProcFunc {

  public Procedure (Identifier iAST, FormalParameterSequence fpsAST,Command cAST, SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    FPS = fpsAST;
    C = cAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitProcedure(this, o);
  }
  
  public Object visit2(Visitor v, Object o) {
    return v.visitProcedure2(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitProcedure(this, o);
  }
  
  public Identifier I;
  public FormalParameterSequence FPS;
  public Command C;
}

