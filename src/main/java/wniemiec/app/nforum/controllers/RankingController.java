package wniemiec.app.nforum.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.app.nforum.core.ViewLoader;
import wniemiec.app.nforum.dto.UserDTO;
import wniemiec.app.nforum.services.AuthService;
import wniemiec.app.nforum.services.UserAccountService;

/**
 * Responsible for handling ranking requests.
 */
@WebServlet(urlPatterns= {"/ranking"})
public class RankingController extends HttpServlet {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private UserAccountService userService = new UserAccountService();
	private AuthService authService = new AuthService();


	//-------------------------------------------------------------------------
	//		Metohds
	//-------------------------------------------------------------------------
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
