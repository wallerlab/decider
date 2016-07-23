package theDecider.services

import groovy.util.Node;

import java.util.Map;

import theDecider.BenchmarkSystem;
import theDecider.models.*;
import theDecider.system.JacobQuerier;

/**
 * Finds Nash Equilibrium QM methods for user-defined system
 * 
 * @author suzanne
 *
 */
class StrategyFinderService {

	def simpleMatchingSystemFinder
	def querier
	def complexityCalculator
	def gameTheoryGamePopulator
	def gameTheoryManager
	def simpleStrategyTransformer

	/**
	 * Given user input such as a molecular system, percentage of tanimoto 
	 * cutoff value, and percentages of zeta, diffuse, and polarizability 
	 * functions, returns all pure Nash Equilibrium strategies.
	 * 
	 * @param params: Map of "smiles" and user params from UI
	 * @return userStrategyOptions
	 */
	public List<OutputMethod> findUserSystemNashStrategies(params){

		String smiles = params.smiles
		Double cutoff = Double.parseDouble(params.cutoff)
		//TODO: add storage of user system/job/eventually NE
		//find systems with smiles with tanimoto coeff >= a percentage of the max value
		//and populate systems into own MolecularSystem domain
		List<MolecularSystem> matchingSystems = simpleMatchingSystemFinder.findMatchingSystems(
			smiles, cutoff)

		//query jacob for systems
		List<Strategy> strategies = querier.queryForSystems(matchingSystems)

		//for each method, calculate complexity
		complexityCalculator.calculate(strategies, params)

		//put both into a map to be read by gameTheoryManager
		Map<?,?> gamePayoffs = gameTheoryGamePopulator.constructGame(strategies)

		//find NE using game theory software
		Matrix equilibriumStrategies = gameTheoryManager.findNashEquilibria(
			gamePayoffs, smiles)

		//create controller-friendly method list
		List<OutputMethod> weightedStrategyOptions = simpleStrategyTransformer.transformStrategies(
			equilibriumStrategies, strategies)
		
		return weightedStrategyOptions
		
	}
		
}
