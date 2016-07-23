package theDecider.gameTheory;

import java.util.ArrayList;
import java.util.Map;

/**
 * Populates a map of PayoffMatrices for game theory software
 * 
 * @author suzanne
 *
 */
public interface PayoffMatrixPopulator {
	
	/**
	 * Creates a map of PayoffMatrices from matchingSystems
	 * 
	 * @param matchingSystems
	 * @return payoffs
	 */
	Map<?,?> constructGame(ArrayList<?> matchingSystems);
	
}
