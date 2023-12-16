package io.github.miracelwhipp.constness.test;

import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstMethod;
import io.github.miracelwhipp.constness.annotation.ConstValue;

public class Bean {

    public int number;
    public long otherNumber;

    public Bean mutableBean;

    @Const
    public Bean constBean;

    public void doSomething() {

    }

    @Const
    public void doConst() {

    }

    @ConstMethod
    public @ConstValue Bean getConstBean() {

        return mutableBean;
    }

    public Bean getMutableBean() {

        return mutableBean;
    }


}
