package wniemiec.app.nforum.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wniemiec.app.nforum.core.ViewLoader;
import wniemiec.app.nforum.dto.UserDTO;
import wniemiec.app.nforum.services.AuthService;
import wniemiec.app.nforum.services.UserAccountService;

@WebServlet(urlPatterns= {"/ranking"})
public class RankingController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserAccountService userService = new UserAccountService();
	private AuthService authService = new AuthService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if (!authService.isLogged(request.getSession())) {
			response.sendRedirect("/");
			return;
		}
		
		List<UserDTO> ranking = userService.getRanking();
		
		request.setAttribute("ranking", ranking);
		ViewLoader.loadView(request, response, "RankingPage", "Ranking");
	}
}
