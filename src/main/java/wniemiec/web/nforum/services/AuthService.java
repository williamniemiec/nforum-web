package wniemiec.web.nforum.services;

import javax.servlet.http.HttpSession;

import wniemiec.web.nforum.dto.CredentialsDTO;
import wniemiec.web.nforum.dto.UserDTO;

/**
 * Responsible for providing authentication services.
 */
public class AuthService {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private UserAccountService userService = new UserAccountService();


	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public boolean signin(CredentialsDTO credentials, HttpSession session) {
		UserDTO user = userService.findByLoginAndPassword(
			credentials.getLogin(),
			credentials.getPassword()
		);
		
		if (user == null)
			return false;
		
		session.setAttribute("userId", credentials.getLogin());
		
		return true;
	}
	
	public void logout(HttpSession session) {
		session.removeAttribute("userId");
	}
	
	public boolean isLogged(HttpSession session) {
		return (session.getAttribute("userId") != null);
	}
}
