package wniemiec.web.nforum.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import wniemiec.web.nforum.config.DatabaseConfig;


/**
 * Responsible for accessing a table from database.
 */
public abstract class Repository {
	
	//-------------------------------------------------------------------------
	//		Initialization block
	//-------------------------------------------------------------------------
	static {
		loadDatasetDriver();
	}


	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	private static void loadDatasetDriver() {
		try {
			Class.forName(DatabaseConfig.getDriver());
		}
		catch (ClassNotFoundException e) {
			System.err.println(e.toString());
		}
	}
	
	public Connection buildDatabaseConnection() throws SQLException {
		return DriverManager.getConnection(
			DatabaseConfig.getUri(), 
			DatabaseConfig.getUsername(), 
			DatabaseConfig.getPassword()
		);
	}
	
	public String buildQuery(String... statements) {
		StringBuilder query = new StringBuilder();
		
		for (String statement : statements) {
			query.append(statement);
			query.append(' ');
		}
		
		return query.toString();
	}
}
