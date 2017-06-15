/**
 * 
 */
package app;

import persistence.BaseDAO;

/**
 * @author Vinicius
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			BaseDAO.createDerbyDB();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}