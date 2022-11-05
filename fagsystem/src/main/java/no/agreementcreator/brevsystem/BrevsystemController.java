package no.agreementcreator.brevsystem;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import no.agreementcreator.shared.models.Agreement;
import no.agreementcreator.shared.models.AgreementStatus;

@Controller(value = "/brevsystem", port = "8084")
public class BrevsystemController {

    private final BrevsystemService service;

    public BrevsystemController(BrevsystemService service) {
        this.service = service;
    }

    @Post("/send")
    public AgreementStatus send(@Body Agreement agreement) {
        return service.sendAgreement(agreement);
    }

}
