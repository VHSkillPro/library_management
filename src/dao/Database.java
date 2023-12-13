package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static Database instance;
	private static String serverName;
	private static String databaseName;
	private static String username;
	private static String password;
	private static Connection conn = null;
	
	public Database(String serverName, String databaseName, String username, String password) {
		Database.serverName = serverName;
		Database.databaseName = databaseName;
		Database.username = username;
		Database.password = password;
		Database.connect();
	}
	
	public static Database getInstance(String serverName, String databaseName, String username, String password) {
		if (instance == null) {
			instance = new Database(serverName, databaseName, username, password);
		}
		return instance;
	}

	public static Boolean connect() {
		try {
			Database.conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";databaseName=" + databaseName, username, password);
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