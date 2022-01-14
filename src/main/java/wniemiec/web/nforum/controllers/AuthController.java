package wniemiec.web.nforum.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Responsible for handling authentication requests.
 */
@WebServlet({
	"/auth/signin", 
	"/auth/signup", 
	"/auth/logout"
})
public class AuthController extends HttpServlet {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private final SignInController signInController;
	private final SignUpController signUpController;
	private final LogoutController logoutController;


	//-------------------------------------------------------------------------
	//		Constructor
	//-------------------------------------------------------------------------
	public AuthController() {
		signInController = new SignInController();
		signUpController = new SignUpController();
		logoutController = new LogoutController();
	}
    

	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		if ("/auth/signin".equalsIgnoreCase(uri)) {
			signInController.doGet(request, response);
		}
		else if ("/auth/signup".equalsIgnoreCase(uri)) {
			signUpController.doGet(request, response);
		}
		else if ("/auth/logout".equalsIgnoreCase(uri)) {
			logoutController.doGet(request, response);
		}
		else {
			signInController.doGet(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		if ("/auth/signin".equalsIgnoreCase(uri)) {
			signInController.doPost(request, response);
		}
		else if ("/auth/signup".equalsIgnoreCase(uri)) {
			signUpController.doPost(request, response);
		}
	}
}
