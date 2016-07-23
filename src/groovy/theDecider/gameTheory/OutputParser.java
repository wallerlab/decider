package theDecider.gameTheory;

import java.util.Map;

import theDecider.models.Matrix;
import theDecider.models.PayoffMatrix;

/**
 * Parses game theory software output
 * 
 * @author suzanne
 *
 */
public interface OutputParser {
	
	/**
	 * Given the output from the game theory software and a folder in which to
	 * look for the input file, returns the Nash equilibrium strategies from game
	 * 
	 * @param gameTheoryOutput
	 * @param payoffs
	 * @return strategies
	 */
	Matrix parseOutput(String gameTheoryOutput, Map<String, PayoffMatrix> payoffs);
	
}
