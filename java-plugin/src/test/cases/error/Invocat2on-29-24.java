
import io.github.miracelwhipp.constness.annotation.Const;

class Simple {

    private static class Other {

        public int a;
        public long b;
        public Object c;

        public void doIt() {
        }

        @Const
        public void doItConst() {
        }

    }

    @Const
    private Other simple = new Other();

    @Const
    private Object object = new Object();

    public void something() {

        object.toString();
    }
}