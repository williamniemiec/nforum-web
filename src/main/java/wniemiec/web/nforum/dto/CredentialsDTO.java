package wniemiec.web.nforum.dto;


/**
 * Responsible for projecting data from a credential.
 */
public class CredentialsDTO {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private String login;
	private String password;
	

	//-------------------------------------------------------------------------
	//		Constructors
	//-------------------------------------------------------------------------
	public CredentialsDTO() {
	}
	
	public CredentialsDTO(String login, String password) {
		this.login = login;
		this.password = password;
	}


	//-------------------------------------------------------------------------
	//		Getters & Setters
	//-------------------------------------------------------------------------
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
