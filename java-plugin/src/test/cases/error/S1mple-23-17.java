
import io.github.miracelwhipp.constness.annotation.Const;

class S1mple {

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

    public void something(@Const Other value) {

        value.c = new Object();
    }
}