
import io.github.miracelwhipp.constness.test.Anything;

class UseAnyThing {

    static {

        Anything anything = new Anything();

        Object object = new Object();

        object = anything.method(5, 7L, "a string", object);
    }
}