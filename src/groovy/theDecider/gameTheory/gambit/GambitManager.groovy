package theDecider.gameTheory.gambit

import theDecider.gameTheory.GameTheoryManager
import theDecider.models.Matrix

import javax.annotation.Resource

/**
 * Runs all things having to do with Gambit v14.1.0, the game theory software
 * 
 * @author suzanne
 *
 */
class GambitManager implements GameTheoryManager{
	
	@Resource
	def inputFileCreator
	@Resource
	def outputParser
	@Resource
	def commandExecutor
	@Resource
	def gambitPure
	@Resource
	def mainFolder

	/**
	 * Given a map of payoffs, finds Nash equilibria using Gambit
	 * 
	 * @param payoffs
	 * @return strategies
	 */
	@Override
	public Matrix findNashEquilibria(Map<?,?> payoffs, String smiles){
		
		//create directory for input file
		File directory = createDirectory(smiles)
		
		inputFileCreator.createFile(directory, payoffs)

		String gambitOutput = commandExecutor.execute(
			gambitPure + " input.nfg", directory)
		Matrix strategies = outputParser.parseOutput(
			gambitOutput, payoffs)

		//delete directory when finished
		directory.deleteDir()

		return strategies
		
	}

	/*
	 * Creates the working directory using the smiles string of the molecular
	 * system, stripping it of anything non-alphabetical and non-numerical
	 * 
	 */
	private File createDirectory(String smilesString){

		String smilesStringMod = smilesString.replaceAll(/[^a-zA-Z0-9]+/,"")
		File directory = new File(mainFolder, smilesStringMod)
		if(directory.exists()){
			int lastNum = 0
			mainFolder.eachDirMatch(~/${smilesStringMod}_\d*?/){ dir ->
				int folderNum = Integer.parseInt(dir.name.split("_")[1].split(/\./)[0])
				if(folderNum > lastNum){
					lastNum = folderNum
				}
			}
			lastNum++
			def folderName = smilesStringMod + "_" + lastNum
			directory = new File(mainFolder, folderName)
		}
		directory.mkdir()
		return directory
	}
	
}
