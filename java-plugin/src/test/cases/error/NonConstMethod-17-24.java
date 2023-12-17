import io.github.miracelwhipp.constness.annotation.Const;


class NonConstMethod {

    private int value;

    public void method() {
        // ...
    }

    static {

        @Const
        NonConstMethod instance = new NonConstMethod();

        instance.method(); // compile error
    }
}
