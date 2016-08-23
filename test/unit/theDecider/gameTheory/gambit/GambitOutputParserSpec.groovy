package theDecider.gameTheory.gambit

import java.util.ArrayList;

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*
import theDecider.models.Matrix
import theDecider.models.NDimensionalMatrix
import theDecider.models.NDimensionalPayoffMatrix
import theDecider.models.PayoffMatrix
import theDecider.models.Strategy

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class GambitOutputParserSpec extends Specification {
	
	GambitOutputParser gop = new GambitOutputParser()
	
	@Unroll
	void "test that getAllNashStrategyIndices gets nash equilibrium indices"(){
		expect:
		Matrix neIndices = gop.getAllNashStrategyIndices(a,b)
		neIndices.getMatrix() == c
		
		where:
		a						|	b		|	c
		["1,0,1,0", "0,1,0,1"]	|	[2, 2]	|	[[0,0]:0,[0,1]:0,[1,0]:1,[1,1]:1]
		["1,0,0,1"]				|	[2, 2]	|	[[0,0]:0,[0,1]:1]
		["1,0,0,1,0"]			|	[2, 3]	|	[[0,0]:0,[0,1]:1]
		["0,1,1,0,0"]			|	[2, 3]	|	[[0,0]:1,[0,1]:0]
	}
	
	void "test transformStrategies"(){
		setup:
		Matrix allStrategiesIndicesNE = new NDimensionalMatrix([0,0]:1,[0,1]:0,
			[0,2]:0,[1,0]:1,[1,1]:1, [1,2]:1)
		PayoffMatrix payoffs1 = new NDimensionalPayoffMatrix(["Player1 choice1", 
			"Player1 choice2"], [[0,0,0]: 1, [0,0,1]:2, [0,1,0]:3, [0,1,1]:4, 
			[0,2,0]:5, [0,2,1]:6, [1,0,0]:7, [1,0,1]:8, [1,1,0]:9,[1,1,1]:10, 
			[1,2,0]:11, [1,2,1]:12])
		PayoffMatrix payoffs2 = new NDimensionalPayoffMatrix(["Player2 choice1", 
			"Player2 choice2", "Player2 choice3"], [[0,0,0]: 13, [0,0,1]:14, 
			[0,1,0]:15, [0,1,1]:16, [0,2,0]:17, [0,2,1]:18, [1,0,0]:19, [1,0,1]:20, 
			[1,1,0]:21, [1,1,1]:22, [1,2,0]:23, [1,2,1]:24])
		PayoffMatrix payoffs3 = new NDimensionalPayoffMatrix(["Player3 choice1", 
			"Player3 choice2"], [[0,0,0]: 25, [0,0,1]:26, [0,1,0]:27, [0,1,1]:28, 
			[0,2,0]:29, [0,2,1]:30, [1,0,0]:31, [1,0,1]:32, [1,1,0]:33, [1,1,1]:34, 
			[1,2,0]:35, [1,2,1]:36])
		Map values = new TreeMap(["functional": payoffs1, "basisSet": payoffs2, 
			"system": payoffs3])
		
		when:
		Matrix nePlayerStrategies = gop.transformStrategies(allStrategiesIndicesNE,
			values)
		
		then:
		nePlayerStrategies.getSizeAtDepth(0) == 2
		nePlayerStrategies.getMatrixValues()[0] == new Strategy(functional: "Player1 choice2",
			basisSet: "Player2 choice1", system: "Player3 choice1")
	}
	
	@Unroll
    void "test that parseOutput creates paramsList"() {
		setup:
		PayoffMatrix payoffs1 = new NDimensionalPayoffMatrix(["Player1 choice1", 
			"Player1 choice2"], [[0,0,0]: 1, [0,0,1]:2, [0,1,0]:3, [0,1,1]:4, 
			[0,2,0]:5, [0,2,1]:6, [1,0,0]:7, [1,0,1]:8, [1,1,0]:9,[1,1,1]:10, 
			[1,2,0]:11, [1,2,1]:12])
		PayoffMatrix payoffs2 = new NDimensionalPayoffMatrix(["Player2 choice1", 
			"Player2 choice2", "Player2 choice3"], [[0,0,0]: 13, [0,0,1]:14, 
			[0,1,0]:15, [0,1,1]:16, [0,2,0]:17, [0,2,1]:18, [1,0,0]:19, [1,0,1]:20, 
			[1,1,0]:21, [1,1,1]:22, [1,2,0]:23, [1,2,1]:24])
		PayoffMatrix payoffs3 = new NDimensionalPayoffMatrix(["Player3 choice1", 
			"Player3 choice2"], [[0,0,0]: 25, [0,0,1]:26, [0,1,0]:27, [0,1,1]:28, 
			[0,2,0]:29, [0,2,1]:30, [1,0,0]:31, [1,0,1]:32, [1,1,0]:33, [1,1,1]:34, 
			[1,2,0]:35, [1,2,1]:36])
		Map payoffs = new TreeMap(["functional": payoffs1, "basisSet": payoffs2, 
			"system": payoffs3])
		
		expect:
		Matrix neStrategies = gop.parseOutput(a, payoffs)
		neStrategies.getMatrixValues()[0].equals(b)
		neStrategies.getSizeAtDepth(0) == c
		
		where:
		a									|	b					|	c
		"NE,1,0,1,0,0,1,0NE,0,1,0,1,0,0,1"	|	new Strategy(functional: "Player1 choice1",
			basisSet: "Player2 choice1", system: "Player3 choice1")	|	2
		"NE,1,0,0,1,0,0,1"					|	new Strategy(functional: "Player1 choice1",
			basisSet: "Player2 choice2", system: "Player3 choice2")	|	1
		"NE,1,0,0,1,0,1,0"					|	new Strategy(functional: "Player1 choice1",
			basisSet: "Player2 choice2", system: "Player3 choice1")	|	1
		"NE,0,1,1,0,0,0,1"					|	new Strategy(functional: "Player1 choice2",
			basisSet: "Player2 choice1", system: "Player3 choice2")	|	1
    }
	
	
}

