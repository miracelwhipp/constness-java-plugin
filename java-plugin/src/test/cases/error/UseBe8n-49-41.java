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

        @Const
        public void doConst() {

        }

// tag::const-return-type[]
        public @Const Bean getConstBean() {
// end::const-return-type[]

            return mutableBean;
        }

        public Bean getMutableBean() {

            return mutableBean;
        }
    }
    static {

        Bohn bean = new Bohn();

        bean.doSomething();

        bean.mutableBean.mutableBean.number = 5;

        bean.getConstBean().otherNumber = 6L;
    }

}