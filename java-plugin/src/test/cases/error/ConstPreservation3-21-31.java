import io.github.miracelwhipp.constness.annotation.Const;

import io.github.miracelwhipp.constness.test.Address;
import io.github.miracelwhipp.constness.test.Person;

class ConstPreservation {

    static {

        Address address = new Address("Los Angeles", "2121 Avenue Of The Stars");
        Person person = new Person("Hans", "Gruber", address);

        person.getAddress().setCity("New York");
        person.getAddress().setStreet("188 Audobon Avenue");
        person.setFirstName("Bill");
        person.setSurName("Clay");

        @Const
        Person constPerson = person;

        constPerson.setAddress(address); // compile error
    }

}