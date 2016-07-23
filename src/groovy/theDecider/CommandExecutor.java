package theDecider;

import java.io.File;

/**
 * Executes commands through the command line
 * 
 * @author suzanne
 *
 */
public interface CommandExecutor {
	
	/**
	 * Executes commands through the command line in the specified directory
	 * 
	 * @param String command
	 * @param File directory
	 * @return String output
	 */
	String execute(String command, File directory);
	
}
