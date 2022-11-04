package no.agreementCreator.shared.models;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Introspected
public class RegisterCustomerRequest {

    private String firstName;
    private String lastName;

    public static RegisterCustomerRequest of(String firstName, String lastName) {
        RegisterCustomerRequest customer = new RegisterCustomerRequest();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        return customer;
    }

    public boolean canRegister() {
        return firstName != null && !firstName.isBlank()
            && lastName != null && !lastName.isBlank();
    }

    @Override
    public String toString() {
        return "Customer{" +
            "firstName='" + firstName + '\'' +
            ", LastName='" + lastName + '\'' +
            '}';
    }
}
