
import io.github.miracelwhipp.constness.annotation.Const;

class Simple {

    private static class Other {

        public int a;
        public long b;
        public Object c;

        public void doIt() {
        }

// tag::const-method[]
        @Const
        public Object method() {
// end::const-method[]
            return null;
        }
    }

    @Const
    private Other simple = new Other();

    public void something() {

        simple.method();
        simple.doIt();
    }
}