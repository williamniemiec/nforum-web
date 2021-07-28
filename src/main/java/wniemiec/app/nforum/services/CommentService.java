package wniemiec.app.nforum.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import wniemiec.app.nforum.dto.CommentDTO;
import wniemiec.app.nforum.dto.CommentNewDTO;
import wniemiec.app.nforum.repositories.CommentRepository;
import wniemiec.app.nforum.services.exceptions.NewElementException;

public class CommentService {

	private CommentRepository commentRepository = new CommentRepository();
	
	public List<CommentDTO> findAllCommentsFromTopicWithId(Integer id) {
		try {
			return commentRepository.findAllCommentsFromTopicWithId(id);
		} 
		catch (SQLException e) {
			System.err.println(e.toString());
			return new ArrayList<>();
		}
	}
	
	public void insert(CommentNewDTO comment, HttpSession session) 
			throws NewElementException {
		bindWithSessionId(comment, session);
		validateComment(comment);
		
		try {
			commentRepository.insert(comment);
		} 
		catch (SQLException e) {
			System.err.println(e.toString());
			throw new NewElementException();
		}
	}

	private void bindWithSessionId(CommentNewDTO comment, HttpSession session) {
		comment.setAuthor((String) session.getAttribute("userId"));
	}

	private void validateComment(CommentNewDTO comment) throws NewElementException {
		if (comment.getAuthorId() == null)
			throw new NewElementException("Unauthenticated user");
		
		if (comment.getTopicId() == null)
			throw new NewElementException("Invalid topic id");
		
		if (comment.getContent() == null)
			throw new NewElementException("Content cannot be null");
	}
}
