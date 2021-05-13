/*
 * IDE-Triangle v1.0
 * TreeVisitor.java
 */

package Core.Visitors;
import Triangle.AbstractSyntaxTrees.AST;
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
// @funcionalidad Parseo de nuevos ASTs
// @codigo        J.9
import Triangle.AbstractSyntaxTrees.VarTDDeclaration;
import Triangle.AbstractSyntaxTrees.VarExpDeclaration;
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
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Implements the Triangle Visitor interface, which is used to
 * visit an entire AST. 
 *
 * Generates DefaultMutableTreeNodes, used to draw a JTree.
 *
 * @author Luis Leopoldo P�rez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class TreeVisitor implements Visitor {
      
    /**
     * Creates a new instance of TreeVisitor.
     */
    public TreeVisitor() {
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Commands ">    
    // Commands  
    public Object visitAssignCommand(AssignCommand ast, Object o) {
        return(createBinary("Assign Command", ast.V, ast.E));
    }
    
    // @author        Andres
    // @descripcion   Crea un arbol binario AST para el comando "elsif"
    // @funcionalidad Crear arbol AST para el "elsif"
    // @codigo        A.14
    public Object visitSingleElsifCommand(SingleElsifCommand ast, Object o) {
        return(createBinary("Elsif Command", ast.E, ast.C));
    }
    // END cambio Andres

    // @author        Andres
    // @descripcion   Crea un arbol binario AST para el comando secuencial "elsif"
    // @funcionalidad Crear arbol AST para el "elsif" secuencial
    // @codigo        A.15
    public Object visitSequentialElsifCommand(SequentialElsifCommand ast, Object o) {
        return(createBinary("Sequential Elsif Command", ast.SE1, ast.SE2));
    }
    // END cambio Andres
    
    // @author        Andres
    // @descripcion   Crea un arbol binario AST para el comando nuevo CompoundIf
    // @funcionalidad Crear arbol AST para el comando nuevo CompoundIf
    // @codigo        A.19
    public Object visitCompoundIfCommand(CompoundIfCommand ast, Object o) {
        return(createQuaternary("Compund If Command", ast.E, ast.C1, ast.EIC, ast.C2));
    }
    // END Cambio Andres
    
    // @author        Joseph
    // @descripcion   Cambio en metodo de creacion de arbol binario de call command
    // @funcionalidad Cambio en las alternativas de call command
    // @codigo        J.78
    public Object visitCallCommand(CallCommand ast, Object o) {
        return(createBinary("Call Command", ast.LI, ast.APS));
    }
    /* J.78
    public Object visitCallCommand(CallCommand ast, Object o) {
        return(createBinary("Call Command", ast.I, ast.APS));
    }
    */
    // END CAMBIO Joseph
    
    
    public Object visitEmptyCommand(EmptyCommand ast, Object o) {
        return(createNullary("Empty Command"));
    }
    
    public Object visitIfCommand(IfCommand ast, Object obj) {
        return(createTernary("If Command", ast.E, ast.C1, ast.C2));
    }
    
    public Object visitLetCommand(LetCommand ast, Object obj) {
        return(createBinary("Let Command", ast.D, ast.C));
    }
    
    public Object visitSequentialCommand(SequentialCommand ast, Object obj) {
        return(createBinary("Sequential Command", ast.C1, ast.C2));
    }
    
    // @author        Joseph
    // @descripcion   Metodos dibujantes para visitar ASTS modificados
    // @funcionalidad Cambio en las alternativas de single-command
    // @codigo        J.10
    public Object visitWhileLoopCommand(WhileLoopCommand ast, Object obj) {
        return(createBinary("While Loop Command", ast.E, ast.C));
    }
    /* J.10
    public Object visitWhileCommand(WhileCommand ast, Object obj) {
        return(createBinary("While Command", ast.E, ast.C));
    }
     */
    // END CAMBIO Joseph
    
    // @author        Joseph
    // @descripcion   Metodos dibujantes para visitar nuevos ASTS
    // @funcionalidad Creacion de nuevas alternativas de no-terminales
    // @codigo        J.21
    public Object visitUntilLoopCommand(UntilLoopCommand ast, Object obj) {
        return(createBinary("Until Loop Command", ast.E, ast.C));
    }
    
    public Object visitDoLoopUntilCommand(DoLoopUntilCommand ast, Object obj) {
        return(createBinary("Do Loop Until Command", ast.C, ast.E));
    }
    
    public Object visitDoLoopWhileCommand(DoLoopWhileCommand ast, Object obj) {
        return(createBinary("Do Loop While Command", ast.C, ast.E));
    }

    public Object visitForLoopDoCommand(ForLoopDoCommand ast, Object obj) {
        return(createTernary("For Loop Do Command", ast.FFC, ast.E, ast.C));
    }
    
    public Object visitForLoopWhileCommand(ForLoopWhileCommand ast, Object obj) {
        return(createQuaternary("For Loop While Command", ast.FFC, ast.E1, ast.E2, ast.C));
    }
    
    public Object visitForLoopUntilCommand(ForLoopUntilCommand ast, Object obj) {
        return(createQuaternary("For Loop Until Command", ast.FFC, ast.E1, ast.E2,ast.C));
    }
    
    public Object visitForFromCommand(ForFromCommand ast, Object obj) {
        return(createBinary("For From Command", ast.I, ast.E));
    }
    // END CAMBIO Joseph
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Cambio en las alternativas de single-command
    // @codigo        A.98
    public Object visitChooseCommand(ChooseCommand ast, Object o) {
        return(createBinary("Choose Command", ast.E, ast.CS));
    }
    // End cambio Andres
    
    // </editor-fold>
    
   // @author        Joseph
   // @descripcion   Metodos para visitar nuevos ASTs de ProcFunc
   // @funcionalidad Creacion de nuevas alternativas de no-terminales
   // @codigo        J.36
    // <editor-fold defaultstate="collapsed" desc=" ProcFuncs ">
    
    public Object visitProcedure (Procedure ast, Object obj) {
        return(createTernary("Procedure", ast.I, ast.FPS, ast.C));
    }
    
    public Object visitFunction (Function ast, Object obj) {
        return(createQuaternary("Procedure", ast.I, ast.FPS, ast.TD, ast.E));
    }

    public Object visitSequentialProcFuncs (SequentialProcFuncs ast, Object obj) {
        return(createBinary("ProcFuncs", ast.PF1, ast.PF2));
    }
    
    //END CAMBIO Joseph
   // </editor-fold>
   
    // Cases
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case-literal
    // @codigo        A.29
    public Object visitCaseLiteral(CaseLiteral ast, Object o) {
        return(createUnary("Case Literal", ast.T));
    }
    // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case range
    // @codigo        A.39
    public Object visitSimpleCaseRange(SimpleCaseRange ast, Object o) {
        return(createUnary("Simple Case Range", ast.CL));
    }
    // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case range
    // @codigo        A.40
    public Object visitCompoundCaseRange(CompoundCaseRange ast, Object o) {
      return(createBinary("Compound Case Range", ast.CL1, ast.CL2));
    }
    // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case literals
    // @codigo        A.52
    public Object visitCaseLiterals(CaseLiterals ast, Object o) {
        return(createUnary("Case Literals", ast.CR));
    }
     // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case literals
    // @codigo        A.53
    public Object visitSequentialCaseRange(SequentialCaseRange ast, Object o) {
        return(createBinary("Sequential Case Range", ast.CR1, ast.CR2));
    }
    // END Cambio Andres

    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de else case
    // @codigo        A.73
    public Object visitElseCase(ElseCase ast, Object o) {
        return(createUnary("Else Case", ast.C));
    }
     // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de cases
    // @codigo        A.74
    public Object visitCompoundCases(CompoundCases ast, Object o) {
       return(createBinary("Compound Cases", ast.C, ast.EC));
    }
     // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de cases
    // @codigo        A.75
    public Object visitSequentialCase(SequentialCase ast, Object o) {
       return(createBinary("Sequential Case", ast.C1, ast.C2));
    }
     // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de cases
    // @codigo        A.76
    public Object visitSimpleCases(SimpleCases ast, Object o) {
        return(createUnary("Simple Cases", ast.C));
    }
     // END Cambio Andres
    
    // @author        Andres
    // @descripcion   Metodos dibujantes para visitar ASTS nuevos
    // @funcionalidad Creacion en las alternativas de case
    // @codigo        A.77
    public Object visitSingleCase(SingleCase ast, Object o) {
        return(createBinary("Single Case", ast.CL, ast.C));
    }
     // END Cambio Andres
    
    // <editor-fold defaultstate="collapsed" desc=" Expressions ">
    // Expressions
    public Object visitArrayExpression(ArrayExpression ast, Object obj) {
        return(createUnary("Array Expression", ast.AA));
    }
    
    public Object visitBinaryExpression(BinaryExpression ast, Object obj) {
        return(createTernary("Binary Expression", ast.E1, ast.O, ast.E2));
    }
 
    // @author        Joseph
    // @descripcion   Cambio en metodo de creacion de arbol binario de call expression
    // @funcionalidad Cambio en las alternativas de call expression
    // @codigo        J.79   
    public Object visitCallExpression(CallExpression ast, Object obj) {
        return(createBinary("Call Expression", ast.LI, ast.APS));
    }
    /* J.79
    public Object visitCallExpression(CallExpression ast, Object obj) {
        return(createBinary("Call Expression", ast.I, ast.APS));
    }
    */
    // END CAMBIO Joseph
    
    public Object visitCharacterExpression(CharacterExpression ast, Object obj) {
        return(createUnary("Character Expression", ast.CL));
    }
    
    public Object visitEmptyExpression(EmptyExpression ast, Object obj) {
        return(createNullary("Empty Expression"));
    }
    
    public Object visitIfExpression(IfExpression ast, Object obj) {
        return(createTernary("If Expression", ast.E1, ast.E2, ast.E3));
    }
    
    public Object visitIntegerExpression(IntegerExpression ast, Object obj) {
        return(createUnary("Integer Expression", ast.IL));
    }
    
    public Object visitLetExpression(LetExpression ast, Object obj) {
        return(createBinary("Let Expression", ast.D, ast.E));
    }
    
    public Object visitRecordExpression(RecordExpression ast, Object obj) {
        return(createUnary("Record Expression", ast.RA));
    }
    
    public Object visitUnaryExpression(UnaryExpression ast, Object obj) {
        return(createBinary("Unary Expression", ast.O, ast.E));
    }
    
    public Object visitVnameExpression(VnameExpression ast, Object obj) {
        return(createUnary("Vname Expression", ast.V));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Declarations ">
    // Declarations
    public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object obj) {
        return(createQuaternary("Binary Operator Declaration", ast.O, ast.ARG1, ast.ARG2, ast.RES));
    }
    
    public Object visitConstDeclaration(ConstDeclaration ast, Object obj) {
        return(createBinary("Constant Declaration", ast.I, ast.E));
    }
    
    public Object visitFuncDeclaration(FuncDeclaration ast, Object obj) {
        return(createQuaternary("Function Declaration", ast.I, ast.FPS, ast.T, ast.E));
    }
    
    public Object visitProcDeclaration(ProcDeclaration ast, Object obj) {
        return(createTernary("Procedure Declaration", ast.I, ast.FPS, ast.C));        
    }
    
    public Object visitSequentialDeclaration(SequentialDeclaration ast, Object obj) {
        return(createBinary("Sequential Declaration", ast.D1, ast.D2));
    }
    
    public Object visitTypeDeclaration(TypeDeclaration ast, Object obj) {
        return(createBinary("Type Declaration", ast.I, ast.T));
    }
    
    public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object obj) {
        return(createTernary("Unary Operator Declaration", ast.O, ast.ARG, ast.RES));
    }
    
   // @author        Joseph
   // @descripcion   Metodos para visitar nuevas alternativas de single y compound declarations
   // @funcionalidad Creacion de nuevas alternativas de no-terminales
   // @codigo        J.50
    public Object visitVarTDDeclaration(VarTDDeclaration ast, Object obj) {
        return(createBinary("Variable Typed-denoted Declaration", ast.I, ast.T));
    }
    
    public Object visitVarExpDeclaration(VarExpDeclaration ast, Object obj) {
        return(createBinary("Initialized Variable Declaration", ast.I, ast.E));
    }
    
    public Object visitRecDeclaration(RecDeclaration ast, Object obj) {
        return(createUnary("Recursive declaration", ast.PFs));
    }
    
    public Object visitPrivDeclaration(PrivDeclaration ast, Object obj) {
        return(createBinary("Private declaration", ast.D1, ast.D2));
    }
    /*
    public Object visitVarDeclaration(VarDeclaration ast, Object obj) {
        return(createBinary("Variable Declaration", ast.I, ast.T));
    }
    
    */
    //END CAMBIO Joseph
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Aggregates ">
    // Array Aggregates
    public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object obj) {
        return(createBinary("Multiple Array Aggregate", ast.E, ast.AA));
    }
    
    public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object obj) {
        return(createUnary("Single Array Aggregate", ast.E));
    }
    
    // Record Aggregates
    public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object obj) {
        return(createTernary("Multiple Record Aggregate", ast.I, ast.E, ast.RA));
    }
    
    public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object obj) {
        return(createBinary("Single Record Aggregate", ast.I, ast.E));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Parameters ">
    // Formal Parameters   
    public Object visitConstFormalParameter(ConstFormalParameter ast, Object obj) {
        return(createBinary("Constant Formal Parameter", ast.I, ast.T));
    }
    
    public Object visitFuncFormalParameter(FuncFormalParameter ast, Object obj) {
        return(createTernary("Function Formal Parameter", ast.I, ast.FPS, ast.T));
    }
    
    public Object visitProcFormalParameter(ProcFormalParameter ast, Object obj) {
        return(createBinary("Procedure Formal Parameter", ast.I, ast.FPS));
    }
    
    public Object visitVarFormalParameter(VarFormalParameter ast, Object obj) {
        return(createBinary("Variable Formal Parameter", ast.I, ast.T));
    }
    
    public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object obj) {
        return(createNullary("Empty Formal Parameter Sequence"));
    }
    
    public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object obj) {
        return(createBinary("Multiple Formal Parameter Sequence", ast.FP, ast.FPS));
    }
    
    public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object obj) {
        return(createUnary("Single Formal Parameter Sequence", ast.FP));
    }
    
    // Actual Parameters
    public Object visitConstActualParameter(ConstActualParameter ast, Object obj) {
        return(createUnary("Constant Actual Parameter", ast.E));
    }
    
    public Object visitFuncActualParameter(FuncActualParameter ast, Object obj) {
        return(createUnary("Function Actual Parameter", ast.I));
    }
    
    public Object visitProcActualParameter(ProcActualParameter ast, Object obj) {
        return(createUnary("Procedure Actual Parameter", ast.I));
    }
    
    public Object visitVarActualParameter(VarActualParameter ast, Object obj) {
        return(createUnary("Variable Actual Parameter", ast.V));
    }
    
    public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object obj) {
        return(createNullary("Empty Actual Parameter Sequence"));
    }
    
    public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object obj) {
        return(createBinary("Multiple Actual Parameter Sequence", ast.AP, ast.APS));
    }
    
    public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object obj) {
        return(createUnary("Single Actual Parameter Sequence", ast.AP));
    }
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc=" Type Denoters ">
    // Type Denoters
    public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object obj) {
        return(createNullary("any"));
    }
    
    public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object obj) {
        return(createBinary("Array Type Denoter", ast.IL, ast.T));
    }
    
    public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object obj) {
        return(createNullary("bool"));
    }
    
    public Object visitCharTypeDenoter(CharTypeDenoter ast, Object obj) {
        return(createNullary("char"));
    }
    
    public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object obj) {
        return(createNullary("error"));
    }
   
    // @author        Joseph
    // @descripcion   Cambio en metodo de creacion de arbol unario de simple type denoter
    // @funcionalidad Cambio en las alternativas de type denoter
    // @codigo        J.80
    public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object obj) {
        return(createUnary("Simple Type Denoter", ast.LI));
    }
    /* J.80
    public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object obj) {
        return(createUnary("Simple Type Denoter", ast.I));
    }  
    */
    // END CAMBIO 
    
    public Object visitIntTypeDenoter(IntTypeDenoter ast, Object obj) {
        return(createNullary("int"));
    }
    
    public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object obj) {
        return(createUnary("Record Type Denoter", ast.FT));
    }
    
    public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object obj) {
        return(createTernary("Multiple Field Type Denoter", ast.I, ast.T, ast.FT));
    }
    
    public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object obj) {
        return(createBinary("Single Field Type Denoter", ast.I, ast.T));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Literals, Identifiers and Operators ">
    // Literals, Identifiers and Operators
    public Object visitCharacterLiteral(CharacterLiteral ast, Object obj) {
        return(createNullary(ast.spelling));
    }
    
    public Object visitIdentifier(Identifier ast, Object obj) {
        return(createNullary(ast.spelling));
    }
    
    public Object visitIntegerLiteral(IntegerLiteral ast, Object obj) {
        return(createNullary(ast.spelling));
    }
    
    public Object visitOperator(Operator ast, Object obj) {
        return(createNullary(ast.spelling));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Values or Variable Names ">
    // Values or Variable Names
    // @author        Andres
    // @descripcion   Agregar metodos de visita de nuevos ASTs VarName, Vname y package
    // @funcionalidad metodos de visita para AST de Varname, Vname y package
    // @codigo        A.113
    public Object visitDotVarName(DotVarName ast, Object obj) {
        return(createBinary("Dot Var Name", ast.I, ast.V));
    }
    
    public Object visitSimpleVarName(SimpleVarName ast, Object obj) {
        return(createUnary("Simple Var Name", ast.I));
    }
    
    public Object visitSubscriptVarName(SubscriptVarName ast, Object obj) {
        return(createBinary("Subscript Var Name", ast.V, ast.E));
    }
    
    public Object visitSimpleVname(SimpleVname ast, Object o) {
        return(createUnary("Simple Vname", ast.VN));
    }
    
    public Object visitPackageIdentifier(PackageIdentifier ast, Object o) {
      return(createUnary("Package Identifier", ast.I));
    }
    
    public Object visitPackageVname(PackageVname ast, Object o) {
        return(createBinary("Package Vname", ast.PI, ast.VN));
    }
    
    public Object visitSimpleLongIdentifier(SimpleLongIdentifier ast, Object o) {
       return(createUnary("Simple Long Identifier", ast.I));
    }
    
    public Object visitPackageLongIdentifier(PackageLongIdentifier ast, Object o) {
       return(createBinary("Package Long Identifier", ast.PI, ast.I));
    }
    
    public Object visitSinglePackageDeclaration(SinglePackageDeclaration ast, Object o) {
      return(createBinary("Single Package Declaration", ast.PI, ast.D));
    }
    
    public Object visitSequentialPackageDeclaration(SequentialPackageDeclaration ast, Object o) {
        return(createBinary("Sequential Package Declaration", ast.PD1, ast.PD2));
    }
    
    /*
        public Object visitDotVname(DotVname ast, Object obj) {
           return(createBinary("Dot Vname", ast.I, ast.V));
       }

       public Object visitSimpleVname(SimpleVname ast, Object obj) {
           return(createUnary("Simple Vname", ast.I));
       }

       public Object visitSubscriptVname(SubscriptVname ast, Object obj) {
           return(createBinary("Subscript Vname", ast.V, ast.E));
       }
    */
    // END cambio Andres
    
    // @author        Andres
    // @descripcion   Agregar metodos de visita de nuevos ASTs program
    // @funcionalidad metodos de visita para AST de program
    // @codigo        A.136
    public Object visitProgram(SimpleProgram ast, Object obj) {
        return(createUnary("Simple Program", ast.C));
    }
    
     public Object visitProgram(CompoundProgram ast, Object obj) {
        return(createBinary("Compound Program", ast.PD, ast.C));
    }
     
     public Object visitSimpleProgram(SimpleProgram ast, Object obj) {
        return(createUnary("Simple Program", ast.C));
    }
    
     public Object visitCompoundProgram(CompoundProgram ast, Object obj) {
        return(createBinary("Compound Program", ast.PD, ast.C));
    }
    /*
    public Object visitProgram(Program ast, Object obj) {
        return(createUnary("Program", ast.C));
    }
    */
    // END CAMBIO Andres
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Tree Creation Methods ">
    
    /**
     * Creates a nullary tree node (doesn't have any children).
     * @param caption The tree's caption (text to be shown when the tree is drawn).
     * @return The tree node.
     */
    public DefaultMutableTreeNode createNullary(String caption) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        
        return(t);
    }
    
    /**
     * Creates an unary tree node.
     * @param caption The tree's caption (text to be shown when the tree is drawn).
     * @param child1 The first children node.
     * @return The tree node.
     */
    public DefaultMutableTreeNode createUnary(String caption, AST child1) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        t.add((DefaultMutableTreeNode)child1.visit(this, null));
        
        return(t);
    }
    
    /**
     * Creates a binary tree node.
     * @param caption The tree's caption (text to be shown when the tree is drawn).
     * @param child1 The first children node.
     * @param child2 The second children node.
     * @return The tree node.
     */
    public DefaultMutableTreeNode createBinary(String caption, AST child1, AST child2) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        t.add((DefaultMutableTreeNode)child1.visit(this, null));
        t.add((DefaultMutableTreeNode)child2.visit(this, null));
        
        return(t);
    }
    
    /**
     * Creates a ternary tree node.
     * @param caption The tree's caption (text to be shown when the tree is drawn).
     * @param child1 The first children node.
     * @param child2 The second children node.
     * @param child3 The third children node.
     * @return The tree node.
     */
    public DefaultMutableTreeNode createTernary(String caption, AST child1, AST child2, AST child3) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        t.add((DefaultMutableTreeNode)child1.visit(this, null));
        t.add((DefaultMutableTreeNode)child2.visit(this, null));
        t.add((DefaultMutableTreeNode)child3.visit(this, null));
        
        return(t);        
    }
    
    /**
     * Creates a quaternary tree node.
     * @param caption The tree's caption (text to be shown when the tree is drawn).
     * @param child1 The first children node.
     * @param child2 The second children node.
     * @param child3 The third children node.
     * @param child4 The fourth children node.
     * @return The tree node.
     */
    public DefaultMutableTreeNode createQuaternary(String caption, AST child1, AST child2, AST child3, AST child4) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        t.add((DefaultMutableTreeNode)child1.visit(this, null));
        t.add((DefaultMutableTreeNode)child2.visit(this, null));
        t.add((DefaultMutableTreeNode)child3.visit(this, null));
        t.add((DefaultMutableTreeNode)child4.visit(this, null));
        
        return(t);             
    }
    
    // @author        Joseph
    // @descripcion   Metodo de dibujo de �rboles quinarios
    // @funcionalidad Dibujo de ASTs
    // @codigo        J.27
    public DefaultMutableTreeNode createQuinary(String caption, AST child1, AST child2, AST child3, AST child4, AST child5) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        t.add((DefaultMutableTreeNode)child1.visit(this, null));
        t.add((DefaultMutableTreeNode)child2.visit(this, null));
        t.add((DefaultMutableTreeNode)child3.visit(this, null));
        t.add((DefaultMutableTreeNode)child4.visit(this, null));
        t.add((DefaultMutableTreeNode)child5.visit(this, null));
        return(t);             
    }
    // END CAMBIO Joseph
    
    // </editor-fold>
}
