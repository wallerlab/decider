package theDecider.system

import java.util.ArrayList
import javax.annotation.Resource
import theDecider.models.*

/**
 * ComplexityCalculator for complexity payoffs
 * 
 * @author suzanne
 *
 */
class SimpleComplexityCalculator implements ComplexityCalculator {
	
	@Resource
	simpleChemLibWrapperService
	
	Double diffSlope = 0.1075;
	Double diffIntercept = 0.2823;
	Double polSlope = 0.2525;
	Double polIntercept = 0.2434;
	Double zetaSlope = 0.1518;
	Double userSystemComplexityFactor;

	/**
	 * Calculates complexities of basis set/functional/user system given
	 * 
	 * @param matchingSystems: all DeciderSystems that fit the Tanimoto criteria
	 * @param userSystemSmiles: the smiles string of the user-input system
	 */
	@Override
	public void calculate(ArrayList<?> allStrategies, Map<?,?> params) {
			
		String userSystemSmiles = params.smiles

		int userSystemComplexity = calculateSystemComplexity(userSystemSmiles)
		userSystemComplexityFactor = userSystemComplexity/10 //division by 10 so that the numbers don't get too large
		for(Strategy method : allStrategies){
			Double functionalComplexity = calculateFunctionalComplexity(method.functionalCategory)
			Double basisSetComplexity = calculateBasisSetComplexity(method.basisSet, params)
			//TODO: fix complexity so that it's always non-neg
			method.complexity = 1 - ((basisSetComplexity+functionalComplexity)/userSystemComplexity)
		}
	}

	/*
	 * Calculates system complexity based on number of atoms
	 * 
	 */
	private int calculateSystemComplexity(String smilesString){
		//TODO: change this so that it takes into account heavier atoms
		return simpleChemLibWrapperService.countAtoms(smilesString)

	}

	/*
	 * Calculates basis set complexity based on number of zeta functions, polarization,
	 * and diffuse properties
	 * 
	 */
	private Double calculateBasisSetComplexity(String basisSet, params){
		if(basisSet == "CBS"){
			return 1.0
		}
		Double alpha = Integer.parseInt(params.alpha)/100
		Double beta = Integer.parseInt(params.beta)/100
		Double gamma = Integer.parseInt(params.gamma)/100
		Double numDiff = getNumDiffuse(basisSet)
		Double numPolar = getNumPolar(basisSet)
		Double numZeta = getNumZeta(basisSet)
		return (alpha*numZeta + beta*numPolar + gamma*numDiff)
	}

	/*
	 * Calculates number of diffuse functions
	 * 
	 */
	private getNumDiffuse(String basisSet) {
		Double numDiff = 0.0
		if(basisSet ==~ /aug-cc-pV[DTQ56]Z.*/){
			numDiff = diffSlope*userSystemComplexityFactor + diffIntercept
		}
		return numDiff
	}

	/*
	 * Calculates number of polarization functions
	 * 
	 */
	private BigDecimal getNumPolar(String basisSet) {
		Double numPolar = 0.0
		if(basisSet.contains("LACVP*")){//6-31G with effective core potential
			return polSlope*userSystemComplexityFactor*2.5 + polIntercept
		}
		else if(basisSet.contains("VP")){ //is Ahlrichs
			numPolar = basisSet.count("P")
			if(basisSet.contains("w/CP")){
				numPolar -= 1
			}
		}
		else if(basisSet ==~ /.*cc-pV[DTQ56]Z.*/){//is Dunnings
			numPolar = 1.0
		}
		numPolar = polSlope*userSystemComplexityFactor*numPolar + polIntercept
		return numPolar
	}

	/*
	 * Calculates number of zeta functions
	 * 
	 */
	private BigDecimal getNumZeta(String basisSet) {
		Double numZeta;
		if(basisSet.contains("S")){
			numZeta = 1.0
		}
		else if(basisSet.contains("D")){
			numZeta = 2.0
		}
		else if(basisSet.contains("T")){
			numZeta = 3.0
		}
		else if(basisSet.contains("Q")){
			numZeta = 4.0
		}
		else if(basisSet.contains("5")){
			numZeta = 5.0
		}
		else{
			numZeta = 0.0
		}
		numZeta = zetaSlope*userSystemComplexityFactor*numZeta
		return numZeta
	}

	/*
	 * Calculates functional complexity based on Jacob's ladder
	 * 
	 */
	private Double calculateFunctionalComplexity(String functionalCategory){
		Double result
		switch(functionalCategory){
			case "LDA":
				result = 1
				break
			case "GGA":
				result = 2
				break
			case "meta-GGA":
				result = 3
				break
			case "hybrid-GGA":
				result = 4
				break
			case "double-hybrid-GGA":
				result = 5
				break
			case "wavefunction":
				result = 1
				break
			default:
				result = 2
				break
		}
		return result
	}

}
