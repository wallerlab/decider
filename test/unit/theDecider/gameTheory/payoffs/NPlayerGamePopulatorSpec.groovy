package theDecider.gameTheory.payoffs

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*
import theDecider.models.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class NPlayerGamePopulatorSpec extends Specification {
	
	NPlayerMatrixPopulator ecsg = new NPlayerMatrixPopulator(
		playerNamesAndPayoffs: ["functional": "complexity", 
		"basisSet": "percentageError", "system": "tanimoto"])
	
	void "test that setPayoffValues sets payoffs"(){
		setup:
		Strategy method = new Strategy(basisSet: "LACVP*", functional: "B3LYP",
			percentageError: 102.96357638904976, complexity: 12.123, 
			system: "S22_1_01A", tanimoto: 0.123)
		PayoffMatrix basisSets = new NDimensionalPayoffMatrix()
		basisSets.playerStrategies = ["LACVP*", "aug-cc-pVDZ"]
		PayoffMatrix functionals = new NDimensionalPayoffMatrix()
		functionals.playerStrategies = ["B3LYP", "M06-2X"]
		PayoffMatrix similarities = new NDimensionalPayoffMatrix()
		similarities.playerStrategies = ["S22_1_01A", "S22_16_16A_16B"]
		ecsg.payoffs = ["basisSet":basisSets,"functional":
			functionals, "system": similarities]
		
		when:
		ecsg.setPayoffValues(method)
		
		then:
		ecsg.payoffs["basisSet"].getMatrix() == [[0,0,0]:102.96357638904976]
		ecsg.payoffs["functional"].getMatrix() == [[0,0,0]:12.123]
		ecsg.payoffs["system"].getMatrix() == [[0,0,0]:0.123]
	}
	
	void "test that setPayoffValues sets payoffs in the right position"(){
		setup:
		Strategy method = new Strategy(basisSet: "aug-cc-pVDZ", functional: "M06-2X",
			percentageError:102.96357638904976, complexity: 12.123, 
			system: "S22_1_01A", tanimoto: 0.123)
		PayoffMatrix basisSets = new NDimensionalPayoffMatrix()
		basisSets.playerStrategies = ["LACVP*", "aug-cc-pVDZ"]
		PayoffMatrix functionals = new NDimensionalPayoffMatrix()
		functionals.playerStrategies = ["B3LYP", "M06-2X"]
		PayoffMatrix similarities = new NDimensionalPayoffMatrix()
		similarities.playerStrategies = ["S22_1_01A", "S22_16_16A_16B"]
		ecsg.payoffs = ["basisSet":basisSets,"functional":
			functionals, "system": similarities]
		
		when:
		ecsg.setPayoffValues(method)
		
		then:
		ecsg.payoffs["basisSet"].getMatrix() == [[1,1,0]:102.96357638904976]
		ecsg.payoffs["functional"].getMatrix() == [[1,1,0]:12.123]
		ecsg.payoffs["system"].getMatrix() == [[1,1,0]:0.123]
	}
	
	void "test that setStrategies adds basis set, functional, and system to map"(){
		setup:
		Strategy method = new Strategy(basisSet: "aug-cc-pVDZ", functional: "M06-2X",
			system: "S22_1_01A")
		PayoffMatrix basisSets = new NDimensionalPayoffMatrix()
		basisSets.playerStrategies = a[0]
		PayoffMatrix functionals = new NDimensionalPayoffMatrix()
		functionals.playerStrategies = a[1]
		PayoffMatrix similarities = new NDimensionalPayoffMatrix()
		similarities.playerStrategies = a[2]
		ecsg.payoffs = ["basisSet":basisSets,"functional":
			functionals, "system": similarities]
		
		expect:
		ecsg.setStrategies(method)
		ecsg.payoffs["basisSet"].playerStrategies == b
		ecsg.payoffs["functional"].playerStrategies == c
		ecsg.payoffs["system"].playerStrategies == d
		
		where:
		a													|	b							|	c					|	d
		[[],[],[]]											|	["aug-cc-pVDZ"]				|	["M06-2X"]			|	["S22_1_01A"]
		[["LACVP*"], ["B3LYP"], ["S22_16_16A_16B"]]			|	["LACVP*", "aug-cc-pVDZ"]	|	["B3LYP", "M06-2X"]	|	["S22_16_16A_16B", "S22_1_01A"]
		[["aug-cc-pVDZ"], ["B3LYP"], ["S22_16_16A_16B"]]	|	["aug-cc-pVDZ"]				|	["B3LYP", "M06-2X"]	|	["S22_16_16A_16B", "S22_1_01A"]
		[["LACVP*"], ["M06-2X"], ["S22_16_16A_16B"]]		|	["LACVP*", "aug-cc-pVDZ"]	|	["M06-2X"]			|	["S22_16_16A_16B", "S22_1_01A"]
		[["LACVP*"], ["B3LYP"], ["S22_1_01A"]]				|	["LACVP*", "aug-cc-pVDZ"]	|	["B3LYP", "M06-2X"]	|	["S22_1_01A"]
	}
	
	void "test that setPlayers returns correctly instantiated map"(){
		when:
		ecsg.setPlayers()
		
		then:
		ecsg.payoffs["basisSet"] instanceof NDimensionalPayoffMatrix
		ecsg.payoffs["functional"] instanceof NDimensionalPayoffMatrix
		ecsg.payoffs["system"] instanceof NDimensionalPayoffMatrix
	}
	
	void "test that createPayoffsMap returns the correct payoffs map"(){
		setup:
		Strategy method1 = new Strategy(basisSet: "LACVP*", functional: "B3LYP",
			percentageError:102.96357638904976, complexity: 12.123, 
			system: "S22_1_01A", tanimoto: 0.123)
		Strategy method2 = new Strategy(basisSet: "aug-cc-pVDZ", functional: "B3LYP",
			percentageError: 97.5433333, complexity: 31.56, system: "S22_1_01A",
			tanimoto: 0.123)
		Strategy method3 = new Strategy(basisSet: "LACVP*", functional: "M06-2X",
			percentageError: 27.12345, complexity: 1.02, system: "S22_16_16A_16B",
			tanimoto: 0.345)
		Strategy method4 = new Strategy(basisSet: "aug-cc-pVDZ", functional: "M06-2X",
			percentageError: 0.00018, complexity: 4.36, system: "S22_16_16A_16B",
			tanimoto: 0.345)
		ArrayList<MolecularSystem> allJqParams = new ArrayList([method1, method2,
			method3, method4])
		
		when:
		Map allPayoffs = ecsg.constructGame(allJqParams)
		
		then:
		allPayoffs['basisSet'].playerStrategies == ["aug-cc-pVDZ", "LACVP*"] || allPayoffs[
			'basisSet'].playerStrategies == ["LACVP*", "aug-cc-pVDZ"]
		allPayoffs['basisSet'].getMatrix() == [[0, 0, 0]:102.96357638904976, 
			[0, 1, 0]:97.5433333, [1, 0, 1]:27.12345, [1, 1, 1]:1.8E-4] || allPayoffs[
			'basisSet'].getMatrix() == [[0, 0, 0]:97.5433333, [0, 1, 0]:102.96357638904976, 
			[1, 0, 1]:1.8E-4, [1, 1, 1]:27.12345]
		allPayoffs['functional'].playerStrategies == ["B3LYP", "M06-2X"] || allPayoffs[
			'functional'].playerStrategies == ["M06-2X", "B3LYP"]
		allPayoffs['functional'].getMatrix() == [[0, 0, 0]:12.123, [0, 1, 0]:31.56, 
			[1, 0, 1]:1.02, [1, 1, 1]:4.36] || allPayoffs['functional'
			].getMatrix() == [[0, 0, 0]:31.56, [0, 1, 0]:12.123, [1, 0, 1]:4.36, 
			[1, 1, 1]:1.02]
		allPayoffs['system'].playerStrategies == ["S22_1_01A", "S22_16_16A_16B"]
		allPayoffs['system'].getMatrix() == [[0, 0, 0]:0.123, [0, 1, 0]:0.123,
			[1, 0, 1]:0.345, [1, 1, 1]:0.345]

	}
}
