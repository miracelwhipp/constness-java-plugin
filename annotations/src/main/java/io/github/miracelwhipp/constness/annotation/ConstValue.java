package io.github.miracelwhipp.constness.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE})
@Documented
@MetaConst
public @interface ConstValue {
}
