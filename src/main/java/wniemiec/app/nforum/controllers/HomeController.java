package wniemiec.app.nforum.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.app.nforum.services.AuthService;

/**
 * Responsible for handling initial request.
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private AuthService authService = new AuthService();
       

	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		boolean logged = authService.isLogged(request.getSession());

		if (logged)
			response.sendRedirect("/topics");
		else
			response.sendRedirect("/auth/signin");
	}
}
