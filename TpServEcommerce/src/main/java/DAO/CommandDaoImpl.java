package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.command;
import singleton.connection;

public class CommandDaoImpl implements CommandDAO {

	public int insert(command c){
		connection conInstance=null;
		PreparedStatement ps=null;
		
		try {
			conInstance=connection.getInstance();
			Connection conn=conInstance.getConnection();
		ps=conn.prepareStatement("INSERT INTO `commandes`(`userId`, `produit`) VALUES (?,?)");
		ps.setInt(1,c.getUserId());
		ps.setString(2, c.getProduct());
        int affectedRows = ps.executeUpdate();	
		return affectedRows;
        }catch(SQLException e) {
        	System.out.println(e);
        }finally {
        	if (conInstance != null)
                conInstance.closeConnection();	
        	
        	}
		return 0;
	}

	public List<command> getAll(int userID) throws SQLException {
		List<command> commandes=new ArrayList<command>();
		connection conInstance=null;
		try {
			conInstance=connection.getInstance();
			Connection conn=conInstance.getConnection();
			PreparedStatement ps=conn.prepareStatement("select * from commandes where userId=?");
			ps.setInt(1,userID);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				command cm=new command(rs.getInt("id"),rs.getInt("userId"),rs.getString("produit"));
				commandes.add(cm);
			}
				
		}catch(SQLException e) {
	        	System.out.println(e);
	        }
		finally {
			if (conInstance != null)
                conInstance.closeConnection();
        }		
	
		return commandes;
		}	

}
