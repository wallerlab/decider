import theDecider.security.Role
import theDecider.security.User
import theDecider.security.UserRole

class BootStrap {

    def init = { servletContext ->
		def adminRole = new Role(authority:'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority:'ROLE_USER').save(flush: true)
		def judgeRole = new Role(authority:'ROLE_JUDGE').save(flush: true)
		def playerRole = new Role(authority:'ROLE_PLAYER').save(flush: true)

		def adminUser = new User(username: 'admin', password: 'password', email: "admin@fake.com")
		adminUser.save(flush: true)
			
		def testUser = new User(username: 'user', password: 'password2', email: "user@fake.com")
		testUser.save(flush: true)
		
		UserRole.create(adminUser, adminRole, true)
		UserRole.create(testUser, userRole, true)
    }
    def destroy = {
    }
}
