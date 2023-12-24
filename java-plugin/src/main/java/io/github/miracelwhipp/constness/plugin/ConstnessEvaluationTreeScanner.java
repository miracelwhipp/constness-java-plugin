package io.github.miracelwhipp.constness.plugin;

import com.sun.source.tree.*;
import com.sun.source.util.TreeScanner;
import io.github.miracelwhipp.constness.plugin.api.ConstnessApi;
import io.github.miracelwhipp.constness.plugin.api.ContainingElementTreeScanner;
import io.github.miracelwhipp.constness.plugin.utility.*;

import javax.lang.model.element.*;

public class ConstnessEvaluationTreeScanner extends TreeScanner<Void, ConstnessApi> {

    public static final String KEYWORD_SUPER = "super";

    @Override
    public Void visitVariable(VariableTree node, ConstnessApi constnessApi) {

        boolean isConst = constnessApi.marks().markedAsConst(node);

        if (node.getType() instanceof PrimitiveTypeTree) {

            if (isConst) {

                constnessApi.getJavacApi().reportError("cannot declare primitive type as const", node);
            }
        }

        if (!isConst && node.getInitializer() != null) {

            checkAssignmentToNonConst(node.getInitializer(), constnessApi);
        }

        return super.visitVariable(node, constnessApi);
    }

    public void checkAssignmentToNonConst(Tree assignment, ConstnessApi api) {

        if (api.context().isConstInContext(assignment)) {

            api.getJavacApi().reportError("cannot assign const value to non const value", assignment);
        }
    }


    @Override
    public Void visitAssignment(AssignmentTree node, ConstnessApi api) {

        if (node.getExpression() != null && !api.marks().markedAsConst(node.getVariable())) {

            checkAssignmentToNonConst(node.getExpression(), api);
        }

        if (api.context().isImmutableInContext(node.getVariable())) {

            api.getJavacApi().reportError("cannot assign value to variable that is member of a const object", node);
        }

        return super.visitAssignment(node, api);
    }

    @Override
    public Void visitMethod(MethodTree node, ConstnessApi api) {

        Element element = api.getJavacApi().getElement(node);

        if (element.getKind() == ElementKind.CONSTRUCTOR) {

            if (api.marks().markedAsConst(node.getModifiers())) {

                api.getJavacApi().reportError("cannot declare constructor as const", node);
            }
        }

        if (node.getModifiers().getFlags().contains(Modifier.STATIC)) {

            if (api.marks().markedAsConst(element)) {

                api.getJavacApi().reportError("cannot declare static methods as const", node);
            }
        }

        return super.visitMethod(node, api);
    }

    @Override
    public Void visitMethodInvocation(MethodInvocationTree node, ConstnessApi api) {

        Element methodElement = api.getJavacApi().getElement(node.getMethodSelect());

        if (
                !api.marks().markedAsConst(methodElement) &&
                        methodElement.getKind() != ElementKind.CONSTRUCTOR &&
                        !methodElement.getModifiers().contains(Modifier.STATIC)
        ) {

            if (api.context().isNonConstInvocation(node)) {

                api.getJavacApi().reportError("cannot invoke non-const method in const context", node);
            }
        }

        if (!(methodElement instanceof ExecutableElement executableElement)) {

            api.getJavacApi().reportWarning("unable to load method " + methodElement.getEnclosingElement().getSimpleName() + "." + node.getMethodSelect().toString() + " constness cannot be checked", node.getMethodSelect());

            return super.visitMethodInvocation(node, api);
        }

        int parameterSize = executableElement.getParameters().size();

        for (int index = 0; index < node.getArguments().size(); index++) {

            ExpressionTree argument = node.getArguments().get(index);

            VariableElement parameter = parameterSize > index ? executableElement.getParameters().get(index) : executableElement.getParameters().get(parameterSize - 1);

            if (
                    !api.marks().markedAsConst(parameter) &&
                            api.marks().markedAsConst(argument)
            ) {

                api.getJavacApi().reportError("cannot assign const value to non const parameter", node);
            }
        }

        return super.visitMethodInvocation(node, api);
    }

    public static void checkSatisfaction(ConstnessApi api) {

        api.getJavacApi().compilationUnit().accept(new ConstnessEvaluationTreeScanner(), api);
    }

}
