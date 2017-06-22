/**
 * 
 */
package business;

/**
 * @author Vinicius
 *
 */
public class BusinessException extends Exception {

    public BusinessException() {
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
	
}