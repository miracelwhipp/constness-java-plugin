
import io.github.miracelwhipp.constness.annotation.ConstMethod;

class S6mple {

    private static class A {
        public B b;
    }

    private static class B {
        @ConstMethod
        public C c() { return null;}
    }

    private static class C {
        public D d;
    }

    private static class D {
        public int integer;
    }

    public void something(A a) {

        a.b.c().d.integer = 666;
    }
}