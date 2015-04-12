package com.wth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class MySQLLocationPhotoDAO implements PhotoDAO{
	private Connection conn = null;
	
	public MySQLLocationPhotoDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hackathon";
			conn = DriverManager.getConnection(url, "root", "DLSU1234");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Photo getPhoto(int loc_id, String directory) {
		String sql = "SELECT * FROM location_photos WHERE loc_id = '"
						+ loc_id + "' AND directory = '" + directory + "';";
		Photo[] ret = query(sql);
		return (ret.length == 0) ? null : ret[0];
	}
	@Override
	public Photo[] getAllPhotos(int loc_id) {
		String sql = "SELECT * FROM location_photos WHERE username = '" + loc_id + "';";
		Photo[] ret = query(sql);
		return ret;
	}
	
	private Photo[] query(String sql) {
		List<Photo> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				String directory = resultSet.getString("directory");
				Photo pic = new Photo(directory);
				ret.add(pic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new Photo[ret.size()]);
	}
	
	public void addPhoto(int loc_id, Photo pic) {
		String sql = "INSERT INTO location_photos(loc_id, directory) VALUES('"
				+ loc_id +"', '" + pic.getDirectory() + "');";
				
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
	@Override
	public void deletePhoto(int loc_id, Photo pic) {
		String sql = "DELETE FROM accounts WHERE "
				+ "loc_id = '" + loc_id + " AND directory = '" + pic.getDirectory() + "';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
