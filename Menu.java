package Application;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.DivisionDao;
import DAO.LeagueDao;
import DAO.PlayersDao;
import DAO.TeamsDao;
import Entity.Division;
import Entity.League;
import Entity.Players;
import Entity.Teams;
/*
 * 
 * 
 * 
 * 
 * 
 */
public class Menu {

	private Scanner scanner = new Scanner(System.in);
	private String[] menuOpts = {"Display Leagues", "Update a league", "Delete a league", "Add a league", "Display Divisions", "Update a division", "Delete a division", "Add a division", "Display teams",
			"Update a team", "Delete a team", "Add a team", "Display players", "Update a player", "Delete a player", "Add a player", "Display Players by team", "Delete Players by team"};
	private DivisionDao divisionDao = new DivisionDao();
	private LeagueDao leagueDao = new LeagueDao();
	private PlayersDao playersDao = new PlayersDao();
	private TeamsDao teamsDao = new TeamsDao();
	
	private void printMenu() {
		System.out.println( " ----------------- " );
		for ( int i = 0; i < menuOpts.length; i++ ) {
			System.out.println( ( i + 1) + ")" + menuOpts[ i ] );
		}
	}

/*	
 * Start method for the menu
 */

	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				switch ( selection ) {
				case "1":
					displayLeagues();
					break;
				case "2":
					updateLeague();
					break;
				case "3":
					deleteLeague();
					break;
				case "4":
					addLeague();
					break;
				case "5":
					displayDivisions();
					break;
				case "6":
					updateDivision();
					break;
				case "7":
					deleteDivision();
					break;
				case "8":
					addDivision();
					break;
				case "9":
					displayTeams();
					break;
				case "10":
					updateTeams();
					break;
				case "11":
					deleteTeam();
					break;
				case "12":
					addTeam();
					break;
				case "13":
					getAllPlayers();
					break;
				case "14":
					updatePlayer();
					break;
				case "15":
					deletePlayer();
					break;
				case "16":
					addPlayer();
					break;
				case "17":
					getMembersByteam();
					break;
				case "18":
					deletePlayerByTeam();
					break;
				default:
					selection = "-1";
					break;
				}
				
			} catch ( SQLException e ) {
				e.printStackTrace();
				end();
			}
			
			if ( selection.equals( "-1" ) ) {
				System.out.println( "Leaving Database" );
				end();
			
			}
		}while ( !selection.equals( "-1" ) );
	}
	

	private void getAllPlayers() throws SQLException {
		List<Players> myPlayers = playersDao.getAllPlayers();
		for ( Players p : myPlayers ) {
			System.out.println( "player ID: " + p.getPlayerId() + " " + "Player name: " + p.getPlayer_name() + " " + "Players number: " + p.getPlayer_num() + " " + "Players position: " + p.getPlayer_position() + " " + "Team Id: " + p.getTeamId() );
		}
		
	}



	private void deletePlayerByTeam() throws SQLException {
		System.out.print( "Enter the team ID");
		String nl = scanner.nextLine();
		Integer teamId= null;
		try {
			teamId = Integer.parseInt(nl);
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
			return;
		}
		if ( teamId != null ) {
			playersDao.deletePlayersByTeamId(teamId);
		}
	}



	private void addPlayer() throws SQLException {
		System.out.print( "Enter the player Id" );
		String nl = scanner.nextLine();
		Integer playerId = null;
		try {
			playerId = Integer.parseInt(nl);
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
			return;
		}
		if ( playerId != null ) {
			System.out.print( "Enter the player's name");
			String player_name = scanner.nextLine();
			if ( !player_name.isEmpty() ) {
				System.out.print( "Enter the player's number");
				String pn = scanner.nextLine();
				Integer player_num = Integer.parseInt(pn);
				if ( player_num != null ) {
					System.out.print( "Enter the player's position" );
					String player_position = scanner.nextLine();
					if ( !player_position.isEmpty() ) {
						System.out.println( "Enter the players team Id" );
						String ti = scanner.nextLine();
						Integer teamId = Integer.parseInt(ti);
						if ( teamId != null ) {
							playersDao.addPlayer(playerId, player_name, player_num, player_position, teamId);
						}
					}
				}
			}
		}
	}



	private void deletePlayer() throws SQLException {
		System.out.print( "Enter the player Id to delete" );
		String nl = scanner.nextLine();
		Integer playerId = null;
		try {
			playerId = Integer.parseInt(nl);
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
			return;
		}
		if ( playerId != null ) {
			playersDao.deletePlayer(playerId);
		}
	}



	private void updatePlayer() throws SQLException {
		System.out.print( "Enter the player Id");
		String nl = scanner.nextLine();
		Integer playerId = null;
		try {
			playerId = Integer.parseInt(nl);
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
			return;
		}
		if ( playerId != null ) {
			System.out.print( "Enter player name" );
			String player_name = scanner.nextLine();
			if ( !player_name.isEmpty() ) {
				System.out.print( "Enter Player number" );
				String pn = scanner.nextLine();
				Integer player_num = Integer.parseInt(pn);
				if ( player_name != null ) {
					System.out.print( "Enter Players position" );
					String player_position = scanner.nextLine();
					if ( !player_position.isEmpty() ) {
						System.out.print( "Enter Team Id" );
						String ti = scanner.nextLine();
						Integer teamId = Integer.parseInt(ti);
							if ( teamId != null ) {
								playersDao.updatePlayer(playerId, player_name, player_num, player_position, teamId);
							}
					}
				}
			}
		}
	}



	private void getMembersByteam() throws SQLException {
		System.out.print( "Which team would you like to see players from?" );
		String nt = scanner.nextLine();
		Integer teamId = null;
		try {
			teamId = Integer.parseInt( nt );
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
			return;
		}
		if ( teamId != null) {
			System.out.print( "Here are the players from team: " + teamId);
			playersDao.getMembersByTeamId(teamId);

		}	
	}



	private void addTeam() {
		System.out.print( "Enter the Team Id" );
		String nl = scanner.nextLine();
		Integer teamId = null;
		try {
			teamId = Integer.parseInt( nl) ;
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
			return;
		}
		if ( teamId != null ) {
			System.out.print( "Enter Team name" );
			String team_name = scanner.nextLine();
			if ( !team_name.isEmpty() ) {
				System.out.print( "Enter Team champs" );
				String tc = scanner.nextLine();
				Integer team_champ = Integer.parseInt(tc);
			if ( team_champ != null ) {
				System.out.print( "Enter Division Id" );
				String di = scanner.nextLine();
				Integer divisionId = Integer.parseInt(di);
			if ( divisionId != null )
				teamsDao.addTeam(teamId, team_name, team_champ, divisionId);
				}
			}
		}
	}



	private void deleteTeam() throws SQLException {
		System.out.print( "Enter the team Id to delete");
		String nl = scanner.nextLine();
		Integer teamId = null;
		try {
			teamId = Integer.parseInt( nl );
		} catch ( NumberFormatException e ) {
			System.out.println( "This should b e a number!" );
		}
		if ( teamId != null ) {
			teamsDao.deleteTeam( teamId );
		}
	}



	private void updateTeams() throws SQLException {
		System.out.print( "Enter the team Id" );
		String nl = scanner.nextLine();
		Integer teamId = null;
		try {
			teamId = Integer.parseInt( nl );
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
			return;
		}
		if ( teamId != null ) {
			System.out.print( "Enter team name" );
			String team_name = scanner.nextLine();
			if ( !team_name.isEmpty() ) {
				System.out.print( "Enter team champs");
				String tc = scanner.nextLine();
				Integer team_champs = Integer.parseInt(tc);
			if ( team_champs != null ) {
				System.out.print( "Enter Division Id");
				Integer divisionId = scanner.nextInt();
			if ( divisionId != null ) {
				teamsDao.updateTeam(teamId, team_name, team_champs, divisionId);
			}
			}
			}
		}
	}



	private void displayTeams() throws SQLException {
		List<Teams> myTeam = teamsDao.getAllTeams();
		for ( Teams t : myTeam ) {
			System.out.println( "Team ID: " + t.getTeamId() + " " + "Team name: " + t.getTeam_name() + " " + "Championships: " + t.getTeam_champs() + " " + "Division ID: " + t.getDivisionId() );
		}
	}



	private void addDivision() throws SQLException {
		System.out.print( "Enter the Division Id" );
		String nl = scanner.nextLine();
		Integer divisionId = null;
		try {
			divisionId = Integer.parseInt( nl );
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
			return;
		}
		if ( divisionId != null ) {
			System.out.print( "Enter Division name" );
			String division_name = scanner.nextLine();
				if ( !division_name.isEmpty() ) {
					System.out.print( "Enter Division Champs" );
					Integer division_champ = scanner.nextInt();
				if ( division_champ != null ) {
					System.out.print( "Enter League Id" );
					Integer leagueId = scanner.nextInt();
				if ( leagueId != null ) {
					divisionDao.addDivision(divisionId, division_name, division_champ, leagueId);
					}
				}
			}
		}
	}



	private void deleteDivision() throws SQLException {
		System.out.print( "Enter the Division Id to delete" );
		String nl = scanner.nextLine();
		Integer divisionId = null;
		try {
			divisionId = Integer.parseInt( nl );
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
		}
		if ( divisionId != null ) {
			divisionDao.deleteDivision( divisionId );
		}
	}



	private void updateDivision() throws SQLException {
		System.out.print( "Enter the Division Id" );
		String nl = scanner.nextLine();
		Integer divisionId = null;
		try {
			divisionId = Integer.parseInt(nl);
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
			return;
		}
		if ( divisionId != null ) {
			System.out.print( "Enter Division name" );
			String division_name = scanner.nextLine();
			if ( !division_name.isEmpty() ) {
				System.out.print( "Enter Division champs");
				Integer division_champ = scanner.nextInt();
				if ( division_champ != null) {
				System.out.print( "Enter League Id" );
				Integer leagueId = scanner.nextInt();
				if ( leagueId != null )
					divisionDao.updateDivision(divisionId, division_name, division_champ, leagueId);
				}
			}
		}
	}



	private void displayDivisions() throws SQLException {
		List<Division> myDivision = divisionDao.getAllDivisions();
		for ( Division d : myDivision ) {
			System.out.println( "Division ID: " + d.getDivisionId() + " " + "Division name: " + d.getDivision_name() + " "+ "Championships: " + d.getDivision_champs() + " " + "League Id: "+ d.getLeagueId() );
		}
	}



	private void addLeague() {
		System.out.print( "Enter the new league Id" );
		String nl = scanner.nextLine();
		Integer leagueId = null;
		try {
			leagueId = Integer.parseInt( nl );
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
			return;
		}
		if ( leagueId != null ) {
			System.out.print( "Enter the league name");
			String league_name = scanner.nextLine();
			if ( !league_name.isEmpty() ) {
				leagueDao.AddLeague(leagueId, league_name);
			}
		}
	} 



	private void deleteLeague() throws SQLException {
		System.out.print( "Enter the league id to delete" );
		String nl = scanner.nextLine();
		Integer leagueId = null;
		try {
			leagueId = Integer.parseInt( nl );
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!");
		}
		if ( leagueId != null ) {
			leagueDao.deleteLeague( leagueId );
		}
	}



	private void displayLeagues() throws SQLException {
		List<League> myLeagues = leagueDao.getAllLeagues();
		for ( League l : myLeagues ) {
			System.out.println("League id: " + l.getLeagueId() + " " + "League name: " + l.getLeague_name() );
		}
		
	}


	private void updateLeague() throws SQLException {
		System.out.print( "Enter the league ID" );
		String nl = scanner.nextLine();
		Integer leagueId = null;
		try {
			leagueId = Integer.parseInt( nl ); 
		} catch ( NumberFormatException e ) {
			System.out.println( "This should be a number!" );
			return;
		}
		if ( leagueId != null ) {
			System.out.print( "Enter the league Name");
			String league_name = scanner.nextLine();
		 if ( !league_name.isEmpty() ) {
			 System.out.print( "" );
			 leagueDao.updateLeagues(leagueId, league_name);
		}
		}
	}
		
	


	public void end() {
		System.out.println( "Leaving the database" );
	}
}