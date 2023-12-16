package io.github.miracelwhipp.constness.plugin.utility;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import java.util.List;

public class LoadElementClass implements ElementVisitor<Class<?>, CompilerTaskContext> {

    private static final LoadElementClass INSTANCE = new LoadElementClass();

    private LoadElementClass() {
    }

    public static Class<?> load(Element element, CompilerTaskContext context) {

        return INSTANCE.visit(element, context);
    }

    @Override
    public Class<?> visit(Element e, CompilerTaskContext context) {

        return e.accept(this, context);
    }

    @Override
    public Class<?> visitPackage(PackageElement e, CompilerTaskContext context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitType(TypeElement e, CompilerTaskContext context) {

        try {

            return Class.forName(context.getElements().getBinaryName(e).toString());

        } catch (ClassNotFoundException ex) {

            throw new IllegalStateException(ex);
        }
    }

    @Override
    public Class<?> visitVariable(VariableElement variable, CompilerTaskContext context) {

        return LoadTypeClass.load(variable.asType(), context);
    }

    @Override
    public Class<?> visitExecutable(ExecutableElement e, CompilerTaskContext context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitTypeParameter(TypeParameterElement e, CompilerTaskContext context) {

        List<? extends TypeMirror> bounds = e.getBounds();

        if (bounds.isEmpty()) {

            return Object.class;
        }

        return LoadTypeClass.load(bounds.get(0), context);
    }

    @Override
    public Class<?> visitUnknown(Element e, CompilerTaskContext context) {

        throw new IllegalStateException();
    }
}
