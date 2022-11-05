package no.agreementcreator.brevsystem;

import no.agreementcreator.shared.models.AgreementStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Singleton;
import java.util.UUID;

@Singleton
public class BrevsystemService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AgreementStatus sendAgreement(UUID agreementId) {
        logger.info("Agreement with id: " + agreementId + " has been sent.");
        return AgreementStatus.SENT;
    }

}
