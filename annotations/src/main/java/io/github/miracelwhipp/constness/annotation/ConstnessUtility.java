package io.github.miracelwhipp.constness.annotation;

public final class ConstnessUtility {

    private ConstnessUtility() {
    }

    public static <Type> Type castConstAway(@Const Type element) {

        return element;
    }

}
