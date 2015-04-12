package com.wth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class MySQLDarePhotoDAO implements PhotoDAO{
	private Connection conn = null;
	
	public MySQLDarePhotoDAO() {
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
	public Photo getPhoto(int dare_id, String file_name) {
		String sql = "SELECT * FROM dare_photos WHERE dare_id = '"
						+ dare_id + "' AND file_name = '" + file_name + "';";
		Photo[] ret = query(sql);
		return (ret.length == 0) ? null : ret[0];
	}
	@Override
	public Photo[] getAllPhotos(int dare_id) {
		String sql = "SELECT * FROM dare_photos WHERE username = '" + dare_id + "';";
		Photo[] ret = query(sql);
		return ret;
	}
	
	private Photo[] query(String sql) {
		List<Photo> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				String file_name = resultSet.getString("file_name");
				Photo pic = new Photo(file_name);
				ret.add(pic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new Photo[ret.size()]);
	}
	
	public void addPhoto(int dare_id, Photo pic) {
		String sql = "INSERT INTO dare_photos(dare_id, file_name) VALUES('"
				+ dare_id +"', '" + pic.getDirectory() + "');";
				
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
	@Override
	public void deletePhoto(int dare_id, Photo pic) {
		String sql = "DELETE FROM accounts WHERE "
				+ "dare_id = '" + dare_id + " AND file_name = '" + pic.getDirectory() + "';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
