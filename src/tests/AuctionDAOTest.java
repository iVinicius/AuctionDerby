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

import business.Asset;
import business.Auction;
import business.Bid;
import business.Category;
import business.Lot;
import business.Participant;
import persistence.DAOException;
import persistence.LotDAO;
import persistence.LotDAOImpl;
import persistence.ParticipantDAO;
import persistence.ParticipantDAOImpl;
import persistence.AuctionDAO;
import persistence.AuctionDAOImpl;
import persistence.BidDAO;
import persistence.BidDAOImpl;

/**
 * @author Vinicius
 *
 */
public class AuctionDAOTest extends BaseDAOTest{
	
	private AuctionDAO AuctionDAO = new AuctionDAOImpl();
	
	private BidDAO bidDAO = new BidDAOImpl();
	
	private ParticipantDAO participantDAO = new ParticipantDAOImpl();
	
	private LotDAO lotDAO = new LotDAOImpl();
	
	private Auction entity;
	
	private Participant participant;
	
	private Lot lot;
	
	public void generateObject() throws DAOException{
		participant = generateParticipant();
		participantDAO.createOrUpdate(participant);
		lot = generateLot();
		lotDAO.createOrUpdate(lot);
		
		entity = new Auction(false, false, new Timestamp(Calendar.getInstance().getTime().getTime()),
				new Timestamp(Calendar.getInstance().getTime().getTime()), participant, lot);
		
		ArrayList<Bid> bids = new ArrayList<>();
		int i = 0;
		while(i < 2){
			bids.add(new Bid(participant, 1000L, lot, new Timestamp(Calendar.getInstance().getTime().getTime())));
			i++;
		}
		entity.setBids(bids);
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
		entity = AuctionDAO.createOrUpdate(entity);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		for(Bid bid : entity.getBids()){
			Assert.assertNotNull(bid.getId());
		}
	}
	
	public void testFind() throws DAOException{
		entity = AuctionDAO.findById(entity.getId());
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
	}

	public void testUpdate() throws DAOException{
		entity.setByDemand(true);;
		entity = AuctionDAO.createOrUpdate(entity);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.isByDemand());
	}
	
	public void testDelete() throws DAOException{
		AuctionDAO.delete(entity);
		for(Bid bid : entity.getBids()){
			Assert.assertNull(bidDAO.findById(bid.getId()));
		}
		entity = AuctionDAO.findById(entity.getId());
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
		participantDAO.delete(participant);
		lotDAO.delete(lot);
	}
}