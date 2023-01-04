package wniemiec.web.nforum.config;


/**
 * Defines information needed to communicate with the production database.
 */
public class ProductionDatabase implements Database {

	@Override
	public String getDriver() {
		return "org.postgresql.Driver";
	}
	
	@Override
	public String getUri() {
		return "jdbc:postgresql://github-database-postgres.fly.dev:5432/nform";
	}
	
	@Override
	public String getUsername() {
		return "postgres";
	}
	
	@Override
	public String getPassword() {
		return "oiVDIqbul3VfqAt";
	}
}
