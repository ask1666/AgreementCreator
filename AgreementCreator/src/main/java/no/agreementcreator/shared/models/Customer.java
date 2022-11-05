package no.agreementcreator.shared.models;

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
    private Set<Agreement> agreements = new HashSet<>();

    public static Customer from(CreateCustomerRequest request) {
        Customer customer = new Customer();

        customer.setId(UUID.randomUUID());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());

        return customer;
    }

    public Set<Agreement> getAgreements() {
        return agreements;
    }

    public void addOrUpdateAgreement(Agreement agreement) {
        agreements.remove(agreement);
        agreements.add(agreement);
    }

    public void addAgreement(Agreement agreement) {
        agreements.add(agreement);
    }

    public void removeAgreement(Agreement agreement) {
        agreements.stream()
            .filter(a -> a.getId() == agreement.getId())
            .findAny()
            .ifPresent(agreements::remove);
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
