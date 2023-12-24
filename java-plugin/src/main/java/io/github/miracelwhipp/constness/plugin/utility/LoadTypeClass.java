package io.github.miracelwhipp.constness.plugin.utility;

import io.github.miracelwhipp.constness.plugin.api.JavacApi;

import javax.lang.model.type.*;

public class LoadTypeClass implements TypeVisitor<Class<?>, JavacApi> {

    private static final LoadTypeClass INSTANCE = new LoadTypeClass();

    private LoadTypeClass() {
    }

    public static Class<?> load(TypeMirror type, JavacApi context) {

        return INSTANCE.visit(type, context);
    }

    @Override
    public Class<?> visit(TypeMirror type, JavacApi context) {

        return type.accept(this, context);
    }

    @Override
    public Class<?> visitPrimitive(PrimitiveType primitiveType, JavacApi context) {

        return switch (primitiveType.getKind()) {
            case BOOLEAN -> boolean.class;
            case CHAR -> char.class;
            case LONG -> long.class;
            case INT -> int.class;
            case SHORT -> short.class;
            case BYTE -> byte.class;
            case DOUBLE -> double.class;
            case FLOAT -> float.class;
            default -> throw new IllegalStateException();
        };
    }

    @Override
    public Class<?> visitNull(NullType t, JavacApi context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitArray(ArrayType arrayType, JavacApi context) {

        Class<?> result = arrayType.getComponentType().accept(this, context);

        return result.arrayType();
    }

    @Override
    public Class<?> visitDeclared(DeclaredType type, JavacApi context) {

        return LoadElementClass.load(type.asElement(), context);
    }

    @Override
    public Class<?> visitError(ErrorType t, JavacApi context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitTypeVariable(TypeVariable type, JavacApi context) {

        TypeMirror upperBound = type.getUpperBound();

        if (upperBound == null) {

            return Object.class;
        }

        return upperBound.accept(this, context);
    }

    @Override
    public Class<?> visitWildcard(WildcardType t, JavacApi context) {

        return Object.class;
    }

    @Override
    public Class<?> visitExecutable(ExecutableType t, JavacApi context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitNoType(NoType t, JavacApi context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitUnknown(TypeMirror t, JavacApi context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitUnion(UnionType t, JavacApi context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitIntersection(IntersectionType t, JavacApi context) {

        throw new IllegalStateException();
    }
}
