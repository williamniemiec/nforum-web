package wniemiec.app.nforum.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.app.nforum.core.ViewLoader;
import wniemiec.app.nforum.dto.UserNewDTO;
import wniemiec.app.nforum.services.UserAccountService;
import wniemiec.app.nforum.services.exceptions.NewElementException;

/**
 * Responsible for handling sign out requests.
 */
public class SignUpController {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private UserAccountService userService = new UserAccountService();


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
			ViewLoader.loadView(request, response, "SignUpPage", "Sign Up");
		}
	}
}
