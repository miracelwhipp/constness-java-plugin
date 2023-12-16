package io.github.miracelwhipp.constness.plugin.utility;

import com.sun.source.tree.*;
import com.sun.source.util.TreePathScanner;

public abstract class IllegalTreePathScanner<ReturnType, ParameterType> extends TreePathScanner<ReturnType, ParameterType> {

    @Override
    public ReturnType visitCompilationUnit(CompilationUnitTree node, ParameterType parameterType) {

        throw new IllegalStateException();
    }

    @Override
    public ReturnType visitPackage(PackageTree node, ParameterType parameterType) {

        throw new IllegalStateException();
    }

    @Override
    public ReturnType visitImport(ImportTree node, ParameterType parameterType) {

        throw new IllegalStateException();
    }

    @Override
    public ReturnType visitClass(ClassTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitMethod(MethodTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitVariable(VariableTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitEmptyStatement(EmptyStatementTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitBlock(BlockTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitDoWhileLoop(DoWhileLoopTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitWhileLoop(WhileLoopTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitForLoop(ForLoopTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitEnhancedForLoop(EnhancedForLoopTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitLabeledStatement(LabeledStatementTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitSwitch(SwitchTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitSwitchExpression(SwitchExpressionTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitCase(CaseTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitSynchronized(SynchronizedTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitTry(TryTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitCatch(CatchTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitConditionalExpression(ConditionalExpressionTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitIf(IfTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitExpressionStatement(ExpressionStatementTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitBreak(BreakTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitContinue(ContinueTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitReturn(ReturnTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitThrow(ThrowTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitAssert(AssertTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitMethodInvocation(MethodInvocationTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitNewClass(NewClassTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitNewArray(NewArrayTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitLambdaExpression(LambdaExpressionTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitParenthesized(ParenthesizedTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitAssignment(AssignmentTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitCompoundAssignment(CompoundAssignmentTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitUnary(UnaryTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitBinary(BinaryTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitTypeCast(TypeCastTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitInstanceOf(InstanceOfTree node, ParameterType parameterType) {

        throw new IllegalStateException();
    }

    @Override
    public ReturnType visitDefaultCaseLabel(DefaultCaseLabelTree node, ParameterType parameterType) {

        throw new IllegalStateException();
    }

    @Override
    public ReturnType visitConstantCaseLabel(ConstantCaseLabelTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitPatternCaseLabel(PatternCaseLabelTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitDeconstructionPattern(DeconstructionPatternTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitArrayAccess(ArrayAccessTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitMemberSelect(MemberSelectTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitMemberReference(MemberReferenceTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitIdentifier(IdentifierTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitLiteral(LiteralTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitPrimitiveType(PrimitiveTypeTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitArrayType(ArrayTypeTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitParameterizedType(ParameterizedTypeTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitUnionType(UnionTypeTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitIntersectionType(IntersectionTypeTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitTypeParameter(TypeParameterTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitWildcard(WildcardTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitModifiers(ModifiersTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitAnnotation(AnnotationTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitAnnotatedType(AnnotatedTypeTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitModule(ModuleTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitExports(ExportsTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitOpens(OpensTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitProvides(ProvidesTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitRequires(RequiresTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitUses(UsesTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitOther(Tree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitErroneous(ErroneousTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }

    @Override
    public ReturnType visitYield(YieldTree node, ParameterType parameterType) {

        throw new IllegalStateException();

    }
}
