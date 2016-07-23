package theDecider.system;

import java.util.ArrayList;
import java.util.List;

import theDecider.models.Matrix;
import theDecider.models.Strategy;

/**
 * 
 * 
 * @author suzanne
 *
 */
public interface StrategyTransformer {
	
	/**
	 * Transforms Nash Equilibrium strategies into what the UI will see
	 * 
	 * @param strategies
	 * @param allStrategies
	 * @return 
	 */
	List<?> transformStrategies(Matrix strategies, ArrayList<Strategy> 
		allStrategies);

}
