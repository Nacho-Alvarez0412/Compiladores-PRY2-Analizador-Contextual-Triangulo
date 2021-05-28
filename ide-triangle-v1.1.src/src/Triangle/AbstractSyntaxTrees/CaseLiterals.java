/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Representa el AST de CaseLiterals
 * @author Andres
 * A.48
 */
public class CaseLiterals extends AST {
    
    public CaseLiterals(CaseRange crAST, SourcePosition thePosition) {
        super(thePosition);
        CR = crAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitCaseLiterals(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitCaseLiterals(this, o);
    }
    
    public CaseRange CR;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
