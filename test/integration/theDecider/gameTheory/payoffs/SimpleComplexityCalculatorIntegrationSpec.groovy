package theDecider.gameTheory.payoffs



import spock.lang.*
import theDecider.models.Strategy
import theDecider.models.MolecularSystem
import theDecider.services.wrappers.ChemLibWrapper
import theDecider.system.SimpleComplexityCalculator;


class SimpleComplexityCalculatorIntegrationSpec extends Specification {
	
	SimpleComplexityCalculator scc = new SimpleComplexityCalculator()

    void "test that calculate calculates the complexity factor"(){
		setup:
		scc.simpleChemLibWrapperService = Mock(ChemLibWrapper)
		def method1 = new Strategy(basisSet: "LACVP*", functional: "B3LYP", 
			percentageError: 102.96357638904976)
		def method2 = new Strategy(basisSet: "aug-scc-pVDZ", functional: "B3LYP",
			percentageError: 97.5433333)
		def method3 = new Strategy(basisSet: "LACVP*", functional: "M06-2X",
			percentageError: 27.12345)
		def method4 = new Strategy(basisSet: "aug-scc-pVDZ", functional: "M06-2X",
			percentageError: 0.00018)
		List<Strategy> matchingSystems = new ArrayList([method1, method2, 
			method3, method4])
		Map params = ["smiles": "c1ccc(cc1)C#N.O(C)C", "alpha": "100", 
			"beta": "100", "gamma": "100"]
		
		when:
		scc.calculate(matchingSystems, params)
		
		then:
		matchingSystems[0].complexity != null
	}
	
	@Unroll
	void "test that calculateSystemComplexity counts the number of atoms"(){
		setup:
		scc.simpleChemLibWrapperService = Mock(ChemLibWrapper)
		
		when:
		scc.calculateSystemComplexity("c1ccc(cc1)C#N.O(C)C")
		
		then:
		1*scc.simpleChemLibWrapperService.countAtoms(_)
	}
}
