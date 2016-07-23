package theDecider.system;

import java.util.ArrayList;
import java.util.Map;

/**
 * Calculates complexity of QM methods for payoff values
 * 
 * @author suzanne
 *
 */
public interface ComplexityCalculator {
	
	/**
	 * Calculates the complexity of the Strategies for the given list of 
	 * MatchingSystems using the given params
	 * 
	 * @param matchingSystems
	 * @param params
	 */
	void calculate(ArrayList<?> matchingSystems, Map<?,?> params);
	
}
