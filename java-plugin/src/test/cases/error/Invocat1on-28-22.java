
import io.github.miracelwhipp.constness.annotation.Const;

class Simple {

    private static class Other {

        @Const
        public void callMe(Object a, @Const Object b) {

        }

    }

    @Const
    private Other simple = new Other();

    public void something() {

        Object a = new Object();

        @Const
        Object b = new Object();

        simple.callMe(a, b);
        simple.callMe(a, a);

        simple.callMe(b, b);

    }
}