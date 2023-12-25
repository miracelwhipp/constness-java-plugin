import io.github.miracelwhipp.constness.annotation.Const;



class ReturnAssignment {

    @Const
    private Object object() {return null;};


    public Object getObject() {

        return object(); // compile error
    }

}