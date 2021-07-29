package wniemiec.app.nforum.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wniemiec.app.nforum.services.AuthService;

@WebServlet("/")
public class HomeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private AuthService authService = new AuthService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		boolean logged = authService.isLogged(request.getSession());

		if (logged)
			response.sendRedirect("/topics");
		else
			response.sendRedirect("/auth/signin");
	}
}
