import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstValue;


class ReturnAssignment {

    @Const
    private Object object() {return null;};

    @ConstValue
    public Object getObject() {

        return object();
    }

}