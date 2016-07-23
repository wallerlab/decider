package theDecider.security

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.ui.RegisterCommand;
import grails.plugin.springsecurity.ui.RegistrationCode;
import grails.plugin.springsecurity.SpringSecurityUtils

@Secured(["permitAll()"])
class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
	
	def springSecurityService
	def random
	
	def verifyRegistration(){
		//hijacking the verifyRegistration method to give a person either a judge
		//or player role
		
		String token = params.t
		boolean roleBool = random.nextBoolean()
		Role role = Role.findByAuthority(roleBool? "ROLE_JUDGE" : "ROLE_PLAYER")

		def registrationCode = token ? RegistrationCode.findByToken(token) : null
		String username = registrationCode?.username
		User user = User.findByUsername(username)
		UserRole.create(user, role)
		
		super.verifyRegistration()
	}
	
	@Secured(["hasAnyRole('ROLE_ADMIN', 'ROLE_USER')"])
	def changePassword(){
		
		def registrationCode = new RegistrationCode(username: 
			springSecurityService.currentUser.username)
		registrationCode.save(flush: true)
		RegistrationCode regCode = RegistrationCode.findByUsername(
			springSecurityService.currentUser.username)
		def token = regCode.token
		render view: "resetPassword", model: [token: token]
		
	}
}
