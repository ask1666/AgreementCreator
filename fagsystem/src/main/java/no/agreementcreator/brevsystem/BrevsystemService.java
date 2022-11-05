package no.agreementcreator.brevsystem;

import no.agreementcreator.shared.models.Agreement;
import no.agreementcreator.shared.models.AgreementStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Singleton;

@Singleton
public class BrevsystemService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AgreementStatus sendAgreement(Agreement agreement) {
        logger.info("Agreement with id: " + agreement.getId() + " has been sent.");
        return AgreementStatus.SENT;
    }

}
