package io.github.miracelwhipp.constness.plugin.api;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

public record JavacApi(
        CompilationUnitTree compilationUnit,
        Trees trees,
        Elements elements,
        Types types
) {

    public static JavacApi fromTask(JavacTask task, TaskEvent event) {

        return new JavacApi(event.getCompilationUnit(), Trees.instance(task), task.getElements(), task.getTypes());
    }

    public TreePath getPath(Tree node) {

        return trees().getPath(compilationUnit(), node);
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

    public void reportError(String message, Tree tree) {

        trees().printMessage(Diagnostic.Kind.ERROR, message, tree, compilationUnit());
    }

    public void reportWarning(String message, Tree tree) {

        trees().printMessage(Diagnostic.Kind.MANDATORY_WARNING, message, tree, compilationUnit());
    }

    public Element getElement(Tree element) {

        return trees().getElement(getPath(element));
    }



}
