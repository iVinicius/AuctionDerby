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
			
			Participant p = participantDAO.findById(1L);
			
			p = new Participant("Vini", "8548768", "vvzord@gmail.com");
			
			p = participantDAO.createOrUpdate(p);
			
			p = participantDAO.findById(p.getId());
			
			p.setName("Vv La Lenda");
			
			p = participantDAO.createOrUpdate(p);
			
			p = participantDAO.findById(p.getId());
			
			p = participantDAO.delete(p);
			
			p = participantDAO.findById(p.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}