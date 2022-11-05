package no.agreementcreator.integrasjonslag;

import no.agreementcreator.shared.models.AgreementStatus;
import no.agreementcreator.shared.models.AgreementType;
import no.agreementcreator.shared.models.CreateAgreementRequest;
import no.agreementcreator.shared.models.CreateCustomerRequest;
import no.agreementcreator.shared.models.RegisterAgreementRequest;
import no.agreementcreator.shared.models.RegisterAgreementResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Singleton;
import java.util.UUID;

@Singleton
public class IntegrasjonService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BrevsystemClient client;

    public IntegrasjonService(BrevsystemClient client) {
        this.client = client;
    }

    public RegisterAgreementResponse registerAgreement(RegisterAgreementRequest request) {
        logger.info("Started registering {} for {} {} ", request.getAgreementType(), request.getFirstName(), request.getLastName());

        UUID customerId = client.createCustomer(CreateCustomerRequest.of(request.getFirstName(), request.getLastName()));
        UUID agreementId = client.createAgreement(CreateAgreementRequest.of(AgreementType.AVTALE_TYPE_1, customerId));

        AgreementStatus status = client.sendToCustomer(agreementId);
        AgreementStatus updatedStatus = client.updateAgreementStatus(agreementId, status);

        return new RegisterAgreementResponse(agreementId, updatedStatus);
    }
}
