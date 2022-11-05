package no.agreementcreator.fagsystem;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import no.agreementcreator.shared.models.AgreementStatus;
import no.agreementcreator.shared.models.CreateAgreementRequest;
import no.agreementcreator.shared.models.Agreement;
import no.agreementcreator.shared.models.CreateCustomerRequest;
import no.agreementcreator.shared.models.Customer;

import jakarta.inject.Singleton;
import java.util.UUID;

@Singleton
public class FagsystemService {

    private final FakeDB db;

    public FagsystemService(FakeDB db) {
        this.db = db;
    }

    public Customer createCustomer(CreateCustomerRequest request) {
        if (!request.canRegister()) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Invalid customer.");
        }

        var customer = Customer.from(request);
        db.persistCustomer(customer);

        return customer;
    }

    public Agreement createAgreement(CreateAgreementRequest request) {
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
