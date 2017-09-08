package theDecider.services.wrappers

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.IgnoreRest
import spock.lang.Specification
import spock.lang.Unroll;

import org.openscience.cdk.fingerprint.IBitFingerprint

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class SimpleChemLibWrapperSpec extends Specification {
	
	SimpleChemLibWrapperService scw = new SimpleChemLibWrapperService()

	void "test that getBitFingerprint gets the bit fingerprint"(){
		when:
		def test = scw.getBitFingerprint("c1(c(c(c(c(c1F)F)F)F)F)F.O(C)C")

		then:
		test instanceof IBitFingerprint
	}
	
	@Unroll
	void "test that countAtoms counts the number of atoms"(){
		expect:
		b == scw.countAtoms(a)
		
		where:
		a							|	b
		"c1(c(c(c(c(c1F)F)F)F)F)F"	|	12
		"O(C)C"						|	9
		"c1ccc(cc1)[N+](=[O-])O"	|	15 //should be 14
		"c1ccc(cc1)C#N"				|	13
		"[Mg+]"						|	1
	}
	
	void "test that getTanimoto calculates the Tanimoto number"(){
		when:
		def test = scw.getTanimoto("c1(c(c(c(c(c1F)F)F)F)F)F.O(C)C", "O(C)C")
		
		then:
		test == 0.20833333333333334
	}
}
