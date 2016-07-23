package theDecider

import theDecider.security.User
import theDecider.services.turingTest.TuringTestService
import grails.plugin.springsecurity.annotation.Secured


@Secured(["ROLE_JUDGE", "ROLE_ADMIN"])
class TuringTestController {
	
	def springSecurityService

	@Secured(["permitAll()"])
    def index() { //Turing test main page
	}
	
	def question(){ //question view
	}
	
	def getQuestion(){ //gets question from tester, sends to either comp or human
		
		//TODO: what happens when I try to create more than 10 systems
		if(params.smiles != ""){
			
			StringBuilder sb = new StringBuilder()
			params.image.readLines().each{sb.append(it)}
			byte[] image = sb.toString().decodeBase64()
			UserSystem userSystem = new UserSystem(smiles: params.smiles, image: image)
			User user = springSecurityService.getCurrentUser()
			user.addToUserSystems(userSystem)
			userSystem.save()
			
			/*
			TuringTestService.sendToPlayer(userSystem)*/
			flash.message = message(code: 'default.system.accepted')
			
		} else { flash.error = message(code: 'default.empty.params.message') }
		
		redirect action: "question"
		
	}
	
	def answer(){ //answer view
	}
	
	def getAnswer(){ //gives answer from "answerer", tester checks off if they think it's a human or comp
		flash.message = message(code: 'default.answer.accepted')
		redirect action: "answer"
	}
}
