package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.Teams;

public class TeamsDao {
	
	private final String addteam = "Insert into teams(teamId, team_name, team_champs, divisionId) Values(?, ?, ?, ?)";
	private final String deleteteam = "Delete from teams where teamId =?";
	private final String updateteam = "Update teams set team_name=?, team_champs=?, divisionId=? where teamId=?";
	private Connection connection;
	
	public TeamsDao() { //constructor
		connection = DBConnection.getInstance().getConnection(); //instance of connection
	}
	
	public List<Teams> getAllTeams() throws SQLException{ //gets all teams on the db, returns them as a list
		List<Teams> out = new ArrayList<>();
		
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery( "Select * from teams" );
		
		while ( rs.next() ) {
			out.add( new Teams(rs.getInt( "teamId" ), rs.getString( "team_name" ), rs.getInt( "team_champs" ), rs.getInt( "divisionId" ) ));
			
		}
		return out;
	}
	
	public void addTeam(int teamId, String team_name, int team_champs, int divisionId) { //creates a new team
		try ( PreparedStatement ps = connection.prepareStatement(addteam)){
			ps.setInt(1, teamId);
			ps.setString(2, team_name);
			ps.setInt(3, team_champs);
			ps.setInt(4, divisionId);
			ps.executeUpdate();
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
	}

	public void deleteTeam(int teamId) throws SQLException { //deletes a team based off team id
		try ( PreparedStatement ps = connection.prepareStatement(deleteteam)) {
			ps.setInt(1, teamId);
			ps.executeUpdate();
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public void updateTeam(int teamId, String team_name, int team_champs, int divisionId) throws SQLException { //modifies a team based off of team id
		try ( PreparedStatement ps = connection.prepareStatement(updateteam)) {
			ps.setInt(4, teamId);
			ps.setString(1, team_name);
			ps.setInt(2, team_champs);
			ps.setInt(3, divisionId);
			ps.executeUpdate();
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public Teams getTeamById(int teamId) throws SQLException { //selects a team from the db based off team id
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM players WHERE teamId = ?");
		ps.setInt(1, teamId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateTeam(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)); 
	}

	private Teams populateTeam(int int1, String string, int int2, int int3) { //probably didn't need this extraneous method
		return new Teams( int1, string, int2, int3 );
	}
}
