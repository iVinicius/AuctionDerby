/**
 * 
 */
package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business.Bid;
import business.Participant;

/**
 * @author 14108849
 *
 */
public class BidDAOImpl implements BidDAO{
	
	ParticipantDAO participantDAO = new ParticipantDAOImpl();

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
				Long participantId = resultado.getLong("BIDDER");
				Participant p = participantDAO.findById(participantId);
				// TODO:
				Long lotId = resultado.getLong("LOT");
				Date date = resultado.getDate("bidTime");
				//b = new Bid(name, cpf, email);
				b.setId(bidId);
			}
			stmt.close();
			con.close();
			return b;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao buscar participante.", ex);
		}
	}

	@Override
	public Bid createOrUpdate(Bid entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bid delete(Bid entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}