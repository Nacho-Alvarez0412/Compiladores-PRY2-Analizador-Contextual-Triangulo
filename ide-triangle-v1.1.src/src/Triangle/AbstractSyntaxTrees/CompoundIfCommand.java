/*
 * @(#)IfCommand.java                        2.1 2003/10/07
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

/**
 * @newclass 
 * Clase para representar un AST de un CompundIfCommand
 * A.18
 * @author Andres
 */
public class CompoundIfCommand extends Command {
  public CompoundIfCommand (Expression eAST, Command c1AST, ElsifCommand eiAST, Command c2AST,
                    SourcePosition thePosition) {
    super (thePosition);
    E = eAST;
    C1 = c1AST;
    EIC = eiAST;
    C2 = c2AST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitCompoundIfCommand(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitCompoundIfCommand(this, o);
  }

  public Expression E;
  public Command C1, C2;
  public ElsifCommand EIC;
}
