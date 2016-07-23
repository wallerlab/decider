package theDecider.services.wrappers;

/**
 * Wrapper for a chemistry library
 * 
 * @author suzanne
 *
 */
public interface ChemLibWrapper {
	
	/**
	 * Gets Tanimoto score between 2 smiles strings
	 * 
	 * @param smilesString1
	 * @param smilesString2
	 * @return tanimoto
	 */
	Double getTanimoto(String smilesString1, String smilesString2);
	
	/**
	 * Counts the number of atoms in a given smiles string
	 * 
	 * @param smilesString
	 * @return numAtoms
	 */
	int countAtoms(String smilesString);
}
