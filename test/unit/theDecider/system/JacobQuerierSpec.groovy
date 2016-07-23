package theDecider.system

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import theDecider.system.JacobQuerier;

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class JacobQuerierSpec extends Specification {
	
	JacobQuerier jq = new JacobQuerier()

    void "test that getPayoffs returns null"() {
		when:
		def fakeResponse = jq.queryForSystems(["hi"])
		
		then:
		fakeResponse == null
    }
}
