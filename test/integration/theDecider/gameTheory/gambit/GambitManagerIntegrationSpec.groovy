package theDecider.gameTheory.gambit



import org.codehaus.groovy.grails.commons.GrailsApplication
import spock.lang.*
import theDecider.CommandExecutor;
import theDecider.gameTheory.*
import theDecider.models.NDimensionalMatrix
import theDecider.services.*
import grails.util.Holders

/**
 *
 */
class GambitManagerIntegrationSpec extends Specification {
	
	def gameTheoryManager
	
    def setup() {
		gameTheoryManager.inputFileCreator = Mock(InputFileCreator)
		gameTheoryManager.outputParser = Mock(OutputParser)
		gameTheoryManager.commandExecutor = Mock(CommandExecutor)
		gameTheoryManager.gambitPure = System.getenv("gambit_enumpure")
		gameTheoryManager.commandExecutor.execute(_,_) >> 'NE,1,0,1,0NE,0,1,0,1'
		NDimensionalMatrix dmx = Mock()
		dmx.matrix >> [["Player1 choice1", "Player1 choice2"],
			["Player2 choice1", "Player2 choice2"]]
		gameTheoryManager.outputParser.parseOutput(_,_) >> dmx
		gameTheoryManager.mainFolder = new File("test/Test_Folder/")
    }
	
    void "test that findNashEquilibria finds Nash Equilibria"() {
		setup:
		def payoffs = ["basis sets":[["def2-QZVP", "SVP", "TZVP"], 
			["1", "2", "3", "4"], ["1", "2", "3", "4"], ["1", "2", "3", "4"]], 
			"functionals":[["B3LYP", "B97-D3"], ["5","6","7","8"], 
			["5","6","7","8"]]]
		
		when:
		def strategies = gameTheoryManager.findNashEquilibria(payoffs, "GMTest")
		
		then:
		strategies.matrix == [["Player1 choice1", "Player1 choice2"],
			["Player2 choice1", "Player2 choice2"]]
    }

    void "test that findNashEquilibria calls gameTheoryInputFileCreator"() {
		setup:
		def payoffs = ["basis sets":[["def2-QZVP", "SVP", "TZVP"], 
			["1", "2", "3", "4"], ["1", "2", "3", "4"], ["1", "2", "3", "4"]], 
			"functionals":[["B3LYP", "B97-D3"], ["5","6","7","8"], 
			["5","6","7","8"]]]
		
		when:
		def strategies = gameTheoryManager.findNashEquilibria(payoffs, "GMTest")
		
		then:
		1*gameTheoryManager.inputFileCreator.createFile(_,_)
    }

    void "test that findNashEquilibria calls commandExecutor"() {
		setup:
		def payoffs = ["basis sets":[["def2-QZVP", "SVP", "TZVP"], 
			["1", "2", "3", "4"], ["1", "2", "3", "4"], ["1", "2", "3", "4"]], 
			"functionals":[["B3LYP", "B97-D3"], ["5","6","7","8"], 
			["5","6","7","8"]]]
		
		when:
		def strategies = gameTheoryManager.findNashEquilibria(payoffs, "GMTest")
		
		then:
		gameTheoryManager.commandExecutor.execute(_,_) >> 'NE,1,0,1,0NE,0,1,0,1'
    }

    void "test that findNashEquilibria calls gameTheoryOutputParser"() {
		setup:
		def payoffs = ["basis sets":[["def2-QZVP", "SVP", "TZVP"], 
			["1", "2", "3", "4"], ["1", "2", "3", "4"], ["1", "2", "3", "4"]], 
			"functionals":[["B3LYP", "B97-D3"], ["5","6","7","8"], 
			["5","6","7","8"]]]
		NDimensionalMatrix dmx = Mock()
		dmx.matrix >> [["Player1 choice1", "Player1 choice2"],
			["Player2 choice1", "Player2 choice2"]]
		
		when:
		def strategies = gameTheoryManager.findNashEquilibria(payoffs, "GMTest")
		
		then:
		gameTheoryManager.outputParser.parseOutput(_,_) >> dmx
		
		cleanup:
		new File("test/Test_Folder/GMTest").deleteDir()
    }
}
