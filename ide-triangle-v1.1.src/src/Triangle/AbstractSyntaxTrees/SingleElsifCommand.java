/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * AST para SingleElsifCommand
 * @author Andres
 */
public class SingleElsifCommand extends ElsifCommand {
    
    public SingleElsifCommand(Expression eAST, Command cAST, 
            SourcePosition thePosition) {
        super(thePosition);
        E = eAST;
        C = cAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitSingleElsifCommand(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitSingleElsifCommand(this, o);
    }
    
    public Expression E;
    public Command C;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
