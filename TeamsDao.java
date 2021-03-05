package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Teams;


public class TeamsDao {
	
	private Connection connection;
	private PlayersDao playerDao;

	
	public TeamsDao() {
		connection = DBConnection.getConnection();
		playerDao = new PlayersDao();
	}
	
	public List<Teams> getTeams() throws SQLException {
		ResultSet rs = connection.prepareStatement("SELECT * FROM teams").executeQuery();
		List<Teams> teams = new ArrayList<Teams>();
		
		while (rs.next()) {
			teams.add(populateTeam(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			
		}
		
		return teams;
	}
	
	public Teams getTeamById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM teams WHERE id = ?");
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateTeam(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)); 
	}
	
	public void createNewTeam(String teamName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO teams(name) VALUES(?)");
		ps.setString(1, teamName);
		ps.executeUpdate();
	}
	
	public void deleteTeamById(int id) throws SQLException {
		playerDao.deletePlayersByTeamId(id);
		PreparedStatement ps = connection.prepareStatement("DELETE FROM teams WHERE id = ?");
		ps.setInt(1,  id);
		ps.executeUpdate();
	}
	
	private Teams populateTeam(int id, String team_name, int team_champs, int divisionId) throws SQLException {
		return new Teams(id, team_name, team_champs, playerDao.getPlayersByTeamId(id), divisionId);
	}
}
