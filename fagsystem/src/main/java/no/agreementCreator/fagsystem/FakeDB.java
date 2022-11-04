package no.agreementCreator.fagsystem;

import groovy.lang.Singleton;
import no.agreementCreator.shared.models.Agreement;
import no.agreementCreator.shared.models.Customer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Singleton
public class FakeDB {

    Set<Customer> customers = new HashSet<>();
    Set<Agreement> agreements = new HashSet<>();

    public synchronized Customer getCustomer(Customer customer) {
        return customers.stream()
            .filter(c -> c.getId() == customer.getId())
            .findAny()
            .orElse(null);
    }

    public synchronized void persistCustomer(Customer customer) {
        if (getCustomer(customer) == null) {
            customers.add(customer);
        }
        customers.remove(customer);
        customers.add(customer);
    }

    public synchronized void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public synchronized Agreement getAgreement(Agreement agreement) {
        return getAgreement(agreement.getId());
    }

    public synchronized Agreement getAgreement(UUID id) {
        return agreements.stream()
            .filter(c -> c.getId() == id)
            .findAny()
            .orElse(null);
    }

    public synchronized void persistAgreement(Agreement agreement) {
        if (getAgreement(agreement) == null) {
            Customer customer = agreement.getCustomer();
            customer.addAgreement(agreement);
            persistCustomer(customer);
            agreements.add(agreement);
        }
        agreements.remove(agreement);
        agreements.add(agreement);
    }

    public synchronized void removeAgreement(Agreement agreement) {
        agreements.remove(agreement);
    }

}
