
import io.github.miracelwhipp.constness.annotation.Const;

class S4mple {

    private static class A {
        public B b;
    }

    private static class B {
        public C c;
    }

    private static class C {
        @Const
        public D d;
    }

    private static class D {
        public int integer;
    }

    public void something(A a) {

        a.b.c.d.integer = 666;
    }
}