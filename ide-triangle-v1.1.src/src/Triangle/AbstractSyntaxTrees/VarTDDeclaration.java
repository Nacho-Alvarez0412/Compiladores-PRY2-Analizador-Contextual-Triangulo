/*
 * @(#)VarDeclaration.java                        2.1 2003/10/07
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
// @description   Cambio de var como alternativa de single-declaration
// @funcionalidad Cambio en las alternativas de single declaration
// @codigo        J.42
public class VarTDDeclaration extends SingleDeclaration {

  public VarTDDeclaration (Identifier iAST, TypeDenoter tAST,
                         SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    T = tAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitVarTDDeclaration(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitVarTDDeclaration(this, o);
  }


/* J.42
public class VarDeclaration extends Declaration {

  public VarDeclaration (Identifier iAST, TypeDenoter tAST,
                         SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    T = tAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitVarDeclaration(this, o);
  }
*/
  // END CAMBIO Joseph

  public Identifier I;
  public TypeDenoter T;
}
