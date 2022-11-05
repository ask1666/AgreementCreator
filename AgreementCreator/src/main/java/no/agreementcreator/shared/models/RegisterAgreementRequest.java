package no.agreementcreator.shared.models;

import lombok.Data;

@Data
public class RegisterAgreementRequest {

    private AgreementType agreementType;
    private String firstName;
    private String lastName;

}
