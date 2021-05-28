/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Represents a SubscriptVarName
 * @author Andres
 * A.107
 */
public class SubscriptVarName extends VarName {
    public SubscriptVarName (VarName vAST, Expression eAST, SourcePosition thePosition) {
        super (thePosition);
        V = vAST;
        E = eAST;
    }

  public Object visit (Visitor v, Object o) {
    return v.visitSubscriptVarName(this, o);
  }
  public Object visitXML(Visitor v, Object o) {
    return v.visitSubscriptVarName(this, o);
  }

  public Expression E;
  public VarName V;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
