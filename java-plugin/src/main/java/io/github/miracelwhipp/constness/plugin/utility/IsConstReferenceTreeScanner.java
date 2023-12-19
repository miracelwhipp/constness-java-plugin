package io.github.miracelwhipp.constness.plugin.utility;

import com.sun.source.tree.*;
import com.sun.source.util.TreeScanner;
import io.github.miracelwhipp.constness.annotation.MetaConst;
import io.github.miracelwhipp.constness.plugin.AnnotationElementInheritance;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import java.lang.reflect.Executable;

public final class IsConstReferenceTreeScanner extends BooleanTreeScanner<CompilerTaskContext> {

    private static final IsConstReferenceTreeScanner INSTANCE = new IsConstReferenceTreeScanner();

    private IsConstReferenceTreeScanner() {
    }


    public static boolean isConstReference(Tree node, CompilerTaskContext context) {

        return primitify(node.accept(INSTANCE, context));
    }

    @Override
    public Boolean visitMethodInvocation(MethodInvocationTree node, CompilerTaskContext context) {

        Element element = context.getElement(node);

        if (element.getKind() == ElementKind.CONSTRUCTOR) {

            return false;
        }

        if (element instanceof ExecutableElement executableElement) {

            MethodTree methodTree = context.getTrees().getTree(executableElement);

            if (methodTree == null) {

                // method is part of different compilation unit - load method by reflection
                return hasConstReturnType(executableElement, context);
            }

            return context.isConst(executableElement.getReturnType());
        }

        return false;
    }

    @Override
    public Boolean visitMemberSelect(MemberSelectTree node, CompilerTaskContext context) {

        if (Constness.markedAsConst(context.getElement(node), context)) {

            return true;
        }

        return node.getExpression().accept(this, context);
    }

    @Override
    public Boolean visitIdentifier(IdentifierTree node, CompilerTaskContext context) {

        return Constness.markedAsConst(context.getElement(node), context);
    }

    @Override
    public Boolean visitUnary(UnaryTree node, CompilerTaskContext context) {

        return false;
    }

    @Override
    public Boolean visitBinary(BinaryTree node, CompilerTaskContext context) {
        return false;
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


}
