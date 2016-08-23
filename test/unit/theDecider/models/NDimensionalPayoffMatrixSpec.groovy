package theDecider.models

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class NDimensionalPayoffMatrixSpec extends Specification {

    void "test addPlayerStrategy"() {
		setup:
		def ndpm = new NDimensionalPayoffMatrix()
		
		when:
		ndpm.addPlayerStrategy("header")
		
		then:
		ndpm.playerStrategies == ["header"]
    }

    void "test addPlayerStrategy adds to already populated playerStrategy"() {
		setup:
		def ndpm = new NDimensionalPayoffMatrix(["header1", "header2"], [:])
		
		when:
		ndpm.addPlayerStrategy("header")
		
		then:
		ndpm.playerStrategies == ["header1", "header2", "header"]
    }

    void "test containsPlayerStrategy"() {
		setup:
		def ndpm = new NDimensionalPayoffMatrix(["header1", "header2"], [:])
		
		when:
		boolean hasPlayerStrategy = ndpm.containsPlayerStrategy("header")
		
		then:
		hasPlayerStrategy == false
    }

    void "test containsPlayerStrategy returns true when it contains playerStrategy"() {
		setup:
		def ndpm = new NDimensionalPayoffMatrix(["header1", "header2", "header"], [:])
		
		when:
		boolean hasPlayerStrategy = ndpm.containsPlayerStrategy("header")
		
		then:
		hasPlayerStrategy == true
    }

    void "test indexOfPlayerStrategy returns number when playerStrategy found"() {
		setup:
		def ndpm = new NDimensionalPayoffMatrix(["header1", "header2", "header"], [:])
		
		when:
		def strategyIndex = ndpm.indexOfPlayerStrategy("header")
		
		then:
		strategyIndex == 2
    }

    void "test indexOfPlayerStrategy returns null when playerStrategy not found"() {
		setup:
		def ndpm = new NDimensionalPayoffMatrix(["header1", "header2"], [:])
		
		when:
		def strategyIndex = ndpm.indexOfPlayerStrategy("header")
		
		then:
		strategyIndex == null
    }
}
