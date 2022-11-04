package no.agreementCreator.fagsystem

import no.agreementCreator.shared.models.Agreement
import no.agreementCreator.shared.models.AgreementStatus
import no.agreementCreator.shared.models.AgreementType
import no.agreementCreator.shared.models.Customer
import no.agreementCreator.shared.models.RegisterAgreementRequest
import no.agreementCreator.shared.models.RegisterCustomerRequest
import spock.lang.Specification
import spock.lang.Subject

class FagsystemServiceTest extends Specification {

    FakeDB db = new FakeDB()

    @Subject FagsystemService service = new FagsystemService(db)

    def 'Register customer and agreement'() {
        given:
            RegisterCustomerRequest customerRequest = RegisterCustomerRequest.of('Ola', 'Nordmann')
        when:
            Customer customer = service.registerCustomer(customerRequest)
        then:
            customer.getId().toString().length() == 36
        when:
            Agreement agreement = service.registerAgreement(RegisterAgreementRequest.of(AgreementType.AVTALE_TYPE_1, customer))
        then:
            agreement.getId().toString().length() == 36
            agreement.status == AgreementStatus.REGISTERED
            agreement.customer == customer
            agreement.customer.agreements.first() == agreement
            db.getAgreement(agreement.getId()) == agreement

    }

    def 'Update status'() {
        given:
            RegisterCustomerRequest customerRequest = RegisterCustomerRequest.of('Ola', 'Nordmann')
        when:
            Customer customer = service.registerCustomer(customerRequest)
            Agreement agreement = service.registerAgreement(RegisterAgreementRequest.of(AgreementType.AVTALE_TYPE_1, customer))
        and:
            AgreementStatus status = service.updateAgreementStatus(agreement.getId(), AgreementStatus.SENT)
        then:
            status == AgreementStatus.SENT
            db.getAgreement(agreement.getId()).getStatus() == AgreementStatus.SENT
    }

}
