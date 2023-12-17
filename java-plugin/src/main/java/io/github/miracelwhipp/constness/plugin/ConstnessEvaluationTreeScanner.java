package io.github.miracelwhipp.constness.plugin;

import com.sun.source.tree.*;
import com.sun.source.util.TreeScanner;
import io.github.miracelwhipp.constness.annotation.MetaConst;
import io.github.miracelwhipp.constness.plugin.utility.CompilerTaskContext;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;

public class ConstnessEvaluationTreeScanner extends TreeScanner<Void, CompilerTaskContext> {

    public static final String KEYWORD_SUPER = "super";

    @Override
    public Void visitVariable(VariableTree node, CompilerTaskContext compilerTaskContext) {

        boolean isConst = compilerTaskContext.isConst(node);

        if (node.getType() instanceof PrimitiveTypeTree) {

            if (isConst) {

                compilerTaskContext.reportError("cannot declare primitive type as const", node);
            }
        }

        if (!isConst && node.getInitializer() != null) {

            if (compilerTaskContext.isConst(node.getInitializer())) {

                compilerTaskContext.reportError("cannot assign const value to non const value", node.getInitializer());
            }
        }

        return super.visitVariable(node, compilerTaskContext);
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
    public Void visitAssignment(AssignmentTree node, CompilerTaskContext context) {

        Element assigneeElement = context.getTrees().getElement(context.getPath(node.getVariable()));

        Element assignmentElement = context.getTrees().getElement(context.getPath(node.getExpression()));

        if (assignmentElement != null && !context.isConst(assigneeElement) && context.isConst(assignmentElement)) {

            context.reportError("cannot assign const value to non const value", node);
        }

        if (ConstInCurrentContextTreeScanner.isConstInContext(node.getVariable(), context)) {

            context.reportError("cannot assign value to variable that is member of a const object", node);
        }

        return super.visitAssignment(node, context);
    }

    @Override
    public Void visitMethodInvocation(MethodInvocationTree node, CompilerTaskContext context) {

        if (node.getMethodSelect().toString().equals(KEYWORD_SUPER)) {

            return null;
        }

        Element methodElement = context.getTrees().getElement(context.getPath(node.getMethodSelect()));

        if (
                ConstInCurrentContextTreeScanner.isConstInContext(node.getMethodSelect(), context) &&
                        !context.isConst(methodElement)
        ) {

            context.reportError("cannot invoke non-const method in const context", node);
        }

        if (!(methodElement instanceof ExecutableElement executableElement)) {

            context.reportWarning("unable to load method " + methodElement.getEnclosingElement().getSimpleName() + "." + node.getMethodSelect().toString() + " constness cannot be checked", node.getMethodSelect());
            return null;
        }

        for (int index = 0; index < node.getArguments().size(); index++) {

            ExpressionTree argument = node.getArguments().get(index);

            if (
                    !context.isConst(executableElement.getParameters().get(index)) &&
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
