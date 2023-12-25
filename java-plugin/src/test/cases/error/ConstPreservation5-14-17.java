import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.PreservesConst;


class ConstPreservation {

    void nonConst() {

    }

    @PreservesConst
    void preserveConst() {

        nonConst(); // compile error
    }

}