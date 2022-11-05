package no.agreementcreator.fagsystem

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import no.agreementcreator.shared.models.AgreementStatus
import no.agreementcreator.shared.models.AgreementType
import no.agreementcreator.shared.models.CreateAgreementRequest
import no.agreementcreator.shared.models.CreateCustomerRequest
import spock.lang.Specification

import jakarta.inject.Inject

@MicronautTest
class FagsystemControllerTest extends Specification {

    @Inject
    @Client("http://localhost:8086/fagsystem")
    HttpClient client

    def 'Create customer'() {
        given:
            CreateCustomerRequest customerRequest = CreateCustomerRequest.of('Ola', 'Nordmann')
        when:
            UUID customerUuid =
                client
                    .toBlocking()
                    .retrieve(
                        HttpRequest.POST('/customers/create', customerRequest),
                        UUID
                    )
        then:
            customerUuid.toString().length() == 36
    }

    def 'Create agreement'() {
        given:
            CreateCustomerRequest customerRequest = CreateCustomerRequest.of('Ola', 'Nordmann')
            UUID customerId = client.toBlocking().retrieve(HttpRequest.POST('/customers/create', customerRequest), UUID)
            CreateAgreementRequest agreementRequest = CreateAgreementRequest.of(AgreementType.AVTALE_TYPE_1, customerId)
        when:
            UUID agreementUuid =
                client
                    .toBlocking()
                    .retrieve(
                        HttpRequest.POST('/agreements/create', agreementRequest),
                        UUID
                    )
        then:
            agreementUuid.toString().length() == 36
    }

    def 'Update agreementStatus'() {
        given:
            CreateCustomerRequest customerRequest = CreateCustomerRequest.of('Ola', 'Nordmann')
            UUID customerId = client.toBlocking().retrieve(HttpRequest.POST('/customers/create', customerRequest), UUID)
            CreateAgreementRequest agreementRequest = CreateAgreementRequest.of(AgreementType.AVTALE_TYPE_1, customerId)
            UUID agreementId = client.toBlocking().retrieve(HttpRequest.POST('/agreements/create', agreementRequest), UUID)
        when:
            AgreementStatus status = client
                .toBlocking()
                .retrieve(
                    HttpRequest.PUT("/agreements/${agreementId}/${AgreementStatus.SENT}", null),
                    AgreementStatus)
        then:
            status == AgreementStatus.SENT
    }
}
