/*
 * @(#)SubscriptVname.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SubscriptVname extends Vname {

  public SubscriptVname (Vname vAST, Expression eAST, SourcePosition thePosition) {
    super (thePosition);
    V = vAST;
    E = eAST;
  }
  
  // @author        Andres
  // @descripcion   Eliminar visitors para Vname
  // @funcionalidad Visitors para Vname nuevo
  // @codigo        A.111
  public Object visit (Visitor v, Object o) {
    return null;
  }
  public Object visitXML(Visitor v, Object o) {
    return null;
  }
  /*
   public Object visit (Visitor v, Object o) {
    return v.visitSubscriptVname(this, o);
  }
  */
  // END Cambio Andres

  

  public Expression E;
  public Vname V;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
