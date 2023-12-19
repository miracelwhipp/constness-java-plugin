package io.github.miracelwhipp.constness.plugin.utility;

import com.sun.source.tree.*;
import com.sun.source.util.TreeScanner;

import javax.lang.model.element.Element;

public final class ContainingElementTreeScanner extends TreeScanner<Element, CompilerTaskContext> {

    private static final ContainingElementTreeScanner INSTANCE = new ContainingElementTreeScanner();

    private ContainingElementTreeScanner() {
    }

    public static Element getContainingMethod(Tree tree, CompilerTaskContext context) {

        return tree.accept(INSTANCE, context);
    }

    private Element delegate(Tree node, CompilerTaskContext context) {

        return node.accept(this, context);
    }

    private Element visitTree(Tree node, CompilerTaskContext context) {

        return context.getParent(node).accept(this, context);
    }

    @Override
    public Element visitCompilationUnit(CompilationUnitTree node, CompilerTaskContext context) {
        return null;
    }

    @Override
    public Element visitMemberSelect(MemberSelectTree node, CompilerTaskContext context) {

        return context.getElement(node.getExpression());
    }

    @Override
    public Element visitMethodInvocation(MethodInvocationTree node, CompilerTaskContext context) {

        if (node.getMethodSelect().getKind() != Tree.Kind.IDENTIFIER) {

            delegate(node.getMethodSelect(), context);
        }

        return visitTree(node, context);
    }

    @Override
    public Element visitMethod(MethodTree node, CompilerTaskContext context) {

        return context.getElement(node);
    }

    @Override
    public Element visitPackage(PackageTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitImport(ImportTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitClass(ClassTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitVariable(VariableTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitEmptyStatement(EmptyStatementTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitBlock(BlockTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitDoWhileLoop(DoWhileLoopTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitWhileLoop(WhileLoopTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitForLoop(ForLoopTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitEnhancedForLoop(EnhancedForLoopTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitLabeledStatement(LabeledStatementTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitSwitch(SwitchTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitSwitchExpression(SwitchExpressionTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitCase(CaseTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitSynchronized(SynchronizedTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitTry(TryTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitCatch(CatchTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitConditionalExpression(ConditionalExpressionTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitIf(IfTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitExpressionStatement(ExpressionStatementTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitBreak(BreakTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitContinue(ContinueTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitReturn(ReturnTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitThrow(ThrowTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitAssert(AssertTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitNewClass(NewClassTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitNewArray(NewArrayTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitLambdaExpression(LambdaExpressionTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitParenthesized(ParenthesizedTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitAssignment(AssignmentTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitCompoundAssignment(CompoundAssignmentTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitUnary(UnaryTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitBinary(BinaryTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitTypeCast(TypeCastTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitInstanceOf(InstanceOfTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitArrayAccess(ArrayAccessTree node, CompilerTaskContext context) {

        return delegate(node.getExpression(), context);
    }

    @Override
    public Element visitMemberReference(MemberReferenceTree node, CompilerTaskContext context) {

        return delegate(node.getQualifierExpression(), context);
    }

    @Override
    public Element visitIdentifier(IdentifierTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitLiteral(LiteralTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitPrimitiveType(PrimitiveTypeTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitArrayType(ArrayTypeTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitParameterizedType(ParameterizedTypeTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitUnionType(UnionTypeTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitIntersectionType(IntersectionTypeTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitTypeParameter(TypeParameterTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitWildcard(WildcardTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitModifiers(ModifiersTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitAnnotation(AnnotationTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitAnnotatedType(AnnotatedTypeTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitModule(ModuleTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitExports(ExportsTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitOpens(OpensTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitProvides(ProvidesTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitRequires(RequiresTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitUses(UsesTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitOther(Tree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitErroneous(ErroneousTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitYield(YieldTree node, CompilerTaskContext context) {

        return visitTree(node, context);
    }
}
