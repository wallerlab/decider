package theDecider

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils

@Secured(["ROLE_USER", "ROLE_ADMIN"])
class MainController {
	
	def strategyFinderService
	def springSecurityService
	def obabelWrapperService
	def mainFolder
	
	@Secured(["permitAll()"])
    def index() {
		//Commented out right now because we don't want anyone seeing anything but the front page
		/*if(!springSecurityService.isLoggedIn()){
			redirect controller: "turingTest"
		}*/
		if(!SpringSecurityUtils.ifAllGranted("ROLE_ADMIN")){
			redirect controller: "turingTest"
		}
	}

	def getStrategies(){
		if(params.mrv != ""){
			println params
			def fileToConvert = new File(mainFolder, "test.mrv")
			fileToConvert.write((String) params.mrv)
			params.smiles = obabelWrapperService.run(fileToConvert)
			println params.smiles

			def weightedStrategyOptions = strategyFinderService.findUserSystemNashStrategies(params)
			println weightedStrategyOptions.toString()
			Map wso = ['wso': weightedStrategyOptions]
			JSON.use("deep"){
				render wso as JSON
			}
		}
		else{
			flash.error = message(code: 'default.empty.params.message')
			render(view: "index")
		}
	}
	
	def sendMethod(){
		flash.message = "You have chosen " + params.basisSet + ", " + params.functional
		render(view: "index")
	}
}