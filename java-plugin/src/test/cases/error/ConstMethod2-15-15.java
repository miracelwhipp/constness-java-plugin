import io.github.miracelwhipp.constness.annotation.Const;


class ConstMethod {

    private int field;

    public void method() {

    }

    @Const
    public void constMethod() {

        method();
    }
}
