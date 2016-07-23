package theDecider

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils

@Secured(["ROLE_USER", "ROLE_ADMIN"])
class MainController {
	
	def strategyFinderService
	def springSecurityService
	
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
		if(params.smiles != ""){
			def weightedStrategyOptions = strategyFinderService.findUserSystemNashStrategies(
				params)
			render(view: "index", model: [strategies: weightedStrategyOptions])
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
