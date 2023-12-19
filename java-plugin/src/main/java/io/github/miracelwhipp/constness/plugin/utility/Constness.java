package io.github.miracelwhipp.constness.plugin.utility;

import com.sun.source.tree.Tree;
import io.github.miracelwhipp.constness.annotation.MetaConst;
import io.github.miracelwhipp.constness.plugin.AnnotationElementInheritance;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

public final class Constness {

    private Constness() {
    }

    public static boolean markedAsConst(Tree tree, CompilerTaskContext context) {

        Element element = context.getElement(tree);

        if (element == null) {

            return false;
        }

        return markedAsConst(element, context);
    }

    public static boolean markedAsConst(Element element, CompilerTaskContext context) {

        return AnnotationElementInheritance.getAnnotation(context.getElements(), MetaConst.class, element) != null;
    }

    public static boolean markedAsConst(TypeMirror type, CompilerTaskContext context) {

        return AnnotationElementInheritance.getAnnotation(context.getElements(), MetaConst.class, type) != null;
    }

    public static boolean isConstReference(Tree tree, CompilerTaskContext context) {

        return IsConstReferenceTreeScanner.isConstReference(tree, context);
    }


}
