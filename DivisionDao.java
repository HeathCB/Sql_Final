package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.Division;


public class DivisionDao {

	private final String addDivision = "Insert into divisions(divisionId, division_name, division_champ, leagueId) Values(?,?,?,?)";
	private final String deleteDivision = "Delete from divisions where divisionId =?";
	private final String updateDivision = "Update divisions set divisionId =?, division_name =?, division_champ =?, leagueId =?";
	private Connection connection;
	
	
	public DivisionDao() { //constructor
		connection = DBConnection.getInstance().getConnection(); //instance of connection
	}
	
	public List <Division> getAllDivisions() throws SQLException{ //retrieves all divisions from db
		List<Division> out = new ArrayList<>();
		
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery( "Select * from divisions" );
		
		while ( rs.next() ) {
			out.add(new Division(rs.getInt( "divisionId" ), rs.getString( "division_name" ), rs.getInt( "division_champ" ), rs.getInt( "leagueId") ));
		}
		return out;
	}
	
	public void addDivision( int divisionId, String division_name, int division_champ, int leagueId ) throws SQLException { //creates a division
		try ( PreparedStatement ps = connection.prepareStatement(addDivision) ){
			ps.setInt(1, divisionId);
			ps.setString(2, division_name);
			ps.setInt(3, division_champ);
			ps.setInt(4, leagueId);
			ps.executeUpdate();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public void deleteDivision(int divisionId) throws SQLException { //deletes a division based off division id
		 PreparedStatement ps = connection.prepareStatement(deleteDivision); 
			ps.setInt(1, divisionId);
			ps.executeUpdate();
		
		
	}
	
	public void updateDivision( int divisionId, String division_name, int division_champ, int leagueId ) throws SQLException { //updates a division based off division id
		PreparedStatement ps = connection.prepareStatement(updateDivision);
			ps.setInt(1, divisionId);
			ps.setString(2, division_name);
			ps.setInt(3, division_champ);
			ps.setInt(4, leagueId);
			ps.executeUpdate();
		
	}
}
