
import io.github.miracelwhipp.constness.annotation.Const;

class S3mple {

    private static class Whatever {

        public void something(@Const int value) {

//            value = 6;
        }
    }
}