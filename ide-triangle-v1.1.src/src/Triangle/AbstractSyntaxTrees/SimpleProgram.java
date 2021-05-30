/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Represents a SimpleProgram AST
 * @author Andres
 * A.132
 */
public class SimpleProgram extends Program {
  public SimpleProgram (Command cAST, SourcePosition thePosition) {
    super (thePosition);
    C = cAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitSimpleProgram(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitSimpleProgram(this, o);
  }

  public Command C;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
