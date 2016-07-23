package theDecider.models

/**
 * QM Method containing a molecular system SMILES string, functional name
 * and category, basis set and category, MAPD and complexity values
 * 
 * @author suzanne
 *
 */
class Strategy {
	
	String system
	String functional
	String functionalCategory
	String basisSet
	String basisSetCategory
	Double percentageError
	Double complexity
	Double tanimoto
	
	def boolean equals(Object other){
		if(other instanceof Strategy){
			if(other.system == this.system && other.functional == this.functional &&
				other.basisSet == this.basisSet){
				return true;
			}
		}
		return false;
	}
}
