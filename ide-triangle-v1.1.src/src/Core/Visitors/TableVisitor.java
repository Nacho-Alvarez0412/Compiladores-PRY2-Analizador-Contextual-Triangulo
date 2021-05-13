/*
 * IDE-Triangle v1.0
 * TableDetails.java
 */
package Core.Visitors;

import Triangle.AbstractSyntaxTrees.AnyTypeDenoter;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.BinaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.BoolTypeDenoter;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.CharTypeDenoter;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyExpression;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.ErrorTypeDenoter;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
import Triangle.AbstractSyntaxTrees.IntTypeDenoter;
import Triangle.AbstractSyntaxTrees.IntegerExpression;
import Triangle.AbstractSyntaxTrees.IntegerLiteral;
import Triangle.AbstractSyntaxTrees.LetCommand;
import Triangle.AbstractSyntaxTrees.LetExpression;
import Triangle.AbstractSyntaxTrees.MultipleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleArrayAggregate;
import Triangle.AbstractSyntaxTrees.MultipleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.MultipleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleRecordAggregate;
import Triangle.AbstractSyntaxTrees.Operator;
import Triangle.AbstractSyntaxTrees.ProcActualParameter;
import Triangle.AbstractSyntaxTrees.ProcDeclaration;
import Triangle.AbstractSyntaxTrees.ProcFormalParameter;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.AbstractSyntaxTrees.RecordExpression;
import Triangle.AbstractSyntaxTrees.RecordTypeDenoter;
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleTypeDenoter;
import Triangle.AbstractSyntaxTrees.SimpleVname;
import Triangle.AbstractSyntaxTrees.SingleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleArrayAggregate;
import Triangle.AbstractSyntaxTrees.SingleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.SingleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleRecordAggregate;
import Triangle.AbstractSyntaxTrees.SubscriptVname;
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.UnaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.Visitor;
import Triangle.AbstractSyntaxTrees.VnameExpression;
// @author        Joseph
// @descripcion   Importacion de nuevos ASTs
// @funcionalidad Visitado de nuevos ASTs
// @codigo        J.8
import Triangle.AbstractSyntaxTrees.WhileLoopCommand;
import Triangle.AbstractSyntaxTrees.UntilLoopCommand;
import Triangle.AbstractSyntaxTrees.SingleElsifCommand;
import Triangle.AbstractSyntaxTrees.CompoundIfCommand;
import Triangle.AbstractSyntaxTrees.SequentialElsifCommand;
import Triangle.AbstractSyntaxTrees.DoLoopUntilCommand;
import Triangle.AbstractSyntaxTrees.DoLoopWhileCommand;
import Triangle.AbstractSyntaxTrees.ForLoopDoCommand;
import Triangle.AbstractSyntaxTrees.ForLoopWhileCommand;
import Triangle.AbstractSyntaxTrees.ForLoopUntilCommand;
import Triangle.AbstractSyntaxTrees.Procedure;
import Triangle.AbstractSyntaxTrees.Function;
import Triangle.AbstractSyntaxTrees.SequentialProcFuncs;
import Triangle.AbstractSyntaxTrees.VarTDDeclaration;
import Triangle.AbstractSyntaxTrees.VarExpDeclaration;
import Triangle.AbstractSyntaxTrees.PrivDeclaration;
import Triangle.AbstractSyntaxTrees.RecDeclaration;
import Triangle.AbstractSyntaxTrees.CaseLiteral;
import Triangle.AbstractSyntaxTrees.SimpleCaseRange;
import Triangle.AbstractSyntaxTrees.CompoundCaseRange;
import Triangle.AbstractSyntaxTrees.CaseLiterals;
import Triangle.AbstractSyntaxTrees.SequentialCaseRange;
import Triangle.AbstractSyntaxTrees.ElseCase;
import Triangle.AbstractSyntaxTrees.SingleCase;
import Triangle.AbstractSyntaxTrees.SequentialCase;
import Triangle.AbstractSyntaxTrees.SimpleCases;
import Triangle.AbstractSyntaxTrees.CompoundCases;
import Triangle.AbstractSyntaxTrees.ChooseCommand;
import Triangle.AbstractSyntaxTrees.ForFromCommand;
import Triangle.AbstractSyntaxTrees.SimpleVarName;
import Triangle.AbstractSyntaxTrees.DotVarName;
import Triangle.AbstractSyntaxTrees.SubscriptVarName;
import Triangle.AbstractSyntaxTrees.PackageIdentifier;
import Triangle.AbstractSyntaxTrees.PackageVname;
import Triangle.AbstractSyntaxTrees.SimpleLongIdentifier;
import Triangle.AbstractSyntaxTrees.PackageLongIdentifier;
import Triangle.AbstractSyntaxTrees.SinglePackageDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialPackageDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleProgram;
import Triangle.AbstractSyntaxTrees.CompoundProgram;
/* J.8
import Triangle.AbstractSyntaxTrees.WhileCommand;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
*/
// END CAMBIO Joseph
import Triangle.CodeGenerator.Field;
import Triangle.CodeGenerator.KnownAddress;
import Triangle.CodeGenerator.KnownRoutine;
import Triangle.CodeGenerator.KnownValue;
import Triangle.CodeGenerator.TypeRepresentation;
import Triangle.CodeGenerator.UnknownAddress;
import Triangle.CodeGenerator.UnknownRoutine;
import Triangle.CodeGenerator.UnknownValue;
import javax.swing.table.DefaultTableModel;

