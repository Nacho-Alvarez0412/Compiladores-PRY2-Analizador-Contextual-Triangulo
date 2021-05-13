/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Represents a PackageIdentifier AST
 * @author Andres
 * A.119
 */
public class PackageIdentifier extends AST {
    
    public PackageIdentifier(Identifier iAST, SourcePosition thePosition) {
        super(thePosition);
        
        I = iAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitPackageIdentifier(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitPackageIdentifier(this, o);
    }
    
    public Identifier I;
    
}
