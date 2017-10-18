package theDecider.gameTheory.gambit

import spock.lang.*

class GambitManagerSpec extends Specification {
	GambitManager gm = new GambitManager()

    def setup() {
		gm.mainFolder = new File("test/Test_Folder")
		File dummyFolder1 = new File(gm.mainFolder, "c1ccccc1c1ccccc1")
		dummyFolder1.mkdir()
		for(int i = 0;i < 5; i++){
			File dummyFolder = new File(gm.mainFolder, "c1ccccc1c1ccccc1_${i+1}")
			dummyFolder.mkdir()
		}
    }

    def cleanup() {
		gm.mainFolder = new File("test/Test_Folder")
		File dummyFolder1 = new File(gm.mainFolder, "c1ccccc1c1ccccc1")
		dummyFolder1.delete()
		for(int i = 0;i < 5; i++){
			File dummyFolder = new File(gm.mainFolder, "c1ccccc1c1ccccc1_${i+1}")
			dummyFolder.delete()
		}
    }

	@Unroll
	void "test createDirectory creates the working directory"(){
		setup:
		gm.mainFolder = new File("test/Test_Folder")
		File newFile = new File(gm.mainFolder, folderName)
		gm.createDirectory(smilesString)

		expect:
		newFile.isDirectory()
		newFile.exists()

		cleanup:
		newFile.delete()

		where:
		smilesString 				|	folderName
		"C1CCCCC1.c1ccc(cc1)C#N"	|	"C1CCCCC1c1ccccc1CN"
		"c1ccc(cc1)C#N.O(C)C"		|	"c1ccccc1CNOCC_1"
		"c1ccccc1.c1ccccc1"			|	"c1ccccc1c1ccccc1_6"
	}
}
