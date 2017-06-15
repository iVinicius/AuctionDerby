/**
 * 
 */
package app;

import business.Participant;
import persistence.BaseDAO;
import persistence.ParticipantDAO;
import persistence.ParticipantDAOImpl;

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
			
			ParticipantDAO participantDAO = new ParticipantDAOImpl();
			
			Participant p = participantDAO.findById(10L);
			
			String kiko = "";
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}