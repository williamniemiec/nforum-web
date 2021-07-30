package wniemiec.app.nforum.config;

/**
 * Defines information needed to communicate with a database.
 */
public interface Database {

    /**
     * Gets database driver.
     * 
     * @return      Database class driver
     */
	String getDriver();

    /**
     * Gets database Uniform Resource Identifier.
     * 
     * @return      Database URI
     */
	String getUri();

    /**
     * Gets database username.
     * 
     * @return      Database username
     */
	String getUsername();

    /**
     * Gets database password.
     * 
     * @return      Database password
     */
	String getPassword();
}
