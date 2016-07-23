package theDecider.services



import spock.lang.*
import theDecider.gameTheory.PayoffMatrixPopulator
import theDecider.gameTheory.GameTheoryManager
import theDecider.models.*
import theDecider.system.ComplexityCalculator;
import theDecider.system.Querier;
import theDecider.system.SimpleMatchingSystemFinder;
import theDecider.system.SimpleStrategyTransformer;

/**
 *
 */
class StrategyFinderServiceIntegrationSpec extends Specification {
	
	def strategyFinderService
	
	def setup(){
		strategyFinderService.gameTheoryGamePopulator = Mock(PayoffMatrixPopulator)
		strategyFinderService.querier = Mock(Querier)
		strategyFinderService.complexityCalculator = Mock(ComplexityCalculator)
		strategyFinderService.gameTheoryManager = Mock(GameTheoryManager)
		strategyFinderService.simpleMatchingSystemFinder = Mock(SimpleMatchingSystemFinder)
		strategyFinderService.simpleStrategyTransformer = Mock(SimpleStrategyTransformer)
	}

    void "test that findUserSystemNashStrategies calls simpleMatchingSystemFinder"() {
		setup:
		Map params = [:]
		params.smiles = "c1ccc(cc1)C#N.O(C)C"
		params.cutoff = "0.5"
		
		when:
		strategyFinderService.findUserSystemNashStrategies(params)
		
		then:
		1*strategyFinderService.simpleMatchingSystemFinder.findMatchingSystems(_,_)
    }
	
    void "test that findUserSystemNashStrategies calls querier"() {
		setup:
		Map params = [:]
		params.smiles = "c1ccc(cc1)C#N.O(C)C"
		params.cutoff = "0.5"
		
		when:
		strategyFinderService.findUserSystemNashStrategies(params)
		
		then:
		1*strategyFinderService.querier.queryForSystems(_)
    }

    void "test that findUserSystemNashStrategies calls complexityCalculator"() {
		setup:
		Map params = [:]
		params.smiles = "c1ccc(cc1)C#N.O(C)C"
		params.cutoff = "0.5"
		
		when:
		strategyFinderService.findUserSystemNashStrategies(params)
		
		then:
		1*strategyFinderService.complexityCalculator.calculate(_,_)
    }
	
    void "test that findUserSystemNashStrategies calls gameTheoryManager"() {
		setup:
		Matrix dmx = Mock()
		dmx.getMatrixValues() >> [["choice1", "choice2", "Player1"],
			["choice1", "choice1", "Player2"]]
		Map params = [:]
		params.smiles = "c1ccc(cc1)C#N.O(C)C"
		params.cutoff = "0.5"
		
		when:
		strategyFinderService.findUserSystemNashStrategies(params)
		
		then:
		1*strategyFinderService.gameTheoryManager.findNashEquilibria(_,_)
    }

    void "test that findUserSystemNashStrategies calls gameTheoryGamePopulator"() {
		setup:
		Map params = [:]
		params.smiles = "c1ccc(cc1)C#N.O(C)C"
		params.cutoff = "0.5"
		
		when:
		strategyFinderService.findUserSystemNashStrategies(params)
		
		then:
		1*strategyFinderService.gameTheoryGamePopulator.constructGame(_)
    }

    void "test that findUserSystemNashStrategies calls simpleStrategyTransformer"() {
		setup:
		Map params = [:]
		params.smiles = "c1ccc(cc1)C#N.O(C)C"
		params.cutoff = "0.5"
		
		when:
		strategyFinderService.findUserSystemNashStrategies(params)
		
		then:
		1*strategyFinderService.simpleStrategyTransformer.transformStrategies(_,_)
    }
}