/**
 * Implements the Triangle Visitor interface, which is used to visit an entire
 * AST.
 *
 * Generates a DefaultTableModel, used to draw a Jable.
 *
 * @author Luis Leopoldo P�rez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class TableVisitor implements Visitor {

    /**
     * Creates a new instance of TableDetails
     */
    public TableVisitor() {
    }

  // <editor-fold defaultstate="collapsed" desc=" Commands ">
    // Commands
    public Object visitAssignCommand(AssignCommand ast, Object o) {
        ast.V.visit(this, null);
        ast.E.visit(this, null);

        return (null);
    }

    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de single-command
    // @codigo        A.12
    public Object visitSingleElsifCommand(SingleElsifCommand ast, Object o) {
        ast.E.visit(this, null);
        ast.C.visit(this, null);
        
        return (null);
    }
    // END cambio Andres

    // @author       Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de single-command
    // @codigo        A.13
    public Object visitSequentialElsifCommand(SequentialElsifCommand ast, Object o) {
        ast.SE1.visit(this, null);
        ast.SE2.visit(this, null);
        
        return (null);
    }
    // END cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de single-command
    // @codigo        A.20
    public Object visitCompoundIfCommand(CompoundIfCommand ast, Object o) {
        ast.E.visit(this, null);
        ast.C1.visit(this, null);
        ast.EIC.visit(this, null);
        ast.C2.visit(this, null);
        
        return (null);
    }

    // @author        Joseph
    // @descripcion   Cambio en metodo visitante de call command
    // @funcionalidad Cambio en las alternativas de single-command
    // @codigo        J.75
    public Object visitCallCommand(CallCommand ast, Object o) {
        ast.LI.visit(this, null);
        ast.APS.visit(this, null);

        return (null);
    }
    /* J.75
    public Object visitCallCommand(CallCommand ast, Object o) {
        ast.I.visit(this, null);
        ast.APS.visit(this, null);

        return (null);
    }  
    */
    //END CAMBIO Joseph

    public Object visitEmptyCommand(EmptyCommand ast, Object o) {
        return (null);
    }

    public Object visitIfCommand(IfCommand ast, Object o) {
        ast.E.visit(this, null);
        ast.C1.visit(this, null);
        ast.C2.visit(this, null);

        return (null);
    }

    public Object visitLetCommand(LetCommand ast, Object o) {
        ast.D.visit(this, null);
        ast.C.visit(this, null);

        return (null);
    }

    public Object visitSequentialCommand(SequentialCommand ast, Object o) {
        ast.C1.visit(this, null);
        ast.C2.visit(this, null);

        return (null);
    }
    
    // @author        Joseph
    // @descripcion   Metodos dibujantes para visitar ASTS modificados
    // @funcionalidad Cambio en las alternativas de single-command
    // @codigo        J.7
    public Object visitWhileLoopCommand(WhileLoopCommand ast, Object o) {
        ast.E.visit(this, null);
        ast.C.visit(this, null);

        return (null);
    }
    /* J.7
    public Object visitWhileLoopCommand(WhileCommand ast, Object o) {
        ast.E.visit(this, null);
        ast.C.visit(this, null);

        return (null);
    }
    */
    // END CAMBIO Joseph
    
    // @author        Joseph
    // @descripcion   Metodos para visitar nuevos ASTS
    // @funcionalidad Creacion de nuevas alternativas de no-terminales
    // @codigo        J.20
  public Object visitUntilLoopCommand(UntilLoopCommand ast, Object o) {
        ast.E.visit(this, null);
        ast.C.visit(this, null);

        return (null);
  }
  
   public Object visitDoLoopUntilCommand(DoLoopUntilCommand ast, Object o) {
        ast.C.visit(this, null);
        ast.E.visit(this, null);

        return (null);
  }
   
   public Object visitDoLoopWhileCommand(DoLoopWhileCommand ast, Object o) {
        ast.C.visit(this, null);
        ast.E.visit(this, null);
        return (null);
  }
   
   public Object visitForLoopDoCommand (ForLoopDoCommand ast, Object o) {
        ast.FFC.visit(this, null);
        ast.E.visit(this, null);
        ast.C.visit(this, null);
        return (null);
  }
  
   public Object visitForLoopWhileCommand (ForLoopWhileCommand ast, Object o) {
        ast.FFC.visit(this, null);
        ast.E1.visit(this, null);
        ast.E2.visit(this, null);
        ast.C.visit(this, null);
        return (null);
  }
 
   public Object visitForLoopUntilCommand (ForLoopUntilCommand ast, Object o) {
        ast.FFC.visit(this, null);
        ast.E1.visit(this, null);
        ast.E2.visit(this, null);
        ast.C.visit(this, null);
        return (null);
   }
   
    public Object visitForFromCommand(ForFromCommand ast, Object o) {
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        return (null);
   }
  // END CAMBIO Joseph
  
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Cambio en las alternativas de single-command
    // @codigo        A.97
    public Object visitChooseCommand(ChooseCommand ast, Object o) {
        ast.E.visit(this, null);
        ast.CS.visit(this, null);
        
        return (null);
    }
   
  // </editor-fold>
   
   // @author        Joseph
   // @descripcion   Metodos para visitar nuevos ASTs de ProcFunc
   // @funcionalidad Creacion de nuevas alternativas de no-terminales
   // @codigo        J.35
   
  // <editor-fold defaultstate="collapsed" desc=" ProcFuncs ">
   
   public Object visitProcedure (Procedure ast, Object o) {
        ast.I.visit(this, null);
        ast.FPS.visit(this, null);
        ast.C.visit(this, null);
        return (null);
  }
   
   public Object visitFunction (Function ast, Object o) {
        ast.I.visit(this, null);
        ast.FPS.visit(this, null);
        ast.TD.visit(this, null);
        ast.E.visit(this, null);
        return (null);
  }
   
   public Object visitSequentialProcFuncs (SequentialProcFuncs ast, Object o) {
        ast.PF1.visit(this, null);
        ast.PF2.visit(this, null);
        return (null);
  }
   
   //END CAMBIO Joseph
   
   
   // </editor-fold>
    // Cases
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case-literal
    // @codigo        A.28
    public Object visitCaseLiteral(CaseLiteral ast, Object o) {
        ast.T.visit(this, null);
        
        return (null);
    }
    // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case range
    // @codigo        A.37
    public Object visitSimpleCaseRange(SimpleCaseRange ast, Object o) {
        ast.CL.visit(this, null);
        
        return (null);
    }
    // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case range
    // @codigo        A.38
    public Object visitCompoundCaseRange(CompoundCaseRange ast, Object o) {
        ast.CL1.visit(this, null);
        ast.CL2.visit(this, null);
        
        return (null);
    }
    // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case literals
    // @codigo        A.50
    public Object visitCaseLiterals(CaseLiterals ast, Object o) {
        ast.CR.visit(this, null);
        
        return (null);
    }
     // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case literals
    // @codigo        A.51
    public Object visitSequentialCaseRange(SequentialCaseRange ast, Object o) {
        ast.CR1.visit(this, null);
        ast.CR2.visit(this, null);
        
        return (null);
    }
    // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de else case
    // @codigo        A.68
    public Object visitElseCase(ElseCase ast, Object o) {
        ast.C.visit(this, null);
        
        return (null);
    }
     // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de cases
    // @codigo        A.69
    public Object visitCompoundCases(CompoundCases ast, Object o) {
        ast.C.visit(this, null);
        ast.EC.visit(this, null);
        
        return (null);
    }
     // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de cases
    // @codigo        A.70
    public Object visitSequentialCase(SequentialCase ast, Object o) {
        ast.C1.visit(this, null);
        ast.C2.visit(this, null);
        
        return (null);
    }
     // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de cases
    // @codigo        A.71
    public Object visitSimpleCases(SimpleCases ast, Object o) {
        ast.C.visit(this, null);
        
        return (null);
    }
     // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case
    // @codigo        A.72
    public Object visitSingleCase(SingleCase ast, Object o) {
        ast.CL.visit(this, null);
        ast.C.visit(this, null);
        
        return (null);
    }
     // END Cambio Andres
    
  // <editor-fold defaultstate="collapsed" desc=" Expressions ">
    // Expressions
    public Object visitArrayExpression(ArrayExpression ast, Object o) {
        ast.AA.visit(this, null);

        return (null);
    }

    public Object visitBinaryExpression(BinaryExpression ast, Object o) {
        ast.E1.visit(this, null);
        ast.E2.visit(this, null);
        ast.O.visit(this, null);

        return (null);
    }

    
    // @author        Joseph
    // @descripcion   Cambio en metodo visitante de call expression
    // @funcionalidad Cambio en las alternativas de simple expression
    // @codigo        J.76
    public Object visitCallExpression(CallExpression ast, Object o) {
        ast.LI.visit(this, null);
        ast.APS.visit(this, null);
        return (null);
    }
    /* J.76
    public Object visitCallExpression(CallExpression ast, Object o) {
        ast.I.visit(this, null);
        ast.APS.visit(this, null);
        return (null);
    }   
    */
    //END CAMBIO Joseph

    public Object visitCharacterExpression(CharacterExpression ast, Object o) {
        ast.CL.visit(this, null);

        return (null);
    }

    public Object visitEmptyExpression(EmptyExpression ast, Object o) {
        return (null);
    }

    public Object visitIfExpression(IfExpression ast, Object o) {
        ast.E1.visit(this, null);
        ast.E2.visit(this, null);
        ast.E3.visit(this, null);

        return (null);
    }

    public Object visitIntegerExpression(IntegerExpression ast, Object o) {
        return (null);
    }

    public Object visitLetExpression(LetExpression ast, Object o) {
        ast.D.visit(this, null);
        ast.E.visit(this, null);

        return (null);
    }

    public Object visitRecordExpression(RecordExpression ast, Object o) {
        ast.RA.visit(this, null);

        return (null);
    }

    public Object visitUnaryExpression(UnaryExpression ast, Object o) {
        ast.E.visit(this, null);
        ast.O.visit(this, null);

        return (null);
    }

    public Object visitVnameExpression(VnameExpression ast, Object o) {
        ast.V.visit(this, null);

        return (null);
    }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Declarations ">
    // Declarations
    public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o) {
        return (null);
    }
    
    //Single Declarations
    public Object visitConstDeclaration(ConstDeclaration ast, Object o) {
        String name = ast.I.spelling;
        String type = "N/A";
        try {
            int size = (ast.entity != null ? ast.entity.size : 0);
            int level = -1;
            int displacement = -1;
            int value = -1;

            if (ast.entity instanceof KnownValue) {
                type = "KnownValue";
                value = ((KnownValue) ast.entity).value;
            } else if (ast.entity instanceof UnknownValue) {
                type = "UnknownValue";
                level = ((UnknownValue) ast.entity).address.level;
                displacement = ((UnknownValue) ast.entity).address.displacement;
            }
            addIdentifier(name, type, size, level, displacement, value);
        } catch (NullPointerException e) {
        }

        ast.E.visit(this, null);
        ast.I.visit(this, null);

        return (null);
    }

    public Object visitFuncDeclaration(FuncDeclaration ast, Object o) {
        try {
            addIdentifier(ast.I.spelling,
                    "KnownRoutine",
                    (ast.entity != null ? ast.entity.size : 0),
                    ((KnownRoutine) ast.entity).address.level,
                    ((KnownRoutine) ast.entity).address.displacement,
                    -1);
        } catch (NullPointerException e) {
        }
        ast.T.visit(this, null);
        ast.FPS.visit(this, null);
        ast.E.visit(this, null);

        return (null);
    }

    public Object visitProcDeclaration(ProcDeclaration ast, Object o) {
        try {
            addIdentifier(ast.I.spelling, "KnownRoutine",
                    (ast.entity != null ? ast.entity.size : 0),
                    ((KnownRoutine) ast.entity).address.level,
                    ((KnownRoutine) ast.entity).address.displacement,
                    -1);
        } catch (NullPointerException e) {
        }

        ast.FPS.visit(this, null);
        ast.C.visit(this, null);

        return (null);
    }

    public Object visitSequentialDeclaration(SequentialDeclaration ast, Object o) {
        ast.D1.visit(this, null);
        ast.D2.visit(this, null);

        return (null);
    }

    public Object visitTypeDeclaration(TypeDeclaration ast, Object o) {
        ast.T.visit(this, null);

        return (null);
    }

    public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o) {
        return (null);
    }

    // @author        Joseph
    // @description   Cambio en el metodo de visitado de tabla de alternativas de Declaration
    // @funcionalidad Cambio en las alternativas de declaration
    // @codigo        J.49
    public Object visitVarTDDeclaration(VarTDDeclaration ast, Object o) {
        try {
        addIdentifier(ast.I.spelling,
                "KnownAddress",
                (ast.entity != null ? ast.entity.size : 0),
                ((KnownAddress) ast.entity).address.level,
                ((KnownAddress) ast.entity).address.displacement,
                -1);
        } catch (NullPointerException e) {
        }
        ast.T.visit(this, null);
        return (null);
    }
        
    public Object visitVarExpDeclaration(VarExpDeclaration ast, Object o) {
        try {
        addIdentifier(ast.I.spelling,
                "KnownAddress",
                (ast.entity != null ? ast.entity.size : 0),
                ((KnownAddress) ast.entity).address.level,
                ((KnownAddress) ast.entity).address.displacement,
                -1);
        } catch (NullPointerException e) {
        }
        ast.E.visit(this, null);
        return (null);
    }

    // Non-single declarations
    
    public Object visitRecDeclaration(RecDeclaration ast, Object o) {
        ast.PFs.visit(this, null);
        return (null);
    }
    
    public Object visitPrivDeclaration(PrivDeclaration ast, Object o) {
        ast.D1.visit(this, null);
        ast.D2.visit(this, null);
        return (null);
    }
    
    
    /* J.49
        public Object visitVarDeclaration(VarDeclaration ast, Object o) {
            try {
            addIdentifier(ast.I.spelling,
                    "KnownAddress",
                    (ast.entity != null ? ast.entity.size : 0),
                    ((KnownAddress) ast.entity).address.level,
                    ((KnownAddress) ast.entity).address.displacement,
                    -1);
            } catch (NullPointerException e) {
            }
            ast.T.visit(this, null);
            return (null);
        }
    */
    // END CAMBIO Joseph

  // </editor-fold>
    
  // <editor-fold defaultstate="collapsed" desc=" Aggregates ">
    // Array Aggregates
    public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o) {
        ast.AA.visit(this, null);
        ast.E.visit(this, null);

        return (null);
    }

    public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o) {
        ast.E.visit(this, null);

        return (null);
    }

    // Record Aggregates
    public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o) {
        ast.E.visit(this, null);
        ast.I.visit(this, null);
        ast.RA.visit(this, null);

        return (null);
    }

    public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o) {
        ast.E.visit(this, null);
        ast.I.visit(this, null);

        return (null);
    }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Parameters ">
    // Formal Parameters
    public Object visitConstFormalParameter(ConstFormalParameter ast, Object o) {
        try {
            addIdentifier(ast.I.spelling,
                    "UnknownValue",
                    (ast.entity != null ? ast.entity.size : 0),
                    ((UnknownValue) ast.entity).address.level,
                    ((UnknownValue) ast.entity).address.displacement,
                    -1);
        } catch (NullPointerException e) {
        }

        ast.I.visit(this, null);
        ast.T.visit(this, null);

        return (null);
    }

    public Object visitFuncFormalParameter(FuncFormalParameter ast, Object o) {
        try {
            addIdentifier(ast.I.spelling,
                    "UnknownRoutine",
                    (ast.entity != null ? ast.entity.size : 0),
                    ((UnknownRoutine) ast.entity).address.level,
                    ((UnknownRoutine) ast.entity).address.displacement,
                    -1);
        } catch (NullPointerException e) {
        }
        ast.FPS.visit(this, null);
        ast.T.visit(this, null);

        return (null);
    }

    public Object visitProcFormalParameter(ProcFormalParameter ast, Object o) {
        try {
            addIdentifier(ast.I.spelling,
                    "UnknownRoutine",
                    (ast.entity != null ? ast.entity.size : 0),
                    ((UnknownRoutine) ast.entity).address.level,
                    ((UnknownRoutine) ast.entity).address.displacement,
                    -1);
        } catch (NullPointerException e) {
        }
        ast.FPS.visit(this, null);

        return (null);
    }

    public Object visitVarFormalParameter(VarFormalParameter ast, Object o) {
        try {
            addIdentifier(ast.I.spelling,
                    "UnknownAddress",
                    ast.T.entity.size,
                    ((UnknownAddress) ast.entity).address.level,
                    ((UnknownAddress) ast.entity).address.displacement,
                    -1);
        } catch (NullPointerException e) {
        }
        ast.T.visit(this, null);

        return (null);
    }

    public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o) {
        return (null);
    }

    public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o) {
        ast.FP.visit(this, null);
        ast.FPS.visit(this, null);

        return (null);
    }

    public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o) {
        ast.FP.visit(this, null);

        return (null);
    }

    // Actual Parameters
    public Object visitConstActualParameter(ConstActualParameter ast, Object o) {
        ast.E.visit(this, null);

        return (null);
    }

    public Object visitFuncActualParameter(FuncActualParameter ast, Object o) {
        ast.I.visit(this, null);

        return (null);
    }

    public Object visitProcActualParameter(ProcActualParameter ast, Object o) {
        ast.I.visit(this, null);

        return (null);
    }

    public Object visitVarActualParameter(VarActualParameter ast, Object o) {
        ast.V.visit(this, null);

        return (null);
    }

    public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o) {
        return (null);
    }

    public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o) {
        ast.AP.visit(this, null);
        ast.APS.visit(this, null);

        return (null);
    }

    public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o) {
        ast.AP.visit(this, null);

        return (null);
    }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Type Denoters ">
    // Type Denoters
    public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o) {
        return (null);
    }

    public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o) {
        ast.IL.visit(this, null);
        ast.T.visit(this, null);

        return (null);
    }

    public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o) {
        return (null);
    }

    public Object visitCharTypeDenoter(CharTypeDenoter ast, Object o) {
        return (null);
    }

    public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o) {
        return (null);
    }

    // @author        Joseph
    // @descripcion   Cambio en metodo visitante de simple type-denoter
    // @funcionalidad Cambio en las alternativas de type-denoter
    // @codigo        J.77
    public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o) {
        ast.LI.visit(this, null);
        return (null);
    }
    /* J.77
    public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o) {
        ast.I.visit(this, null);

        return (null);
    }
    */
    // END CAMBIO Joseph

    public Object visitIntTypeDenoter(IntTypeDenoter ast, Object o) {
        return (null);
    }

    public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o) {
        ast.FT.visit(this, null);
        return (null);
    }

    public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o) {
        try {
            addIdentifier(ast.I.spelling,
                    "Field",
                    (ast.entity != null ? ast.entity.size : 0),
                    -1, ((Field) ast.entity).fieldOffset, -1);
        } catch (NullPointerException e) {
        }
        ast.FT.visit(this, null);
        ast.I.visit(this, null);
        ast.T.visit(this, null);

        return (null);
    }

    public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o) {
        try {
            addIdentifier(ast.I.spelling,
                    "Field",
                    (ast.entity != null ? ast.entity.size : 0),
                    -1, ((Field) ast.entity).fieldOffset, -1);
        } catch (NullPointerException e) {
        }
        ast.I.visit(this, null);
        ast.T.visit(this, null);

        return (null);
    }

  // </editor-fold>
    
  // <editor-fold defaultstate="collapsed" desc=" Literals, Identifiers and Operators ">
    // Literals, Identifiers and Operators
    public Object visitCharacterLiteral(CharacterLiteral ast, Object o) {
        return (null);
    }

    public Object visitIdentifier(Identifier ast, Object o) {
        return (null);
    }

    public Object visitIntegerLiteral(IntegerLiteral ast, Object o) {
        return (null);
    }

    public Object visitOperator(Operator ast, Object o) {
        ast.decl.visit(this, null);

        return (null);
    }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Values or Variable Names ">
    // Value-or-variable names
    
    // @author        Andres
    // @descripcion   Agregar metodos de visita de nuevos ASTs VarName, Vname y Package
    // @funcionalidad metodos de visita para AST de Varname, Vname y Package
    // @codigo        A.112
    public Object visitDotVarName(DotVarName ast, Object o) {
        ast.I.visit(this, null);
        ast.V.visit(this, null);

        return (null);
    }

    public Object visitSimpleVarName(SimpleVarName ast, Object o) {
        ast.I.visit(this, null);

        return (null);
    }

    public Object visitSubscriptVarName(SubscriptVarName ast, Object o) {
        ast.E.visit(this, null);
        ast.V.visit(this, null);

        return (null);
    }
    
    public Object visitSimpleVname(SimpleVname ast, Object o) {
        ast.VN.visit(this, null);
        
        return (null);
    }
    
    public Object visitPackageIdentifier(PackageIdentifier ast, Object o) {
        ast.I.visit(this, null);
        
        return (null);
    }
    
    public Object visitPackageVname(PackageVname ast, Object o) {
        ast.PI.visit(this, null);
        ast.VN.visit(this, null);
        
        return (null);
    }
    
    public Object visitSimpleLongIdentifier(SimpleLongIdentifier ast, Object o) {
        ast.I.visit(this, null);
        
        return (null);
    }
    
    public Object visitPackageLongIdentifier(PackageLongIdentifier ast, Object o) {
        ast.PI.visit(this, null);
        ast.I.visit(this, null);
        
        return (null);
    }
    
    public Object visitSinglePackageDeclaration(SinglePackageDeclaration ast, Object o) {
        ast.PI.visit(this, null);
        ast.D.visit(this, null);
        
        return (null);
    }
    
    public Object visitSequentialPackageDeclaration(SequentialPackageDeclaration ast, Object o) {
        ast.PD1.visit(this, null);
        ast.PD2.visit(this, null);
        
        return (null);
    }
    /*
        public Object visitDotVname(DotVname ast, Object o) {
           ast.I.visit(this, null);
           ast.V.visit(this, null);

           return (null);
        }
        public Object visitSimpleVname(SimpleVname ast, Object o) {
          ast.I.visit(this, null);

          return (null);
         }

        public Object visitSubscriptVname(SubscriptVname ast, Object o) {
            ast.E.visit(this, null);
            ast.V.visit(this, null);

            return (null);
        }
    */
    // END Cambio Andres
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Table Creation Methods ">
    // Programs
    
    // @author        Andres
    // @descripcion   Agregar metodos de visita de nuevos ASTs program
    // @funcionalidad metodos de visita para AST de program
    // @codigo        A.134
    public Object visitSimpleProgram(SimpleProgram ast, Object o) {
        ast.C.visit(this, null);

        return (null);
    }
    
     public Object visitCompoundProgram(CompoundProgram ast, Object o) {
        ast.PD.visit(this, null);
        ast.C.visit(this, null);

        return (null);
    }
    /*
        public Object visitProgram(Program ast, Object o) {
        ast.C.visit(this, null);

        return (null);
    }
    */
    // END Cambio Andres

    /**
     * Adds an identifier to the table.
     */
    private void addIdentifier(String name, String type, int size, int level, int displacement, int value) {
        boolean exists = false;

        for (int i = 0; (i < model.getRowCount() && !exists); i++) {
            if (((String) model.getValueAt(i, 0)).compareTo(name) == 0) {
                exists = true;
            }
        }

        if (!exists) {
            model.addRow(new String[]{name,
                type,
                String.valueOf(size),
                (level < 0 ? " " : String.valueOf(level)),
                (displacement < 0 ? " " : String.valueOf(displacement)),
                (value < 0 ? " " : String.valueOf(value))});
        }
    }

    /**
     * Returns the filled table model.
     */
    // @author        Andres
    // @descripcion   Agregar metodos de visita de nuevos ASTs program
    // @funcionalidad metodos de visita para AST de program
    // @codigo        A.135
    public DefaultTableModel getTable(SimpleProgram ast) {
        model = new DefaultTableModel((new String[]{"Name", "Type", "Size", "Level", "Displacement", "Value"}), 0);
        visitSimpleProgram(ast, null);

        return (model);
    }
    
    public DefaultTableModel getTable(CompoundProgram ast) {
        model = new DefaultTableModel((new String[]{"Name", "Type", "Size", "Level", "Displacement", "Value"}), 0);
        visitCompoundProgram(ast, null);

        return (model);
    }
    /*
     public DefaultTableModel getTable(Program ast) {
        model = new DefaultTableModel((new String[]{"Name", "Type", "Size", "Level", "Displacement", "Value"}), 0);
        visitProgram(ast, null);

        return (model);
    }
    */
    // END CAMBIO Andres

    // </editor-fold>
    
  // <editor-fold defaultstate="collapsed" desc=" Attributes ">
    private DefaultTableModel model;
    // </editor-fold>
}
