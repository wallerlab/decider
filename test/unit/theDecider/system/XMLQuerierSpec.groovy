package theDecider.system

import java.util.ArrayList;

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*
import theDecider.models.Strategy;
import theDecider.models.MolecularSystem;
import theDecider.system.XMLQuerier;

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class XMLQuerierSpec extends Specification {
	
	XMLQuerier xq = new XMLQuerier()
	
	void "test that populateMethod populates a Strategy"(){
		setup:
		def hamiltonian = "B3LYP"
		def hamiltonianCategory  = "hybrid-GGA"
		def basisSet = "LACVP*"
		def basisSetCategory = "unclassified"
		def percentageError = -0.029635763890497602
		Node node = new Node(null,"Method", ["Hamiltonian": "B3LYP", 
			"CategoryOfHamiltonian": "hybrid-GGA", "Basis": "LACVP*",
			"CategoryOfBasis": "unclassified", "CounterpoiseCorrection": "noCP"])
		Node resultNode = new Node(node, "Result", ["CalculatedValue": "0.127937"])
		
		when:
		def method = xq.populateMethod(node, -4.31698)
		
		then:
		method.percentageError == percentageError
		method.functional == hamiltonian
		method.functionalCategory == hamiltonianCategory
		method.basisSet == basisSet
		method.basisSetCategory == basisSetCategory
		
	}
	
	void "test that populateMethod populates a Strategy with CP"(){
		setup:
		def hamiltonian = "B3LYP"
		def hamiltonianCategory  = "hybrid-GGA"
		def basisSet = "LACVP*w/CP"
		def basisSetCategory = "unclassified"
		def percentageError = -0.029635763890497602
		Node node = new Node(null,"Method", ["Hamiltonian": "B3LYP", 
			"CategoryOfHamiltonian": "hybrid-GGA", "Basis": "LACVP*",
			"CategoryOfBasis": "unclassified", "CounterpoiseCorrection": "CP"])
		Node resultNode = new Node(node, "Result", ["CalculatedValue": "0.127937"])
		
		when:
		def method = xq.populateMethod(node, -4.31698)
		
		then:
		method.percentageError == percentageError
		method.functional == hamiltonian
		method.functionalCategory == hamiltonianCategory
		method.basisSet == basisSet
		method.basisSetCategory == basisSetCategory
		
	}
	
	void "test that getStrategies gets strategies"() {
		setup:
		xq.xmlFiles = ["test/Test_Folder/testQuerier_test.xml"]
		MolecularSystem ds = Mock()
		ds.name >> 'ch32o_c6f6_lp_towards_eclipsed_3.0a'
		ds.tanimoto >> 0.123
		ds.methods >> new HashSet()
		ArrayList<MolecularSystem> systems = [ds]


		when:
		List<Strategy> strategies = xq.queryForSystems(systems)

		then:
		strategies[0] instanceof Strategy
	}
	@IgnoreRest
	void "test getSystemNameAndSmilesForEnum gets data from xml file"(){
		setup:
		xq.xmlFiles = ["data/Ether_Aromatic_Interaction.xml"]
		File file = new File("data/systemMolecules.txt")
		
		when:
		xq.getSystemNameAndSmilesForEnum()
		
		then:
		file.exists()
		
		cleanup:
		file.delete()
	}
}
