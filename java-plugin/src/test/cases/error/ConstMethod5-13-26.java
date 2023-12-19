import io.github.miracelwhipp.constness.annotation.Const;


class ConstMethod {

    private ConstMethod field;

    @Const
    private ConstMethod constField;

    public void method() {

        constField.method();
    }

    @Const
    public void constMethod() {

    }
}
