package no.agreementcreator.brevsystem

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import no.agreementcreator.shared.models.Agreement
import no.agreementcreator.shared.models.AgreementStatus
import no.agreementcreator.shared.models.AgreementType
import no.agreementcreator.shared.models.CreateAgreementRequest
import spock.lang.Specification

import jakarta.inject.Inject

import static no.agreementcreator.shared.models.AgreementStatus.SENT

@MicronautTest
class BrevsystemControllerTest extends Specification {

    @Inject
    @Client("http://localhost:8084/brevsystem")
    HttpClient client

    def 'Send'() {
        given:
            Agreement agreement = Agreement.from(CreateAgreementRequest.of(AgreementType.AVTALE_TYPE_1, UUID.randomUUID()))
        when:
            def response = client.toBlocking().retrieve(HttpRequest.POST('/send', agreement.getId()), AgreementStatus)
        then:
            response == SENT
    }
}
