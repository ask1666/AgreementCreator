package no.agreementcreator.integrasjonslag;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import no.agreementcreator.shared.models.RegisterAgreementRequest;
import no.agreementcreator.shared.models.RegisterAgreementResponse;

@Controller(value = "/integrasjon", port = "8080")
public class IntegrasjonController {

    IntegrasjonService service;

    public IntegrasjonController(IntegrasjonService service) {
        this.service = service;
    }

    @Post("/registerAgreement")
    public RegisterAgreementResponse registerAgreement(RegisterAgreementRequest request) {
        return service.registerAgreement(request);
    }

}
