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
	private final UserAccountService userService;


	//-------------------------------------------------------------------------
	//		Constructor
	//-------------------------------------------------------------------------
	public AuthService() {
		userService = new UserAccountService();
	}


	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public boolean signIn(CredentialsDTO credentials, HttpSession session) {
		if (!hasUserWithCredentials(credentials)) {
			return false;
		}
		
		session.setAttribute("userId", credentials.getLogin());
		
		return true;
	}

	private boolean hasUserWithCredentials(CredentialsDTO credentials) {
		UserDTO user = userService.findByLoginAndPassword(
			credentials.getLogin(),
			credentials.getPassword()
		);

		return (user != null);
	}
	
	public void logout(HttpSession session) {
		session.removeAttribute("userId");
	}
	
	public boolean isLogged(HttpSession session) {
		return (session.getAttribute("userId") != null);
	}
}
