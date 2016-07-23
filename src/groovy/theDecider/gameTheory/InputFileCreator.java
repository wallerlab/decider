package theDecider.gameTheory;

import java.io.File;
import java.util.Map;

/**
 * Creates game theory software input file
 * 
 * @author suzanne
 *
 */
public interface InputFileCreator {
	
	/**
	 * Creates a game theory software input file from given payoffs map in 
	 * given directory
	 * 
	 * @param File directory
	 * @param Map<?,?> payoffs
	 */
	void createFile(File directory, Map<?,?> payoffs);
	
}
