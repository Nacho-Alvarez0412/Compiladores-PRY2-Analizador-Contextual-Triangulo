package Triangle.AbstractSyntaxTrees;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Andres
 */
public class SingleCase extends Case {
    
    public SingleCase(CaseLiterals clAST, Command cAST, SourcePosition thePosition) {
        super(thePosition);
        
        CL = clAST;
        C = cAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitSingleCase(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitSingleCase(this, o);
    }
    
    public CaseLiterals CL;
    public Command C;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
