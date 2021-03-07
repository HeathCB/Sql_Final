package Entity;

public class Players {

	private int playerID;
	private String player_name;
	private int player_num;
	private String player_position;
	private int teamId;
	
	public Players(int playerID, String player_name, int player_num, String player_position, int teamId) {
		this.setPlayerID(playerID);
		this.setPlayer_name(player_name);
		this.setPlayer_num(player_num);
		this.setPlayer_position(player_position);
		this.setTeamId(teamId);
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getPlayer_position() {
		return player_position;
	}

	public void setPlayer_position(String player_position) {
		this.player_position = player_position;
	}

	public int getPlayer_num() {
		return player_num;
	}

	public void setPlayer_num(int player_num) {
		this.player_num = player_num;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
}
