package wniemiec.web.nforum.config;


/**
 * Defines information needed to communicate with the local database.
 */
public class LocalDatabase implements Database {

	@Override
	public String getDriver() {
		return "org.postgresql.Driver";
	}
	
	@Override
	public String getUri() {
		return "jdbc:postgresql://localhost/nforum";
	}
	
	@Override
	public String getUsername() {
		return "postgres";
	}
	
	@Override
	public String getPassword() {
		return "root";
	}
}
