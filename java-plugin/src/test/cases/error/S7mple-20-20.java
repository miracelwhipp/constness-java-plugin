
import io.github.miracelwhipp.constness.annotation.Const;

class S7mple {

    private static class A {
        public B b;
    }

    private static class B {
        public C c() { return null;}
    }

    private static class C {
        public D d;
    }

    private static class D {
        @Const
        public int integer;
    }

    public void something(A a) {

//        a.b.c().d.integer = 666;
    }
}