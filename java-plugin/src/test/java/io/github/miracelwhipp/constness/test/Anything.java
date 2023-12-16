package io.github.miracelwhipp.constness.test;

import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstnessUtility;

import java.util.List;

public class Anything {

    public Object method(int param1, Long param2, String param3, @Const Object param4) {

        return List.of(param2, param3, ConstnessUtility.castConstAway(param4)).get(param1);
    }

}
