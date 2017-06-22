/**
 * 
 */
package app;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import persistence.dao.BaseDAO;
import persistence.dao.ParticipantDAO;
import persistence.dao.impl.ParticipantDAOImpl;
import persistence.entities.Participant;

/**
 * @author Vinicius
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void kiko(String[] args) {
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
			
			Timestamp stamp = new Timestamp(Calendar.getInstance().getTime().getTime());
			
			long longStamp = stamp.getTime();
			
			String stringStamp = stamp.toString();
			
			Date dateStamp = new Date(stamp.getTime());
			
			Date newDate = new Date(117, 5, 18, 20, 29);
			
			stamp = new Timestamp(dateStamp.getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}