
import io.github.miracelwhipp.constness.annotation.Const;

class S1mple {

    private static class Other {

        public int a;
        public long b;
        public Object member;

        public void doIt() {
        }

        @Const
        public void doItConst() {
        }

    }

    // tag::const-write-access[]
    public static void method(@Const Other value) {

    }
    // end::const-write-access[]

    static {

        // tag::const-access[]
        Other other = new Other();
        @Const
        Other another = other; // <1>

        method(another); // <2>
        method(other);   // <3>

        other.member = new Object(); // <4>
        another.member = new Object(); // compile error <5>

        another = new Other(); // <6>
        // end::const-access[]
    }

}