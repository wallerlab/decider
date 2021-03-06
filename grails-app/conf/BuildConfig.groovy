grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.8
grails.project.source.level = 1.8
grails.project.war.file = "target/decider.war"

grails.server.port.http=9090
grails.project.fork = false/*[
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]*/

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        // runtime 'mysql:mysql-connector-java:5.1.29'
        // runtime 'org.postgresql:postgresql:9.3-1101-jdbc41'
        test "org.grails:grails-datastore-test-support:1.0-grails-2.4"
	    test 'cglib:cglib-nodep:2.2.2'
	    compile group: 'org.openscience.cdk', name: 'cdk-core', version: '1.5.13'
	    compile group: 'org.openscience.cdk', name: 'cdk-standard', version: '1.5.13'
        compile group: 'org.openscience.cdk', name: 'cdk-interfaces', version: '1.5.13'
        compile group: 'org.openscience.cdk', name: 'cdk-smiles', version: '1.5.13'
        compile group: 'org.openscience.cdk', name: 'cdk-pdb', version: '1.5.13'
        compile group: 'org.openscience.cdk', name: 'cdk-silent', version: '1.5.13'
        compile group: 'org.openscience.cdk', name: 'cdk-io', version: '1.5.13'
        compile group: 'org.openscience.cdk', name: 'cdk-fingerprint', version: '1.5.13'
        build 'org.apache.httpcomponents:httpcore:4.3.2'
        build 'org.apache.httpcomponents:httpclient:4.3.2'
        build 'org.apache.httpcomponents:httpmime:4.3.3'
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.54"

        // plugins for the compile step
        compile ":scaffolding:2.1.2"
        compile ':cache:1.1.7'

   // Coveralls plugin
        build(':coveralls:0.1.4', ':rest-client-builder:1.0.3') {
            export = false
        }
        test(':code-coverage:2.0.3-3') {
            export = false
        }
                
        build ":codenarc:0.25.2"


        compile ":asset-pipeline:1.8.11"
		compile ":spring-security-core:2.0-RC4"
		compile ":spring-security-ui:1.0-RC2"
		compile ":mail:1.0.7"

        // plugins needed at runtime but not for compilation
        runtime ":hibernate4:4.3.5.4" // or ":hibernate:3.6.10.16"
        runtime ":database-migration:1.4.0"
        runtime ":jquery:1.11.1"

    }
}


