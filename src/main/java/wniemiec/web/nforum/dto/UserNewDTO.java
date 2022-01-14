package wniemiec.web.nforum.dto;


/**
 * Responsible for projecting data from a new user.
 */
public class UserNewDTO {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private String login;
	private String name;
	private String email;
	private String password;
	

	//-------------------------------------------------------------------------
	//		Constructors
	//-------------------------------------------------------------------------
	public UserNewDTO() {
	}
	
	public UserNewDTO(String name, String login, String email, String password) {
		this.name = name;
		this.login = login;
		this.email = email;
		this.password = password;
	}


	//-------------------------------------------------------------------------
	//		Getters & Setters
	//-------------------------------------------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
