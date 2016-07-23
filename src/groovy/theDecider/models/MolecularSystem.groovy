package theDecider.models

/**
 * Molecular system from database that matches user-input system
 * 
 * @author suzanne
 *
 */
class MolecularSystem {
	
	String name
	String smilesString
	Double tanimoto
	
	public String toString(){
		return name
	}

}
