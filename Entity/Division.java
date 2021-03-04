package Entity;

public class Division {

	private int divisionId;
	private String division_name;
	private int leagueId;
	private int division_champs;
	
	
	public Division(int divisionId, String division_name, int leagueId, int division_champs) {
		this.setDivisionId(divisionId);
		this.setDivision_name(division_name);
		this.setLeagueId(leagueId);
		this.setDivision_champs(division_champs);
	}
	public int getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public String getDivision_name() {
		return division_name;
	}
	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}
	public int getDivision_champs() {
		return division_champs;
	}
	public void setDivision_champs(int division_champs) {
		this.division_champs = division_champs;
	}

	
}
