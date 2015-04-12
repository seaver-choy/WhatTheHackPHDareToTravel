package com.wth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLAccountDAO implements AccountDAO {
	private Connection conn;
	
	public MySQLAccountDAO() {
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
	public Account[] getAllAccounts() {
		String sql = "SELECT * FROM accounts;";
		Account[] ret = query(sql);
		return ret;
	}

	@Override
	public Account getAccount(String username) {
		String sql = "SELECT * FROM accounts WHERE username ='"
						+ username + "';";
		Account[] ret = query(sql);
		return (ret.length == 0) ? null : ret[0];
	}
	
	private Account[] query(String sql) {
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
	public void createUserAccount(Account user) throws SQLException {
		String sql = "INSERT INTO accounts(username, password, "
				+ "first_name, last_name, email) VALUES('"
				+ user.getUsername() +"', '" + user.getPassword() + "', '"
				+ user.getFirstName() + "', '" + user.getLastName() + "', '"
				+ user.getEmail() + "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}
	
	@Override
	public void updateUserAccount(Account user) {
		String sql = "UPDATE accounts SET password ='" + user.getPassword()
				+ "', first_name = '" + user.getFirstName() + "', last_name ='"
				+ user.getLastName() + "', email = '" + user.getEmail()
				+ "' WHERE username = '" + user.getUsername() +"';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAccount(Account acct) {
		String sql = "DELETE FROM accounts WHERE "
				+ "username = '" + acct.getUsername() + "';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

