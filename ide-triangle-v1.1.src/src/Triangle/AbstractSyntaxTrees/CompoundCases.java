/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Representa un CompoundCases AST
 * @author Andres
 */
public class CompoundCases extends Cases {
    
    public CompoundCases(Case cAST, ElseCase ecAST, SourcePosition thePosition) {
        super(thePosition);
        
        C = cAST;
        EC = ecAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitCompoundCases(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitCompoundCases(this, o);
    }
    
    public Case C;
    public ElseCase EC;
}
