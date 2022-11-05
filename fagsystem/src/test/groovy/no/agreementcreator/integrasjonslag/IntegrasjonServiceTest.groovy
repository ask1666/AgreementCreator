package no.agreementcreator.integrasjonslag

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import no.agreementcreator.shared.models.AgreementStatus
import no.agreementcreator.shared.models.AgreementType
import no.agreementcreator.shared.models.RegisterAgreementRequest
import no.agreementcreator.shared.models.RegisterAgreementResponse
import spock.lang.Specification
import spock.lang.Subject

import jakarta.inject.Inject

@MicronautTest
class IntegrasjonServiceTest extends Specification {

    @Inject
    @Subject IntegrasjonService service

    def 'registerAgreement'() {
        given:
            def registerAgreementRequest = new RegisterAgreementRequest(
                agreementType: AgreementType.AVTALE_TYPE_1,
                firstName: 'Ola',
                lastName: 'Nordmann'
            )
        when:
            RegisterAgreementResponse response = service.registerAgreement(registerAgreementRequest)
        then:
            response.agreementId().toString().length() == 36
            response.status() == AgreementStatus.SENT

    }

}
