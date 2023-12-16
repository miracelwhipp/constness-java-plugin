package io.github.miracelwhipp.constness.plugin;

import com.sun.source.tree.MethodTree;
import com.sun.source.tree.ModifiersTree;
import io.github.miracelwhipp.constness.annotation.MetaConst;

import javax.lang.model.AnnotatedConstruct;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class AnnotationElementInheritance {

    private AnnotationElementInheritance() {
    }

    public static <AnnotationType extends Annotation> AnnotationType getAnnotation(
            Elements elements,
            Class<AnnotationType> annotationClass,
            Element element
    ) {

        return getAnnotation(elements, elements::getAllAnnotationMirrors, annotationClass, element, new HashSet<>());
    }

    public static <AnnotationType extends Annotation> AnnotationType getAnnotation(
            Elements elements,
            Class<AnnotationType> annotationClass,
            TypeMirror typeMirror
    ) {

        return getAnnotation(elements, TypeMirror::getAnnotationMirrors, annotationClass, typeMirror, new HashSet<>());
    }

    private static <AnnotationType extends Annotation, ElementType extends AnnotatedConstruct> AnnotationType getAnnotation(
            Elements elements,
            Function<ElementType, List<? extends AnnotationMirror>> getAllAnnotations,
            Class<AnnotationType> annotationClass,
            ElementType element,
            Set<Object> encountered
    ) {

        return AnnotationElementInheritance.<AnnotationType, ElementType, AnnotationMirror>getAbstractAnnotation(
                AnnotatedConstruct::getAnnotation,
                getAllAnnotations,
                (annotationMirror, encounteredInner) -> AnnotationElementInheritance.getAnnotation(elements, elements::getAllAnnotationMirrors, annotationClass, annotationMirror.getAnnotationType().asElement(), encounteredInner),
                annotationClass,
                element,
                encountered
        );
    }

    private static <
            AnnotationType extends Annotation,
            ElementType,
            AnnotationRepresentationType
            > AnnotationType getAbstractAnnotation(
            BiFunction<ElementType, Class<AnnotationType>, AnnotationType> getSpecificAnnotation,
            Function<ElementType, List<? extends AnnotationRepresentationType>> getAllAnnotations,
            BiFunction<AnnotationRepresentationType, Set<Object>, AnnotationType> recursiveCall,
            Class<AnnotationType> annotationClass,
            ElementType element,
            Set<Object> encountered
    ) {

        AnnotationType annotation = getSpecificAnnotation.apply(element, annotationClass);

        if (annotation != null) {

            return annotation;
        }

        if (encountered.contains(element)) {

            return null;
        }

        encountered.add(element);

        List<? extends AnnotationRepresentationType> annotationsFound = getAllAnnotations.apply(element);

        for (AnnotationRepresentationType annotationFound : annotationsFound) {

            AnnotationType result = recursiveCall.apply(annotationFound, encountered);

            if (result != null) {

                return result;
            }
        }

        return null;
    }

    public static <AnnotationType extends Annotation> AnnotationType getAnnotation(Class<AnnotationType> annotationType, AnnotatedElement element) {

        return getAnnotation(annotationType, element, new HashSet<>());
    }

    public static <AnnotationType extends Annotation> AnnotationType getAnnotation(Class<AnnotationType> annotationType, AnnotatedElement element, Set<Object> encountered) {

        return AnnotationElementInheritance.<AnnotationType, AnnotatedElement, Annotation>getAbstractAnnotation(
                AnnotatedElement::getAnnotation,
                (AnnotatedElement annotatedElement) -> Arrays.asList(annotatedElement.getAnnotations()),
                (annotation, encounteredInner) -> getAnnotation(annotationType, annotation.annotationType(), encounteredInner),
                annotationType,
                element,
                encountered
        );
    }
}
