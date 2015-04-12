package com.wth.model;

import java.sql.SQLException;

public interface DareDAO {
	public Dare getDare(String dareID);
        public Dare[] getDares();
        public Dare[] searchByTag(Tag[] tag);
        public int addDare(Dare dare) throws SQLException;
        public void addToRace(AmazingRace race, Dare dare) throws SQLException;
        public void cancelDare(Dare dare);
	public boolean hasDare(Location loc);
        public boolean isCompleted(Account acct, Dare dare);
        public boolean isApproved(Account acct, Dare dare);
        public Account[] getWinners(Dare dare);
        public void completeDare(Dare dare);
}
