package wniemiec.app.nforum.config;

public class ProductionDatabase implements Database {

	@Override
	public String getDriver() {
		return "org.postgresql.Driver";
	}
	
	@Override
	public String getUri() {
		return System.getenv("JDBC_DATABASE_URL");
	}
	
	@Override
	public String getUsername() {
		return "jrctucgfmasimh";
	}
	
	@Override
	public String getPassword() {
		return "6e44e97bcbe891fc33e0e673f0d65ab143522a33c7683e221b9a82dddc6b8403";
	}
}
