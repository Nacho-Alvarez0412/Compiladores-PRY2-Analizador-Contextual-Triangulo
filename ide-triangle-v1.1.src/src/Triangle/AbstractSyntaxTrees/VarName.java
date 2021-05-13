/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Represents a VarName AST
 * @author Andres
 * A.104
 */
public abstract class VarName extends AST {
    
    public VarName(SourcePosition thePosition) {
        super(thePosition);
        variable = false;
        type = null;
    }
    
  public boolean variable, indexed;
  public int offset;
  public TypeDenoter type;
    
}
