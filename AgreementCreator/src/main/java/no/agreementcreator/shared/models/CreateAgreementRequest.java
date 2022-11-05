package no.agreementcreator.shared.models;

import io.micronaut.core.annotation.Introspected;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Introspected
public class CreateAgreementRequest {

    private AgreementType agreementType;
    private UUID customerId;

    public static CreateAgreementRequest of(AgreementType agreementType, UUID customerId) {
        return new CreateAgreementRequest(agreementType, customerId);
    }

    public boolean canRegister() {
        return agreementType != null && customerId != null;
    }

    @Override
    public String toString() {
        return "RegisterAgreementRequest{" +
            "agreementType=" + agreementType +
            ", customerId=" + customerId +
            '}';
    }
}
