package Core.Visitors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import Triangle.AbstractSyntaxTrees.*;

import java.io.File;

public class XMLVisitor implements Visitor {

  public XMLVisitor(){

  }

  private Document doc;

  public Object visitAssignCommand(AssignCommand ast, Object o) {
    Element elemento = doc.createElement("AssignCommand");

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    elemento.appendChild((Node) ast.V.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitCallCommand(CallCommand ast, Object o) {
    Element elemento = doc.createElement("CallCommand");

    elemento.appendChild((Node) ast.APS.visitXML(this, null));

    elemento.appendChild((Node) ast.LI.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitEmptyCommand(EmptyCommand ast, Object o) {
    Element elemento = doc.createElement("EmptyCommand");


    return elemento;
  }

  @Override
  public Object visitCompoundIfCommand(CompoundIfCommand ast, Object o) {
    Element elemento = doc.createElement("CompoundIfCommand");

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    elemento.appendChild((Node) ast.C1.visitXML(this, null));

    elemento.appendChild((Node) ast.C2.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitIfCommand(IfCommand ast, Object o) {
    Element elemento = doc.createElement("IfCommand");

    elemento.appendChild((Node) ast.C1.visitXML(this, null));

    elemento.appendChild((Node) ast.C2.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitLetCommand(LetCommand ast, Object o) {
    Element elemento = doc.createElement("LetCommand");

    elemento.appendChild((Node) ast.C.visitXML(this, null));
    
    elemento.appendChild((Node) ast.D.visitXML(this, null));


    return elemento;
  }

  @Override
  public Object visitSequentialCommand(SequentialCommand ast, Object o) {
    Element elemento = doc.createElement("SequentialCommand");

    elemento.appendChild((Node) ast.C1.visitXML(this, null));
    
    elemento.appendChild((Node) ast.C2.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitWhileLoopCommand(WhileLoopCommand ast, Object o) {
    Element elemento = doc.createElement("WhileLoopCommand");
    
    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitUntilLoopCommand(UntilLoopCommand ast, Object o) {
    Element elemento = doc.createElement("UntilLoopCommand");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitDoLoopUntilCommand(DoLoopUntilCommand ast, Object o) {
    Element elemento = doc.createElement("DoLoopUntilCommand");
    
    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitDoLoopWhileCommand(DoLoopWhileCommand ast, Object o) {
    Element elemento = doc.createElement("DoLoopWhileCommand");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSequentialElsifCommand(SequentialElsifCommand ast, Object o) {
    Element elemento = doc.createElement("SequentialElseIfCommand");

    elemento.appendChild((Node) ast.SE1.visitXML(this, null));

    elemento.appendChild((Node) ast.SE2.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSingleElsifCommand(SingleElsifCommand ast, Object o) {
    Element elemento = doc.createElement("SingleElseIfCommand");
    
    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitForLoopDoCommand(ForLoopDoCommand ast, Object o) {
    Element elemento = doc.createElement("ForLoopDoCommand");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    elemento.appendChild((Node) ast.FFC.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitForLoopWhileCommand(ForLoopWhileCommand ast, Object o) {
    Element elemento = doc.createElement("ForLoopWhileCommand");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.E1.visitXML(this, null));

    elemento.appendChild((Node) ast.E2.visitXML(this, null));

    elemento.appendChild((Node) ast.FFC.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitForLoopUntilCommand(ForLoopUntilCommand ast, Object o) {
    Element elemento = doc.createElement("ForLoopUntilCommand");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.E1.visitXML(this, null));

    elemento.appendChild((Node) ast.E2.visitXML(this, null));

    elemento.appendChild((Node) ast.FFC.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitProcedure(Procedure ast, Object o) {
    Element elemento = doc.createElement("Procedure");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.FPS.visitXML(this, null));

    elemento.appendChild((Node) ast.I.visitXML(this, null));


    return elemento;
  }

  @Override
  public Object visitFunction(Function ast, Object o) {
    Element elemento = doc.createElement("Function");

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    elemento.appendChild((Node) ast.FPS.visitXML(this, null));

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.TD.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSequentialProcFuncs(SequentialProcFuncs ast, Object o) {
    Element elemento = doc.createElement("ProcFuncs");

    elemento.appendChild((Node) ast.PF1.visitXML(this, null));

    elemento.appendChild((Node) ast.PF2.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitChooseCommand(ChooseCommand ast, Object o) {
    Element elemento = doc.createElement("ChooseCommand");

    elemento.appendChild((Node) ast.CS.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));


    return elemento;
  }

  @Override
  public Object visitForFromCommand(ForFromCommand ast, Object o) {
    Element elemento = doc.createElement("ForFromCommand");

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitCaseLiteral(CaseLiteral ast, Object o) {
    Element elemento = doc.createElement("CaseLiteral");

    elemento.appendChild((Node) ast.T.visitXML(this, null));


    return elemento;
  }

  @Override
  public Object visitSimpleCaseRange(SimpleCaseRange ast, Object o) {
    Element elemento = doc.createElement("SimpleCaseRange");

    elemento.appendChild((Node) ast.CL.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitCompoundCaseRange(CompoundCaseRange ast, Object o) {
    Element elemento = doc.createElement("CompoundCaseRange");

    elemento.appendChild((Node) ast.CL1.visitXML(this, null));

    elemento.appendChild((Node) ast.CL2.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSequentialCaseRange(SequentialCaseRange ast, Object o) {
    Element elemento = doc.createElement("SequentialCaseRange");

    elemento.appendChild((Node) ast.CR1.visitXML(this, null));

    elemento.appendChild((Node) ast.CR2.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitCaseLiterals(CaseLiterals ast, Object o) {
    Element elemento = doc.createElement("CaseLiterals");

    elemento.appendChild((Node) ast.CR.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitCompoundCases(CompoundCases ast, Object o) {
    Element elemento = doc.createElement("CompundCases");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.EC.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitElseCase(ElseCase ast, Object o) {
    Element elemento = doc.createElement("ElseCase");

    elemento.appendChild((Node) ast.C.visitXML(this, null));


    return elemento;
  }

  @Override
  public Object visitSequentialCase(SequentialCase ast, Object o) {
    Element elemento = doc.createElement("SequentialCase");

    elemento.appendChild((Node) ast.C1.visitXML(this, null));

    elemento.appendChild((Node) ast.C2.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSimpleCases(SimpleCases ast, Object o) {
    Element elemento = doc.createElement("SimpleCases");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSingleCase(SingleCase ast, Object o) {
    Element elemento = doc.createElement("SingleCase");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.CL.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitArrayExpression(ArrayExpression ast, Object o) {
    Element elemento = doc.createElement("ArrayExpression");

    elemento.appendChild((Node) ast.AA.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitBinaryExpression(BinaryExpression ast, Object o) {
    Element elemento = doc.createElement("BinaryExpression");

    elemento.appendChild((Node) ast.E1.visitXML(this, null));

    elemento.appendChild((Node) ast.E2.visitXML(this, null));

    elemento.appendChild((Node) ast.O.visitXML(this, null));


    return elemento;
  }

  @Override
  public Object visitCallExpression(CallExpression ast, Object o) {
    Element elemento = doc.createElement("CallExpression");

    elemento.appendChild((Node) ast.APS.visitXML(this, null));

    elemento.appendChild((Node) ast.LI.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitCharacterExpression(CharacterExpression ast, Object o) {
    Element elemento = doc.createElement("CharacterExpression");

    elemento.appendChild((Node) ast.CL.visitXML(this, null));


    return elemento;
  }

  @Override
  public Object visitEmptyExpression(EmptyExpression ast, Object o) {
    Element elemento = doc.createElement("EmptyExpression");

    return elemento;
  }

  @Override
  public Object visitIfExpression(IfExpression ast, Object o) {
    Element elemento = doc.createElement("IfExpression");

    elemento.appendChild((Node) ast.E1.visitXML(this, null));

    elemento.appendChild((Node) ast.E2.visitXML(this, null));

    elemento.appendChild((Node) ast.E3.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitIntegerExpression(IntegerExpression ast, Object o) {
    Element elemento = doc.createElement("IntegerExpression");

    elemento.appendChild((Node) ast.IL.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitLetExpression(LetExpression ast, Object o) {
    Element elemento = doc.createElement("LetExpression");

    elemento.appendChild((Node) ast.D.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitRecordExpression(RecordExpression ast, Object o) {
    Element elemento = doc.createElement("RecordExpression");

    elemento.appendChild((Node) ast.RA.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitUnaryExpression(UnaryExpression ast, Object o) {
    Element elemento = doc.createElement("UnaryExpression");

    elemento.appendChild((Node) ast.O.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitVnameExpression(VnameExpression ast, Object o) {
    Element elemento = doc.createElement("VnameExpression");

    elemento.appendChild((Node) ast.V.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o) {
    Element elemento = doc.createElement("BinaryOperatorDeclaration");

    elemento.appendChild((Node) ast.ARG1.visitXML(this, null));

    elemento.appendChild((Node) ast.ARG2.visitXML(this, null));

    elemento.appendChild((Node) ast.O.visitXML(this, null));

    elemento.appendChild((Node) ast.RES.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitConstDeclaration(ConstDeclaration ast, Object o) {
    Element elemento = doc.createElement("ConstantDeclaration");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitFuncDeclaration(FuncDeclaration ast, Object o) {
    Element elemento = doc.createElement("FunctionDeclaration");

    elemento.appendChild((Node) ast.FPS.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.T.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitProcDeclaration(ProcDeclaration ast, Object o) {
    Element elemento = doc.createElement("ProcedureDeclaration");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.FPS.visitXML(this, null));

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSequentialDeclaration(SequentialDeclaration ast, Object o) {
    Element elemento = doc.createElement("SequentialDeclaration");

    elemento.appendChild((Node) ast.D1.visitXML(this, null));

    elemento.appendChild((Node) ast.D2.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitTypeDeclaration(TypeDeclaration ast, Object o) {
    Element elemento = doc.createElement("TypeDeclaration");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.T.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o) {
    Element elemento = doc.createElement("UnaryOperatorDeclaration");

    elemento.appendChild((Node) ast.ARG.visitXML(this, null));

    elemento.appendChild((Node) ast.O.visitXML(this, null));

    elemento.appendChild((Node) ast.RES.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitVarTDDeclaration(VarTDDeclaration ast, Object o) {
    Element elemento = doc.createElement("VariableTyped-denotedDeclaration");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.T.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitVarExpDeclaration(VarExpDeclaration ast, Object o) {
    Element elemento = doc.createElement("InitializedVariableDeclaration");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitRecDeclaration(RecDeclaration ast, Object o) {
    Element elemento = doc.createElement("RecursiveDeclaration");

    elemento.appendChild((Node) ast.PFs.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitPrivDeclaration(PrivDeclaration ast, Object o) {
    Element elemento = doc.createElement("PrivateDeclaration");

    elemento.appendChild((Node) ast.D1.visitXML(this, null));

    elemento.appendChild((Node) ast.D2.visitXML(this, null));

    return elemento;
  }

 
  @Override
  public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o) {
    Element elemento = doc.createElement("MultipleArrayAggregate");

    elemento.appendChild((Node) ast.AA.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o) {
    Element elemento = doc.createElement("SingleArrayAggregate");

    elemento.appendChild((Node) ast.E.visitXML(this, null));


    return elemento;
  }

  @Override
  public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o) {
    Element elemento = doc.createElement("MultipleRecordAggregate");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    elemento.appendChild((Node) ast.RA.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o) {
    Element elemento = doc.createElement("SingleRecordAggregate");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }


  @Override
  public Object visitConstFormalParameter(ConstFormalParameter ast, Object o) {
    Element elemento = doc.createElement("ConstantFormalParameter");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.T.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitFuncFormalParameter(FuncFormalParameter ast, Object o) {
    Element elemento = doc.createElement("FunctionFormalParameter");

    elemento.appendChild((Node) ast.FPS.visitXML(this, null));

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.T.visitXML(this, null));


    return elemento;
  }

  @Override
  public Object visitProcFormalParameter(ProcFormalParameter ast, Object o) {
    Element elemento = doc.createElement("ProcedureFormalParameter");

    elemento.appendChild((Node) ast.FPS.visitXML(this, null));

    elemento.appendChild((Node) ast.I.visitXML(this, null));
 

    return elemento;
  }

  @Override
  public Object visitVarFormalParameter(VarFormalParameter ast, Object o) {
    Element elemento = doc.createElement("VariableFormalParameter");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.T.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o) {
    Element elemento = doc.createElement("Empty FormalParameterSequence");

    return elemento;
  }

  @Override
  public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o) {
    Element elemento = doc.createElement("MultipleFormalParameterSequence");
    
    elemento.appendChild((Node) ast.FP.visitXML(this, null));

    elemento.appendChild((Node) ast.FPS.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o) {
    Element elemento = doc.createElement("SingleFormalParameterSequence");

    elemento.appendChild((Node) ast.FP.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitConstActualParameter(ConstActualParameter ast, Object o) {
    Element elemento = doc.createElement("ConstantActualParameter");

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitFuncActualParameter(FuncActualParameter ast, Object o) {
    Element elemento = doc.createElement("FunctionActualParameter");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitProcActualParameter(ProcActualParameter ast, Object o) {
    Element elemento = doc.createElement("ProcedureActualParameter");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitVarActualParameter(VarActualParameter ast, Object o) {
    Element elemento = doc.createElement("VariableFormalParameter");

    elemento.appendChild((Node) ast.V.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o) {
    Element elemento = doc.createElement("EmptyFormalParameterSequence");

    return elemento;
  }

  @Override
  public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o) {
    Element elemento = doc.createElement("MultipleFormalParameterSequence");

    elemento.appendChild((Node) ast.AP.visitXML(this, null));

    elemento.appendChild((Node) ast.APS.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o) {
    Element elemento = doc.createElement("SingleFormalParameterSequence");

    elemento.appendChild((Node) ast.AP.visitXML(this, null));

    return elemento;
  }


  @Override
  public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o) {
    Element elemento = doc.createElement("any");

    return elemento;
  }

  @Override
  public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o) {
    Element elemento = doc.createElement("ArrayTypeDenoter");

    elemento.appendChild((Node) ast.IL.visitXML(this, null));

    elemento.appendChild((Node) ast.T.visitXML(this, null));

    return elemento;
  }


  @Override
  public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o) {
    Element elemento = doc.createElement("bool");

    return elemento;
  }

  @Override
  public Object visitCharTypeDenoter(CharTypeDenoter ast, Object o) {
    Element elemento = doc.createElement("char");

    return elemento;
  }

  @Override
  public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o) {
    Element elemento = doc.createElement("error");

    return elemento;
  }

  @Override
  public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o) {

    Element elemento = doc.createElement("SimpleTypeDenoter");

    elemento.appendChild((Node) ast.LI.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitIntTypeDenoter(IntTypeDenoter ast, Object o) {

    Element elemento = doc.createElement("int");

    return elemento;
  }

  @Override
  public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o) {

    Element elemento = doc.createElement("RecordTypeDenoter");

    elemento.appendChild((Node) ast.FT.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o) {

    Element elemento = doc.createElement("MultipleFieldTypeDenoter");

    elemento.appendChild((Node) ast.FT.visitXML(this, null));

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.T.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o) {

    Element elemento = doc.createElement("SingleFieldTypeDenoter");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.T.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitCharacterLiteral(CharacterLiteral ast, Object o) {

    Element elemento = doc.createElement("CharacterLiteral");

    Attr attrType = doc.createAttribute("value");
    attrType.setValue(ast.spelling);
    elemento.setAttributeNode(attrType);

    return elemento;
  }

  @Override
  public Object visitIdentifier(Identifier ast, Object o) {

    Element elemento = doc.createElement("Identifier");

    Attr attrType = doc.createAttribute("value");
    attrType.setValue(ast.spelling);
    elemento.setAttributeNode(attrType);

//    elemento.appendChild((Node) ast.decl.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitIntegerLiteral(IntegerLiteral ast, Object o) {

    Element elemento = doc.createElement("IntegerLiteral");

    Attr attrType = doc.createAttribute("value");
    attrType.setValue(ast.spelling);
    elemento.setAttributeNode(attrType);

    return elemento;
  }

  @Override
  public Object visitOperator(Operator ast, Object o) {

    Element elemento = doc.createElement("Operator");

    //elemento.appendChild((Node) ast.decl.visitXML(this, null));

    Attr attrType = doc.createAttribute("value");
    attrType.setValue(ast.spelling);
    elemento.setAttributeNode(attrType);


    return elemento;
  }

  @Override
  public Object visitDotVarName(DotVarName ast, Object o) {

    Element elemento = doc.createElement("DotVarName");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.V.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSimpleVarName(SimpleVarName ast, Object o) {

    Element elemento = doc.createElement("SimpleVarName");

    elemento.appendChild((Node) ast.I.visitXML(this, null));


    return elemento;
  }

  @Override
  public Object visitSubscriptVarName(SubscriptVarName ast, Object o) {

    Element elemento = doc.createElement("SubscriptVarName");

    elemento.appendChild((Node) ast.V.visitXML(this, null));

    elemento.appendChild((Node) ast.E.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSimpleVname(SimpleVname ast, Object o) {

    Element elemento = doc.createElement("SimpleVname");

    elemento.appendChild((Node) ast.VN.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitPackageIdentifier(PackageIdentifier ast, Object o) {

    Element elemento = doc.createElement("PackageIdentifier");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitPackageVname(PackageVname ast, Object o) {

    Element elemento = doc.createElement("PackageVname");

    elemento.appendChild((Node) ast.PI.visitXML(this, null));

    elemento.appendChild((Node) ast.VN.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSimpleLongIdentifier(SimpleLongIdentifier ast, Object o) {

    Element elemento = doc.createElement("SimpleLongIdentifier");

    elemento.appendChild((Node) ast.I.visitXML(this, null));
    
    return elemento;
  }

  @Override
  public Object visitPackageLongIdentifier(PackageLongIdentifier ast, Object o) {

    Element elemento = doc.createElement("PackageLongIdentifier");

    elemento.appendChild((Node) ast.I.visitXML(this, null));

    elemento.appendChild((Node) ast.PI.visitXML(this, null));
  
    return elemento;
  }

  @Override
  public Object visitSinglePackageDeclaration(SinglePackageDeclaration ast, Object o) {

    Element elemento = doc.createElement("SinglePackageDeclaration");

    elemento.appendChild((Node) ast.D.visitXML(this, null));

    elemento.appendChild((Node) ast.PI.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSequentialPackageDeclaration(SequentialPackageDeclaration ast, Object o) {
   
    Element elemento = doc.createElement("SequentialPackageDeclaration");

    elemento.appendChild((Node) ast.PD1.visitXML(this, null));

    elemento.appendChild((Node) ast.PD2.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitSimpleProgram(SimpleProgram ast, Object o) {
    Element elemento = this.doc.createElement("SimpleProgram");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    return elemento;
  }

  @Override
  public Object visitCompoundProgram(CompoundProgram ast, Object o) {
    
    Element elemento = this.doc.createElement("CompoundProgram");

    elemento.appendChild((Node) ast.C.visitXML(this, null));

    elemento.appendChild((Node) ast.PD.visitXML(this, null));

    return elemento;
  }

  public Object generateXML(AST rootElement,String fileName){
    
    try {

      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

      this.doc = dBuilder.newDocument();

      this.doc.appendChild((Node) rootElement.visitXML(this, null));

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(this.doc);

      File file = new File("XMLs\\"+fileName);
      file.getParentFile().mkdirs();
      
      StreamResult result = new StreamResult(file);
      transformer.transform(source, result);

   } catch (Exception e) {
      e.printStackTrace();
   }
    return null;

  }

    @Override
    public Object visitFuncDeclaration2(FuncDeclaration ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitProcDeclaration2(ProcDeclaration ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitProcedure2(Procedure ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitFunction2(Function ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
