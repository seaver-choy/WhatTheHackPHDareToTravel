package com.wth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLLocationDAO implements LocationDAO{
    private Connection conn;
	
	public MySQLLocationDAO() {
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
	public Location[] getLocations() {
		String sql = "SELECT * FROM location;";
		Location[] ret = query(sql);
		return ret;
	}
        
    public Location getLocation(int loc_id){
    	String sql = "SELECT * FROM location where loc_id = '"+ loc_id + "';";
    	Location[] ret = query(sql);
    	if(ret.length == 0){
    		return null;
    	}else{
    		return ret[0];
    	}
    }
	@Override
	public Location[] searchByTag(Tag[] tag) {
                ArrayList<Location> ret = new ArrayList<>();
          
                for(int i = 0; i < tag.length; i++){
                    String sql = "SELECT * FROM tags_location WHERE tag_name ='" + tag[i] + "';";
                    Location[] temp = query(sql);
                    
                    for(int j = 0; j < temp.length; i++)
                        ret.add(temp[j]);
                }
                
		return ret.toArray(new Location[ret.size()]);
	}
        
        private Location[] query(String sql) {
		java.util.List<Location> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				int locID = Integer.parseInt(resultSet.getString("loc_id"));
				String locName = resultSet.getString("loc_name");
				String address = resultSet.getString("address");
				String description = resultSet.getString("loc_desc");

                                Location loc = new Location();
                                loc.setLocID(locID);
				loc.setLocName(locName);
                                loc.setAddress(address);
                                loc.setDescription(description);
				ret.add(loc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new Location[ret.size()]);
	}
        
        @Override
	public void uploadLocation(Location loc) throws SQLException {
		String sql = "INSERT INTO location(loc_id, loc_name, "
				+ "address, loc_desc) VALUES('"
				+ loc.getLocID() +"', '" + loc.getLocName() + "', '"
				+ loc.getAddress() + "', '" + loc.getDescription() + "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}
	
        @Override
	public void addToPack(Package pack, Location loc) throws SQLException {
		String sql = "INSERT INTO location_pack(loc_id, pack_id VALUES('"
				+ loc.getLocID() +"', '" + pack.getPackName() + "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}
        
        @Override
	public void removeFromPack(Package pack, Location loc) {
		String sql = "DELETE FROM location_pack WHERE "
			+ "loc_id = '" + loc.getLocID() + "' AND"
                        + "pack_id = '" + pack.getPackID() + "';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
