/**
 * 
 */
package persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Vinicius
 * @param <E>
 *
 */
public interface BaseDAO<E>{
	
	public E findById(Long id) throws DAOException;
	
	public E createOrUpdate(E entity) throws DAOException;
	
	public E delete(E entity) throws DAOException;
	
	public static Connection getConnection() throws SQLException {
        //derbyDB sera o nome do diretorio criado localmente
        return DriverManager.getConnection("jdbc:derby:derbyDB");
    }
	
	public static void createDerbyDB() throws DAOException{
		try {
            Connection con = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            con.close();
        } catch (SQLException ex) {
            //throw new DAOException(ex.getMessage());
        	System.out.println("DerbyDB is already running");
        }
		System.out.println("DerbyDB created successfuly");
	}
}