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
public class ParticipantDAOImpl implements ParticipantDAO {

	@Override
	public Participant findById(Long id) throws DAOException {
		try {
			if(id == null){
				return null;
			}
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM PARTICIPANT WHERE ID=?");
			stmt.setLong(1, id);
			ResultSet resultado = stmt.executeQuery();
			Participant p = null;
			if (resultado.next()) {
				Long participantId = resultado.getLong("ID");
				String name = resultado.getString("NAME");
				String cpf = resultado.getString("CPF");
				String email = resultado.getString("EMAIL");
				p = new Participant(name, cpf, email);
				p.setId(participantId);
			}
			return p;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao buscar participante.", ex);
		}
	}

	@Override
	public boolean createOrUpdate(Participant entity) throws DAOException {
		try {
			Connection con = BaseDAO.getConnection();

			if (entity == null) {
				return false;
			}

			if (entity.getId() != null && this.findById(entity.getId()) != null) {
				// update
				PreparedStatement stmt = con.prepareStatement(
						"UPDATE PARTICIPANT " + " SET NAME = ?, CPF = ?, EMAIL = ?" + " WHERE ID = ?");
				stmt.setString(1, entity.getName());
				stmt.setString(2, entity.getCpf());
				stmt.setString(3, entity.getEmail());
				stmt.setLong(4, entity.getId());
				int ret = stmt.executeUpdate();
				con.close();
				return (ret > 0);
			} else {		
				// create
				PreparedStatement stmt = con
						.prepareStatement("INSERT INTO PARTICIPANT (NAME, CPF, EMAIL) VALUES (?,?,?)");
				stmt.setString(1, entity.getName());
				stmt.setString(2, entity.getCpf());
				stmt.setString(3, entity.getEmail());
				int ret = stmt.executeUpdate();
				con.close();
				return (ret > 0);
			}
		} catch (SQLException ex) {
			throw new DAOException("Falha ao criar ou atualizar participante.", ex);
		}
	}

	@Override
	public boolean delete(Participant entity) throws DAOException {
		try {
			if(entity == null){
				return false;
			}
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE PARTICIPANT WHERE ID=?");
			stmt.setLong(1, entity.getId());
			int ret = stmt.executeUpdate();
			con.close();
			return (ret > 0);
		} catch (SQLException ex) {
			throw new DAOException("Falha ao deletar participante.", ex);
		}
	}

}