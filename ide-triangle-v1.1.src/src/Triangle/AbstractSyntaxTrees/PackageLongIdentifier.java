/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Represents a PackageLongIdentifierAST
 * @author Andres
 * A.124
 */
public class PackageLongIdentifier extends LongIdentifier {
    
    public PackageLongIdentifier(PackageIdentifier piAST, Identifier iAST, SourcePosition thePosition) {
        super(thePosition);
        
        PI = piAST;
        I = iAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitPackageLongIdentifier(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitPackageLongIdentifier(this, o);
    }
    
    public PackageIdentifier PI;

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
