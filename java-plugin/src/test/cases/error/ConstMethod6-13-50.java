import io.github.miracelwhipp.constness.annotation.Const;


class ConstMethod {

    private ConstMethod field;

    @Const
    private ConstMethod constField;

    public void method() {

        field.field.constField.field.field.method();
    }

    @Const
    public void constMethod() {

    }
}
