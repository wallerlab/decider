package theDecider.models

/**
 * N-dimensional PayoffMatrix
 * 
 * @author suzanne
 *
 */
class NDimensionalPayoffMatrix extends NDimensionalMatrix implements PayoffMatrix{
	
	private List<?> playerStrategies
	
	/**
	 * Constructs an empty NDimensionalPayoffMatrix
	 *
	 */
	NDimensionalPayoffMatrix(){
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
	NDimensionalPayoffMatrix(ArrayList<String> playerStrategies,
		Map<ArrayList<Integer>,?> payoffs){
		super(payoffs)
		this.playerStrategies = playerStrategies
	}
	
	/**
	 * Adds playerStrategy to NDimensionalPayoffMatrix
	 * 
	 * @param playerStrategies
	 */
	void addPlayerStrategy(String playerStrategy){
		this.playerStrategies << playerStrategy
	}
	
	/**
	 * Returns true if playerStrategy in matrix
	 * 
	 * @param playerStrategy
	 * @return containsPlayerStrategy
	 */
	boolean containsPlayerStrategy(String playerStrategy){
		return this.playerStrategies.contains(playerStrategy)
	}
	
	/**
	 * Returns index of playerStrategy
	 * 
	 * @param playerStrategy
	 * @return index
	 */
	Integer indexOfPlayerStrategy(String playerStrategy){
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
	String getStrategyAtIndex(int index){
		return this.playerStrategies[index]
	}
	
	/**
	 * Returns number of playerStrategies
	 * 
	 * @return numPlayerStrategies
	 */
	int getNumPlayerStrategies(){
		return this.playerStrategies.size()
	}

}
