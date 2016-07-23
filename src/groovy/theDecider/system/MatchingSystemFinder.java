package theDecider.system;

import java.util.List;

/**
 * Enum crawler of systems in database
 * 
 * @author suzanne
 *
 */
public interface MatchingSystemFinder {
	
	/**
	 * Finds systems that match given Tanimoto cutoff value as compared
	 * to given system
	 * 
	 * @param smiles
	 * @param cutoff
	 * @return matchingSystems
	 */
	List<?> findMatchingSystems(String smiles, Double cutoff);

}
