package theDecider.gameTheory.gambit

import java.util.ArrayList;
import java.util.List;

import theDecider.gameTheory.OutputParser
import theDecider.models.NDimensionalMatrix
import theDecider.models.Matrix
import theDecider.models.PayoffMatrix
import theDecider.models.Strategy

/**
 * Reads output from Gambit v14.1.0, the game theory program
 * 
 * (Works only with pure strategies at the moment)
 *
 */
class GambitOutputParser implements OutputParser{
	
	List<String> playerNames = ["functional", "basisSet", "system"]

	/**
	 * Given the output from Gambit and the game's payoff matrix, returns the 
	 * Nash Equilibrium player strategies from game
	 * 
	 * @param gambitOutput
	 * @param payoffs
	 * @return strategies
	 */
	@Override
	public Matrix parseOutput(String gambitOutput, Map<String, PayoffMatrix> payoffs) {
		
		ArrayList<String> nashEquilibria = Arrays.asList(gambitOutput.split("NE,"))
		nashEquilibria.removeAll('')
		
		int numPlayers = payoffs.size()
		List<Integer> numPlayerStrategiesList = new ArrayList<Integer>()
		for(int i = 0; i < numPlayers; i++){
			numPlayerStrategiesList << payoffs[playerNames[i]].getNumPlayerStrategies()
		}
		
		Matrix allNashStrategyIndices = getAllNashStrategyIndices(nashEquilibria, 
			numPlayerStrategiesList)
		
		Matrix strategies = transformStrategies(allNashStrategyIndices, payoffs)
		return strategies;
		
	}

	/*
	 * Returns all Nash Equilibrium strategy indices for each player
	 * 
	 * (Works only with pure strategies at the moment)
	 * 
	 */
	private Matrix getAllNashStrategyIndices(ArrayList nashEquilibria, 
		ArrayList numPlayerStrategiesList) {
		
		Matrix allNashStrategyIndices = new NDimensionalMatrix()
		for(String nashEquilibrium: nashEquilibria){
			String[] nashEquilibriumArray = nashEquilibrium.split(",")
			Integer beginNum = 0
			for(int i = 0; i < numPlayerStrategiesList.size(); i++){
				int numPlayerStrategies = numPlayerStrategiesList[i]
				ArrayList<String> singlePlayerStrategyList = Arrays.asList(
						nashEquilibriumArray[beginNum..<beginNum+numPlayerStrategies])
				int nashStrategy = singlePlayerStrategyList.indexOf("1")
				allNashStrategyIndices.addAt(nashStrategy,
					[nashEquilibria.indexOf(nashEquilibrium), i])
				beginNum += numPlayerStrategies
			}
		}
		return allNashStrategyIndices
		
	}
	
	/*
	 * Returns a Matrix of Strings with the full strategy names
	 * 
	 */
	private Matrix transformStrategies(Matrix allNashStrategyIndices, 
		Map<String, PayoffMatrix> payoffs) {
		
		Matrix nashPlayerStrategies = new NDimensionalMatrix()
		
		for(nashIndex in allNashStrategyIndices.getMatrix()){
			String playerName = playerNames[nashIndex.key[1]]
			def nashStrategy = nashPlayerStrategies.getAt([nashIndex.key[0]])
			if(nashStrategy == 0){
				nashStrategy = new Strategy()
				nashPlayerStrategies.addAt(nashStrategy, [nashIndex.key[0]])
			}
			nashStrategy."$playerName" = payoffs[playerName].getStrategyAtIndex(
				nashIndex.value)
		}
		return nashPlayerStrategies
		
	}
	
}
