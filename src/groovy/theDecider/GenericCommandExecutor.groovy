package theDecider

import java.io.File;
import java.io.IOException;

/**
 * Executes command line commands
 * 
 * @author suzanne
 *
 */
class GenericCommandExecutor implements CommandExecutor{
	
	/**
	 * Given a String and a directory, executes the string as a 
	 * terminal command in the given directory and returns
	 * the output.
	 *
	 * @param String command
	 * @param File directory
	 * @return String output
	 *
	 */
	@Override
	public String execute(String command, File directory) {
		
		List<String> commandList = command.split(" ").toList()
		StringBuilder output = new StringBuilder()
		Process process = new ProcessBuilder(commandList)
			.directory(directory).start()
		process.inputStream.eachLine {output << it}
		process.waitFor()
		return output.toString()
		
	}
	
}
