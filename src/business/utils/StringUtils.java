/**
 * 
 */
package business.utils;

/**
 * @author Vinicius
 *
 */
public class StringUtils {

	private static StringUtils ref;
	
	public static StringUtils getInstance(){
		if(ref == null){
			ref = new StringUtils();
		}
		return ref;
	}
	
	public boolean isEmpty(String value){
		if(value == null || value.isEmpty()){
			return true;
		}
		return false;
	}
}