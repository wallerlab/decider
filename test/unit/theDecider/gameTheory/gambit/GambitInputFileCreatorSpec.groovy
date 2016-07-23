package theDecider.gameTheory.gambit

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*
import theDecider.gameTheory.gambit.GambitInputFileCreator;
import theDecider.models.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class GambitInputFileCreatorSpec extends Specification {
	
	GambitInputFileCreator gifc = new GambitInputFileCreator()
	
	void "test that recursiveCreatePayoffsString recursively creates payoffs string"(){
		when:
		PayoffMatrix payoffs1 = new NDimensionalPayoffMatrix(["Player1 choice1", 
			"Player1 choice2"], [[0,0]:1,[0,1]:2,[1,0]:3,[1,1]:4,[2,0]:5,[2,1]:6])
		PayoffMatrix payoffs2 = new NDimensionalPayoffMatrix(["Player2 choice1", 
			"Player2 choice2", "Player2 choice3"], [[0,0]:7,[0,1]:8,[1,0]:9,[1,1]:10,
			[2,0]:11,[2,1]:12])
		Map map = new TreeMap(["payoffs1": payoffs1, "payoffs2": payoffs2])
		gifc.playerNames = map.keySet()
		String ints = gifc.recursiveCreatePayoffsString(map, new StringBuilder(
			"{ \n"), 0, [])
		
		then:
		ints == '{ \n{ "" 1, 7 }\n{ "" 2, 8 }\n{ "" 3, 9 }\n{ "" 4, 10 }\n'+
		'{ "" 5, 11 }\n{ "" 6, 12 }\n'
	}
	
	void "test that recursiveCreatePayoffsString recursively creates payoffs string with payoffmatrixmap"(){
		when:
		PayoffMatrix payoffs1 = new NDimensionalPayoffMatrix(["Player1 choice1", "Player1 choice2"],
			[[0,0,0]: 1, [0,0,1]:2, [0,1,0]:3, [0,1,1]:4, [0,2,0]:5, [0,2,1]:6, [1,0,0]:7, [1,0,1]:8, [1,1,0]:9,
				[1,1,1]:10, [1,2,0]:11, [1,2,1]:12])
		PayoffMatrix payoffs2 = new NDimensionalPayoffMatrix(["Player2 choice1", "Player2 choice2", "Player2 choice3"],
			[[0,0,0]: 13, [0,0,1]:14, [0,1,0]:15, [0,1,1]:16, [0,2,0]:17, [0,2,1]:18, [1,0,0]:19, [1,0,1]:20, 
				[1,1,0]:21, [1,1,1]:22, [1,2,0]:23, [1,2,1]:24])
		PayoffMatrix payoffs3 = new NDimensionalPayoffMatrix(["Player3 choice1", "Player3 choice2"],
			[[0,0,0]: 25, [0,0,1]:26, [0,1,0]:27, [0,1,1]:28, [0,2,0]:29, [0,2,1]:30, [1,0,0]:31, [1,0,1]:32, 
				[1,1,0]:33, [1,1,1]:34, [1,2,0]:35, [1,2,1]:36])
		Map map = new TreeMap(["payoffs1": payoffs1, "payoffs2": payoffs2, "payoffs3": payoffs3])
		gifc.playerNames = map.keySet()
		String ints = gifc.recursiveCreatePayoffsString(map, new StringBuilder("{ \n"), 0, [])
		
		then:
		ints == '{ \n{ "" 1, 13, 25 }\n{ "" 2, 14, 26 }\n{ "" 3, 15, 27 }\n{ "" 4, 16, 28 }\n{ "" 5, 17, 29 }\n{ "" 6, 18, 30 }\n{ "" 7, 19, 31 }\n{ "" 8, 20, 32 }\n{ "" 9, 21, 33 }\n{ "" 10, 22, 34 }\n{ "" 11, 23, 35 }\n{ "" 12, 24, 36 }\n'
	}
	
    void "test that createFormattedString creates string of names"() {
		when:
		gifc.playerNames = new TreeSet(['payoffs1', 'payoffs2'])
		String names = gifc.createFormattedString(gifc.playerNames, "string")
		
		then:
		names == '{ "payoffs1" "payoffs2"  }'
    }
	
	void "test that createFormattedString creates string of ints"(){
		when:
		String ints = gifc.createFormattedString(["1","2"], "int")
		
		then:
		ints == '{ "" 1, 2 }'
	}
	
	void "test that createFormattedString recursively creates strings of ints"(){
		when:
		PayoffMatrix payoffs1 = new NDimensionalPayoffMatrix(["Player1 choice1", "Player1 choice2"],
			[[0,0]:1,[0,1]:2,[1,0]:3,[1,1]:4,[2,0]:5,[2,1]:6])
		PayoffMatrix payoffs2 = new NDimensionalPayoffMatrix(["Player2 choice1", "Player2 choice2", "Player2 choice3"],
			[[0,0]:7,[0,1]:8,[1,0]:9,[1,1]:10,[2,0]:11,[2,1]:12])
		Map map = new TreeMap(["payoffs1": payoffs1, "payoffs2": payoffs2])
		gifc.playerNames = map.keySet()
		String ints = gifc.createFormattedString(map, "neither")
		
		then:
		ints == '{ \n{ "" 1, 7 }\n{ "" 2, 8 }\n{ "" 3, 9 }\n{ "" 4, 10 }\n{ "" 5, 11 }\n{ "" 6, 12 }\n }'
	}
	
	void "test that createFormattedString recursively creates strings of strings"(){
		when:
		ArrayList<String> headers = new ArrayList<String>([["Player1 choice1", "Player1 choice2"],
			["Player2 choice1", "Player2 choice2", "Player2 choice3"]])
		String strings = gifc.createFormattedString(headers, "neither")
		
		then:
		strings == '{ \n{ "Player1 choice1" "Player1 choice2"  }\n{ "Player2 choice1" "Player2 choice2" "Player2 choice3"  }\n }'
	}
	
	void "test that createFile creates a file"(){
		when:
		PayoffMatrix payoffs1 = new NDimensionalPayoffMatrix(["Player1 choice1", "Player1 choice2"],
			[[0,0]:1,[0,1]:2,[1,0]:3,[1,1]:4,[2,0]:5,[2,1]:6])
		PayoffMatrix payoffs2 = new NDimensionalPayoffMatrix(["Player2 choice1", "Player2 choice2", "Player2 choice3"],
			[[0,0]:7,[0,1]:8,[1,0]:9,[1,1]:10,[2,0]:11,[2,1]:12])
		Map map = new TreeMap(["payoffs1": payoffs1, "payoffs2": payoffs2])
		File directory = new File("test game")
		directory.mkdir()
		gifc.createFile(directory, map)
		
		then:
		File file = new File(directory, "input.nfg")
		file.exists()
		
		cleanup:
		file.delete()
		directory.delete()
	}
	
	void "test that createFile populates file"(){
		when:
		PayoffMatrix payoffs1 = new NDimensionalPayoffMatrix(["Player1 choice1", "Player1 choice2"],
			[[0,0]:1,[0,1]:2,[1,0]:3,[1,1]:4,[2,0]:5,[2,1]:6])
		PayoffMatrix payoffs2 = new NDimensionalPayoffMatrix(["Player2 choice1", "Player2 choice2", "Player2 choice3"],
			[[0,0]:7,[0,1]:8,[1,0]:9,[1,1]:10,[2,0]:11,[2,1]:12])
		Map map = new TreeMap(["payoffs1": payoffs1, "payoffs2": payoffs2])
		File directory = new File("test game")
		directory.mkdir()
		gifc.createFile(directory, map)
		
		then:
		File file = new File(directory, "input.nfg")
		file.text == 'NFG 1 R "test game" { "payoffs1" "payoffs2"  }\n'+
			' \n{ \n{ "Player1 choice1" "Player1 choice2"  }\n'+
			'{ "Player2 choice1" "Player2 choice2" "Player2 choice3"  }\n }\n""\n \n{ \n' +
			'{ "" 1, 7 }\n{ "" 2, 8 }\n{ "" 3, 9 }\n{ "" 4, 10 }\n{ "" 5, 11 }\n'+
			'{ "" 6, 12 }\n }\n'
		
		cleanup:
		file.delete()
		directory.delete()
	}
}
