package com.wth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLTagDAO implements TagDAO{
        private Connection conn;
	
	public MySQLTagDAO() {
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
	public Tag[] getTags() {
		String sql = "SELECT * FROM tags;";
		Tag[] ret = query(sql);
		return ret;
	}
        
        @Override
	public boolean isTagExisting(Tag tag) {
		String sql = "SELECT * FROM tags WHERE tag ='"
			+ tag.getName() + "';";
		Tag[] ret = query(sql);
		
                if(ret.length == 0)
                    return false;
                
                return true;
	}
        
        private Tag[] query(String sql) {
		java.util.List<Tag> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				String tagName = resultSet.getString("tag_name");
                                Tag tag = new Tag(tagName);
				ret.add(tag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new Tag[ret.size()]);
	}
        
        @Override
	public void createTag(Tag tag) throws SQLException {
		String sql = "INSERT INTO tags(tag_name VALUES('"
                        + tag.getName() + "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}
        
        @Override
	public void deleteTag(Tag tag) {
		String sql = "DELETE FROM tags WHERE "
			+ "tag_name = '" + tag.getName() + "';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
        @Override
	public void tagLocation(Location loc, Tag tag) throws SQLException {
		String sql = "INSERT INTO tags_location(loc_id, tag_name VALUES('"
                        + loc.getLocID() + "', '" + tag.getName() + "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}
        
        @Override
	public void untagLocation(Location loc, Tag tag) {
		String sql = "DELETE FROM tags_location WHERE "                       
                        + "loc_id = '" + loc.getLocID() + "' AND"
                        + "tag_name = '" + tag.getName() + "';";
		try (Statement stmt = conn.createStatement()){
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
        @Override
	public void tagDare(Dare dare, Tag tag) throws SQLException {
		String sql = "INSERT INTO tags_dare(dare_id, tag_name VALUES('"
                        + dare.getDareID() + "', '" + tag.getName() + "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}
        
        @Override
	public void untagDare(Dare dare, Tag tag) {
		String sql = "DELETE FROM tags_dare WHERE "                       
                        + "dare_id = '" + dare.getDareID() + "' AND"
                        + "tag_name = '" + tag.getName() + "';";
		try (Statement stmt = conn.createStatement()){
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
