package theDecider.models;

/**
 * Payoff matrix
 * 
 * @author suzanne
 *
 */
public interface PayoffMatrix {

	/**
	 * Adds playerStrategy to PayoffMatrix
	 * 
	 * @param String playerStrategy
	 */
	void addPlayerStrategy(String playerStrategy);

	/**
	 * Returns true if playerStrategy in matrix
	 * 
	 * @param String playerStrategy
	 * @return boolean containsPlayerStrategy
	 */
	boolean containsPlayerStrategy(String playerStrategy);

	/**
	 * Returns index of playerStrategy
	 * 
	 * @param String playerStrategy
	 * @return Integer index
	 */
	Integer indexOfPlayerStrategy(String playerStrategy);
	
	/**
	 * Returns playerStrategy at given index
	 * 
	 * @param index
	 * @return playerStrategy
	 */
	String getStrategyAtIndex(int index);
	
	/**
	 * Returns number of playerStrategies
	 * 
	 * @return numPlayerStrategies
	 */
	int getNumPlayerStrategies();
	
}
