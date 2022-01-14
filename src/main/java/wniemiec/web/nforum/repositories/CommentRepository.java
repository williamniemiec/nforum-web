package wniemiec.web.nforum.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import wniemiec.web.nforum.dto.CommentDTO;
import wniemiec.web.nforum.dto.CommentNewDTO;
import wniemiec.web.nforum.repositories.enums.Points;
import wniemiec.web.nforum.services.UserAccountService;


/**
 * Responsible for accessing the topic comments table from database.
 */
public class CommentRepository extends Repository {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private UserAccountService userService = new UserAccountService();


	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public List<CommentDTO> findAllCommentsFromTopicWithId(Integer id) 
			throws SQLException {
		List<CommentDTO> comments = new ArrayList<>();
		
		try (Connection c = buildDatabaseConnection()) {
			PreparedStatement stmt = c.prepareStatement(buildQuery(
				"SELECT	*",
				"FROM	topic_comment",
				"WHERE	id_topic = ?"
			));
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				comments.add(new CommentDTO(
					rs.getInt("id_comment"),
					rs.getString("comment"),
					userService.findByLogin(rs.getString("login"))
				));
			}
		}
		
		return comments;
	}
	
	public void insert(CommentNewDTO comment) throws SQLException {
		try (Connection c = buildDatabaseConnection()) {			
			c.setAutoCommit(false);
			
			insertComment(comment, c);
			addPointsForAuthor(comment, c);
			
			c.commit();
		}
	}

	private void insertComment(CommentNewDTO comment, Connection c) 
			throws SQLException {
		PreparedStatement stmt = c.prepareStatement(buildQuery(
			"INSERT	INTO topic_comment",
			"(\"comment\", login, id_topic)",
			"VALUES (?, ?, ?)"
		));
		
		stmt.setString(1, comment.getContent());
		stmt.setString(2, comment.getAuthorId());
		stmt.setInt(3, comment.getTopicId());
		stmt.executeUpdate();
	}

	private void addPointsForAuthor(CommentNewDTO comment, Connection c) 
			throws SQLException {
		PreparedStatement stmt = c.prepareStatement(buildQuery(
			"UPDATE	user_account",
			"SET 	points = points + ?",
			"WHERE 	login = ?"
		));
		
		stmt.setInt(1, Points.NEW_COMMENT.getValue());
		stmt.setString(2, comment.getAuthorId());
		stmt.executeUpdate();
	}
}
