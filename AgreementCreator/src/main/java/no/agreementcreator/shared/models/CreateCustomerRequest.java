package no.agreementcreator.shared.models;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Introspected
public class CreateCustomerRequest {

    private String firstName;
    private String lastName;

    public static CreateCustomerRequest of(String firstName, String lastName) {
        CreateCustomerRequest customer = new CreateCustomerRequest();

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
            ", lastName='" + lastName + '\'' +
            '}';
    }
}
