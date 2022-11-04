package no.agreementCreator.shared.models;

import io.micronaut.core.annotation.Introspected;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Introspected
public class RegisterAgreementRequest {

    private AgreementType agreementType;
    private Customer customer;

    public static RegisterAgreementRequest of(AgreementType agreementType, Customer customer) {
        return new RegisterAgreementRequest(agreementType, customer);
    }

    public boolean canRegister() {
        return agreementType != null && customer != null;
    }

    @Override
    public String toString() {
        return "RegisterAgreementRequest{" +
            "agreementType=" + agreementType +
            ", customer=" + customer +
            '}';
    }
}
