package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static String serverName = null;
	private static String databaseName = null;
	private static String username = null;
	private static String password = null;
	private static Connection conn = null; 
	
	public static void setDatabase(String serverName, String databaseName, String username, String password) {
		Database.disconnect();
		Database.serverName = serverName;
		Database.databaseName = databaseName;
		Database.username = username;
		Database.password = password;
	}
	
	public static Boolean connect() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";databaseName=" + databaseName, username, password);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Boolean disconnect() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Connection getConnection() {
		return Database.conn;
	}
	
	public static void setServerName(String serverName) {
		Database.serverName = serverName;
	}

	public static void setDatabaseName(String databaseName) {
		Database.databaseName = databaseName;
	}

	public static void setUsername(String username) {
		Database.username = username;
	}

	public static void setPassword(String password) {
		Database.password = password;
	}
}