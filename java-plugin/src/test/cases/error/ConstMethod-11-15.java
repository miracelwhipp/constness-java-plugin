import io.github.miracelwhipp.constness.annotation.Const;


class ConstMethod {

    private int field;

    @Const
    public void method() {

        field = 6; // compile error
    }
}
