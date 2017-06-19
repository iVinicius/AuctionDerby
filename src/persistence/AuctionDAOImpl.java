/**
 * 
 */
package persistence;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import business.Auction;
import business.Bid;
import business.Lot;
import business.Participant;

/**
 * @author Vinicius
 *
 */
public class AuctionDAOImpl implements AuctionDAO{
	
	private ParticipantDAO participantDAO = new ParticipantDAOImpl();
	
	private LotDAO lotDAO = new LotDAOImpl();
	
	private BidDAO bidDAO = new BidDAOImpl();

	@Override
	public Auction findById(Long id) throws DAOException {
		try {
			if(id == null){
				return null;
			}
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM AUCTION WHERE ID=?");
			stmt.setLong(1, id);
			ResultSet resultado = stmt.executeQuery();
			Auction a = null;
			if (resultado.next()) {
				Long auctionId = resultado.getLong("ID");
				String byDemand = resultado.getString("BYDEMAND");
				String hiddenBids = resultado.getString("HIDDENBIDS");
				Timestamp startBidding = resultado.getTimestamp("STARTBIDDING");
				Timestamp endBidding = resultado.getTimestamp("ENDBIDDING");
				Long sellerOrBuyerId = resultado.getLong("SELLERORBUYER_ID");
				Participant sellerOrBuyer = participantDAO.findById(sellerOrBuyerId);
				Long lotId = resultado.getLong("LOT_ID");
				Lot lot = lotDAO.findById(lotId);

				a = new Auction(byDemand.equalsIgnoreCase("Y") ? true : false, 
						hiddenBids.equalsIgnoreCase("Y") ? true : false, startBidding, endBidding, sellerOrBuyer, lot); 
				a.setId(auctionId);
			}
			stmt.close();
			
			stmt = con.prepareStatement("SELECT * FROM LOT_BID WHERE LOT_ID=?");
			stmt.setLong(1, id);
			resultado = stmt.executeQuery();
			ArrayList<Bid> bids = new ArrayList<>();
			while(resultado.next()){
				Long bidId = resultado.getLong("BID_ID");
				Bid bid = bidDAO.findById(bidId);
				bids.add(bid);
			}
			if(a != null){
				a.setBids(bids);
			}
			
			stmt.close();		
			con.close();
			return a;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao buscar leilao.", ex);
		}
	}

	@Override
	public Auction createOrUpdate(Auction entity) throws DAOException {
		try {
			Connection con = BaseDAO.getConnection();

			if (entity == null) {
				return null;
			}

			if (entity.getId() != null && this.findById(entity.getId()) != null) {
				// update
				PreparedStatement stmt = con.prepareStatement(
						"UPDATE AUCTION " + " SET BYDEMAND = ?, HIDDENBIDS = ?, STARTBIDDING = ?, ENDBIDDING = ?"
								+ ", SELLERORBUYER_ID = ?, LOT_ID = ?"
								+ " WHERE ID = ?");
				stmt.setString(1, entity.isByDemand() ? "Y" : "N");
				stmt.setString(2, entity.isHiddenBids() ? "Y" : "N");
				stmt.setTimestamp(3, entity.getStartBidding());
				stmt.setTimestamp(4, entity.getEndBidding());
				stmt.setLong(5, entity.getSellerOrBuyer().getId());
				stmt.setLong(6, entity.getLot().getId());
				stmt.setLong(7, entity.getId());
				
				stmt.executeUpdate();
				stmt.close();
				
				for(Bid bids : entity.getBids()){
					bidDAO.createOrUpdate(bids);
				}
				
				con.close();
				return entity;
			} else {		
				// create 
				PreparedStatement stmt = con
						.prepareStatement("values (NEXT VALUE FOR auction_seq)");
				
				Long nextValue = 0L;
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					nextValue = rs.getLong("1");
				}			
				
				stmt.close();
				stmt = con
						.prepareStatement("INSERT INTO AUCTION (ID, BYDEMAND, HIDDENBIDS, STARTBIDDING, ENDBIDDING"
								+ ", SELLERORBUYER_ID, LOT_ID)"
								+ " VALUES (?,?,?,?,?,?,?)");
				stmt.setLong(1, nextValue);
				stmt.setString(2, entity.isByDemand() ? "Y" : "N");
				stmt.setString(3, entity.isHiddenBids() ? "Y" : "N");
				stmt.setTimestamp(4, entity.getStartBidding());
				stmt.setTimestamp(5, entity.getEndBidding());
				stmt.setLong(6, entity.getSellerOrBuyer().getId());
				stmt.setLong(7, entity.getLot().getId());

				stmt.executeUpdate();
				stmt.close();
				
				for(Bid bids : entity.getBids()){
					bidDAO.createOrUpdate(bids);
				}
				
				con.close();
				entity.setId(nextValue);
				return entity;
			}
		} catch (SQLException ex) {
			throw new DAOException("Falha ao criar ou atualizar leilao.", ex);
		}
	}

	@Override
	public Auction delete(Auction entity) throws DAOException {
		try {
			if(entity == null){
				return null;
			}
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM AUCTION WHERE ID=?");
			stmt.setLong(1, entity.getId());
			stmt.executeUpdate();
			stmt.close();
			
			for(Bid bids : entity.getBids()){
				bidDAO.delete(bids);
			}
			
			con.close();
			return entity;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao deletar leilao.", ex);
		}
	}

}
