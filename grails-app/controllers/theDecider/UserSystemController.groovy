package theDecider



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
@Transactional(readOnly = true)
class UserSystemController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserSystem.list(params), model:[userSystemInstanceCount: UserSystem.count()]
    }
	
	def displayImage(UserSystem userSystemInstance){
		response.contentType = "image/png"
		response.contentLength = userSystemInstance?.image.length
		response.outputStream.write(userSystemInstance?.image)
	}

    def show(UserSystem userSystemInstance) {
        respond userSystemInstance
    }

    def create() {
        respond new UserSystem(params)
    }

    @Transactional
    def save(UserSystem userSystemInstance) {
        if (userSystemInstance == null) {
            notFound()
            return
        }

        if (userSystemInstance.hasErrors()) {
            respond userSystemInstance.errors, view:'create'
            return
        }

        userSystemInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userSystem.label', default: 'UserSystem'), userSystemInstance.id])
                redirect userSystemInstance
            }
            '*' { respond userSystemInstance, [status: CREATED] }
        }
    }

    def edit(UserSystem userSystemInstance) {
        respond userSystemInstance
    }

    @Transactional
    def update(UserSystem userSystemInstance) {
        if (userSystemInstance == null) {
            notFound()
            return
        }

        if (userSystemInstance.hasErrors()) {
            respond userSystemInstance.errors, view:'edit'
            return
        }

        userSystemInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'UserSystem.label', default: 'UserSystem'), userSystemInstance.id])
                redirect userSystemInstance
            }
            '*'{ respond userSystemInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(UserSystem userSystemInstance) {

        if (userSystemInstance == null) {
            notFound()
            return
        }

        userSystemInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'UserSystem.label', default: 'UserSystem'), userSystemInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userSystem.label', default: 'UserSystem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
