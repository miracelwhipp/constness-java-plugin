import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstValue;



class ReturnAssignment {

    @Const
    private Object object;

    @ConstValue
    public Object getObject() {

        return object;
    }

}