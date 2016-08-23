package theDecider.system

import java.util.ArrayList;

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*
import theDecider.BenchmarkSystem
import theDecider.models.*;
import theDecider.services.wrappers.ChemLibWrapper
import theDecider.system.SimpleMatchingSystemFinder

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class SimpleMatchingSystemFinderSpec extends Specification {
	
	SimpleMatchingSystemFinder smsf = new SimpleMatchingSystemFinder()
	
	void "test checkMatchingSystems"(){
		setup:
		MolecularSystem ds = Mock()
		ds.tanimoto >> 0.01
		MolecularSystem ds1 = Mock()
		ds1.tanimoto >> 0.50
		
		when:
		ArrayList notEmpty = smsf.checkMatchingSystems([ds, ds1], 0.4)
		
		then:
		notEmpty == [ds1]
	}
	
	void "test that populateSystem populates a MolecularSystem"(){
		setup:
		BenchmarkSystem benchmarkSystem = BenchmarkSystem.CH32O_C6F6_LP_TOWARDS_ECLIPSED_3PT0A
		Double tanimoto = 0.9166666666666666
		
		when:
		def system = smsf.populateSystem(benchmarkSystem, tanimoto)
		
		then:
		system.name == "ch32o_c6f6_lp_towards_eclipsed_3.0a"
		system.smilesString == "c1(c(c(c(c(c1F)F)F)F)F)F.O(C)C"
		system.tanimoto == 0.9166666666666666
	}
	
	void "test findSystemsForQuery"(){
		setup:
		smsf.simpleChemLibWrapperService = Mock(ChemLibWrapper)
		smsf.simpleChemLibWrapperService.getTanimoto(_,_) >> 0.5
		
		when:
		def systems = smsf.findMatchingSystems("CCC", 0.5)
		
		then:
		systems.size() == 44
	}
}