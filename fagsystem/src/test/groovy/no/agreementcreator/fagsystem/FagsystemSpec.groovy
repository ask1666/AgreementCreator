package no.agreementcreator.fagsystem

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import jakarta.inject.Inject

@MicronautTest
class FagsystemSpec extends Specification {

    @Inject
    EmbeddedServer application

    def 'test it works'() {
        expect:
        application.running
    }

}
