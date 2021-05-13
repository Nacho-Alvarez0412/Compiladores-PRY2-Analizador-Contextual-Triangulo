/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Represents a CompoundProgram
 * @author Andres
 * A.133
 */
public class CompoundProgram extends Program {
  public CompoundProgram (PackageDeclaration pdAST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    PD = pdAST;
    C = cAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitCompoundProgram(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitCompoundProgram(this, o);
  }

  public PackageDeclaration PD;
  public Command C;
}
