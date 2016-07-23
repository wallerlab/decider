package theDecider

import spock.lang.*
import theDecider.models.Strategy
import theDecider.services.StrategyFinderService

/**
 *
 */
class MainControllerIntegrationSpec extends Specification {
	
	MainController mc = new MainController()

    void "test getStrategies calls deciderMainService"() {
		setup:
		mc.strategyFinderService = Mock(StrategyFinderService)
		Strategy strategy = Mock()
		
		when:
		mc.getStrategies()
		
		then:
		1*mc.strategyFinderService.findUserSystemNashStrategies(_) >> [strategy]
    }
	
	void "test index"(){
		when:
		mc.index()
		
		then:
		println "result"
	}
	
	void "test sendMethod"(){
		when:
		mc.sendMethod()
		
		then:
		println "result"
	}
}
