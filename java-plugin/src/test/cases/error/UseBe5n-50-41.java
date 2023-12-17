import io.github.miracelwhipp.constness.test.Bean;

import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstMethod;
import io.github.miracelwhipp.constness.annotation.ConstValue;


class UseBean {

    static class Bohn {

        public int number;
        public long otherNumber;

        public Bean mutableBean;

        @Const
        public Bean constBean;

        public void doSomething() {

        }

// tag::const-method[]
        @ConstMethod
        public Object method() {
// end::const-method[]
            return null;
        }

        @ConstMethod
        public @ConstValue Bean getConstBean() {

            return mutableBean;
        }

        public Bean getMutableBean() {

            return mutableBean;
        }
    }
    static {

        Bean bean = new Bean();

        bean.doSomething();

        bean.mutableBean.mutableBean.number = 5;

        bean.getConstBean().otherNumber = 6L;
    }

}