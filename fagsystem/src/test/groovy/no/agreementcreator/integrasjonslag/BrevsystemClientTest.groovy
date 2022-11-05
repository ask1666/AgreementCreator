package no.agreementcreator.integrasjonslag

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import no.agreementcreator.shared.models.AgreementStatus
import no.agreementcreator.shared.models.AgreementType
import no.agreementcreator.shared.models.CreateAgreementRequest
import no.agreementcreator.shared.models.CreateCustomerRequest
import spock.lang.Specification
import spock.lang.Subject

import jakarta.inject.Inject

@MicronautTest
class BrevsystemClientTest extends Specification {

    @Inject
    @Subject BrevsystemClient client

    def 'Create customer'() {
        given:
            CreateCustomerRequest customerRequest = CreateCustomerRequest.of('Ola', 'Nordmann')
        when:
            UUID response = client.createCustomer(customerRequest)
        then:
            response.toString().length() == 36
    }

    def 'Create agreement'() {
        given:
            CreateCustomerRequest customerRequest = CreateCustomerRequest.of('Ola', 'Nordmann')
        when:
            UUID customerId = client.createCustomer(customerRequest)
            def agreementRequest = CreateAgreementRequest.of(AgreementType.AVTALE_TYPE_1, customerId)
            UUID response = client.createAgreement(agreementRequest)
        then:
            response.toString().length() == 36
    }

    def 'Send to user'() {
        given:
            CreateCustomerRequest customerRequest = CreateCustomerRequest.of('Ola', 'Nordmann')
        when:
            UUID customerId = client.createCustomer(customerRequest)
        and:
            def agreementRequest = CreateAgreementRequest.of(AgreementType.AVTALE_TYPE_1, customerId)
            UUID agreementId = client.createAgreement(agreementRequest)
        and:
            AgreementStatus response = client.sendToCustomer(agreementId)
        then:
            response == AgreementStatus.SENT
    }

    def 'update agreement status'() {
        given:
            CreateCustomerRequest customerRequest = CreateCustomerRequest.of('Ola', 'Nordmann')
        when:
            UUID customerId = client.createCustomer(customerRequest)
        and:
            def agreementRequest = CreateAgreementRequest.of(AgreementType.AVTALE_TYPE_1, customerId)
            UUID agreementId = client.createAgreement(agreementRequest)
        and:
            AgreementStatus response = client.updateAgreementStatus(agreementId, AgreementStatus.SENT)
        then:
            response == AgreementStatus.SENT
    }
}
