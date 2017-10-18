package theDecider.gameTheory.gambit

import theDecider.gameTheory.InputFileCreator
import theDecider.models.PayoffMatrix

/**
 * Creates input files for use with Gambit v14.1.0
 * 
 * @author suzanne
 *
 */
class GambitInputFileCreator implements InputFileCreator{
	
	Set playerNames = new TreeSet()

	/**
	 * Takes Map of payoffs and creates a Gambit input file in given directory
	 * 
	 * @param payoffs
	 * @param directory
	 */
	@Override
	public void createFile(File directory, Map<String, PayoffMatrix> payoffs){
		
		playerNames = payoffs.keySet()
		File inputFile = new File(directory, "input.nfg")
		inputFile.createNewFile()

		String namesString = createFormattedString(playerNames, "string")
		List<String> playerStrategies = new ArrayList()
		for(String playerName: playerNames){
			playerStrategies << payoffs[playerName].playerStrategies
		}
		String playerStrategiesString = createFormattedString(playerStrategies, "neither")
		String payoffsString = createFormattedString(payoffs, "neither")

		inputFile.withWriter(){out ->
			[
				out.writeLine('NFG 1 R "' + directory.name + '" '+ namesString),
				out.writeLine(" "),
				out.writeLine(playerStrategiesString),
				out.writeLine('""'),
				out.writeLine(" "),
				out.writeLine(payoffsString)
			]
		}
		
	}

	/*
	 * Recursively creates Strings in correct format for input file
	 * 
	 */
	private String createFormattedString(set, String type){
		
		StringBuilder namesBuilder = new StringBuilder()
		namesBuilder << '{ '
		if(type == "string"){
			for(String paramName : set){
				namesBuilder << '"' + paramName + '" '
			}
		}
		else if(type == "int"){
			namesBuilder << '"" '
			String prefix = ''
			for(String paramName : set){
				namesBuilder << prefix + paramName
				prefix = ', '
			}
		}
		else{
			namesBuilder << '\n'
			if(set instanceof ArrayList){
				for(int i = 0; i < set.size(); i++){
					String stringString = createFormattedString(set[i], "string")
					namesBuilder << stringString + '\n'
				}
			}
			else{
				namesBuilder = recursiveCreatePayoffsString(set, namesBuilder, 0, [])
			}
		}
		namesBuilder << ' }'
		return namesBuilder.toString()
		
	}

	/*
	 * Creates payoffs string (as StringBuilder) for any number of players
	 * 
	 */
	private StringBuilder recursiveCreatePayoffsString(Map payoffs, 
		StringBuilder namesBuilder, int index, List<Integer> indicesToGet) {
		
		for(int i = 0; i < payoffs[playerNames[0]].getSizeAtDepth(index); i++){
			indicesToGet[index] = i
			if(index == payoffs[playerNames[0]].getMatrixDimensions()-1){
				List<String> newSet = []
				for(String paramName : playerNames){
					newSet.add(payoffs[paramName].getAt(indicesToGet))
				}
				String intString = createFormattedString(newSet, "int")
				namesBuilder << intString + '\n'
			}
			else{
				recursiveCreatePayoffsString(payoffs, namesBuilder, index+1, indicesToGet)
			}
		}
		return namesBuilder
	}

}
