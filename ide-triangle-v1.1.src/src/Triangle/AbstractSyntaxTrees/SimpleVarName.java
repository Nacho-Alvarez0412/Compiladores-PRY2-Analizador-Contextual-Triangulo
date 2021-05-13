/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Represents a SimpleVarName AST
 * @author Andres
 * A.105
 */
public class SimpleVarName extends VarName {
  public SimpleVarName (Identifier iAST, SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
  }

  public Object visit (Visitor v, Object o) {
    return v.visitSimpleVarName(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitSimpleVarName(this, o);
  }

  public Identifier I;
}
