package theDecider.system


import javax.annotation.Resource

import theDecider.models.Strategy

/**
 * Looks in XML file for systems whose smiles strings are close to the user's input
 * 
 * @author suzanne
 *
 */
class XMLQuerier implements Querier {

	@Resource
	xmlFiles

	/**
	 * Populates an ArrayList of DeciderSystems with their respective methods
	 * using a Jacob XML file
	 * 
	 * @param systems: list of systems to populate with methods
	 * 
	 */
	@Override
	public List<?> queryForSystems(ArrayList<?> systems) {

		List<Strategy> allStrategies = new ArrayList<Strategy>()
		for (xmlFile in xmlFiles) {
			Node jacobFile = new XmlParser().parse(xmlFile)

			for(jacobSystem in jacobFile.Dataset.System){
				for(system in systems){
					if(jacobSystem['@Name'].toLowerCase().trim() == system.name){
						Double referenceResult = Double.parseDouble(
								jacobSystem.ReferenceResult.ReferenceValue[0]['@Value'])

						for(jacobMethod in jacobSystem.Methods.Method){
							if(jacobMethod['@Basis'] != "CBS" &&
									//Filters out functions not used by
									// gaussian or orca
									new FunctionalsToTest().isTestableFunctional(
											jacobMethod['@Hamiltonian'])){
								Strategy method = populateMethod(jacobMethod, referenceResult);
								method.system = system.name
								method.tanimoto = system.tanimoto
								allStrategies << method
							}
						}
						break
					}
				}
			}
		}
		return allStrategies
		
	}

	/*
	 * Populates DeciderMethod for each basis set/functional pair
	 * 
	 */
	private Strategy populateMethod(Node jacobMethod, double referenceResult) {

		Strategy method = new Strategy()
		method.functional = jacobMethod['@Hamiltonian']
		method.functionalCategory = jacobMethod['@CategoryOfHamiltonian']
		method.basisSet = jacobMethod['@Basis']
		method.basisSetCategory = jacobMethod['@CategoryOfBasis']
		if(jacobMethod['@CounterpoiseCorrection'] == "CP"){
			method.basisSet = method.basisSet + "w/CP"
		}
		//taken from Jack's code and cleaned a bit
		Double result = Double.parseDouble(jacobMethod.Result[0]['@CalculatedValue'])
		method.percentageError = 1-Math.abs((result-referenceResult)/referenceResult)
		return method

	}

	/*
	 * Run in order to populate BenchmarkSystem with new systems from xml files.
	 * Only run once per file.
	 * 
	 */
	private void getSystemNameAndSmilesForEnum(){

		Node jacobFile = new XmlParser().parse(xmlFiles)
		File enumFile = new File("data/systemMolecules.txt")

		enumFile.withWriter(){out ->
			for(jacobSystem in jacobFile.Dataset.System){
				String systemName = jacobSystem['@Name']
				StringBuilder smileString = new StringBuilder()
				String prefix = ""
				for(jacobMolecule in jacobSystem.Molecules.Molecule){
					smileString.append(prefix + jacobMolecule['@SmileString'])
					prefix = "."
				}
				out.writeLine(systemName.trim().replace(".", "pt")
						.replace(" ", "_").toUpperCase() + '("'
						+ smileString.toString().replace("	", "") + '"), ')
			}
		}
		
	}
	
}
