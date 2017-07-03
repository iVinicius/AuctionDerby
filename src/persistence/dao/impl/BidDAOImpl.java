/**
 * 
 */
package persistence.dao.impl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.dao.BaseDAO;
import persistence.dao.BidDAO;
import persistence.dao.DAOException;
import persistence.dao.LotDAO;
import persistence.dao.ParticipantDAO;
import persistence.entities.Bid;
import persistence.entities.Lot;
import persistence.entities.Participant;

/**
 * @author 14108849
 *
 */
public class BidDAOImpl implements BidDAO{
	
	ParticipantDAO participantDAO = new ParticipantDAOImpl();
	
	LotDAO lotDAO = new LotDAOImpl();

	@Override
	public Bid findById(Long id) throws DAOException {
		try {
			if(id == null){
				return null;
			}
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM BID WHERE ID=?");
			stmt.setLong(1, id);
			ResultSet resultado = stmt.executeQuery();
			Bid b = null;
			if (resultado.next()) {
				Long bidId = resultado.getLong("ID");
				Long value = resultado.getLong("VALUE");
				Long bidderId = resultado.getLong("BIDDER_ID");
				Participant bidder = participantDAO.findById(bidderId);
				Long lotId = resultado.getLong("LOT_ID");
				Lot lot = lotDAO.findById(lotId);
				Timestamp bidTime = resultado.getTimestamp("bidTime");
				b = new Bid(bidder, value, lot, bidTime);
				b.setId(bidId);
			}
			stmt.close();
			con.close();
			return b;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao buscar lance.", ex);
		}
	}

	@Override
	public Bid createOrUpdate(Bid entity) throws DAOException {
		try {
			Connection con = BaseDAO.getConnection();

			if (entity == null) {
				return null;
			}

			if (entity.getId() != null && this.findById(entity.getId()) != null) {
				// update
				PreparedStatement stmt = con.prepareStatement(
						"UPDATE BID " + " SET VALUE = ?, BIDDER_ID = ?, LOT_ID = ?, BIDTIME = ?" + " WHERE ID = ?");
				stmt.setLong(1, entity.getValue());
				stmt.setLong(2, entity.getBidder().getId());
				stmt.setLong(3, entity.getLot().getId());
				stmt.setTimestamp(4, entity.getBidTime());
				stmt.setLong(5, entity.getId());
				stmt.executeUpdate();
				stmt.close();
				con.close();
				return entity;
			} else {		
				// create 
				PreparedStatement stmt = con
						.prepareStatement("values (NEXT VALUE FOR bid_seq)");
				
				Long nextValue = 0L;
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					nextValue = rs.getLong("1");
				}			
				
				stmt.close();
				stmt = con
						.prepareStatement("INSERT INTO BID (ID, VALUE, BIDDER_ID, LOT_ID, BIDTIME) VALUES (?,?,?,?,?)");
				stmt.setLong(1, nextValue);
				stmt.setLong(2, entity.getValue());
				stmt.setLong(3, entity.getBidder().getId());
				stmt.setLong(4, entity.getLot().getId());
				stmt.setTimestamp(5, entity.getBidTime());
				stmt.executeUpdate();
				entity.setId(nextValue);
				stmt.close();
				
				stmt = con
						.prepareStatement("INSERT INTO LOT_BID (LOT_ID, BID_ID) VALUES (?,?)");
				stmt.setLong(1, entity.getLot().getId());
				stmt.setLong(2, entity.getId());
				stmt.executeUpdate();
				stmt.close();
				
				con.close();			
				return entity;
			}
		} catch (SQLException ex) {
			throw new DAOException("Falha ao criar ou atualizar lance.", ex);
		}
	}

	@Override
	public Bid delete(Bid entity) throws DAOException {
		try {
			if(entity == null){
				return null;
			}
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM BID WHERE ID=?");
			stmt.setLong(1, entity.getId());
			stmt.executeUpdate();
			stmt.close();
			
			stmt = con.prepareStatement("DELETE FROM LOT_BID WHERE BID_ID=?");
			stmt.setLong(1, entity.getId());
			stmt.executeUpdate();
			stmt.close();
			
			con.close();
			return entity;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao deletar lance.", ex);
		}
	}

	@Override
	public List<Bid> findByLotId(Long lotId) throws DAOException {
		try {
			if(lotId == null){
				return null;
			}
			List<Bid> bidList = new ArrayList<>();
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM BID WHERE LOT_ID=?");
			stmt.setLong(1, lotId);
			ResultSet resultado = stmt.executeQuery();
			Bid b = null;
			while (resultado.next()) {
				Long bidId = resultado.getLong("ID");
				Long value = resultado.getLong("VALUE");
				Long bidderId = resultado.getLong("BIDDER_ID");
				Participant bidder = participantDAO.findById(bidderId);
				Long lotIdd = resultado.getLong("LOT_ID");
				Lot lot = lotDAO.findById(lotIdd);
				Timestamp bidTime = resultado.getTimestamp("bidTime");
				b = new Bid(bidder, value, lot, bidTime);
				b.setId(bidId);
				
				bidList.add(b);
			}
			stmt.close();
			con.close();
			return bidList;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao buscar lances do lote.", ex);
		}
	}

}