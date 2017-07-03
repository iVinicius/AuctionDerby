/**
 * 
 */
package business;

import java.util.ArrayList;
import java.util.List;

import persistence.dao.AuctionDAO;
import persistence.dao.BidDAO;
import persistence.dao.impl.AuctionDAOImpl;
import persistence.dao.impl.BidDAOImpl;
import persistence.entities.Auction;
import persistence.entities.Bid;

/**
 * @author Vinicius
 *
 */
public class BidBO {
	
	private BidDAO bidDao = new BidDAOImpl();
	
	AuctionDAO auctionDao = new AuctionDAOImpl() ;
	
	public List<Bid> getBidsByAuction(Long auctionId) throws Exception{
		try{
			List<Bid> bidList = new ArrayList<>();
			
			Auction auction = auctionDao.findById(auctionId);
			
			if(auction == null || auction.isHiddenBids() || auction.getLot() == null){
				return bidList;
			}
			
			bidList = bidDao.findByLotId(auction.getLot().getId());
			
			return bidList;
		}catch(Exception e){
			//TODO:
			throw e;
		}
	}

}
