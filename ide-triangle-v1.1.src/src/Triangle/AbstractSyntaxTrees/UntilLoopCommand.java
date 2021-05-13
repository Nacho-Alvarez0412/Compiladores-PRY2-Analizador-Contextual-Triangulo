package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * @description Clase para representar un AST de un comando until loop
 * @author Joseph
 * @codigo J.15
 */
public class UntilLoopCommand extends Command {

  public UntilLoopCommand (Expression eAST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    E = eAST;
    C = cAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitUntilLoopCommand(this, o);
  }
  public Object visitXML(Visitor v, Object o) {
    return v.visitUntilLoopCommand(this, o);
  }
  
  public Expression E;
  public Command C;
}
