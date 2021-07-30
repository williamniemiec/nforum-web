package wniemiec.app.nforum.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.app.nforum.core.ViewLoader;
import wniemiec.app.nforum.dto.CredentialsDTO;
import wniemiec.app.nforum.services.AuthService;

/**
 * Responsible for handling sign in requests.
 */
public class SignInController {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private AuthService authService = new AuthService();
	

	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ViewLoader.loadView(request, response, "SignInPage", "Sign In");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		CredentialsDTO credentialsDto = new CredentialsDTO(
			request.getParameter("login"),
			request.getParameter("password")
		);
		
		boolean success = authService.signin(credentialsDto, request.getSession());
		
		if (success)
			response.sendRedirect("/topics");
		else {
			request.setAttribute("error", "Authentication failed");
			ViewLoader.loadView(request, response, "SignInPage", "Sign In");
		}
	}
}
