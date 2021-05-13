package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * @description Clase para representar un AST de un comando loo for do
 * @author Joseph
 * @codigo J.24
 */
public class ForLoopDoCommand extends Command {

  public ForLoopDoCommand (ForFromCommand ffcAST, Expression e1AST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    FFC = ffcAST;
    E = e1AST;
    C = cAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitForLoopDoCommand(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitForLoopDoCommand(this, o);
  }
  
  public ForFromCommand FFC;
  public Expression E;
  public Command C;
}
