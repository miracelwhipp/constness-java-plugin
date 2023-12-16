package io.github.miracelwhipp.constness.plugin.utility;

import javax.lang.model.type.*;

public class LoadTypeClass implements TypeVisitor<Class<?>, CompilerTaskContext> {

    private static final LoadTypeClass INSTANCE = new LoadTypeClass();

    private LoadTypeClass() {
    }

    public static Class<?> load(TypeMirror type, CompilerTaskContext context) {

        return INSTANCE.visit(type, context);
    }

    @Override
    public Class<?> visit(TypeMirror type, CompilerTaskContext context) {

        return type.accept(this, context);
    }

    @Override
    public Class<?> visitPrimitive(PrimitiveType primitiveType, CompilerTaskContext context) {

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
    public Class<?> visitNull(NullType t, CompilerTaskContext context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitArray(ArrayType arrayType, CompilerTaskContext context) {

        Class<?> result = arrayType.getComponentType().accept(this, context);

        return result.arrayType();
    }

    @Override
    public Class<?> visitDeclared(DeclaredType type, CompilerTaskContext context) {

        return LoadElementClass.load(type.asElement(), context);
    }

    @Override
    public Class<?> visitError(ErrorType t, CompilerTaskContext context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitTypeVariable(TypeVariable type, CompilerTaskContext context) {

        TypeMirror upperBound = type.getUpperBound();

        if (upperBound == null) {

            return Object.class;
        }

        return upperBound.accept(this, context);
    }

    @Override
    public Class<?> visitWildcard(WildcardType t, CompilerTaskContext context) {

        return Object.class;
    }

    @Override
    public Class<?> visitExecutable(ExecutableType t, CompilerTaskContext context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitNoType(NoType t, CompilerTaskContext context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitUnknown(TypeMirror t, CompilerTaskContext context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitUnion(UnionType t, CompilerTaskContext context) {

        throw new IllegalStateException();
    }

    @Override
    public Class<?> visitIntersection(IntersectionType t, CompilerTaskContext context) {

        throw new IllegalStateException();
    }
}
