package com.wth.model;

import java.sql.SQLException;

public interface AccountDAO {
	public Account[] getAllAccounts();
	public Account getAccount(String username);
	public void createUserAccount(Account acct) throws SQLException;
        public void updateUserAccount(Account user);
	public void deleteAccount(Account acct);
}   