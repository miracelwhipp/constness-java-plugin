package io.github.miracelwhipp.constness.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE_USE})
@Documented
@MetaConst
public @interface Const {
}
