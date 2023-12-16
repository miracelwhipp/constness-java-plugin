import io.github.miracelwhipp.constness.test.Bean;

class UseBean {

    static {

        Bean bean = new Bean();

        bean.doSomething();

        bean.constBean.doConst();

        bean.mutableBean.constBean.doSomething();
    }

}