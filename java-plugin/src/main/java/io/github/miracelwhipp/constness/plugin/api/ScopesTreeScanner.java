package io.github.miracelwhipp.constness.plugin.api;

import com.sun.source.tree.*;
import com.sun.source.util.TreePathScanner;

import java.util.ArrayDeque;
import java.util.Queue;

public class ScopesTreeScanner extends TreePathScanner<Tree, Queue<Tree>> {

    private static final ScopesTreeScanner INSTANCE = new ScopesTreeScanner();

    private Tree delegate(Tree node, Queue<Tree> api) {

        return node.accept(this, api);
    }

    public static Queue<Tree> getScopes(Tree node) {

        Queue<Tree> result = new ArrayDeque<>();

        node.accept(INSTANCE, result);

        return result;
    }

    private Tree getParent() {

        return getCurrentPath().getParentPath().getLeaf();
    }

    private Tree delegateParent(Tree node, Queue<Tree> api) {

        return delegate(getParent(), api);
    }


    @Override
    public Tree visitCompilationUnit(CompilationUnitTree node, Queue<Tree> result) {

        return null;
    }

    @Override
    public Tree visitPackage(PackageTree node, Queue<Tree> result) {
        return null;
    }

    @Override
    public Tree visitImport(ImportTree node, Queue<Tree> result) {
        return null;
    }

    @Override
    public Tree visitClass(ClassTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitMethod(MethodTree node, Queue<Tree> result) {

        result.add(node);

        return delegateParent(node, result);
    }

    @Override
    public Tree visitVariable(VariableTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitEmptyStatement(EmptyStatementTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitBlock(BlockTree node, Queue<Tree> result) {

        result.add(node);

        return delegateParent(node, result);
    }

    @Override
    public Tree visitDoWhileLoop(DoWhileLoopTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitWhileLoop(WhileLoopTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitForLoop(ForLoopTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitEnhancedForLoop(EnhancedForLoopTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitLabeledStatement(LabeledStatementTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitSwitch(SwitchTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitSwitchExpression(SwitchExpressionTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitCase(CaseTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitSynchronized(SynchronizedTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitTry(TryTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitCatch(CatchTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitConditionalExpression(ConditionalExpressionTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitIf(IfTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitExpressionStatement(ExpressionStatementTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitBreak(BreakTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitContinue(ContinueTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitReturn(ReturnTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitThrow(ThrowTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitAssert(AssertTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitMethodInvocation(MethodInvocationTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitNewClass(NewClassTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitNewArray(NewArrayTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitLambdaExpression(LambdaExpressionTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitParenthesized(ParenthesizedTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitAssignment(AssignmentTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitCompoundAssignment(CompoundAssignmentTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitUnary(UnaryTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitBinary(BinaryTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitTypeCast(TypeCastTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitInstanceOf(InstanceOfTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitBindingPattern(BindingPatternTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitArrayAccess(ArrayAccessTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitMemberSelect(MemberSelectTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitMemberReference(MemberReferenceTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitIdentifier(IdentifierTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitLiteral(LiteralTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitPrimitiveType(PrimitiveTypeTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitArrayType(ArrayTypeTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitParameterizedType(ParameterizedTypeTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitUnionType(UnionTypeTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitIntersectionType(IntersectionTypeTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitTypeParameter(TypeParameterTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitWildcard(WildcardTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitModifiers(ModifiersTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitAnnotation(AnnotationTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitAnnotatedType(AnnotatedTypeTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitModule(ModuleTree node, Queue<Tree> result) {

        return null;
    }

    @Override
    public Tree visitExports(ExportsTree node, Queue<Tree> result) {

        return null;
    }

    @Override
    public Tree visitOpens(OpensTree node, Queue<Tree> result) {

        return null;
    }

    @Override
    public Tree visitProvides(ProvidesTree node, Queue<Tree> result) {

        return null;
    }

    @Override
    public Tree visitRequires(RequiresTree node, Queue<Tree> result) {

        return null;
    }

    @Override
    public Tree visitUses(UsesTree node, Queue<Tree> result) {

        return null;
    }

    @Override
    public Tree visitOther(Tree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitErroneous(ErroneousTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }

    @Override
    public Tree visitYield(YieldTree node, Queue<Tree> result) {

        return delegateParent(node, result);
    }
}
