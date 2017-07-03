/**
 * 
 */
package business;

import java.sql.Timestamp;
import java.util.List;

import persistence.dao.AuctionDAO;
import persistence.dao.DAOException;
import persistence.dao.LotDAO;
import persistence.dao.ParticipantDAO;
import persistence.dao.impl.AuctionDAOImpl;
import persistence.dao.impl.LotDAOImpl;
import persistence.dao.impl.ParticipantDAOImpl;
import persistence.entities.Auction;
import persistence.entities.Lot;
import persistence.entities.Participant;

/**
 * @author Vinicius
 *
 */
public class AuctionBO {

	AuctionDAO auctionDao = new AuctionDAOImpl() ;
	
	ParticipantDAO participantDao = new ParticipantDAOImpl();
	
	LotDAO lotDao = new LotDAOImpl();
	
	public List<Auction> getAuctionList() throws Exception{
		try{
			List<Auction> list = auctionDao.getAll();
			
			return list;
		}catch(Exception e){
			throw e;
		}
	}
	
	public Auction createAuction(boolean byDemand, boolean hiddenBids, Timestamp startBidding, Timestamp endBidding,
			Long sellerOrBuyerId, Long lotValue) throws Exception{
		try{
			Participant sellerOrBuyer = participantDao.findById(sellerOrBuyerId);
			if(sellerOrBuyer == null){
				throw new BusinessException("O vendedor/comprador nao pode ser nullo. Tente logar novamente.");
			}
			
			boolean isLotMaxValue = !byDemand;
			Lot newLot = lotDao.createOrUpdate(new Lot(lotValue, isLotMaxValue));
			if(newLot == null){
				throw new BusinessException("Ocorreu um erro ao criar o lote.");
			}
			
			Auction newAuction = new Auction(byDemand, hiddenBids, startBidding, endBidding, sellerOrBuyer, newLot);
			
			return newAuction;
		} catch(Exception e){
			//TODO:
			throw e;
		}
	}
	
	public Auction findAuctionById(Long auctionId) throws DAOException{
		return auctionDao.findById(auctionId);
	}
}
