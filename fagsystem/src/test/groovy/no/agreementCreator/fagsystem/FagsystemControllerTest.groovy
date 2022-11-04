package no.agreementCreator.fagsystem

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import no.agreementCreator.shared.models.Agreement
import no.agreementCreator.shared.models.AgreementStatus
import no.agreementCreator.shared.models.AgreementType
import no.agreementCreator.shared.models.Customer
import no.agreementCreator.shared.models.RegisterAgreementRequest
import no.agreementCreator.shared.models.RegisterCustomerRequest
import spock.lang.Specification

import jakarta.inject.Inject

@MicronautTest
class FagsystemControllerTest extends Specification {

    @Inject
    FagsystemService service = Mock()

    @Inject
    @Client("/")
    HttpClient client

    def 'Register customer'() {
        given:
            RegisterCustomerRequest customerRequest = RegisterCustomerRequest.of('Ola', 'Nordmann')
        when:
            UUID customerUuid = client.toBlocking().retrieve(HttpRequest.POST('/customers/register', customerRequest), UUID)
        then:
            1 * service.registerCustomer(_) >> Customer.from(customerRequest)
        and:
            customerUuid.toString().length() == 36
    }

    def 'Register agreement'() {
        given:
            Customer customer = Customer.from(RegisterCustomerRequest.of('Ola', 'Nordmann'))
            RegisterAgreementRequest agreementRequest = RegisterAgreementRequest.of(AgreementType.AVTALE_TYPE_1, customer)
        when:
            UUID agreementUuid = client.toBlocking().retrieve(HttpRequest.POST('/agreements/register', agreementRequest), UUID)
        then:
            1 * service.registerAgreement(_) >> Agreement.from(agreementRequest)
        and:
            agreementUuid.toString().length() == 36
    }

    def 'Update agreementStatus'() {
        given:
            def agreementUuid = UUID.randomUUID()
        when:
            AgreementStatus status = client.toBlocking().retrieve(HttpRequest.PUT("/agreements/${agreementUuid}/${AgreementStatus.SENT}", null), AgreementStatus)
        then:
            1 * service.updateAgreementStatus(_, _) >> AgreementStatus.SENT
        and:
            status == AgreementStatus.SENT
    }

    @MockBean(FagsystemService)
    FagsystemService fagsystemService() {
        Mock(FagsystemService)
    }
}
