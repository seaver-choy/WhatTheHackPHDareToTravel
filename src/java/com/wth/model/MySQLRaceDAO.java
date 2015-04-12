package com.wth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MySQLRaceDAO implements RaceDAO{
        private Connection conn;
	
	public MySQLRaceDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/hackathon";
			conn = DriverManager.getConnection(url, "root", "DLSU1234");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
        
        @Override
	public AmazingRace[] getRaces() {
		String sql = "SELECT * FROM challenge_dares;";
		AmazingRace[] ret = raceQuery(sql);
		return ret;
	}
        
        @Override
	public AmazingRace[] searchByTag(Tag[] tag) {
                ArrayList<AmazingRace> ret = new ArrayList<>();
          
                for(int i = 0; i < tag.length; i++){
                    String sql = "SELECT DISTINCT R.race_id, R.username, R.race_name, "
                            + "R.race_desc, R.date_posted FROM challenge_race R, "
                            + "amazing_race A, tags_dare T WHERE T.tag_name = '"
                            + tag[i].getName() + "' AND T.dare_id = A.dare_id "
                            + "AND R.race_id = A.race_id;";
                    AmazingRace[] temp = raceQuery(sql);
                    
                    for(int j = 0; j < temp.length; j++)
                        ret.add(temp[j]);
                }
                
		return ret.toArray(new AmazingRace[ret.size()]);
	}
        
        private AmazingRace[] raceQuery(String sql) {
		java.util.List<AmazingRace> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				int raceID = Integer.parseInt(resultSet.getString("race_id"));
				String userName = resultSet.getString("username");
				String raceName = resultSet.getString("race_name");
				String description = resultSet.getString("race_desc");
                                LocalDateTime datePosted = resultSet.getTimestamp("date_posted").toLocalDateTime();
                                
                                AmazingRace race = new AmazingRace();
                                race.setRaceID(raceID);
                                race.setUsername(userName);
				race.setRaceName(raceName);
                                race.setDescription(description);
                                race.setPostDate(datePosted);
				ret.add(race);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new AmazingRace[ret.size()]);
	}
        
        private Account[] acctQuery(String sql) {
		java.util.List<Account> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				
                                Account acct = new Account();
                                acct.setUsername(username);
				acct.setPassword(password);
                                acct.setFirstName(firstName);
                                acct.setLastName(lastName);
                                acct.setEmail(email);
				ret.add(acct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new Account[ret.size()]);
	}
        
        @Override
	public void addRace(AmazingRace race) throws SQLException {
		String sql = "INSERT INTO challenge_race(race_id, username, "
                        + "race_name, race_desc, date_posted) VALUES('"
			+ race.getRaceID() +"', '" + race.getUsername() + "', '"
			+ race.getRaceName() + "', '" + race.getDescription() + "', '"
                        + race.getPostDate()+ "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}
        
        @Override
	public void cancelRace(AmazingRace race) {
		String sql = "DELETE FROM challenge_race WHERE "
				+ "race_id = '" + race.getRaceID() + "';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
        @Override
        public boolean isCompleted(Account acct, AmazingRace race){
            String sql = "SELECT * FROM complete_race WHERE dare_id = '"
                    + race.getRaceID() + "' AND username = '"
                    + acct.getUsername() + "' AND completed = 1;";
		AmazingRace[] ret = raceQuery(sql);
		
                if(ret.length == 0)
                    return false;
                
                return true;
        }
        
        @Override
        public boolean isApproved(Account acct, AmazingRace race){
            String sql = "SELECT * FROM complete_race WHERE dare_id = '"
                    + race.getRaceID() + "' AND username = '"
                    + acct.getUsername() + "' AND approved = 1;";
		AmazingRace[] ret = raceQuery(sql);
		
                if(ret.length == 0)
                    return false;
                
                return true;
        }
        
        @Override
	public Account[] getWinners(AmazingRace race) {
		String sql = "SELECT * FROM accounts A, complete_race R WHERE "
                        + "A.username = R.username AND R.race_id = '" + race.getRaceID()
                        + "' AND R.approved = 1;";
		Account[] ret = acctQuery(sql);
		return ret;
	}
}
