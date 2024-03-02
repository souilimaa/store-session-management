package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.user;
import singleton.connection;
public class UserDaoImpl implements dao<user> {
	public int insert(user u) throws SQLException {
		 connection conInstance = null;
	        ResultSet rs = null;
	        int userId = -1;
	        try {
	            conInstance = connection.getInstance();
	            Connection conn = conInstance.getConnection();
	            PreparedStatement checkStmt = conn.prepareStatement("SELECT id FROM users WHERE nom = ? and password=?");
	            checkStmt.setString(1, u.getNom());
	            checkStmt.setString(2, u.getPassword());
	            ResultSet existingUserRs = checkStmt.executeQuery();
	            if (existingUserRs.next()) {
	                userId = existingUserRs.getInt("id");
	            } else {
	                PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO users (nom, password) VALUES (?, ?)",
	                        PreparedStatement.RETURN_GENERATED_KEYS);
	                insertStmt.setString(1, u.getNom());
	                insertStmt.setString(2, u.getPassword());
	                int affectedRows = insertStmt.executeUpdate();
	                if (affectedRows == 0) {
	                    throw new SQLException("Creating user failed, no rows affected.");
	                }
	                rs = insertStmt.getGeneratedKeys();
	                if (rs.next()) {
	                    userId = rs.getInt(1); 
	                } else {
	                    throw new SQLException("Creating user failed, no ID obtained.");
	                }
	            }

	        } catch (SQLException e) {
	            System.out.println(e);
	            throw e; 
	        } finally {
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    System.out.println("Error closing ResultSet");
	                    e.printStackTrace();
	                }
	            }
	            if (conInstance != null) {
	                conInstance.closeConnection();
	            }
	        }
	        return userId;
	}

}
