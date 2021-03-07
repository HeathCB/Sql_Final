package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.League;

public class LeagueDao {
	
	private Connection connection;
	private final String addLeague = "Insert into league(leagueId, league_name)  Values (?,?)";
	private final String updateLeague = "Update league set league_name=?, where id=?";
	private final String deleteLeague = " Delete from leagues where leagueId =?";
	
	public LeagueDao() { //constructor
		connection = DBConnection.getInstance().getConnection(); //instance of connection
	}
	
	public List<League> getAllLeagues() throws SQLException{ //returns a list of all leagues in the db
		List<League> out = new ArrayList<>();
		
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery( "Select * from Leagues" );
		
		while ( rs.next() ) {
			out.add( new League(rs.getInt( "LeagueId" ), rs.getString( "League_name ") ));
		}
		return out;
	}
	
	public void AddLeague( int leagueId, String league_name ) { //creates a new league
		try ( PreparedStatement ps = connection.prepareStatement( addLeague ) ) {
			ps.setInt(1, leagueId);
			ps.setString(2, league_name);
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public void updateLeagues( int leagueId, String league_name ) throws SQLException { //modifies a league based on league id
		PreparedStatement ps = connection.prepareStatement( updateLeague );
			ps.setInt(2, leagueId);
			ps.setString(1, league_name);
			ps.executeUpdate();
			ps.close();
	}
	
	public void deleteLeague( int leagueId ) throws SQLException { //deletes a league based on league id
		PreparedStatement ps = connection.prepareStatement( deleteLeague ); 
			ps.setInt(1, leagueId);
			ps.executeUpdate();
	}
}
