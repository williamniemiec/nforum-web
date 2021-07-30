package wniemiec.app.nforum.config;

public class ProductionDatabase implements Database {

	@Override
	public String getDriver() {
		return "org.postgresql.Driver";
	}
	
	@Override
	public String getUri() {
		return "jdbc:postgresql://ec2-3-212-75-25.compute-1.amazonaws.com:5432/dbjglsh5vl0jup?password=6e44e97bcbe891fc33e0e673f0d65ab143522a33c7683e221b9a82dddc6b8403&sslmode=require&user=jrctucgfmasimh";
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
