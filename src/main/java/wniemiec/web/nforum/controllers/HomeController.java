package wniemiec.web.nforum.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.web.nforum.services.AuthService;


/**
 * Responsible for handling initial request.
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private final AuthService authService;


	//-------------------------------------------------------------------------
	//		Constructor
	//-------------------------------------------------------------------------
	public HomeController() {
		authService = new AuthService();
	}
       

	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		if (isLogged(request)) {
			response.sendRedirect("/topics");
		}
		else {
			response.sendRedirect("/auth/signin");
		}
	}
	
	private boolean isLogged(HttpServletRequest request) {
		return authService.isLogged(request.getSession());
	}
}
