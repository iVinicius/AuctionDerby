/**
 * 
 */
package business;

import persistence.dao.ParticipantDAO;
import persistence.dao.impl.ParticipantDAOImpl;
import persistence.entities.Participant;

/**
 * @author Vinicius
 *
 */
public class ParticipantBO {
	
	ParticipantDAO participantDao = new ParticipantDAOImpl();

	public Long createParticipant(String name, String cpf, String email) throws Exception{
		try{
			Participant newP = new Participant(name, cpf, email);
			
			newP = participantDao.createOrUpdate(newP);
			
			if(newP == null){
				throw new Exception("Failed to create participant");
			}
			return newP.getId();
		}catch(Exception e){
			//TODO:
			throw e;
		}
	}
	
	public Long login(Long id) throws Exception{
		try{		
			Participant newP = participantDao.findById(id);
			return newP.getId();
		}catch(Exception e){
			//TODO:
			throw e;
		}
	}
}