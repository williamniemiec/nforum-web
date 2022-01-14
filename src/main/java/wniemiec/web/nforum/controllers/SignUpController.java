package wniemiec.web.nforum.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.web.nforum.core.ViewLoader;
import wniemiec.web.nforum.dto.UserNewDTO;
import wniemiec.web.nforum.services.UserAccountService;
import wniemiec.web.nforum.services.exceptions.NewElementException;


/**
 * Responsible for handling sign out requests.
 */
public class SignUpController {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private final UserAccountService userService;


	//-------------------------------------------------------------------------
	//		Constructor
	//-------------------------------------------------------------------------
	public SignUpController() {
		userService = new UserAccountService();
	}


	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		ViewLoader.loadView(request, response, "SignUpPage", "Sign Up");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		UserNewDTO newUser = new UserNewDTO(
			request.getParameter("name"),
			request.getParameter("login"),
			request.getParameter("email"),
			request.getParameter("password")
		);
		
		try {
			userService.insert(newUser);
			response.sendRedirect("/");
		} 
		catch (NewElementException e) {
			request.setAttribute("error", e.getMessage());
			ViewLoader.loadView(
				request, 
				response, 
				"SignUpPage", 
				"Sign Up"
			);
		}
	}
}
