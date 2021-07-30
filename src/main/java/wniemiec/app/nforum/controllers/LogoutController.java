package wniemiec.app.nforum.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.app.nforum.services.AuthService;

/**
 * Responsible for handling logout requests.
 */
public class LogoutController {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private AuthService authService = new AuthService();
	

	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		authService.logout(request.getSession());
		response.sendRedirect("/");
	}
}
