package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * @description Clase para representar un AST de un comando do while loop
 * @author Joseph
 * @codigo J.22
 */
public class DoLoopWhileCommand extends Command {

  public DoLoopWhileCommand (Command cAST, Expression eAST, SourcePosition thePosition) {
    super (thePosition);
    C = cAST;
    E = eAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitDoLoopWhileCommand(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitDoLoopWhileCommand(this, o);
  }
  
  public Command C;
  public Expression E;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}