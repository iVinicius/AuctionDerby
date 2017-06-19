/**
 * 
 */
package tests;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import persistence.dao.BidDAO;
import persistence.dao.DAOException;
import persistence.dao.LotDAO;
import persistence.dao.ParticipantDAO;
import persistence.dao.impl.BidDAOImpl;
import persistence.dao.impl.LotDAOImpl;
import persistence.dao.impl.ParticipantDAOImpl;
import persistence.entities.Asset;
import persistence.entities.Bid;
import persistence.entities.Category;
import persistence.entities.Lot;
import persistence.entities.Participant;

/**
 * @author Vinicius
 *
 */
public class BidDAOTest extends BaseDAOTest {

	private BidDAO bidDAO = new BidDAOImpl();
	
	private ParticipantDAO participantDAO = new ParticipantDAOImpl();
	
	private LotDAO lotDAO = new LotDAOImpl();

	private Bid entity;

	private Participant bidder;
	
	private Lot lot;

	public void generateObject() throws DAOException{
		bidder = generateParticipant();
		participantDAO.createOrUpdate(bidder);
		lot = generateLot();
		lotDAO.createOrUpdate(lot);
		entity = new Bid(bidder, 1000L, lot, new Timestamp(Calendar.getInstance().getTime().getTime()));
	}

	@Test
	public void testCRUD() throws DAOException {
		generateObject();
		testCreate();
		testFind();
		testUpdate();
		testDelete();
	}

	public void testCreate() throws DAOException {
		entity = bidDAO.createOrUpdate(entity);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
	}

	public void testFind() throws DAOException {
		entity = bidDAO.findById(entity.getId());
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
	}

	public void testUpdate() throws DAOException {
		entity.setValue(1500L);
		entity = bidDAO.createOrUpdate(entity);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertEquals(entity.getValue().longValue(), 1500L);
	}

	public void testDelete() throws DAOException {
		bidDAO.delete(entity);
		entity = bidDAO.findById(entity.getId());
		Assert.assertNull(entity);
	}

	private Participant generateParticipant() {
		Random rand = new Random();
		Participant part = new Participant("TesteParticipante", rand.nextInt(99999999) + "", "teste@teste.com");
		return part;
	}

	private Lot generateLot() {
		Lot lot = new Lot(1000L, false);
		ArrayList<Asset> assets = new ArrayList<>();
		int i = 0;
		while (i < 2) {
			assets.add(new Asset("TestBid " + i, "Oculos Rayban", Category.INFORMATICA));
			i++;
		}
		lot.setAssets(assets);
		return lot;
	}
	
	@After
	public void toDelete() throws DAOException{
		participantDAO.delete(bidder);
		lotDAO.delete(lot);
	}
}