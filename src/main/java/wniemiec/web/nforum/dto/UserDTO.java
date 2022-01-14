package wniemiec.web.nforum.dto;


/**
 * Responsible for projecting data from a user.
 */
public class UserDTO {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private String login;
	private String name;
	private String email;
	private int points;
	

	//-------------------------------------------------------------------------
	//		Constructors
	//-------------------------------------------------------------------------
	public UserDTO() {
	}
	
	public UserDTO(String login) {
		this.login = login;
	}
	
	public UserDTO(String login, String name, String email, int points) {
		this.login = login;
		this.name = name;
		this.email = email;
		this.setPoints(points);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
