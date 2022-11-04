package no.agreementCreator.fagsystem;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import no.agreementCreator.shared.models.AgreementStatus;
import no.agreementCreator.shared.models.RegisterAgreementRequest;
import no.agreementCreator.shared.models.RegisterCustomerRequest;

import java.util.UUID;

@Controller
public class FagsystemController {

    FagsystemService fagsystemService;

    public FagsystemController(FagsystemService fagsystemService) {
        this.fagsystemService = fagsystemService;
    }

    @Post("/customers/register")
    public UUID registerCustomer(@Body RegisterCustomerRequest request) {
        return fagsystemService.registerCustomer(request).getId();
    }

    @Post("/agreements/register")
    public UUID registerAgreement(@Body RegisterAgreementRequest request) {
        return fagsystemService.registerAgreement(request).getId();
    }

    @Put("/agreements/{id}/{status}")
    public AgreementStatus setAgreementStatus(@PathVariable UUID id, @PathVariable AgreementStatus status) {
        return fagsystemService.updateAgreementStatus(id, status);
    }

}
