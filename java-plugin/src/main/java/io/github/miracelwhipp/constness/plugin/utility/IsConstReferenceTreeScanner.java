package io.github.miracelwhipp.constness.plugin.utility;

import io.github.miracelwhipp.constness.plugin.api.ConstnessMarkApi;

public final class IsConstReferenceTreeScanner extends BooleanTreeScanner<ConstnessMarkApi> {

//    private static final IsConstReferenceTreeScanner INSTANCE = new IsConstReferenceTreeScanner();
//
//    private IsConstReferenceTreeScanner() {
//    }
//
//
//    public static boolean isConstReference(Tree node, ConstnessMarkApi context) {
//
//        return primitify(node.accept(INSTANCE, context));
//    }
//
//    @Override
//    public Boolean visitMethodInvocation(MethodInvocationTree node, ConstnessMarkApi api) {
//
//        Element element = api.getJavacApi().getElement(node);
//
//        if (element.getKind() == ElementKind.CONSTRUCTOR) {
//
//            return false;
//        }
//
//        if (element instanceof ExecutableElement executableElement) {
//
//            MethodTree methodTree = api.getJavacApi().trees().getTree(executableElement);
//
//            if (methodTree == null) {
//
//                // method is part of different compilation unit - load method by reflection
//                return hasConstReturnType(executableElement, api);
//            }
//
//            return api.markedAsConst(executableElement.getReturnType());
//        }
//
//        return false;
//    }
//
//    @Override
//    public Boolean visitMemberSelect(MemberSelectTree node, ConstnessMarkApi api) {
//
//        if (api.markedAsConst(node)) {
//
//            return true;
//        }
//
//        return node.getExpression().accept(this, api);
//    }
//
//    @Override
//    public Boolean visitIdentifier(IdentifierTree node, ConstnessMarkApi api) {
//
//        return api.markedAsConst(node);
//    }
//
//    @Override
//    public Boolean visitUnary(UnaryTree node, ConstnessMarkApi context) {
//
//        return false;
//    }
//
//    @Override
//    public Boolean visitBinary(BinaryTree node, ConstnessMarkApi context) {
//        return false;
//    }
//
//    private boolean hasConstReturnType(ExecutableElement executableElement, ConstnessMarkApi context) {
//
//        Element enclosingElement = executableElement.getEnclosingElement();
//
//        Class<?> clazz = LoadElementClass.load(enclosingElement, context);
//
//        Class<?>[] parameters = executableElement.getParameters().stream()
//                .map(variableElement -> LoadElementClass.load(variableElement, context))
//                .toArray(Class<?>[]::new);
//
//        try {
//
//            String methodName = executableElement.getSimpleName().toString();
//
//            if (methodName.equals("<init>")) {
//
//                return false;
//            }
//
//            Executable executable = clazz.getMethod(methodName, parameters);
//
//            return AnnotationElementInheritance.getAnnotation(MetaConst.class, executable.getAnnotatedReturnType()) != null;
//
//        } catch (NoSuchMethodException e) {
//
//            throw new IllegalStateException(e);
//        }
//    }
//

}
