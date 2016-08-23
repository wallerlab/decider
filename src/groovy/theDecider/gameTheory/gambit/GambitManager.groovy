package theDecider.gameTheory.gambit

import java.io.File;
import java.util.Map;

import grails.util.Holders
import theDecider.gameTheory.InputFileCreator
import theDecider.gameTheory.OutputParser
import theDecider.gameTheory.GameTheoryManager
import theDecider.models.Matrix
import theDecider.models.PayoffMatrix;

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
	public Matrix findNashEquilibria(
		Map<String,PayoffMatrix> payoffs, String smiles){
		
		//create directory for input file
		File directory = createDirectory(smiles)
		
		inputFileCreator.createFile(directory, payoffs)
		prtinln """%%%%% gambitPure is ${gambitPure} """
		prtinln """%%%%% directory is ${directory} """
		String gambitOutput = commandExecutor.execute(
			gambitPure + " input.nfg", directory)
		Matrix strategies = outputParser.parseOutput(
			gambitOutput, payoffs)
		return strategies
		
	}

	/*
	 * Creates the working directory using the smiles string of the molecular
	 * system
	 * 
	 */
	private File createDirectory(String smilesString){
		
		//TODO: create a better directory name
		File directory = new File(mainFolder, smilesString)
		if(directory.exists()){
			//TODO: find the last number of folders with the same name
			def num 
			def folderName = smilesString + "_" + num
			directory = new File(mainFolder, folderName)
		}
		directory.mkdir()
		return directory
	}
	
}
