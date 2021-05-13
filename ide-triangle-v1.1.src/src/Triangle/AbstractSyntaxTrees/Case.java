/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Representa un Case AST
 * @author Andres
 * A.62
 */
public abstract class Case extends AST {
    
    public Case(SourcePosition thePosition) {
        super(thePosition);
    }
    
}
