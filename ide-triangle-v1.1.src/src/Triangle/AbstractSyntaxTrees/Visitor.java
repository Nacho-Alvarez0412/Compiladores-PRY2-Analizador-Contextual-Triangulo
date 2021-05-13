/*
 * @(#)Visitor.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.AbstractSyntaxTrees;

public interface Visitor {

  // Commands
  // @author        Andres
  // @descripcion   Agregar metodos de visita de nuevos ASTs
  // @funcionalidad metodos de visita para nuevos ASTs
  // @codigo        A.7, J.6
  public abstract Object visitAssignCommand(AssignCommand ast, Object o);
  public abstract Object visitCallCommand(CallCommand ast, Object o);
  public abstract Object visitEmptyCommand(EmptyCommand ast, Object o);
  public abstract Object visitCompoundIfCommand(CompoundIfCommand ast, Object o);
  public abstract Object visitIfCommand(IfCommand ast, Object o);
  public abstract Object visitLetCommand(LetCommand ast, Object o);
  public abstract Object visitSequentialCommand(SequentialCommand ast, Object o);
  public abstract Object visitWhileLoopCommand(WhileLoopCommand ast, Object o);
  public abstract Object visitUntilLoopCommand(UntilLoopCommand ast, Object o);
  public abstract Object visitDoLoopUntilCommand(DoLoopUntilCommand ast, Object o);
  public abstract Object visitDoLoopWhileCommand(DoLoopWhileCommand ast, Object o);
  public abstract Object visitSequentialElsifCommand(SequentialElsifCommand ast, Object o);
  public abstract Object visitSingleElsifCommand(SingleElsifCommand ast, Object o);
  public abstract Object visitForLoopDoCommand(ForLoopDoCommand ast, Object o);
  public abstract Object visitForLoopWhileCommand(ForLoopWhileCommand ast, Object o);
  public abstract Object visitForLoopUntilCommand(ForLoopUntilCommand ast, Object o);
  public abstract Object visitProcedure(Procedure ast, Object o);
  public abstract Object visitFunction(Function ast, Object o);
  public abstract Object visitSequentialProcFuncs(SequentialProcFuncs ast, Object o);
  public abstract Object visitChooseCommand(ChooseCommand ast, Object o);
  public abstract Object visitForFromCommand(ForFromCommand ast, Object o);
  /* J.6
   public abstract Object visitAssignCommand(AssignCommand ast, Object o);
  public abstract Object visitCallCommand(CallCommand ast, Object o);
  public abstract Object visitEmptyCommand(EmptyCommand ast, Object o);
  public abstract Object visitIfCommand(IfCommand ast, Object o);
  public abstract Object visitLetCommand(LetCommand ast, Object o);
  public abstract Object visitSequentialCommand(SequentialCommand ast, Object o);
  public abstract Object visitWhileCommand(WhileCommand ast, Object o);
  public abstract Object visitSequentialElsifCommand(SequentialElsifCommand ast, Object o);
  public abstract Object visitSingleElsifCommand(SingleElsifCommand ast, Object o);
  */
  /* A.7
  public abstract Object visitAssignCommand(AssignCommand ast, Object o);
  public abstract Object visitCallCommand(CallCommand ast, Object o);
  public abstract Object visitEmptyCommand(EmptyCommand ast, Object o);
  public abstract Object visitIfCommand(IfCommand ast, Object o);
  public abstract Object visitLetCommand(LetCommand ast, Object o);
  public abstract Object visitSequentialCommand(SequentialCommand ast, Object o);
  public abstract Object visitWhileCommand(WhileCommand ast, Object o);
  */
  // END Cambio Andres
  
  // @author        Andres
  // @descripcion   Agregar metodos de visita para AST Cases
  // @funcionalidad metodos de visita ASTs de Cases
  // @codigo        A.27
  // Cases
  public abstract Object visitCaseLiteral(CaseLiteral ast, Object o);
  public abstract Object visitSimpleCaseRange(SimpleCaseRange ast, Object o);
  public abstract Object visitCompoundCaseRange(CompoundCaseRange ast, Object o);
  public abstract Object visitSequentialCaseRange(SequentialCaseRange ast, Object o);
  public abstract Object visitCaseLiterals(CaseLiterals ast, Object o);
  public abstract Object visitCompoundCases(CompoundCases ast, Object o);
  public abstract Object visitElseCase(ElseCase ast, Object o);
  public abstract Object visitSequentialCase(SequentialCase ast, Object o);
  public abstract Object visitSimpleCases(SimpleCases ast, Object o);
  public abstract Object visitSingleCase(SingleCase ast, Object o);
  // End Cambio Andres
  
  // Expressions
  public abstract Object visitArrayExpression(ArrayExpression ast, Object o);
  public abstract Object visitBinaryExpression(BinaryExpression ast, Object o);
  public abstract Object visitCallExpression(CallExpression ast, Object o);
  public abstract Object visitCharacterExpression(CharacterExpression ast, Object o);
  public abstract Object visitEmptyExpression(EmptyExpression ast, Object o);
  public abstract Object visitIfExpression(IfExpression ast, Object o);
  public abstract Object visitIntegerExpression(IntegerExpression ast, Object o);
  public abstract Object visitLetExpression(LetExpression ast, Object o);
  public abstract Object visitRecordExpression(RecordExpression ast, Object o);
  public abstract Object visitUnaryExpression(UnaryExpression ast, Object o);
  public abstract Object visitVnameExpression(VnameExpression ast, Object o);

  // Declarations
  // @author        Joseph
  // @descripcion   Agregar metodos de visita de nuevos ASTs
  // @funcionalidad metodos de visita para nuevos ASTs
  // @codigo        J.44 
  public abstract Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o);
  public abstract Object visitConstDeclaration(ConstDeclaration ast, Object o);
  public abstract Object visitFuncDeclaration(FuncDeclaration ast, Object o);
  public abstract Object visitProcDeclaration(ProcDeclaration ast, Object o);
  public abstract Object visitSequentialDeclaration(SequentialDeclaration ast, Object o);
  public abstract Object visitTypeDeclaration(TypeDeclaration ast, Object o);
  public abstract Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o);
  public abstract Object visitVarTDDeclaration(VarTDDeclaration ast, Object o);
  public abstract Object visitVarExpDeclaration(VarExpDeclaration ast, Object o);
  public abstract Object visitRecDeclaration(RecDeclaration ast, Object o);
  public abstract Object visitPrivDeclaration(PrivDeclaration ast, Object o);
  /* J.44
  public abstract Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o);
  public abstract Object visitConstDeclaration(ConstDeclaration ast, Object o);
  public abstract Object visitFuncDeclaration(FuncDeclaration ast, Object o);
  public abstract Object visitProcDeclaration(ProcDeclaration ast, Object o);
  public abstract Object visitSequentialDeclaration(SequentialDeclaration ast, Object o);
  public abstract Object visitTypeDeclaration(TypeDeclaration ast, Object o);
  public abstract Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o);
  public abstract Object visitVarDeclaration(VarDeclaration ast, Object o);
  */
  // END CAMBIO Joseph
  // Array Aggregates
  public abstract Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o);
  public abstract Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o);

  // Record Aggregates
  public abstract Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o);
  public abstract Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o);

  // Formal Parameters
  public abstract Object visitConstFormalParameter(ConstFormalParameter ast, Object o);
  public abstract Object visitFuncFormalParameter(FuncFormalParameter ast, Object o);
  public abstract Object visitProcFormalParameter(ProcFormalParameter ast, Object o);
  public abstract Object visitVarFormalParameter(VarFormalParameter ast, Object o);

  public abstract Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o);
  public abstract Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o);
  public abstract Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o);

  // Actual Parameters
  public abstract Object visitConstActualParameter(ConstActualParameter ast, Object o);
  public abstract Object visitFuncActualParameter(FuncActualParameter ast, Object o);
  public abstract Object visitProcActualParameter(ProcActualParameter ast, Object o);
  public abstract Object visitVarActualParameter(VarActualParameter ast, Object o);

  public abstract Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o);
  public abstract Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o);
  public abstract Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o);

  // Type Denoters
  public abstract Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o);
  public abstract Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o);
  public abstract Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o);
  public abstract Object visitCharTypeDenoter(CharTypeDenoter ast, Object o);
  public abstract Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o);
  public abstract Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o);
  public abstract Object visitIntTypeDenoter(IntTypeDenoter ast, Object o);
  public abstract Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o);

  public abstract Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o);
  public abstract Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o);

  // Literals, Identifiers and Operators
  public abstract Object visitCharacterLiteral(CharacterLiteral ast, Object o);
  public abstract Object visitIdentifier(Identifier ast, Object o);
  public abstract Object visitIntegerLiteral(IntegerLiteral ast, Object o);
  public abstract Object visitOperator(Operator ast, Object o);
  
  // @author        Andres
  // @descripcion   Agregar metodos visitor para VarName, Vname y package
  // @funcionalidad Visitors para VarName, Vname y package
  // @codigo        A.108
  public abstract Object visitDotVarName(DotVarName ast, Object o);
  public abstract Object visitSimpleVarName(SimpleVarName ast, Object o);
  public abstract Object visitSubscriptVarName(SubscriptVarName ast, Object o);
  public abstract Object visitSimpleVname(SimpleVname ast, Object o);
  public abstract Object visitPackageIdentifier(PackageIdentifier ast, Object o);
  public abstract Object visitPackageVname(PackageVname ast, Object o);
  public abstract Object visitSimpleLongIdentifier(SimpleLongIdentifier ast, Object o);
  public abstract Object visitPackageLongIdentifier(PackageLongIdentifier ast, Object o);
  public abstract Object visitSinglePackageDeclaration(SinglePackageDeclaration ast, Object o);
  public abstract Object visitSequentialPackageDeclaration(SequentialPackageDeclaration ast, Object o);
  /*
    public abstract Object visitDotVname(DotVname ast, Object o);
    public abstract Object visitSimpleVname(SimpleVname ast, Object o);
    public abstract Object visitSubscriptVname(SubscriptVname ast, Object o);
  */
  // END Cambio Andres

  // Programs
  // @author        Andres
  // @descripcion   Agregar metodos visitor para program
  // @funcionalidad Visitors para program
  // @codigo        A.131
  public abstract Object visitSimpleProgram(SimpleProgram ast, Object o);
  public abstract Object visitCompoundProgram(CompoundProgram ast, Object o);
  /*
   public abstract Object visitProgram(Program ast, Object o);
  */
  // END Cambio Andres

}
