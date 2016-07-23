package theDecider.models

import java.util.ArrayList;
import java.util.Map;

/**
 * N-dimensional PayoffMatrix
 * 
 * @author suzanne
 *
 */
class NDimensionalPayoffMatrix extends NDimensionalMatrix implements PayoffMatrix{
	
	private List<?> playerStrategies;
	
	/**
	 * Constructs an empty NDimensionalPayoffMatrix
	 *
	 */
	public NDimensionalPayoffMatrix(){
		super()
		this.playerStrategies = []
	}
	
	/**
	 * Constructs a NDimensionalPayoffMatrix from an ArrayList of playerStrategies and an
	 * ArrayList of ArrayLists
	 *
	 * @param playerStrategies
	 * @param payoffs
	 */
	public NDimensionalPayoffMatrix(ArrayList<String> playerStrategies, 
		Map<ArrayList<Integer>,?> payoffs){
		super(payoffs)
		this.playerStrategies = playerStrategies
	}
	
	/**
	 * Adds playerStrategy to NDimensionalPayoffMatrix
	 * 
	 * @param playerStrategies
	 */
	public void addPlayerStrategy(String playerStrategy){
		this.playerStrategies << playerStrategy
	}
	
	/**
	 * Returns true if playerStrategy in matrix
	 * 
	 * @param playerStrategy
	 * @return containsPlayerStrategy
	 */
	public boolean containsPlayerStrategy(String playerStrategy){
		return this.playerStrategies.contains(playerStrategy)
	}
	
	/**
	 * Returns index of playerStrategy
	 * 
	 * @param playerStrategy
	 * @return index
	 */
	public Integer indexOfPlayerStrategy(String playerStrategy){
		if(this.containsPlayerStrategy(playerStrategy)){
			return this.playerStrategies.indexOf(playerStrategy)
		}
		else return null
	}
	
	/**
	 * Returns playerStrategy at given index
	 * 
	 * @param index
	 * @return playerStrategy
	 */
	public String getStrategyAtIndex(int index){
		return this.playerStrategies[index]
	}
	
	/**
	 * Returns number of playerStrategies
	 * 
	 * @return numPlayerStrategies
	 */
	public int getNumPlayerStrategies(){
		return this.playerStrategies.size()
	}

}
