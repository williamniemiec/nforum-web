package wniemiec.app.nforum.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConfig {

	private static Database database;
	
	static {
		database = new ProductionDatabase();
	}
	
	public static void resetIndex() throws SQLException {
		try {
			Class.forName(getDriver());
		}
		catch (ClassNotFoundException e) {
		}
		
		try (Connection c = DriverManager.getConnection(
			getUri(), getUsername(), getPassword()
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
		return database.getDriver();
	}
	
	public static String getUri() {
		return database.getUri();
	}
	
	public static String getUsername() {
		return database.getUsername();
	}
	
	public static String getPassword() {
		return database.getPassword();
	}
	
	public static void setDatabase(Database database) {
		this.database = database;
	}
}
