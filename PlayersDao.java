package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Needs player entity imported still

public class PlayersDao {
	
		private Connection connection;
		
		public PlayersDao () {
			connection = DBConnection.getConnection();		
		}
		
		public List<Player> getMembersByTeamId(int teamId) throws SQLException {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM players WHERE team_ID = ?");
			ps.setInt(1, teamId);
			ResultSet rs = ps.executeQuery();
			List<Player> players = new ArrayList<Player>();
			
			while (rs.next()) {
				players.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			return players; //returns list of players by team id
		}
		
		public void createNewPlayer(String player_name, int player_num, String player_position, int teamId) throws SQLException {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO players( player_name, player_num, player_position, team_id) VALUES(?,?,?,?)");
			ps.setString(1,  player_name);
			ps.setInt(2,  player_num);
			ps.setString(3,  player_position);
			ps.setInt(4,  teamId);
			ps.executeUpdate();
		}
		public void deletePlayersByTeamId(int teamId) throws SQLException {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM players WHERE team_id = ?");
			ps.setInt(1, teamId);
			ps.executeUpdate();
		}
		
		public void deletePlayerById(int id) throws SQLException {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM players WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
					
		}
}
