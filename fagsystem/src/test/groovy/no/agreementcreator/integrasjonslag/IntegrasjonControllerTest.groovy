package no.agreementcreator.integrasjonslag

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import no.agreementcreator.shared.models.AgreementStatus
import no.agreementcreator.shared.models.AgreementType
import no.agreementcreator.shared.models.RegisterAgreementRequest
import no.agreementcreator.shared.models.RegisterAgreementResponse
import spock.lang.Specification

import jakarta.inject.Inject

@MicronautTest
class IntegrasjonControllerTest extends Specification {

    @Inject
    @Client("http://localhost:8080/integrasjon")
    HttpClient client

    def 'register agreement'() {
        given:
            def registerAgreementRequest = new RegisterAgreementRequest(
                agreementType: AgreementType.AVTALE_TYPE_1,
                firstName: 'Ola',
                lastName: 'Nordmann'
            )
        when:
            RegisterAgreementResponse response = client.toBlocking().retrieve(HttpRequest.POST("/registerAgreement", registerAgreementRequest), RegisterAgreementResponse.class)
        then:
            println response
            response.agreementId().toString().length() == 36
            response.status() == AgreementStatus.SENT
    }

}
