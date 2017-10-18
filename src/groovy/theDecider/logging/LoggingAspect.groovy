package theDecider.logging

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

import com.google.common.base.Stopwatch
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Component

/**
 * Logger for theDecider
 * 
 * @author suzanne
 *
 */
@Aspect
@Component
class LoggingAspect {
	
	private static final log = LogFactory.getLog("decider")

	/**
	 * Logs around all public theDecider calls. Gives info about what's being called, with which arguments, 
	 * what values it's returning, and timings.
	 * 
	 * @param joinPoint
	 * @return value that method returns
	 */
	//grails calls getMetaClass and getProperty for every execution, clutters up logging. Also, testing fails with this logging, so any class with Spec at the end is excluded
	@Around("execution(public * theDecider..*.*(..)) && !execution(public * theDecider..*.getMetaClass(..)) && !execution(public * theDecider..*.getProperty(..)) && !execution(* theDecider.*Spec.*(..)) && !execution(* theDecider..*.*Spec.*(..))")
    def logAround(ProceedingJoinPoint joinPoint) {

		log.info("method " + joinPoint.getSignature().getDeclaringTypeName() + "."+ joinPoint.getSignature().getName() + " has begun")
		log.info("with arguments : " + Arrays.toString(joinPoint.getArgs()));
		Stopwatch stopwatch = Stopwatch.createStarted();
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		stopwatch.stop();
		long millisecs = stopwatch.elapsed(TimeUnit.MILLISECONDS);
		log.info("method " + joinPoint.getSignature().getDeclaringTypeName() + "."+ joinPoint.getSignature().getName() + " has ended")
		log.info("with a value of " + value)
		log.info("and a time of " + millisecs + " ms")
		return value;

    }
	
}
