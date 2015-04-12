package com.wth.model;

import java.sql.SQLException;

public interface PackageDAO {
	public Package[] getPacks();
	public Package getPack(String packName);
        public Package[] searchByTag(Tag[] tag);
	public void createPack(Package pack) throws SQLException;
        public void deletePack(Package pack);
        public void addPackToAccount(Account acct, Package pack) throws SQLException;
        public void removePackFromAccount(Account acct, Package pack);
}
