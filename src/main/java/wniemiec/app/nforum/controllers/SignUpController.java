package wniemiec.app.nforum.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wniemiec.app.nforum.core.ViewLoader;
import wniemiec.app.nforum.dto.UserNewDTO;
import wniemiec.app.nforum.services.UserService;
import wniemiec.app.nforum.services.exceptions.NewElementException;

public class SignUpController {
	
	private UserService userService = new UserService();

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
