package Entity;

public class Teams {

	private int teamId;
	private String team_name;
	private int team_champs;
	private int divisionId;
	
	
	public Teams(int teamId, String team_name, int team_champs, int divisionId) {
		this.setTeam_champs(team_champs);
		this.setDivisionId(divisionId);
		this.setTeamId(teamId);
		this.setTeam_name(team_name);
		
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}
	public int getTeam_champs() {
		return team_champs;
	}
	public void setTeam_champs(int team_champs) {
		this.team_champs = team_champs;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	
	
}
