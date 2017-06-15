/**
 * 
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business.Participant;

/**
 * @author Vinicius
 *
 */
public class ParticipantDAOImpl implements ParticipantDAO{

	@Override
	public Participant findById(Long id) throws DAOException{
		 try {
	            Connection con = BaseDAO.getConnection();
	            PreparedStatement stmt = con.prepareStatement(
	                    "SELECT * FROM PARTICIPANT WHERE ID=?"
	                    );
	            stmt.setString(1, id.toString());
	            ResultSet resultado = stmt.executeQuery();
	            Participant p = null;
	            if(resultado.next()) {
	            	Long participantId = new Long(resultado.getString("ID"));
	                String name = resultado.getString("NAME");
	                String cpf = resultado.getString("CPF");
	                String email = resultado.getString("EMAIL");
	                p = new Participant(participantId, name, cpf, email);
	            }
	            return p;
	        } catch (SQLException ex) {
	            throw new DAOException("Falha ao buscar participante.", ex);
	        }
	}

}