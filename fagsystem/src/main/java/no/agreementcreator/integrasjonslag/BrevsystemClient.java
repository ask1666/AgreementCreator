package no.agreementcreator.integrasjonslag;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import no.agreementcreator.shared.models.AgreementStatus;
import no.agreementcreator.shared.models.CreateAgreementRequest;
import no.agreementcreator.shared.models.CreateCustomerRequest;

import jakarta.inject.Singleton;
import java.util.UUID;

@Singleton
public class BrevsystemClient {

    private final HttpClient httpClient;

    public BrevsystemClient(HttpClient client) {
        httpClient = client;
    }

    public UUID createCustomer(CreateCustomerRequest customer) {
        return httpClient
            .toBlocking()
            .retrieve(
                HttpRequest.POST("http://localhost:8086/fagsystem/customers/create", customer),
                UUID.class
            );
    }

    public UUID createAgreement(CreateAgreementRequest agreement) {
        return httpClient
            .toBlocking()
            .retrieve(
                HttpRequest.POST("http://localhost:8086/fagsystem/agreements/create", agreement),
                UUID.class
            );
    }

    public AgreementStatus sendToCustomer(UUID agreementId) {
        return httpClient
            .toBlocking()
            .retrieve(
                HttpRequest.POST("http://localhost:8084/brevsystem/send", agreementId),
                AgreementStatus.class
            );

    }

    public AgreementStatus updateAgreementStatus(UUID agreementId, AgreementStatus status) {
        return httpClient
            .toBlocking()
            .retrieve(
                HttpRequest.PUT("http://localhost:8086/fagsystem/agreements/" + agreementId + "/" + status, null),
                AgreementStatus.class
            );
    }
}
