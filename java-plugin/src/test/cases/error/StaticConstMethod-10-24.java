import io.github.miracelwhipp.constness.annotation.Const;


class ConstMethod {

    private int field;

// tag::method[]
    @Const // compile error
    public static void method() {
// end::method[]
        // ...
    }
}
