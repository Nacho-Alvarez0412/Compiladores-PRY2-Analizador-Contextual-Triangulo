package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * @description Clase para representar un AST de un comando loop for until
 * @author Joseph
 * @codigo J.26
 */
public class ForLoopUntilCommand extends Command {

  public ForLoopUntilCommand (ForFromCommand ffcAST, Expression e1AST, Expression e2AST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    FFC = ffcAST;
    E1 = e1AST;
    E2 = e2AST;
    C = cAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitForLoopUntilCommand(this, o);
  }
  public Object visitXML(Visitor v, Object o) {
    return v.visitForLoopUntilCommand(this, o);
  }
  
  public ForFromCommand FFC;
  public Expression E1,E2;
  public Command C;
  
}