package com.wth.model;

import java.sql.SQLException;

public interface RaceDAO {
        public AmazingRace[] getRaces();
        public AmazingRace[] searchByTag(Tag[] tag);
        public void addRace(AmazingRace race) throws SQLException;
        public void cancelRace(AmazingRace race);
        public boolean isCompleted(Account acct, AmazingRace race);
        public boolean isApproved(Account acct, AmazingRace race);      
        public Account[] getWinners(AmazingRace race);
}
