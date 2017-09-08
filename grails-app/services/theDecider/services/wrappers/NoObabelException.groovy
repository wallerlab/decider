package theDecider.services.wrappers

/**
 * Exception specifically for when obabel is not found. Usually due to not
 * having the environment variable set when running.
 *
 * Created by suzanne on 16.05.17.
 */
class NoObabelException extends Exception{

    NoObabelException(){
        super("obabel has not been found. Please install or set as an " +
                "environment variable")
    }
}
