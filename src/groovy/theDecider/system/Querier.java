package theDecider.system;

import java.util.ArrayList;
import java.util.List;

/**
 * Database querier
 * 
 * @author suzanne
 *
 */
public interface Querier {
	
	/**
	 * Gets methods corresponding to each matching system
	 * 
	 * @param matchingSystems
	 */
	List<?> queryForSystems(ArrayList<?> matchingSystems);
	
}
