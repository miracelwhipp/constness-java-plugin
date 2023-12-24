package io.github.miracelwhipp.constness.plugin.api;

import com.sun.source.tree.*;
import com.sun.source.util.TreeScanner;

import javax.lang.model.element.Element;

public final class ContainingElementTreeScanner extends TreeScanner<Element, JavacApi> {

    private static final ContainingElementTreeScanner INSTANCE = new ContainingElementTreeScanner();

    private ContainingElementTreeScanner() {
    }

    public static Element getContainingMethod(Tree tree, JavacApi context) {

        return tree.accept(INSTANCE, context);
    }

    private Element delegate(Tree node, JavacApi context) {

        return node.accept(this, context);
    }

    private Element visitTree(Tree node, JavacApi context) {

        return context.getParent(node).accept(this, context);
    }

    @Override
    public Element visitCompilationUnit(CompilationUnitTree node, JavacApi context) {
        return null;
    }

    @Override
    public Element visitMemberSelect(MemberSelectTree node, JavacApi context) {

        return context.getElement(node.getExpression());
    }

    @Override
    public Element visitMethodInvocation(MethodInvocationTree node, JavacApi context) {

        if (node.getMethodSelect().getKind() != Tree.Kind.IDENTIFIER) {

            delegate(node.getMethodSelect(), context);
        }

        return visitTree(node, context);
    }

    @Override
    public Element visitMethod(MethodTree node, JavacApi context) {

        return context.getElement(node);
    }

    @Override
    public Element visitPackage(PackageTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitImport(ImportTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitClass(ClassTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitVariable(VariableTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitEmptyStatement(EmptyStatementTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitBlock(BlockTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitDoWhileLoop(DoWhileLoopTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitWhileLoop(WhileLoopTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitForLoop(ForLoopTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitEnhancedForLoop(EnhancedForLoopTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitLabeledStatement(LabeledStatementTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitSwitch(SwitchTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitSwitchExpression(SwitchExpressionTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitCase(CaseTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitSynchronized(SynchronizedTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitTry(TryTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitCatch(CatchTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitConditionalExpression(ConditionalExpressionTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitIf(IfTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitExpressionStatement(ExpressionStatementTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitBreak(BreakTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitContinue(ContinueTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitReturn(ReturnTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitThrow(ThrowTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitAssert(AssertTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitNewClass(NewClassTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitNewArray(NewArrayTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitLambdaExpression(LambdaExpressionTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitParenthesized(ParenthesizedTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitAssignment(AssignmentTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitCompoundAssignment(CompoundAssignmentTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitUnary(UnaryTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitBinary(BinaryTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitTypeCast(TypeCastTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitInstanceOf(InstanceOfTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitArrayAccess(ArrayAccessTree node, JavacApi context) {

        return delegate(node.getExpression(), context);
    }

    @Override
    public Element visitMemberReference(MemberReferenceTree node, JavacApi context) {

        return delegate(node.getQualifierExpression(), context);
    }

    @Override
    public Element visitIdentifier(IdentifierTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitLiteral(LiteralTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitPrimitiveType(PrimitiveTypeTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitArrayType(ArrayTypeTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitParameterizedType(ParameterizedTypeTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitUnionType(UnionTypeTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitIntersectionType(IntersectionTypeTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitTypeParameter(TypeParameterTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitWildcard(WildcardTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitModifiers(ModifiersTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitAnnotation(AnnotationTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitAnnotatedType(AnnotatedTypeTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitModule(ModuleTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitExports(ExportsTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitOpens(OpensTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitProvides(ProvidesTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitRequires(RequiresTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitUses(UsesTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitOther(Tree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitErroneous(ErroneousTree node, JavacApi context) {

        return visitTree(node, context);
    }

    @Override
    public Element visitYield(YieldTree node, JavacApi context) {

        return visitTree(node, context);
    }
}
