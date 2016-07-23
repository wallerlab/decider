package theDecider

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class BenchmarkSystemsSpec extends Specification {

    void "test smilesString returns smiles string"() {
		when:
		String smilesString = BenchmarkSystem.CH32O_C6F6_LP_TOWARDS_ECLIPSED_3PT0A.smilesString()
		
		then:
		smilesString == "c1(c(c(c(c(c1F)F)F)F)F)F.O(C)C"
    }
}
