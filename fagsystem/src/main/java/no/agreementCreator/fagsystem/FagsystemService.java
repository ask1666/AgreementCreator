package no.agreementCreator.fagsystem;

import groovy.lang.Singleton;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import no.agreementCreator.shared.models.AgreementStatus;
import no.agreementCreator.shared.models.RegisterAgreementRequest;
import no.agreementCreator.shared.models.Agreement;
import no.agreementCreator.shared.models.RegisterCustomerRequest;
import no.agreementCreator.shared.models.Customer;

import java.util.UUID;

@Singleton
public class FagsystemService {

    private final FakeDB db;

    public FagsystemService(FakeDB db) {
        this.db = db;
    }

    public Customer registerCustomer(RegisterCustomerRequest request) {
        if (!request.canRegister()) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Invalid customer.");
        }

        var customer = Customer.from(request);
        db.persistCustomer(customer);

        return customer;
    }

    public Agreement registerAgreement(RegisterAgreementRequest request) {
        if (!request.canRegister()) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Invalid agreement.");
        }

        var agreement = Agreement.from(request);
        db.persistAgreement(agreement);

        return agreement;
    }

    public AgreementStatus updateAgreementStatus(UUID id, AgreementStatus status) {
        var agreement = db.getAgreement(id);

        agreement.setAgreementStatus(status);
        db.persistAgreement(agreement);

        return status;
    }

}
