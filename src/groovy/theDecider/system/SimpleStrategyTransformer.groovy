package theDecider.system

import java.util.ArrayList
import java.util.List;
import java.util.stream.Collectors

import theDecider.models.Matrix;
import theDecider.models.OutputMethod;
import theDecider.models.Strategy;

class SimpleStrategyTransformer implements StrategyTransformer {

	/**
	 * Creates an ArrayList of Nash Equilibrium OutputMethods
	 * 
	 * @param strategies
	 * @param allStrategies
	 * @return list of OutputMethods
	 */
	@Override
	public List<OutputMethod> transformStrategies(Matrix strategies,
			ArrayList<Strategy> allStrategies) {

		List<OutputMethod> outputMethods = []
		if(!strategies.isEmpty()){
			List<Strategy> userStrategyOptions = []
			for(strategy in strategies.getMatrixValues()){
				userStrategyOptions << allStrategies.parallelStream().filter({ method ->
					method.equals(strategy)
				}).collect(Collectors.toList())
				if(!userStrategyOptions.flatten().contains(strategy)){
					userStrategyOptions << strategy
				}
			}
			outputMethods = changeStrategyToOutput(userStrategyOptions.flatten())
		}
		return outputMethods
	}

	/*
	 * Creates a list of OutputMethods from a list of Strategies
	 * 
	 */
	private List<OutputMethod> changeStrategyToOutput(List<Strategy> userStrategyOptions){
		Map groupedByBasisAndFunc = userStrategyOptions.groupBy({it.basisSet}, 
			{it.functional})
		List<OutputMethod> outputMethods = []
		groupedByBasisAndFunc.each{outerMap ->
			outerMap.value.each{
				outputMethods << new OutputMethod(basisSet: outerMap.key, 
					functional: it.key, weight: calcWeight(new ArrayList(it.value)),
					numAppearances: it.value.size())
			}
		}
		calcAbsWeight(outputMethods)
		return outputMethods
	}

	/*
	 * Calculates the weight value for a single OutputMethod from a list of 
	 * Strategies
	 * 
	 */
	private Double calcWeight(List<Strategy> strategyList){
		return strategyList.parallelStream().mapToDouble({method ->
			method.tanimoto ? method.tanimoto*method.percentageError*method.complexity : new Double(0.0)
		}).average().getAsDouble()
	}

	/*
	 * Calculates the normalized weighting value for each OutputMethod in list
	 * 
	 */
	private void calcAbsWeight(List outputMethods) {
		Double maxNumWeight = outputMethods.parallelStream().mapToDouble({method ->
			method.weight*method.numAppearances}).max().getAsDouble()
		outputMethods.parallelStream().forEach({method ->
			method.normWeight = method.weight*method.numAppearances/maxNumWeight})
	}
	
}
