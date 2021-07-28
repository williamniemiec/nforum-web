package wniemiec.app.nforum.services;

import jakarta.servlet.http.HttpSession;
import wniemiec.app.nforum.dto.CredentialsDTO;
import wniemiec.app.nforum.dto.UserDTO;

public class AuthService {
	
	private UserService userService = new UserService();

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
