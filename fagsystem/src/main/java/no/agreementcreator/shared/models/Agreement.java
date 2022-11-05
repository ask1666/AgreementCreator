package no.agreementcreator.shared.models;

import io.micronaut.core.annotation.Introspected;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@Introspected
public class Agreement {

    private UUID id;
    private AgreementType type;
    private AgreementStatus status;
    private Customer customer;

    public static Agreement from(RegisterAgreementRequest request) {
        Agreement response = new Agreement();

        response.setId(UUID.randomUUID());
        response.setType(request.getAgreementType());
        response.setAgreementStatus(AgreementStatus.REGISTERED);
        response.setCustomer(request.getCustomer());

        return response;
    }

    public void setAgreementStatus(AgreementStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Agreement{" +
            "id=" + id +
            ", type=" + type +
            ", status=" + status +
            ", customer=" + customer +
            '}';
    }
}
