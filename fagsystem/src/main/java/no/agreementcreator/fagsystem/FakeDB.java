package no.agreementcreator.fagsystem;

import no.agreementcreator.shared.models.Agreement;
import no.agreementcreator.shared.models.Customer;

import jakarta.inject.Singleton;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Singleton
public class FakeDB {

    private final Set<Customer> customers = Collections.synchronizedSet(new HashSet<>());
    private final Set<Agreement> agreements = Collections.synchronizedSet(new HashSet<>());

    public Customer getCustomer(Customer customer) {
        return customers.stream()
            .filter(c -> c.getId() == customer.getId())
            .findAny()
            .orElse(null);
    }

    public void persistCustomer(Customer customer) {
        if (getCustomer(customer) == null) {
            customers.add(customer);
        }
        customers.remove(customer);
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public Agreement getAgreement(Agreement agreement) {
        return getAgreement(agreement.getId());
    }

    public Agreement getAgreement(UUID id) {
        return agreements.stream()
            .filter(c -> c.getId() == id)
            .findAny()
            .orElse(null);
    }

    public void persistAgreement(Agreement agreement) {
        if (getAgreement(agreement) == null) {
            Customer customer = agreement.getCustomer();
            customer.addAgreement(agreement);
            persistCustomer(customer);
            agreements.add(agreement);
        }
        agreements.remove(agreement);
        agreements.add(agreement);
    }

    public void removeAgreement(Agreement agreement) {
        agreements.remove(agreement);
    }

}
