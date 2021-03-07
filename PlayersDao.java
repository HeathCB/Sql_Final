package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.Players;

public class PlayersDao {
	
	private final String addPlayer = "Insert into players(playerId, player_name, player_num, player_position, teamId) Values(?,?,?,?,?)";
	private final String deleteplayer = "Delete from players where playerId =?";
	private final String updateplayer = "Update players set player_name=?, player_num=?, player_position=?, teamId=? where playerId =?";
	private static Connection connection;
	
	public PlayersDao() { //constructor
		connection = DBConnection.getInstance().getConnection(); //instance of connection
	}

	public List<Players> getMembersByTeamId(int teamId) throws SQLException { //gets all members of a team and returns a list, based off of team id
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM players where teamId = ?");
		ps.setInt(1, teamId);
		ResultSet rs = ps.executeQuery();
		List<Players> players = new ArrayList<>();
		
		while (rs.next()) {
			players.add( new Players(rs.getInt( 1 ), rs.getString( 2 ), rs.getInt( 3 ), rs.getString( 4 ), rs.getInt( 5 ) ) );
		}
		return players;
	}
	
	public void addPlayer ( int playerId, String player_name, int player_num, String player_position, int teamId ) throws SQLException { //creates a new player
		try ( PreparedStatement ps = connection.prepareStatement(addPlayer)) {
			ps.setInt(1, playerId);
			ps.setString(2, player_name);
			ps.setInt(3, player_num);
			ps.setString(4, player_position);
			ps.setInt(5, teamId);
			ps.executeUpdate();
		}
		catch( SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletePlayer( int playerId ) throws SQLException { //deletes a player based off of player id
		try ( PreparedStatement ps = connection.prepareStatement(deleteplayer)){
			ps.setInt(1, playerId);
			ps.executeUpdate();
		}
		catch( SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletePlayersByTeamId(int teamId) throws SQLException { //deletes all the players of a given team, based off team id
		PreparedStatement ps = connection.prepareStatement("DELETE FROM players WHERE teamId =?");
		ps.setInt(1, teamId);
		ps.executeUpdate();
	}
	
	public void updatePlayer( int playerId, String player_name, int player_num, String player_position, int teamId ) throws SQLException { //modifies a player, based off player id
		try ( PreparedStatement ps = connection.prepareStatement(updateplayer)){
			ps.setInt(5, playerId);
			ps.setString(1, player_name);
			ps.setInt(2, player_num);
			ps.setString(3, player_position);
			ps.setInt(4, teamId);
			ps.executeUpdate();
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
	}

	public List<Players> getAllPlayers() throws SQLException { //gets all players in the db. Returns them in a list
		List<Players> out = new ArrayList<>();
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery( "Select * from players" );
		
		while ( rs.next() ) {
			out.add( new Players( rs.getInt( "playerid" ), rs.getString( "player_name" ), rs.getInt( "player_num" ), rs.getString( "player_position" ), rs.getInt( "teamId" ) ) );
		}
		return out;
	}
}
