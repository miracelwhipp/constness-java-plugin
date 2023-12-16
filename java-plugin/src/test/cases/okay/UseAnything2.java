
import io.github.miracelwhipp.constness.test.Anything;
import io.github.miracelwhipp.constness.annotation.Const;

class UseAnyThing {

    static Anything any(Object object) {

        return new Anything();
    }

    static {

        Anything anything = new Anything();

        Object object = new Object();

        @Const
        Object otherObject = new Object();

        any(anything.method(5, 7L, "a string", object)).method(7, 5L, "another-string", otherObject);
    }
}