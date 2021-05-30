/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Representa un SimpleCases AST
 * @author Andres
 * A.66
 */
public class SimpleCases extends Cases {
    
     public SimpleCases(Case cAST, SourcePosition thePosition) {
        super(thePosition);
        
        C = cAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitSimpleCases(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitSimpleCases(this, o);
    }
    
    public Case C;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
