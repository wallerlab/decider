package theDecider.gameTheory;

import java.util.Map;

import theDecider.models.Matrix;

/**
 * Manages everything to do with game theory software in The Decider
 * 
 * @author suzanne
 *
 */
public interface GameTheoryManager {
	
	/**
	 * Given a Map of payoffs and a directory in which to work, returns a
	 * Matrix of strategies
	 * 
	 * @param Map<?,?> payoffs
	 * @param File directory
	 * @return Matrix strategies
	 */
	//TODO: remove String when sure it's working because will be deleting input files
	Matrix findNashEquilibria(Map<?,?> payoffs, String smiles);
	
}
