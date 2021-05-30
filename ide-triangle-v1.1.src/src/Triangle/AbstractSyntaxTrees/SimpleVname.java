/*
 * @(#)SimpleVname.java                        2.1 2003/10/07
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

public class SimpleVname extends Vname {
   
  // @author Andres
  // @descripcion Cambio de constructor en Vname
  // @funcionalidad modificacion de Vname
  // @codigo A.110
  public SimpleVname (VarName vnAST, SourcePosition thePosition) {
    super (thePosition);
    VN = vnAST;
  }
  
  
  public Object visit (Visitor v, Object o) {
    return v.visitSimpleVname(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitSimpleVname(this, o);
  }

  public VarName VN;
  /*
    public SimpleVname (Identifier iAST, SourcePosition thePosition) {
        super (thePosition);
         I = iAST;
    }
  
    public Object visit (Visitor v, Object o) {
      return v.visitSimpleVname(this, o);
    }
  
    public Identifier iAST;
  */
  // END Cambio Andres

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
