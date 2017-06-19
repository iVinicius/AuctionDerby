/**
 * 
 */
package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.dao.BaseDAO;
import persistence.dao.DAOException;
import persistence.dao.ParticipantDAO;
import persistence.entities.Participant;

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
			stmt.close();
			con.close();
			return p;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao buscar participante.", ex);
		}
	}

	@Override
	public Participant createOrUpdate(Participant entity) throws DAOException {
		try {
			Connection con = BaseDAO.getConnection();

			if (entity == null) {
				return null;
			}

			if (entity.getId() != null && this.findById(entity.getId()) != null) {
				// update
				PreparedStatement stmt = con.prepareStatement(
						"UPDATE PARTICIPANT " + " SET NAME = ?, CPF = ?, EMAIL = ?" + " WHERE ID = ?");
				stmt.setString(1, entity.getName());
				stmt.setString(2, entity.getCpf());
				stmt.setString(3, entity.getEmail());
				stmt.setLong(4, entity.getId());
				stmt.executeUpdate();
				stmt.close();
				con.close();
				return entity;
			} else {		
				// create 
				PreparedStatement stmt = con
						.prepareStatement("values (NEXT VALUE FOR participant_seq)");
				
				Long nextValue = 0L;
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					nextValue = rs.getLong("1");
				}			
				
				stmt.close();
				stmt = con
						.prepareStatement("INSERT INTO PARTICIPANT (ID, NAME, CPF, EMAIL) VALUES (?,?,?,?)");
				stmt.setLong(1, nextValue);
				stmt.setString(2, entity.getName());
				stmt.setString(3, entity.getCpf());
				stmt.setString(4, entity.getEmail());
				stmt.executeUpdate();
				stmt.close();
				con.close();
				entity.setId(nextValue);
				return entity;
			}
		} catch (SQLException ex) {
			throw new DAOException("Falha ao criar ou atualizar participante.", ex);
		}
	}

	@Override
	public Participant delete(Participant entity) throws DAOException {
		try {
			if(entity == null){
				return null;
			}
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM PARTICIPANT WHERE ID=?");
			stmt.setLong(1, entity.getId());
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return entity;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao deletar participante.", ex);
		}
	}

}