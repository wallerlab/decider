package theDecider.system

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*
import theDecider.models.*
import theDecider.system.SimpleStrategyTransformer;

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class SimpleStrategyTransformerSpec extends Specification {
	
	SimpleStrategyTransformer sst = new SimpleStrategyTransformer()
	
	void "test calcAbsWeight"(){
		expect:
		sst.calcAbsWeight(b)
		b[1].normWeight == a
		
		where:
		a					|	b
		-0.04631653182431824	|	[new OutputMethod(basisSet: "aug-cc-pVDZ", functional: "M06-2X",
			weight: 0.07561187899999999, numAppearances: 5), new OutputMethod(
			basisSet: "LACVP*", functional: "M06-2X", weight: -0.0087552, 
			numAppearances: 2), new OutputMethod(basisSet: "aug-cc-pVDZ", 
			functional: "B3LYP", weight: 0.0, numAppearances: 4)]
	}
	
	@Unroll
	void "test calcWeight"(){
		expect:
		a == sst.calcWeight(b)
		
		where:
		a					|	b
		0.07561187899999999	|	[new Strategy(basisSet: "aug-cc-pVDZ", functional: "M06-2X",
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase(), tanimoto: 0.123, 
			percentageError: 1.234, complexity: 0.345), new Strategy(
			basisSet: "aug-cc-pVDZ", functional: "M06-2X", 
			system: "CH32O_C6H5CN_LP_away_eclipsed_3.0A".toLowerCase(), 
			tanimoto: 0.851, percentageError: 2.234, complexity: 0.052)]
		-0.0087552			|	[new Strategy(basisSet: "LACVP*", functional: "M06-2X",
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase(), 
			tanimoto: 0.912, percentageError: 0.008, complexity: -1.2)]
		0.0					|	[new Strategy(basisSet: "aug-cc-pVDZ", 
			functional: "B3LYP", system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase())]
			 
	}

    void "test getMethodsForTable returns arraylist of methods that match strategy"(){
		setup:
		Matrix strategies = new NDimensionalMatrix([[0]: new Strategy(
			basisSet: "aug-cc-pVDZ", functional: "M06-2X", 
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase())])
		Strategy dm = new Strategy(basisSet: "aug-cc-pVDZ", functional: "M06-2X",
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase(), 
			tanimoto: 0.123, percentageError: 1.234, complexity: 0.345)
		Strategy dm1 = new Strategy(basisSet: "LACVP*", functional: "M06-2X",
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase(), 
			tanimoto: 0.912, percentageError: 0.008, complexity: -1.2)
		Strategy dm2 = new Strategy(basisSet: "aug-cc-pVDZ", functional: "B3LYP",
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase(), 
			tanimoto: 0.784, percentageError: 0.012, complexity: 0.934)
		Strategy dm11 = new Strategy(basisSet: "aug-cc-pVDZ", functional: "M06-2X",
			system: "CH32O_C6H5CN_LP_away_eclipsed_3.0A".toLowerCase(), 
			tanimoto: 0.851, percentageError: 2.234, complexity: 0.052)
		ArrayList<Strategy> matchingSystems = [dm, dm1, dm2, dm11]
		
		when:
		def methodOptions = sst.transformStrategies(strategies, matchingSystems)
		
		then:
		methodOptions.size() == 1
		methodOptions[0].basisSet == "aug-cc-pVDZ"
		methodOptions[0].functional == "M06-2X"
	}
	
	void "test getMethodsForTable returns arraylist of methods that match more than one strategy"(){
		setup:
		Matrix strategies = new NDimensionalMatrix([[0]: new Strategy(
			basisSet: "aug-cc-pVDZ", functional: "M06-2X", 
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase()), [1]:
			new Strategy(basisSet: "LACVP*", functional: "B3LYP",
			system: "CH32O_C6H5CN_LP_away_eclipsed_3.0A".toLowerCase())])
		Strategy dm = new Strategy(basisSet: "aug-cc-pVDZ", functional: "M06-2X",
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase(), 
			tanimoto: 0.123, percentageError: 1.234, complexity: 0.345)
		Strategy dm1 = new Strategy(basisSet: "LACVP*", functional: "M06-2X",
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase(), 
			tanimoto: 0.912, percentageError: 0.008, complexity: -1.2)
		Strategy dm2 = new Strategy(basisSet: "aug-cc-pVDZ", functional: "B3LYP",
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase(), 
			tanimoto: 0.784, percentageError: 0.012, complexity: 0.934)
		Strategy dm11 = new Strategy(basisSet: "aug-cc-pVDZ", functional: "M06-2X",
			system: "CH32O_C6H5CN_LP_away_eclipsed_3.0A".toLowerCase(), 
			tanimoto: 0.851, percentageError: 2.234, complexity: 0.052)
		Strategy dm12 = new Strategy(basisSet: "LACVP*", functional: "B3LYP",
			system: "CH32O_C6H5CN_LP_away_eclipsed_3.0A".toLowerCase(), 
			tanimoto: 0.212, percentageError: 0.718, complexity: -0.347)
		ArrayList<Strategy> matchingSystems = [dm, dm1, dm2, dm11, dm12]
		
		when:
		def methodOptions = sst.transformStrategies(strategies, matchingSystems)
		
		then:
		methodOptions.size() == 2
		methodOptions[0].basisSet == "aug-cc-pVDZ"
		methodOptions[0].functional == "M06-2X"
		methodOptions[1].basisSet == "LACVP*"
		methodOptions[1].functional == "B3LYP"
	}
	
	void "test getMethodsForTable returns empty arraylist if strategies is empty"(){
		setup:
		Matrix strategies = Mock()
		strategies.isEmpty() >> true
		Strategy dm = new Strategy(basisSet: "aug-cc-pVDZ", functional: "M06-2X",
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase(), 
			tanimoto: 0.123, percentageError: 1.234, complexity: 0.345)
		Strategy dm1 = new Strategy(basisSet: "LACVP*", functional: "M06-2X",
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase(), 
			tanimoto: 0.912, percentageError: 0.008, complexity: -1.2)
		Strategy dm2 = new Strategy(basisSet: "aug-cc-pVDZ", functional: "B3LYP",
			system: "CH32O_C6H5CN_LP_away_eclipsed_4.6A".toLowerCase(), 
			tanimoto: 0.784, percentageError: 0.012, complexity: 0.934)
		ArrayList<Strategy> matchingSystems = [dm, dm1, dm2]
		
		when:
		def methodOptions = sst.transformStrategies(strategies, matchingSystems)
		
		then:
		methodOptions.size() == 0
	}
}
