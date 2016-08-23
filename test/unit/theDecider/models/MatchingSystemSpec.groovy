package theDecider.models

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class MatchingSystemSpec extends Specification {

    void "test toString"() {
		setup:
		MolecularSystem ms = new MolecularSystem()
		ms.name = "S22_8_08A"
		
		when:
		def systemName = ms.toString()
		
		then:
		systemName == "S22_8_08A"
    }

    void "test name"() {
		setup:
		MolecularSystem ms = new MolecularSystem()
		
		when:
		ms.name = "S22_8_08A"
		
		then:
		ms.name == "S22_8_08A"
    }

    void "test smilesString"() {
		setup:
		MolecularSystem ms = new MolecularSystem()
		
		when:
		ms.smilesString = "c1ccccc1"
		
		then:
		ms.smilesString == "c1ccccc1"
    }

    void "test tanimoto"() {
		setup:
		MolecularSystem ms = new MolecularSystem()
		
		when:
		ms.tanimoto = 0.2
		
		then:
		ms.tanimoto == 0.2
    }
}
