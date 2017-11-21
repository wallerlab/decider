// Place your Spring DSL code here
import theDecider.gameTheory.gambit.*
import theDecider.gameTheory.payoffs.*
import theDecider.system.SimpleComplexityCalculator;
import theDecider.system.SimpleStrategyTransformer;
import theDecider.system.SimpleMatchingSystemFinder;
import theDecider.system.XMLQuerier;
import theDecider.GenericCommandExecutor

beans = {
	xmlns aop: "http://www.springframework.org/schema/aop"
	loggingAspect(theDecider.logging.LoggingAspect)
	aop.config("proxy-target-class": true){
		aspect(id:"loggingAspectService",ref: "loggingAspect")
	}
	gameTheoryManager(GambitManager){
	}
	inputFileCreator(GambitInputFileCreator){
	}
	outputParser(GambitOutputParser){
	}
	commandExecutor(GenericCommandExecutor){
	}
	querier(XMLQuerier){
	}
	complexityCalculator(SimpleComplexityCalculator){
	}
	gameTheoryGamePopulator(NPlayerMatrixPopulator){
	}
	simpleStrategyTransformer(SimpleStrategyTransformer){
	}
	simpleMatchingSystemFinder(SimpleMatchingSystemFinder){
	}
	random(Random){
	}
	gambitPure(String, System.getenv("gambitEnumpure")){
	}
	mainFolder(File, "decider-workspace"){
	}
	xmlFiles(ArrayList,["data/S22.xml"]){
	}
	playerNamesAndPayoffs(HashMap, ["functional": "complexity", 
		"basisSet": "percentageError", "system": "tanimoto"])
	nameMap(HashMap,["functional": 0, "basisSet":1, "system":2])
}
