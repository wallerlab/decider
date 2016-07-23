class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		"/"{
			controller = "main"
			action = "index"
		}
        "500"(view:'/error')
	}
}
