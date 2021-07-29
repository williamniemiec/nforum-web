package wniemiec.app.nforum.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConfig {

	private static final String DB_DRIVER;
	private static final String DB_HOST;
	private static final String DB_USERNAME;
	private static final String DB_PASSWORD;
	
	static {
		DB_DRIVER = "org.postgresql.Driver";
		DB_HOST = "jdbc:postgresql://localhost/nforum";
		DB_USERNAME = "postgres";
		DB_PASSWORD = "root";
	}
	
	public static void resetIndex() throws SQLException {
		try {
			Class.forName(DB_DRIVER);
		}
		catch (ClassNotFoundException e) {
		}
		
		try (Connection c = DriverManager.getConnection(
			DB_HOST, DB_USERNAME, DB_PASSWORD
		)) {
			executeUpdate(c, "ALTER SEQUENCE comment_id_comment_seq RESTART WITH 3");
			executeUpdate(c, "ALTER SEQUENCE topic_id_topic_seq RESTART WITH 3");
		}
	}
	
	private static void executeUpdate(Connection c, String sql) 
			throws SQLException {
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.executeUpdate();
	}
	
	public static String getDriver() {
		return DB_DRIVER;
	}
	
	public static String getHost() {
		return DB_HOST;
	}
	
	public static String getUsername() {
		return DB_USERNAME;
	}
	
	public static String getPassword() {
		return DB_PASSWORD;
	}
}
