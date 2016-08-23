package theDecider

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import theDecider.GenericCommandExecutor;

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class GenericCommandExecutorSpec extends Specification {
	
	GenericCommandExecutor cmdExec = new GenericCommandExecutor()
	
	void "test that execute executes a gambit command"() {
		setup:
		String gambitPure = System.getenv("gambit_enumpure")
		
		when:
		String output = cmdExec.execute(gambitPure + " testInput.nfg",
			new File("test/Test_Folder/"))
		
		then:
		output == 'NE,0,0,1,0,1'
	}
	
	void "test that execute executes a gambit command with more than 1 Nash Equilibrium"() {
		setup:
		String gambitPure = System.getenv("gambit_enumpure")
		
		when:
		String output = cmdExec.execute(gambitPure + " testInput2.nfg",
			new File("test/Test_Folder/"))
		
		then:
		output == 'NE,1,0,1,0NE,0,1,0,1'
	}
}
