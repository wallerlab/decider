package theDecider.services.turingTest

import theDecider.UserSystem
import theDecider.models.Strategy
import theDecider.security.*

/**
 * Service for the Turing test
 * 
 * @author suzanne
 *
 */
class TuringTestService {
	
	def strategyFinderService
	def mailService
	def random

	/**
	 * Sends UserSystem to either computer or human player
	 * 
	 * @param userSystem
	 * @return
	 */
	def sendToPlayer(UserSystem userSystem){
		
		userSystem.isPerson = random.nextBoolean()
		
		if(userSystem.isPerson){
			User player = choosePlayer()
			emailPlayer(userSystem, player)
		}
		else{
			def params = [smiles: userSystem.smiles, cutoff: 0.5, alpha: 100,
				 beta: 100, gamma: 100]
			ArrayList<Strategy> options = strategyFinderService.findUserSystemNashStrategies(params)
			//TODO: choose best option from list and set it to userSystem.method
			//options.parallelStream()
		}
	}
	
	/* 
	 * Chooses a player who has been assigned the "ROLE_PLAYER" role
	 * at random
	 * 
	 */
	def User choosePlayer(){
		
		List<UserRole> roles = Arrays.asList(UserRole.findAllByRole(
			Role.findByAuthority("ROLE_PLAYER"), [sort: "id"]))
		int rolesListIndex = random.nextInt(roles.size())
		
		return roles[rolesListIndex].user
		
	}
	
	/*
	 * Emails chosen player with system and options
	 * 
	 */
	def emailPlayer(UserSystem userSystem, User player){
		//TODO: create html email, even though I hate html email
		
		mailService.sendMail {
			to player.email
			from "do.not.reply@localhost"
			subject "New System"
			html """"""
		}
		
	}
	
}
