package io.github.miracelwhipp.constness.plugin.api;

import com.sun.source.util.JavacTask;
import com.sun.source.util.TaskEvent;

public class ConstnessApi {

    private final JavacApi javacApi;

    public ConstnessApi(JavacApi javacApi) {
        this.javacApi = javacApi;
    }


    public static ConstnessApi fromTask(JavacTask task, TaskEvent event) {

        return new ConstnessApi(JavacApi.fromTask(task, event));
    }

    public JavacApi getJavacApi() {
        return javacApi;
    }


//
//    public CompilationUnitTree getCompilationUnit() {
//        return javacApi.compilationUnit();
//    }
//
//
//    public Trees getTrees() {
//        return javacApi.trees();
//    }
//
//    public TreePath getPath(Tree node) {
//
//        return javacApi.trees().getPath(javacApi.compilationUnit(), node);
//    }
//
//    public Tree getParent(Tree node) {
//
//        TreePath path = getPath(node);
//
//        if (path == null) {
//
//            return null;
//        }
//
//        TreePath parentPath = path.getParentPath();
//
//        if (parentPath == null) {
//
//            return null;
//        }
//
//        return parentPath.getLeaf();
//    }
//
//    public Elements getElements() {
//        return javacApi.elements();
//    }
//
//    public void reportError(String message, Tree tree) {
//
//        javacApi.trees().printMessage(Diagnostic.Kind.ERROR, message, tree, javacApi.compilationUnit());
//    }
//
//    public void reportWarning(String message, Tree tree) {
//
//        javacApi.trees().printMessage(Diagnostic.Kind.MANDATORY_WARNING, message, tree, javacApi.compilationUnit());
//    }
//
//    public boolean isConst(ModifiersTree modifiers) {
//
//        for (AnnotationTree annotation : modifiers.getAnnotations()) {
//
//            if (isConst(annotation.getAnnotationType())) {
//
//                return true;
//            }
//        }
//
//        return false;
//    }

//    public boolean isConst(Tree tree) {
//
//        Element element = getElement(tree);
//
//        if (element == null) {
//
//            return false;
//        }
//
////        if (element.getSimpleName().toString().equals("this")) {
////
////            Element containingElement = ContainingMethodTreeScanner.getContainingMethod(tree, this);
////
////            return isConst(containingElement);
////        }
//
//        return isConst(element);
//    }

//    public Element getElement(Tree element) {
//
//        return javacApi.trees().getElement(getPath(element));
//    }
//
//    public Types getTypes() {
//        return javacApi.types();
//    }
//
//    public boolean isConst(Element element) {
//
//        // TODO: check for this!
//
//
//        return AnnotationElementInheritance.getAnnotation(javacApi.elements(), MetaConst.class, element) != null;
//    }
//
//    public Boolean isConst(TypeMirror type) {
//        return AnnotationElementInheritance.getAnnotation(javacApi.elements(), MetaConst.class, type) != null;
//    }



    public ConstnessMarkApi marks() {

        return new ConstnessMarkApi(javacApi);
    }

    public ConstnessContextApi context() {

        return new ConstnessContextApi(marks());
    }
}
