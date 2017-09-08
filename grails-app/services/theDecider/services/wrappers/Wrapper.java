package theDecider.services.wrappers;

import java.io.File;

public interface Wrapper {
	
	File run(String smilesString, String outFile);

	String run(File fileToConvert);

}
