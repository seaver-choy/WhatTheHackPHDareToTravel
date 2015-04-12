package com.wth.model;

import java.sql.SQLException;

public interface LocationDAO {
        public Location[] getLocations();
        public Location getLocation(int loc_id);
	public Location[] searchByTag(Tag[] tag);
        public void uploadLocation(Location loc) throws SQLException;
	public void addToPack(Package pack, Location item) throws SQLException;
        public void removeFromPack(Package pack, Location loc);
}
