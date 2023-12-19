package io.github.miracelwhipp.constness.plugin;

import com.sun.source.tree.*;
import com.sun.source.util.TreeScanner;
import io.github.miracelwhipp.constness.plugin.utility.*;

import javax.lang.model.element.*;

public class ConstnessEvaluationTreeScanner extends TreeScanner<Void, CompilerTaskContext> {

    public static final String KEYWORD_SUPER = "super";

    @Override
    public Void visitVariable(VariableTree node, CompilerTaskContext compilerTaskContext) {

        boolean isConst = Constness.markedAsConst(node, compilerTaskContext);

        if (node.getType() instanceof PrimitiveTypeTree) {

            if (isConst) {

                compilerTaskContext.reportError("cannot declare primitive type as const", node);
            }
        }

        if (!isConst && node.getInitializer() != null) {

            checkAssignmentToNonConst(node.getInitializer(), compilerTaskContext);
        }

        return super.visitVariable(node, compilerTaskContext);
    }

    public void checkAssignmentToNonConst(Tree assignment, CompilerTaskContext context) {

        if (Constness.isConstReference(assignment, context)) {

            context.reportError("cannot assign const value to non const value", assignment);
        }
    }


    @Override
    public Void visitAssignment(AssignmentTree node, CompilerTaskContext context) {

        Element assigneeElement = context.getElement(node.getVariable());

        if (node.getExpression() != null && !Constness.markedAsConst(assigneeElement, context)) {

            checkAssignmentToNonConst(node.getExpression(), context);
        }

        if (!ModifiabilityTreeScanner.isModifiable(node.getVariable(), context)) {

            context.reportError("cannot assign value to variable that is member of a const object", node);
        }

        return super.visitAssignment(node, context);
    }

    @Override
    public Void visitMethod(MethodTree node, CompilerTaskContext context) {

        Element element = context.getTrees().getElement(context.getPath(node));

        if (element.getKind() == ElementKind.CONSTRUCTOR) {

            if (context.isConst(node.getModifiers())) {

                context.reportError("cannot declare constructor as const", node);
            }
        }

        if (node.getModifiers().getFlags().contains(Modifier.STATIC)) {

            if (context.isConst(element)) {

                context.reportError("cannot declare static methods as const", node);
            }
        }

        return super.visitMethod(node, context);
    }

    @Override
    public Void visitMethodInvocation(MethodInvocationTree node, CompilerTaskContext context) {

        Element methodElement = context.getTrees().getElement(context.getPath(node.getMethodSelect()));

        if (!Constness.markedAsConst(methodElement, context) && methodElement.getKind() != ElementKind.CONSTRUCTOR) {

            Element containingElement = ContainingElementTreeScanner.getContainingMethod(node, context);

            if (containingElement != null && Constness.markedAsConst(containingElement, context)) {

                context.reportError("cannot invoke non-const method in const context", node);
            }

            if (Constness.isConstReference(node.getMethodSelect(), context)) {

                context.reportError("cannot invoke non-const method in const context", node);
            }
        }

        if (!(methodElement instanceof ExecutableElement executableElement)) {

            context.reportWarning("unable to load method " + methodElement.getEnclosingElement().getSimpleName() + "." + node.getMethodSelect().toString() + " constness cannot be checked", node.getMethodSelect());

            return super.visitMethodInvocation(node, context);
        }

        int parameterSize = executableElement.getParameters().size();

        for (int index = 0; index < node.getArguments().size(); index++) {

            ExpressionTree argument = node.getArguments().get(index);

            VariableElement parameter = parameterSize > index ? executableElement.getParameters().get(index) : executableElement.getParameters().get(parameterSize - 1);

            if (
                    !context.isConst(parameter) &&
                            context.isConst(argument)
            ) {

                context.reportError("cannot assign const value to non const parameter", node);
            }
        }

        return super.visitMethodInvocation(node, context);
    }

    public static void checkSatisfaction(CompilerTaskContext context) {

        context.getCompilationUnit().accept(new ConstnessEvaluationTreeScanner(), context);
    }

}
