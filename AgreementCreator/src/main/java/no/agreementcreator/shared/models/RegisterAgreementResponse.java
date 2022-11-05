package no.agreementcreator.shared.models;

import java.util.UUID;

public record RegisterAgreementResponse(
    UUID agreementId,
    AgreementStatus status
) {

}
