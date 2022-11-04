package no.agreementCreator.shared.models;

import io.micronaut.core.annotation.Introspected;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@Introspected
public class Customer {

    private UUID id;
    private String firstName;
    private String lastName;
    private Set<Agreement> agreements;

    public static Customer from(RegisterCustomerRequest request) {
        Customer customer = new Customer();

        customer.setId(UUID.randomUUID());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());

        return customer;
    }

    public Set<Agreement> getAgreements() {
        if (agreements == null) {
            agreements = new HashSet<>();
        }
        return agreements;
    }

    public void addAgreement(Agreement agreement) {
        if (agreements == null) {
            agreements = new HashSet<>();
        }
        agreements.add(agreement);
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", agreements.size=" + getAgreements().size() +
            '}';
    }
}
