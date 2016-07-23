package theDecider

import theDecider.security.User

class UserSystem {
	
	String smiles
	String functional
	String basisSet
	boolean isPerson
	boolean testerGuess
	byte[] image
	
	static belongsTo = [user: User]

    static constraints = {
		isPerson nullable: true
		testerGuess nullable: true
		basisSet nullable: true
		functional nullable: true
		image maxSize:1000000
    }
}
