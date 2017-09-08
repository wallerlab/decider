package theDecider.system

import theDecider.BenchmarkSystem
import theDecider.models.MolecularSystem

import javax.annotation.Resource
import java.util.stream.Collectors

/**
 * Implemented enum crawler for systems in database
 * 
 * @author suzanne
 *
 */
class SimpleMatchingSystemFinder implements MatchingSystemFinder {
	
	@Resource
	def simpleChemLibWrapperService

	/**
	 * Returns all systems from database that meet the given Tanimoto cutoff
	 * for the given smiles string.
	 * 
	 */
	@Override
	public List<?> findMatchingSystems(String smiles, Double cutoff) {
		
		List<MolecularSystem> matchingSystems = Collections.synchronizedList(
			new ArrayList())
		EnumSet<BenchmarkSystem> benchmarkSystem = EnumSet.allOf(BenchmarkSystem)
		benchmarkSystem.parallelStream().forEach({system ->
			matchingSystems.add(populateSystem(system,
				simpleChemLibWrapperService.getTanimoto(smiles, system.smilesString)))
			})
		matchingSystems = checkMatchingSystems(matchingSystems, cutoff)
		return matchingSystems
		
	}

	/*
	 * Populates MolecularSystem for each Jacob system
	 * 
	 */
	private MolecularSystem populateSystem(BenchmarkSystem benchmarkSystem, 
		Double tanimoto) {
		
		MolecularSystem matchingSystem = new MolecularSystem()
		if(benchmarkSystem.toString().startsWith("S22_")){
			matchingSystem.name = benchmarkSystem.toString().toLowerCase().
			replace("s22_", "").replace("_", " ")
		}
		else{
			matchingSystem.name = benchmarkSystem.toString().toLowerCase().replace("pt", ".")
		}
		matchingSystem.smilesString = benchmarkSystem.smilesString
		matchingSystem.tanimoto = tanimoto
		return matchingSystem
		
	}
	
	/*
	 * Takes anything out of the list of systems that's below the 
	 * max tanimoto * cutoff value
	 * 
	 */
	private List<MolecularSystem> checkMatchingSystems(List<MolecularSystem> allSystems, 
		cutoff){
		
		Double maxTanimoto = allSystems.parallelStream().mapToDouble({system -> 
			system.tanimoto}).max().getAsDouble()
		def systemsAboveCutoff = allSystems.parallelStream().filter({system -> 
			system.tanimoto >= maxTanimoto*cutoff
			}).collect(Collectors.toList())
		return systemsAboveCutoff
		
	}

}
