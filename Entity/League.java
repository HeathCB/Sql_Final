package Entity;

public class League {

	private int leagueId;
	private String league_name;
	
	public League(int leagueId, String league_name) {
		this.setLeagueId(leagueId);
		this.setLeague_name(league_name);
		
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public String getLeague_name() {
		return league_name;
	}
	public void setLeague_name(String league_name) {
		this.league_name = league_name;
	}
	
	
}
