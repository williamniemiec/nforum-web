package wniemiec.app.nforum.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wniemiec.app.nforum.services.AuthService;

public class LogoutController {

	private AuthService authService = new AuthService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		authService.logout(request.getSession());
		response.sendRedirect("/");
	}
}
