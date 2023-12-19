package io.github.miracelwhipp.constness.plugin.utility;

import com.sun.source.tree.*;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import io.github.miracelwhipp.constness.annotation.MetaConst;
import io.github.miracelwhipp.constness.plugin.AnnotationElementInheritance;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

public class CompilerTaskContext {

    private final CompilationUnitTree compilationUnit;
    private final Trees trees;

    private final Elements elements;

    private final Types types;

    public CompilerTaskContext(CompilationUnitTree compilationUnit, Trees trees, Elements elements, Types types) {
        this.compilationUnit = compilationUnit;
        this.trees = trees;
        this.elements = elements;
        this.types = types;
    }

    public static CompilerTaskContext fromTask(JavacTask task, TaskEvent event) {

        return new CompilerTaskContext(event.getCompilationUnit(), Trees.instance(task), task.getElements(), task.getTypes());
    }

    public Trees getTrees() {
        return trees;
    }

    public CompilationUnitTree getCompilationUnit() {
        return compilationUnit;
    }

    public TreePath getPath(Tree node) {

        return trees.getPath(compilationUnit, node);
    }

    public Tree getParent(Tree node) {

        TreePath path = getPath(node);

        if (path == null) {

            return null;
        }

        TreePath parentPath = path.getParentPath();

        if (parentPath == null) {

            return null;
        }

        return parentPath.getLeaf();
    }

    public Elements getElements() {
        return elements;
    }

    public void reportError(String message, Tree tree) {

        trees.printMessage(Diagnostic.Kind.ERROR, message, tree, compilationUnit);
    }

    public void reportWarning(String message, Tree tree) {

        trees.printMessage(Diagnostic.Kind.MANDATORY_WARNING, message, tree, compilationUnit);
    }

    public boolean isConst(ModifiersTree modifiers) {

        for (AnnotationTree annotation : modifiers.getAnnotations()) {

            if (isConst(annotation.getAnnotationType())) {

                return true;
            }
        }

        return false;
    }

    public boolean isConst(Tree tree) {

        Element element = getElement(tree);

        if (element == null) {

            return false;
        }

//        if (element.getSimpleName().toString().equals("this")) {
//
//            Element containingElement = ContainingMethodTreeScanner.getContainingMethod(tree, this);
//
//            return isConst(containingElement);
//        }

        return isConst(element);
    }

    public Element getElement(Tree element) {

        return trees.getElement(getPath(element));
    }

    public Types getTypes() {
        return types;
    }

    public boolean isConst(Element element) {

        // TODO: check for this!


        return AnnotationElementInheritance.getAnnotation(elements, MetaConst.class, element) != null;
    }

    public Boolean isConst(TypeMirror type) {
        return AnnotationElementInheritance.getAnnotation(elements, MetaConst.class, type) != null;
    }
}
