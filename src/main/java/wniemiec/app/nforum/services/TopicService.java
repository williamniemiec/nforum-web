package wniemiec.app.nforum.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import wniemiec.app.nforum.dto.TopicDTO;
import wniemiec.app.nforum.dto.UserDTO;
import wniemiec.app.nforum.repositories.TopicRepository;
import wniemiec.app.nforum.services.exceptions.ElementNotFoundException;
import wniemiec.app.nforum.services.exceptions.NewElementException;

/**
 * Responsible for providing topic services.
 */
public class TopicService {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private TopicRepository topicRepository = new TopicRepository();
	private UserAccountService userService = new UserAccountService();
	

	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public TopicDTO findById(Integer id) throws ElementNotFoundException {
		try {
			return topicRepository.findById(id);
		} 
		catch (SQLException e) {
			throw new ElementNotFoundException();
		}
	}
	
	public void insert(TopicDTO topic, HttpSession session) 
			throws NewElementException {
		bindWithSessionId(topic, session);
		validateNewTopic(topic);
		
		try {
			topicRepository.insert(topic);
		} 
		catch (SQLException e) {
			System.err.println(e.toString());
			throw new NewElementException();
		}
	}

	private void validateNewTopic(TopicDTO topic) throws NewElementException {
		if (topic.getTitle() == null)
			throw new NewElementException("Title cannot be null");
		
		if (topic.getContent() == null)
			throw new NewElementException("Content cannot be null");
		
		if (topic.getAuthor() == null)
			throw new NewElementException("Unauthenticated user");
	}

	private void bindWithSessionId(TopicDTO topic, HttpSession session) {
		UserDTO author = userService.findByLogin((String) session.getAttribute("userId"));
		topic.setAuthor(author);
	}
	
	public List<TopicDTO> getTopics() {
		try {
			return topicRepository.getTopics();
		} 
		catch (SQLException e) {
			System.err.println(e.toString());
			return new ArrayList<>();
		}
	}
}
