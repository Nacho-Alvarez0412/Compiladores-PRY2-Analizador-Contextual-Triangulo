/*
 * @(#)ConstDeclaration.java                        2.1 2003/10/07
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


// @author        Joseph
// @description   Agrupacion de alternativas de single-command bajo nueva clase abstracta
// @funcionalidad Cambio en las alternativas de single declaration
// @codigo        J.52
public class ConstDeclaration extends SingleDeclaration {

  public ConstDeclaration (Identifier iAST, Expression eAST,
                    SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    E = eAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitConstDeclaration(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitConstDeclaration(this, o);
  }

  public Identifier I;
  public Expression E;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
/*
public class ConstDeclaration extends Declaration {

  public ConstDeclaration (Identifier iAST, Expression eAST,
                    SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    E = eAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitConstDeclaration(this, o);
  }

  public Identifier I;
  public Expression E;
}
*/
// END CAMBIO Joseph
