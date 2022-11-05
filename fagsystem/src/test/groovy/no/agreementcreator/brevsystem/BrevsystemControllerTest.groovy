package no.agreementcreator.brevsystem

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import no.agreementcreator.shared.models.Agreement
import no.agreementcreator.shared.models.AgreementStatus
import no.agreementcreator.shared.models.AgreementType
import no.agreementcreator.shared.models.Customer
import no.agreementcreator.shared.models.RegisterAgreementRequest
import no.agreementcreator.shared.models.RegisterCustomerRequest
import spock.lang.Specification

import jakarta.inject.Inject

import static no.agreementcreator.shared.models.AgreementStatus.SENT

@MicronautTest
class BrevsystemControllerTest extends Specification {

    @Inject
    BrevsystemService brevsystemService = Mock()

    @Inject
    @Client("http://localhost:8084/brevsystem")
    HttpClient client

    def 'Send'() {
        given:
            Agreement agreement = Agreement.from(RegisterAgreementRequest.of(AgreementType.AVTALE_TYPE_1, Customer.from(RegisterCustomerRequest.of("firstname", "lastname"))))
        when:
            def response = client.toBlocking().retrieve(HttpRequest.POST('/send', agreement), AgreementStatus)
        then:
            1 * brevsystemService.sendAgreement(_) >> SENT
            and:
            response == SENT
    }


    @MockBean(BrevsystemService)
    BrevsystemService brevsystemService() {
        Mock(BrevsystemService)
    }
}
