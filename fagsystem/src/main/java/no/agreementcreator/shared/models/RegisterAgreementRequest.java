package no.agreementcreator.shared.models;

import lombok.Data;
import lombok.Getter;

@Data
public class RegisterAgreementRequest {

    private AgreementType agreementType;
    private String firstName;
    private String lastName;

}
