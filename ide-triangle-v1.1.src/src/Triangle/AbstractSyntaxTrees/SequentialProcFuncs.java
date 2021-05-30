/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

/**
 * @newclass
 * @description Clase para representar un AST de dos o m�s procedures-functions
 * @author Joseph
 * @codigo J.39
 */

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialProcFuncs extends ProcFuncs {

  public SequentialProcFuncs (ProcFuncs pf1AST, ProcFuncs pf2AST,
                       SourcePosition thePosition) {
    super (thePosition);
    PF1 = pf1AST;
    PF2 = pf2AST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitSequentialProcFuncs(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitSequentialProcFuncs(this, o);
  }

  public ProcFuncs PF1, PF2;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}