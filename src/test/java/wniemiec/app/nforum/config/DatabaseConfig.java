package wniemiec.app.nforum.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConfig {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
		}
	}
	
	public static void resetIndex() throws SQLException {
		try (Connection c = DriverManager.getConnection(
			"jdbc:postgresql://localhost/nforum", "postgres", "root"
		)) {
			String sql = new StringBuilder()
				.append("ALTER SEQUENCE comentario_id_comentario_seq RESTART WITH 3")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.executeUpdate();
		}
	}
}
