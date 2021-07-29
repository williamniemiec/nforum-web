package wniemiec.app.nforum.config;

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
