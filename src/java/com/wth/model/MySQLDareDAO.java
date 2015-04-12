package com.wth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MySQLDareDAO implements DareDAO{
        private Connection conn;
	
	public MySQLDareDAO() {
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
	public Dare[] getDares() {
		String sql = "SELECT * FROM challenge_dares;";
		Dare[] ret = dareQuery(sql);
		return ret;
	}
        
        @Override
	public Dare[] searchByTag(Tag[] tag) {
                ArrayList<Dare> ret = null;
          
                for(int i = 0; i < tag.length; i++){
                    String sql = "SELECT * FROM challenge_dares D, tags_dare T WHERE "
                            + "T.tag_name ='" + tag[i].getName() + "' AND " 
                            + "T.dare_id = D.dare_id;";
                    Dare[] temp = dareQuery(sql);
                    
                    ArrayList<Dare> ns = new ArrayList<>();
                    for(int j = 0; j < temp.length; j++){
                    	ns.add(temp[j]);
                    }
                    
                    if (ret == null) {
                    	ret = ns;
                    } else {
                    	ret.retainAll(ns);
                    }
                }
                
		return ret.toArray(new Dare[ret.size()]);
	}
        
        private Dare[] dareQuery(String sql) {
		java.util.List<Dare> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				int dareID = Integer.parseInt(resultSet.getString("dare_id"));
				String userName = resultSet.getString("username");
                                int locID = Integer.parseInt(resultSet.getString("loc_id"));
				String dareName = resultSet.getString("dare_name");
				String description = resultSet.getString("dare_desc");
                                LocalDateTime datePosted = resultSet.getTimestamp("date_posted").toLocalDateTime();
                                
                                Dare dare = new Dare();
                                dare.setDareID(dareID);
                                dare.setUsername(userName);
                                dare.setLocID(locID);
				dare.setDareName(dareName);
                                dare.setDescription(description);
                                dare.setPostDate(datePosted);
				ret.add(dare);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new Dare[ret.size()]);
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
	public int addDare(Dare dare) throws SQLException {
		String sql = "INSERT INTO challenge_dares(username, "
				+ "loc_id, dare_name, dare_desc, date_posted) VALUES('" 
                                + dare.getUsername() + "', '"
				+ dare.getLocID() + "', '" + dare.getDareName() + "', '"
				+ dare.getDescription() + "', '" + dare.getPostDate()+ "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql, RETURN_GENERATED_KEYS);
                        ResultSet rs = stmt.getGeneratedKeys();
                        rs.next();
                        return rs.getInt(1);
		}
                
	}
        
        @Override
        public void addToRace(AmazingRace race, Dare dare) throws SQLException{
            String sql = "INSERT INTO amazing_race(race_id, dare_id) VALUES('"
				+ race.getRaceID() +"', '" + dare.getDareID() + "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
        }
        
        @Override
	public void cancelDare(Dare dare) {
		String sql = "DELETE FROM challenge_dares WHERE "
				+ "dare_id = '" + dare.getDareID() + "';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
        @Override
        public boolean hasDare(Location loc){
                String sql = "SELECT * FROM challenge_dares WHERE loc_id = '"
                    + loc.getLocID() + "';";
		Dare[] ret = dareQuery(sql);
		
                if(ret.length == 0)
                    return false;
                
                return true;
        }
        
        @Override
        public boolean isCompleted(Account acct, Dare dare){
            String sql = "SELECT * FROM complete_dares WHERE dare_id = '"
                    + dare.getDareID() + "' AND username = '"
                    + acct.getUsername() + "' AND completed = 1;";
		Dare[] ret = dareQuery(sql);
		
                if(ret.length == 0)
                    return false;
                
                return true;
        }
        
        @Override
        public boolean isApproved(Account acct, Dare dare){
            String sql = "SELECT * FROM complete_dares WHERE dare_id = '"
                    + dare.getDareID() + "' AND username = '"
                    + acct.getUsername() + "' AND approved = 1;";
		Dare[] ret = dareQuery(sql);
		
                if(ret.length == 0)
                    return false;
                
                return true;
        }
        
        @Override
	public Account[] getWinners(Dare dare) {
		String sql = "SELECT * FROM accounts A, complete_dares D WHERE "
                        + "A.username = D.username AND D.dare_id = '" + dare.getDareID()
                        + "' AND D.approved = 1;";
		Account[] ret = acctQuery(sql);
		return ret;
	}

		@Override
		public Dare getDare(String dareID) {
			String sql = "SELECT * FROM challenge_dares WHERE dare_id = " + dareID + ";";
			Dare[] ret = dareQuery(sql);
			return (ret.length != 0) ? ret[0] : null;
		}
        
		@Override
		public void completeDare(Dare dare){
			String sql = "INSERT INTO complete_dares(dare_id, username, "
					+ "completed, date_completed) VALUES('" +dare.getDareID()
					+ "', '" + dare.getUsername() + "', '"
					+ 1 +  "', '" + LocalDateTime.now() + "');";
			try (Statement stmt = conn.createStatement()) {
					stmt.executeUpdate(sql);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
}
