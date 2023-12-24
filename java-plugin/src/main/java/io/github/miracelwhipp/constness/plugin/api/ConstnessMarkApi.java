package io.github.miracelwhipp.constness.plugin.api;

import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ModifiersTree;
import com.sun.source.tree.Tree;
import io.github.miracelwhipp.constness.annotation.MetaConst;
import io.github.miracelwhipp.constness.plugin.utility.AnnotationElementInheritance;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import java.lang.reflect.AnnotatedElement;

public class ConstnessMarkApi {

    private final JavacApi javacApi;

    public ConstnessMarkApi(JavacApi javacApi) {
        this.javacApi = javacApi;
    }

    public JavacApi getJavacApi() {
        return javacApi;
    }

    public boolean markedAsConst(Element element) {

        return AnnotationElementInheritance.getAnnotation(javacApi.elements(), MetaConst.class, element) != null;
    }

    public boolean markedAsConst(TypeMirror type) {

        return AnnotationElementInheritance.getAnnotation(javacApi.elements(), MetaConst.class, type) != null;
    }

    public boolean markedAsConst(AnnotatedElement annotatedElement) {

        return AnnotationElementInheritance.getAnnotation(MetaConst.class, annotatedElement) != null;
    }

    public boolean markedAsConst(ModifiersTree modifiers) {

        for (AnnotationTree annotation : modifiers.getAnnotations()) {

            if (markedAsConst(annotation.getAnnotationType())) {

                return true;
            }
        }

        return false;
    }

    public boolean markedAsConst(Tree tree) {

        Element element = javacApi.getElement(tree);

        if (element == null) {

            return false;
        }

        return markedAsConst(element);
    }
}
