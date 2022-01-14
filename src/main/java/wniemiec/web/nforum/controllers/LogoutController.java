package wniemiec.web.nforum.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.web.nforum.services.AuthService;


/**
 * Responsible for handling logout requests.
 */
public class LogoutController {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private AuthService authService;


	//-------------------------------------------------------------------------
	//		Constructor
	//-------------------------------------------------------------------------
	public LogoutController() {
		authService = new AuthService();
	}
	

	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		authService.logout(request.getSession());
		response.sendRedirect("/");
	}
}
