
import io.github.miracelwhipp.constness.annotation.Const;

class S2mple {

    private static class Other {

        public int a;
        public long b;
        @Const
        public Object c;

        public void doIt() {
        }

        @Const
        public void doItConst() {
        }

    }

    public void something(Other value) {

        value.c = new Object();
    }
}