/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Represents a SequentialPackageDeclaration AST
 * @author Andres
 * A.129
 */
public class SequentialPackageDeclaration extends PackageDeclaration {
    
    public SequentialPackageDeclaration(PackageDeclaration pd1AST, PackageDeclaration pd2AST, 
            SourcePosition thePosition) {
        super(thePosition);
        
        PD1 = pd1AST;
        PD2 = pd2AST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitSequentialPackageDeclaration(this, o);
    }
    

    public Object visitXML(Visitor v, Object o) {
        return v.visitSequentialPackageDeclaration(this, o);
    }
    
    public PackageDeclaration PD1;
    public PackageDeclaration PD2;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
