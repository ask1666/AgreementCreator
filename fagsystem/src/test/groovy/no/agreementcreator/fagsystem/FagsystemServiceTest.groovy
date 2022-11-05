package no.agreementcreator.fagsystem

import no.agreementcreator.shared.models.Agreement
import no.agreementcreator.shared.models.AgreementStatus
import no.agreementcreator.shared.models.AgreementType
import no.agreementcreator.shared.models.Customer
import no.agreementcreator.shared.models.CreateAgreementRequest
import no.agreementcreator.shared.models.CreateCustomerRequest
import spock.lang.Specification
import spock.lang.Subject

class FagsystemServiceTest extends Specification {

    FakeDB db = new FakeDB()

    @Subject FagsystemService service = new FagsystemService(db)

    def 'Create customer and agreement'() {
        given:
            CreateCustomerRequest customerRequest = CreateCustomerRequest.of('Ola', 'Nordmann')
        when:
            Customer customer = service.createCustomer(customerRequest)
        then:
            customer.getId().toString().length() == 36
        when:
            Agreement agreement = service.createAgreement(CreateAgreementRequest.of(AgreementType.AVTALE_TYPE_1, customer.getId()))
        then:
            agreement.getId().toString().length() == 36
            agreement.status == AgreementStatus.REGISTERED
            db.getCustomer(agreement.customerId) == customer
            db.getAgreement(agreement.getId()) == agreement

    }

    def 'Update status'() {
        given:
            CreateCustomerRequest customerRequest = CreateCustomerRequest.of('Ola', 'Nordmann')
        when:
            Customer customer = service.createCustomer(customerRequest)
            Agreement agreement = service.createAgreement(CreateAgreementRequest.of(AgreementType.AVTALE_TYPE_1, customer.getId()))
        and:
            AgreementStatus status = service.updateAgreementStatus(agreement.getId(), AgreementStatus.SENT)
        then:
            status == AgreementStatus.SENT
            db.getAgreement(agreement.getId()).getStatus() == AgreementStatus.SENT
    }

}
