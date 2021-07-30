package wniemiec.app.nforum.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wniemiec.app.nforum.core.ViewLoader;
import wniemiec.app.nforum.dto.CommentNewDTO;
import wniemiec.app.nforum.dto.TopicDTO;
import wniemiec.app.nforum.services.AuthService;
import wniemiec.app.nforum.services.CommentService;
import wniemiec.app.nforum.services.TopicService;
import wniemiec.app.nforum.services.exceptions.ElementNotFoundException;
import wniemiec.app.nforum.services.exceptions.NewElementException;

@WebServlet(urlPatterns={"/topics/*"})
public class TopicController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private TopicService topicService = new TopicService();
	private CommentService commentService = new CommentService();
	private AuthService authService = new AuthService();
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if (!authService.isLogged(request.getSession())) {
			response.sendRedirect("/");
			return;
		}
		
		String url = request.getRequestURL().toString();

		if (url.contains("/topics/new")) {
			newTopic(request, response);
		}
		else if (url.contains("/topics/open/")) {
			openTopic(request, response);
		}
		else {
			request.setAttribute("topics", topicService.getTopics());
			ViewLoader.loadView(request, response, "TopicsPage", "Topics");
		}
	}
	
	private void openTopic(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int id = extractId(request.getRequestURL().toString());
		
		try {
			TopicDTO topic = topicService.findById(id);
			request.setAttribute("topic", topic);
			ViewLoader.loadView(request, response, "TopicPage", "Topics - " + topic.getTitle());
		} 
		catch (ElementNotFoundException e) {
			response.sendRedirect("/");
		}
	}
	
	private int extractId(String path) {
		String id = path.substring(path.lastIndexOf('/') + 1);
		
		return Integer.parseInt(id);
	}
	
	private void newTopic(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ViewLoader.loadView(request, response, "NewTopicPage", "Topics - New");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if (!authService.isLogged(request.getSession())) {
			response.sendRedirect("/");
			return;
		}
		
		String url = request.getRequestURL().toString();

		if (url.contains("/topics/new")) {
			createNewTopic(request, response);
		}
		else if (url.contains("/topics/open/")) {
			createNewComment(request, response);
		}
	}

	private void createNewTopic(HttpServletRequest request, HttpServletResponse response) 
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
			ViewLoader.loadView(request, response, "NewTopicPage", "Topics - New");
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
