package com.wth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLPackageDAO implements PackageDAO{
        private Connection conn;
        
        public MySQLPackageDAO() {
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
	public Package[] getPacks() {
		String sql = "SELECT * FROM package;";
		Package[] ret = query(sql);
		return ret;
	}
        
        @Override
	public Package getPack(String packName) {
		String sql = "SELECT * FROM package WHERE pack_name ='"
						+ packName + "';";
		Package[] ret = query(sql);
		return (ret.length == 0) ? null : ret[0];
	}
        
        @Override
	public Package[] searchByTag(Tag[] tag) {
                ArrayList<Package> ret = new ArrayList<>();
          
                for(int i = 0; i < tag.length; i++){
                    String sql = "SELECT DISTINCT P.pack_id, P.pack_name, P.pack_desc "
                            + "FROM package P, location_pack L, tags_location T "
                            + "WHERE T.tag_name = '" + tag[i].getName() + "' "
                            + "AND T.loc_id = L.loc_id "
                            + "AND P.pack_id = L.pack_id;";
                    Package[] temp = query(sql);
                    
                    for(int j = 0; j < temp.length; j++)
                        ret.add(temp[j]);
                }
                
		return ret.toArray(new Package[ret.size()]);
	}
        
        private Package[] query(String sql) {
		java.util.List<Package> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString("pack_id"));
				String userName = resultSet.getString("username");
				String packName = resultSet.getString("pack_name");
				String description = resultSet.getString("pack_desc");
				
                                Package pack = new Package();
                                pack.setPackID(id);
				pack.setUsername(userName);
                                pack.setPackName(packName);
                                pack.setDescription(description);
				ret.add(pack);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new Package[ret.size()]);
	}
        
        @Override
	public void createPack(Package pack) throws SQLException {
		String sql = "INSERT INTO package(username, pack_name, pack_desc VALUES('"
			+ pack.getUsername() +"', '" + pack.getPackName() + "', '"
			+ pack.getDescription() + "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}
        
        @Override
	public void deletePack(Package pack) {
		String sql = "DELETE FROM package WHERE "
			+ "pack_id = '" + pack.getPackID() + "';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
        @Override
	public void addPackToAccount(Account acct, Package pack) throws SQLException {
		String sql = "INSERT INTO favorite_packs(username, pack_id VALUES('"
			+ acct.getUsername() +"', '" + pack.getPackID() + "', '"
			+ pack.getDescription() + "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}
        
        @Override
	public void removePackFromAccount(Account acct, Package pack) {
		String sql = "DELETE FROM favorite_packs WHERE "
                        + "username = '" + acct.getUsername() + "';"
                        + "pack_id = '" + pack.getPackID() + "';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        }
}
