package io.github.miracelwhipp.constness.plugin.api;

import com.sun.source.tree.*;
import io.github.miracelwhipp.constness.plugin.utility.BooleanTreeScanner;
import io.github.miracelwhipp.constness.plugin.utility.LoadElementClass;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import java.lang.reflect.Method;

public final class RefersToConstContextTreeScanner extends BooleanTreeScanner<ConstnessMarkApi> {

    private static final RefersToConstContextTreeScanner INSTANCE = new RefersToConstContextTreeScanner();

    private RefersToConstContextTreeScanner() {
    }

    public static boolean refersToConstContext(Tree node, ConstnessMarkApi api) {

        return primitify(node.accept(INSTANCE, api));
    }

    @Override
    public Boolean visitMemberSelect(MemberSelectTree node, ConstnessMarkApi constnessApi) {

        if (constnessApi.markedAsConst(node)) {

            return true;
        }

        return node.getExpression().accept(this, constnessApi);
    }

    @Override
    public Boolean visitIdentifier(IdentifierTree node, ConstnessMarkApi constnessApi) {

        return constnessApi.markedAsConst(node);
    }

    @Override
    public Boolean visitMethod(MethodTree node, ConstnessMarkApi constnessApi) {

        return constnessApi.markedAsConst(node) || constnessApi.markedAsPreservesConst(node);
    }

    @Override
    public Boolean visitMethodInvocation(MethodInvocationTree node, ConstnessMarkApi constnessApi) {

        Element element = constnessApi.getJavacApi().getElement(node);

        if (element.getKind() == ElementKind.CONSTRUCTOR) {

            return false;
        }

        if (element instanceof ExecutableElement executableElement) {

            MethodTree methodTree = constnessApi.getJavacApi().trees().getTree(executableElement);

            if (methodTree == null) {

                // method is part of different compilation unit - load method by reflection
                // return type annotations seem not to be evaluated correctly otherwise
                if (hasConstReturnType(executableElement, constnessApi)) {

                    return true;
                }

            } else if (constnessApi.markedAsConst(executableElement.getReturnType())) {

                return true;
            }

            if (!constnessApi.markedAsPreservesConst(element)) {

                return false;
            }

            return node.getMethodSelect().accept(this, constnessApi);
        }

        return super.visitMethodInvocation(node, constnessApi);
    }

    private boolean hasConstReturnType(ExecutableElement executableElement, ConstnessMarkApi api) {

        String methodName = executableElement.getSimpleName().toString();

        Element enclosingElement = executableElement.getEnclosingElement();

        Class<?> clazz = LoadElementClass.load(enclosingElement, api.getJavacApi());

        Class<?>[] parameters = executableElement.getParameters().stream()
                .map(variableElement -> LoadElementClass.load(variableElement, api.getJavacApi()))
                .toArray(Class<?>[]::new);

        try {

            Method executable = clazz.getMethod(methodName, parameters);

            return api.markedAsConst(executable.getAnnotatedReturnType());

        } catch (NoSuchMethodException e) {

            throw new IllegalStateException(e);
        }
    }

    public Boolean isParentConst(Tree tree, ConstnessMarkApi api) {

        Tree parent = api.getJavacApi().getParent(tree);

        if (parent == null) {

            return false;
        }

        return parent.accept(this, api);
    }

    @Override
    public Boolean visitVariable(VariableTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitEmptyStatement(EmptyStatementTree node, ConstnessMarkApi constnessApi) {

        return false;
    }

    @Override
    public Boolean visitBlock(BlockTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitDoWhileLoop(DoWhileLoopTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitWhileLoop(WhileLoopTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitForLoop(ForLoopTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitEnhancedForLoop(EnhancedForLoopTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitLabeledStatement(LabeledStatementTree node, ConstnessMarkApi constnessApi) {

        return false;
    }

    @Override
    public Boolean visitSwitch(SwitchTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitSwitchExpression(SwitchExpressionTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitCase(CaseTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitSynchronized(SynchronizedTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitTry(TryTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitCatch(CatchTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitConditionalExpression(ConditionalExpressionTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitIf(IfTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitExpressionStatement(ExpressionStatementTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitBreak(BreakTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitContinue(ContinueTree node, ConstnessMarkApi constnessApi) {

        return false;
    }

    @Override
    public Boolean visitReturn(ReturnTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitThrow(ThrowTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitLambdaExpression(LambdaExpressionTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitParenthesized(ParenthesizedTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitAssignment(AssignmentTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitCompoundAssignment(CompoundAssignmentTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitUnary(UnaryTree node, ConstnessMarkApi constnessApi) {

        return false;
    }

    @Override
    public Boolean visitBinary(BinaryTree node, ConstnessMarkApi constnessApi) {

        return false;
    }

    @Override
    public Boolean visitTypeCast(TypeCastTree node, ConstnessMarkApi constnessApi) {

        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitInstanceOf(InstanceOfTree node, ConstnessMarkApi constnessApi) {

        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitArrayAccess(ArrayAccessTree node, ConstnessMarkApi constnessApi) {

        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitMemberReference(MemberReferenceTree node, ConstnessMarkApi constnessApi) {

        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitLiteral(LiteralTree node, ConstnessMarkApi constnessApi) {

        return false;
    }

    @Override
    public Boolean visitPrimitiveType(PrimitiveTypeTree node, ConstnessMarkApi constnessApi) {

        return false;
    }

    @Override
    public Boolean visitArrayType(ArrayTypeTree node, ConstnessMarkApi constnessApi) {

        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitParameterizedType(ParameterizedTypeTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitUnionType(UnionTypeTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitIntersectionType(IntersectionTypeTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitTypeParameter(TypeParameterTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitWildcard(WildcardTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitModifiers(ModifiersTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitAnnotation(AnnotationTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitAnnotatedType(AnnotatedTypeTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitYield(YieldTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitOther(Tree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitErroneous(ErroneousTree node, ConstnessMarkApi constnessApi) {
        return isParentConst(node, constnessApi);
    }

    @Override
    public Boolean visitModule(ModuleTree node, ConstnessMarkApi constnessApi) {
        return false;
    }

    @Override
    public Boolean visitExports(ExportsTree node, ConstnessMarkApi constnessApi) {
        return false;
    }

    @Override
    public Boolean visitOpens(OpensTree node, ConstnessMarkApi constnessApi) {
        return false;
    }

    @Override
    public Boolean visitProvides(ProvidesTree node, ConstnessMarkApi constnessApi) {
        return false;
    }

    @Override
    public Boolean visitRequires(RequiresTree node, ConstnessMarkApi constnessApi) {
        return false;
    }

    @Override
    public Boolean visitUses(UsesTree node, ConstnessMarkApi constnessApi) {
        return false;
    }


    @Override
    public Boolean visitCompilationUnit(CompilationUnitTree node, ConstnessMarkApi constnessApi) {

        return false;
    }

    @Override
    public Boolean visitPackage(PackageTree node, ConstnessMarkApi constnessApi) {

        return false;
    }

    @Override
    public Boolean visitImport(ImportTree node, ConstnessMarkApi constnessApi) {
        return false;
    }

    @Override
    public Boolean visitClass(ClassTree node, ConstnessMarkApi constnessApi) {

        return false;
    }

    @Override
    public Boolean visitAssert(AssertTree node, ConstnessMarkApi constnessApi) {
        return false;
    }

    @Override
    public Boolean visitNewClass(NewClassTree node, ConstnessMarkApi constnessApi) {
        return false;
    }

    @Override
    public Boolean visitNewArray(NewArrayTree node, ConstnessMarkApi constnessApi) {
        return false;
    }
}
