package theDecider.gameTheory;

import theDecider.models.PayoffMatrix;

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
	 * @param directory
	 * @param payoffs
	 */
	void createFile(File directory, Map<String,PayoffMatrix> payoffs);
	
}
