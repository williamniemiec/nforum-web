package wniemiec.web.nforum.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.web.nforum.core.ViewLoader;
import wniemiec.web.nforum.dto.CredentialsDTO;
import wniemiec.web.nforum.services.AuthService;


/**
 * Responsible for handling sign in requests.
 */
public class SignInController {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private final AuthService authService;


	//-------------------------------------------------------------------------
	//		Constructor
	//-------------------------------------------------------------------------
	public SignInController() {
		authService = new AuthService();
	}


	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		ViewLoader.loadView(
			request, 
			response,
			"SignInPage", 
			"Sign In"
		);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		CredentialsDTO credentialsDto = new CredentialsDTO(
			request.getParameter("login"),
			request.getParameter("password")
		);
		
		boolean success = authService.signin(
			credentialsDto, 
			request.getSession()
		);
		
		if (success) {
			response.sendRedirect("/topics");
		}
		else {
			request.setAttribute("error", "Authentication failed");
			ViewLoader.loadView(request, response, "SignInPage", "Sign In");
		}
	}
}
