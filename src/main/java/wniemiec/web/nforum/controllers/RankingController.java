package wniemiec.web.nforum.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.web.nforum.core.ViewLoader;
import wniemiec.web.nforum.dto.UserDTO;
import wniemiec.web.nforum.services.AuthService;
import wniemiec.web.nforum.services.UserAccountService;


/**
 * Responsible for handling ranking requests.
 */
@WebServlet(urlPatterns= {"/ranking"})
public class RankingController extends HttpServlet {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private final UserAccountService userService;
	private final AuthService authService;


	//-------------------------------------------------------------------------
	//		Constructor
	//-------------------------------------------------------------------------
	public RankingController() {
		userService = new UserAccountService();
		authService = new AuthService();
	}


	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		if (!isLogged(request)) {
			response.sendRedirect("/");
			return;
		}
		
		List<UserDTO> ranking = userService.getRanking();
		
		request.setAttribute("ranking", ranking);
		ViewLoader.loadView(
			request, 
			response, 
			"RankingPage", 
			"Ranking"
		);
	}

	private boolean isLogged(HttpServletRequest request) {
		return authService.isLogged(request.getSession());
	}
}
