package io.github.miracelwhipp.constness.plugin.utility;

import io.github.miracelwhipp.constness.plugin.api.JavacApi;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import java.util.List;

public class LoadElementClass implements ElementVisitor<Class<?>, JavacApi> {

    private static final LoadElementClass INSTANCE = new LoadElementClass();

    private LoadElementClass() {
    }

    public static Class<?> load(Element element, JavacApi context) {

        return INSTANCE.visit(element, context);
    }

    @Override
    public Class<?> visit(Element e, JavacApi context) {

        return e.accept(this, context);
    }

    @Override
    public Class<?> visitPackage(PackageElement e, JavacApi context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitType(TypeElement e, JavacApi context) {

        try {

            return Class.forName(context.elements().getBinaryName(e).toString());

        } catch (ClassNotFoundException ex) {

            throw new IllegalStateException(ex);
        }
    }

    @Override
    public Class<?> visitVariable(VariableElement variable, JavacApi context) {

        return LoadTypeClass.load(variable.asType(), context);
    }

    @Override
    public Class<?> visitExecutable(ExecutableElement e, JavacApi context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitTypeParameter(TypeParameterElement e, JavacApi context) {

        List<? extends TypeMirror> bounds = e.getBounds();

        if (bounds.isEmpty()) {

            return Object.class;
        }

        return LoadTypeClass.load(bounds.get(0), context);
    }

    @Override
    public Class<?> visitUnknown(Element e, JavacApi context) {

        throw new IllegalStateException();
    }
}
