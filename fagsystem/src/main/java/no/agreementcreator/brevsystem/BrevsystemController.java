package no.agreementcreator.brevsystem;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import no.agreementcreator.shared.models.AgreementStatus;

import java.util.UUID;

@Controller(value = "/brevsystem", port = "8084")
public class BrevsystemController {

    private final BrevsystemService service;

    public BrevsystemController(BrevsystemService service) {
        this.service = service;
    }

    @Post("/send")
    public AgreementStatus send(@Body UUID agreementId) {
        return service.sendAgreement(agreementId);
    }

}
