/*
 * @(#)WhileCommand.java                        2.1 2003/10/07
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
// @description   Cambio de while como alternativa de single-command
// @funcionalidad Cambio en las alternativas de single-command
// @codigo        J.5
public class WhileLoopCommand extends Command {

  public WhileLoopCommand (Expression eAST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    E = eAST;
    C = cAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitWhileLoopCommand(this, o);
  }

  public Object visitXML(Visitor v, Object o) {
    return v.visitWhileLoopCommand(this, o);
  }
  
  /* J.5
  public class WhileCommand extends Command {

  public WhileCommand (Expression eAST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    E = eAST;
    C = cAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitWhileCommand(this, o);
  }
 */
  //END CAMBIO Joseph
  public Expression E;
  public Command C;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
