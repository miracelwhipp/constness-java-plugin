package io.github.miracelwhipp.constness.plugin.utility;

import com.sun.source.tree.*;
import io.github.miracelwhipp.constness.annotation.MetaConst;
import io.github.miracelwhipp.constness.plugin.AnnotationElementInheritance;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import java.lang.reflect.Executable;

public final class RefersToConstContextTreeScanner extends BooleanTreeScanner<CompilerTaskContext> {

    private static final RefersToConstContextTreeScanner INSTANCE = new RefersToConstContextTreeScanner();

    private RefersToConstContextTreeScanner() {
    }

    public static boolean refersToConstContext(Tree node, CompilerTaskContext context) {

        return primitify(node.accept(INSTANCE, context));
    }

    @Override
    public Boolean visitMemberSelect(MemberSelectTree node, CompilerTaskContext compilerTaskContext) {


        if (node.getExpression().accept(this, compilerTaskContext)) {

            return true;
        }

        return compilerTaskContext.isConst(node);
    }

    @Override
    public Boolean visitIdentifier(IdentifierTree node, CompilerTaskContext compilerTaskContext) {

        return compilerTaskContext.isConst(node);
    }

    @Override
    public Boolean visitMethod(MethodTree node, CompilerTaskContext compilerTaskContext) {

        return compilerTaskContext.isConst(node);
    }

    @Override
    public Boolean visitMethodInvocation(MethodInvocationTree node, CompilerTaskContext compilerTaskContext) {

        Element element = compilerTaskContext.getElement(node);

        if (element.getKind() == ElementKind.CONSTRUCTOR) {

            return false;
        }

        if (element instanceof ExecutableElement executableElement) {

            MethodTree methodTree = compilerTaskContext.getTrees().getTree(executableElement);

            if (methodTree == null) {

                // method is part of different compilation unit - load method by reflection
                return hasConstReturnType(executableElement, compilerTaskContext);
            }

            return compilerTaskContext.isConst(executableElement.getReturnType());
        }

        return super.visitMethodInvocation(node, compilerTaskContext);
    }

    private boolean hasConstReturnType(ExecutableElement executableElement, CompilerTaskContext context) {

        Element enclosingElement = executableElement.getEnclosingElement();

        Class<?> clazz = LoadElementClass.load(enclosingElement, context);

        Class<?>[] parameters = executableElement.getParameters().stream()
                .map(variableElement -> LoadElementClass.load(variableElement, context))
                .toArray(Class<?>[]::new);

        try {

            String methodName = executableElement.getSimpleName().toString();

            if (methodName.equals("<init>")) {

                return false;
            }

            Executable executable = clazz.getMethod(methodName, parameters);

            return AnnotationElementInheritance.getAnnotation(MetaConst.class, executable.getAnnotatedReturnType()) != null;

        } catch (NoSuchMethodException e) {

            throw new IllegalStateException(e);
        }
    }

    public Boolean isParentConst(Tree tree, CompilerTaskContext context) {

        Tree parent = context.getParent(tree);

        if (parent == null) {

            return false;
        }

        return parent.accept(this, context);
    }

    @Override
    public Boolean visitVariable(VariableTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitEmptyStatement(EmptyStatementTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitBlock(BlockTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitDoWhileLoop(DoWhileLoopTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitWhileLoop(WhileLoopTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitForLoop(ForLoopTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitEnhancedForLoop(EnhancedForLoopTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitLabeledStatement(LabeledStatementTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitSwitch(SwitchTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitSwitchExpression(SwitchExpressionTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitCase(CaseTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitSynchronized(SynchronizedTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitTry(TryTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitCatch(CatchTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitConditionalExpression(ConditionalExpressionTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitIf(IfTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitExpressionStatement(ExpressionStatementTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitBreak(BreakTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitContinue(ContinueTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitReturn(ReturnTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitThrow(ThrowTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitLambdaExpression(LambdaExpressionTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitParenthesized(ParenthesizedTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitAssignment(AssignmentTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitCompoundAssignment(CompoundAssignmentTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitUnary(UnaryTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitBinary(BinaryTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitTypeCast(TypeCastTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitInstanceOf(InstanceOfTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitArrayAccess(ArrayAccessTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitMemberReference(MemberReferenceTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitLiteral(LiteralTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitPrimitiveType(PrimitiveTypeTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitArrayType(ArrayTypeTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitParameterizedType(ParameterizedTypeTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitUnionType(UnionTypeTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitIntersectionType(IntersectionTypeTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitTypeParameter(TypeParameterTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitWildcard(WildcardTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitModifiers(ModifiersTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitAnnotation(AnnotationTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitAnnotatedType(AnnotatedTypeTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitYield(YieldTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitOther(Tree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitErroneous(ErroneousTree node, CompilerTaskContext compilerTaskContext) {
        return isParentConst(node, compilerTaskContext);
    }

    @Override
    public Boolean visitModule(ModuleTree node, CompilerTaskContext compilerTaskContext) {
        return false;
    }

    @Override
    public Boolean visitExports(ExportsTree node, CompilerTaskContext compilerTaskContext) {
        return false;
    }

    @Override
    public Boolean visitOpens(OpensTree node, CompilerTaskContext compilerTaskContext) {
        return false;
    }

    @Override
    public Boolean visitProvides(ProvidesTree node, CompilerTaskContext compilerTaskContext) {
        return false;
    }

    @Override
    public Boolean visitRequires(RequiresTree node, CompilerTaskContext compilerTaskContext) {
        return false;
    }

    @Override
    public Boolean visitUses(UsesTree node, CompilerTaskContext compilerTaskContext) {
        return false;
    }


    @Override
    public Boolean visitCompilationUnit(CompilationUnitTree node, CompilerTaskContext compilerTaskContext) {

        return false;
    }

    @Override
    public Boolean visitPackage(PackageTree node, CompilerTaskContext compilerTaskContext) {

        return false;
    }

    @Override
    public Boolean visitImport(ImportTree node, CompilerTaskContext compilerTaskContext) {
        return false;
    }

    @Override
    public Boolean visitClass(ClassTree node, CompilerTaskContext compilerTaskContext) {

        return false;
    }

    @Override
    public Boolean visitAssert(AssertTree node, CompilerTaskContext compilerTaskContext) {
        return false;
    }

    @Override
    public Boolean visitNewClass(NewClassTree node, CompilerTaskContext compilerTaskContext) {
        return false;
    }

    @Override
    public Boolean visitNewArray(NewArrayTree node, CompilerTaskContext compilerTaskContext) {
        return false;
    }
}
