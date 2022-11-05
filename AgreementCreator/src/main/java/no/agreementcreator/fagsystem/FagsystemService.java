package no.agreementcreator.fagsystem;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import no.agreementcreator.shared.models.AgreementStatus;
import no.agreementcreator.shared.models.CreateAgreementRequest;
import no.agreementcreator.shared.models.Agreement;
import no.agreementcreator.shared.models.CreateCustomerRequest;
import no.agreementcreator.shared.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Singleton;
import java.util.UUID;

@Singleton
public class FagsystemService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

        logger.info("Registered customer: {}", customer);

        return customer;
    }

    public Agreement createAgreement(CreateAgreementRequest request) {
        if (!request.canRegister()) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Invalid agreement.");
        }

        var agreement = Agreement.from(request);
        db.persistAgreement(agreement);

        logger.info("Registered agreement: {}", agreement);
        logger.info("Customer is now: {}", db.getCustomer(agreement.getCustomerId()));

        return agreement;
    }

    public AgreementStatus updateAgreementStatus(UUID id, AgreementStatus status) {
        var agreement = db.getAgreement(id);

        agreement.setAgreementStatus(status);
        db.persistAgreement(agreement);

        logger.info("Updated status of agreement: {}", agreement);


        return status;
    }

}
