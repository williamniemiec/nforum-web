package wniemiec.web.nforum.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.web.nforum.core.ViewLoader;
import wniemiec.web.nforum.dto.CommentNewDTO;
import wniemiec.web.nforum.dto.TopicDTO;
import wniemiec.web.nforum.services.AuthService;
import wniemiec.web.nforum.services.CommentService;
import wniemiec.web.nforum.services.TopicService;
import wniemiec.web.nforum.services.exceptions.ElementNotFoundException;
import wniemiec.web.nforum.services.exceptions.NewElementException;


/**
 * Responsible for handling topic requests.
 */
@WebServlet(urlPatterns={"/topics/*"})
public class TopicController extends HttpServlet {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private final TopicService topicService;
	private final CommentService commentService;
	private final AuthService authService ;


	//-------------------------------------------------------------------------
	//		Constructor
	//-------------------------------------------------------------------------
	public TopicController() {
		topicService = new TopicService();
		commentService = new CommentService();
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
		
		String url = request.getRequestURL().toString();

		if (url.contains("/topics/new")) {
			loadNewTopicView(request, response);
		}
		else if (url.contains("/topics/open/")) {
			openTopic(request, response);
		}
		else {
			request.setAttribute("topics", topicService.getTopics());
			ViewLoader.loadView(request, response, "TopicsPage", "Topics");
		}
	}

	private boolean isLogged(HttpServletRequest request) {
		return authService.isLogged(request.getSession());
	}
	
	private void openTopic(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		int id = extractId(request.getRequestURL().toString());
		
		try {
			TopicDTO topic = topicService.findById(id);

			request.setAttribute("topic", topic);
			ViewLoader.loadView(
				request, 
				response, 
				"TopicPage", "Topics - " + topic.getTitle()
			);
		} 
		catch (ElementNotFoundException e) {
			response.sendRedirect("/");
		}
	}
	
	private int extractId(String path) {
		String id = path.substring(path.lastIndexOf('/') + 1);
		
		return Integer.parseInt(id);
	}
	
	private void loadNewTopicView(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		ViewLoader.loadView(
			request, 
			response, 
			"NewTopicPage", 
			"Topics - New"
		);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		if (!isLogged(request)) {
			response.sendRedirect("/");
			return;
		}
		
		String url = request.getRequestURL().toString();

		if (url.contains("/topics/new")) {
			loadCreateNewTopicView(request, response);
		}
		else if (url.contains("/topics/open/")) {
			createNewComment(request, response);
		}
	}

	private void loadCreateNewTopicView(HttpServletRequest request, 
										HttpServletResponse response) 
	throws IOException, ServletException {
		TopicDTO topic = new TopicDTO(
			request.getParameter("title"), 
			request.getParameter("body")
		);
		
		try {
			topicService.insert(topic, request.getSession());
			response.sendRedirect("/");
		} 
		catch (NewElementException e) {
			request.setAttribute("error", e.getMessage());
			ViewLoader.loadView(
				request, 
				response, 
				"NewTopicPage", 
				"Topics - New"
			);
		}
	}
	
	private void createNewComment(HttpServletRequest request, HttpServletResponse response) 
	throws IOException, ServletException {
		int topicId = extractId(request.getRequestURL().toString());
		CommentNewDTO comment = new CommentNewDTO(
			request.getParameter("body"), 
			topicId
		);
		
		try {
			commentService.insert(comment, request.getSession());
			response.sendRedirect("/topics/open/" + topicId);
		} 
		catch (NewElementException e) {
			request.setAttribute("error", e.getMessage());
			openTopic(request, response);
		}
	}
}
