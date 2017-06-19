/**
 * 
 */
package tests;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import persistence.dao.DAOException;
import persistence.dao.ParticipantDAO;
import persistence.dao.impl.ParticipantDAOImpl;
import persistence.entities.Participant;

/**
 * @author Vinicius
 *
 */
public class ParticipantDAOTest extends BaseDAOTest{
	
	private ParticipantDAO participantDAO = new ParticipantDAOImpl();
	
	private Participant entity;
	
	public void generateObject(){
		Random rand = new Random();
		entity = new Participant("TesteParticipante", rand.nextInt(99999999)+"", "teste@teste.com");
	}
	
	@Test
	public void testCRUD() throws DAOException{
		generateObject();
		testCreate();
		testFind();
		testUpdate();
		testDelete();
	}

	public void testCreate() throws DAOException{
		entity = participantDAO.createOrUpdate(entity);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
	}
	
	public void testFind() throws DAOException{
		entity = participantDAO.findById(entity.getId());
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
	}

	public void testUpdate() throws DAOException{
		entity.setName("TesteAlterado");
		entity = participantDAO.createOrUpdate(entity);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertEquals(entity.getName(), "TesteAlterado");
	}
	
	public void testDelete() throws DAOException{
		participantDAO.delete(entity);
		entity = participantDAO.findById(entity.getId());
		Assert.assertNull(entity);
	}
}