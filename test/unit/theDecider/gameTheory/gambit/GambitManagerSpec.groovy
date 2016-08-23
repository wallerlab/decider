package theDecider.gameTheory.gambit

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class GambitManagerSpec extends Specification {
	GambitManager gm = new GambitManager();

    def setup() {
    }

    def cleanup() {
    }
	
	void "test createDirectory creates the working directory"(){
		when:
		gm.mainFolder = new File("decider-workspace")
		gm.createDirectory("c1ccc(cc1)C#N.O(C)C")
		
		then:
		File newFile = new File(gm.mainFolder, "c1ccc(cc1)C#N.O(C)C")
		newFile.isDirectory() && newFile.exists()
		
		cleanup:
		newFile.delete()
	}
}
