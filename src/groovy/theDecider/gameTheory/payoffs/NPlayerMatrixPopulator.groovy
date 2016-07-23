package theDecider.gameTheory.payoffs

import java.util.ArrayList;
import java.util.Map;
import javax.annotation.Resource;

import theDecider.gameTheory.PayoffMatrixPopulator;
import theDecider.models.*;

/**
 * Populates a Map of PayoffMatrices for a 3-player game consisting of
 * Error (MAPD), Complexity score, and Similarity score
 * 
 * @author suzanne
 *
 */
class NPlayerMatrixPopulator implements PayoffMatrixPopulator{
	
	private Map<String, PayoffMatrix> payoffs
	
	@Resource
	private Map<String, String>  playerNamesAndPayoffs

	/**
	 * Creates a map of PayoffMatrices from allStrategies
	 * 
	 * @param allStrategies
	 * @return payoffs
	 */
	public Map<?, ?> constructGame(ArrayList<?> allStrategies) {

		setPlayers()

		for(Strategy method : allStrategies){
			setStrategies(method)
			setPayoffValues(method)
		}

		return payoffs
		
	}
	
	/*
	 * Sets up the map for an MAPD vs. Complexity vs. Similarity game
	 *
	 */
	private void setPlayers(){
		
		payoffs = [:]
		for(playerName in playerNamesAndPayoffs.keySet()){
			payoffs[playerName] = new NDimensionalPayoffMatrix()
		}
		
	}

	/*
	 * Sets the player names
	 * 
	 */
	private void setStrategies(Strategy method) {
		
		for(playerName in playerNamesAndPayoffs.keySet()){
			if(!payoffs[playerName].containsPlayerStrategy(method."${playerName}")){
				payoffs[playerName].addPlayerStrategy(method."${playerName}")
			}
		}
		
	}
	
	/*
	 * Sets the payoff values
	 *
	 */
	private void setPayoffValues(Strategy method) {
		
		List<Integer> indices = new ArrayList<Integer>()
		for(playerName in playerNamesAndPayoffs.keySet()){
			indices << payoffs[playerName].indexOfPlayerStrategy(method."${playerName}")
		}
		for(player in playerNamesAndPayoffs){
			payoffs[player.key].addAt(method."${player.value}", indices)
		}

	}

}
