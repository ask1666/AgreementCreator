package no.agreementcreator.fagsystem;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import no.agreementcreator.shared.models.AgreementStatus;
import no.agreementcreator.shared.models.CreateAgreementRequest;
import no.agreementcreator.shared.models.CreateCustomerRequest;

import java.util.UUID;

@Controller(value = "/fagsystem", port = "8086")
public class FagsystemController {

    FagsystemService fagsystemService;

    public FagsystemController(FagsystemService fagsystemService) {
        this.fagsystemService = fagsystemService;
    }

    @Post("/customers/create")
    public UUID createCustomer(@Body CreateCustomerRequest request) {
        return fagsystemService.createCustomer(request).getId();
    }

    @Post("/agreements/create")
    public UUID createAgreement(@Body CreateAgreementRequest request) {
        return fagsystemService.createAgreement(request).getId();
    }

    @Put("/agreements/{id}/{status}")
    public AgreementStatus setAgreementStatus(@PathVariable UUID id, @PathVariable AgreementStatus status) {
        return fagsystemService.updateAgreementStatus(id, status);
    }

}
