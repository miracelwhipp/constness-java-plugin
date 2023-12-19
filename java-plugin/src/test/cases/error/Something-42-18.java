package io.github.miracelwhipp.constness.test;

import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstMethod;
import io.github.miracelwhipp.constness.annotation.ConstValue;
import io.github.miracelwhipp.constness.test.Anything;

class Something {

    private static class Other {

        public int a;
        public long b;
        public Object c;

        public Anything anything;
        public void doIt() {
        }

        @Const
        public void doItConst() {
        }

    }

    private final Object nowhere;

    public Something(Object nowhere) {
        @Const
        Object huhu = null;

        huhu = nowhere;

        Object elseThing;

        this.nowhere = nowhere;
    }

    @Const
    public void constMethod() {

        something(); // compile error
    }

    public void something() {

    }

    public void something(@Const Other value) {

        value.doItConst();

    }

    @Const
    public Object none() {

        return null;
    }

    @ConstValue
    public Object none2() {

        return null;
    }

    @ConstMethod
    public Object none3() {

        return null;
    }


    public @Const Object none4() {

        return null;
    }

}
