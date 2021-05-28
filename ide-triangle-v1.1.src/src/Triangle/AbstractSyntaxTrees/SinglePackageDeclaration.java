/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Represents a Single PackageDeclaration AST
 * @author Andres
 * A.125
 */
public class SinglePackageDeclaration extends PackageDeclaration {
    
    public SinglePackageDeclaration(PackageIdentifier piAST, Declaration dAST, 
            SourcePosition thePosition) {
        super(thePosition);
        
        PI = piAST;
        D = dAST;
    }
    
     public Object visit(Visitor v, Object o) {
        return v.visitSinglePackageDeclaration(this, o);
    }
    public Object visitXML(Visitor v, Object o) {
        return v.visitSinglePackageDeclaration(this, o);
    }
    
    public PackageIdentifier PI;
    public Declaration D;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
