package wniemiec.web.nforum.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import wniemiec.web.nforum.dto.TopicDTO;
import wniemiec.web.nforum.repositories.enums.Points;
import wniemiec.web.nforum.services.CommentService;
import wniemiec.web.nforum.services.UserAccountService;


/**
 * Responsible for accessing the topics table from database.
 */
public class TopicRepository extends Repository {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private final UserAccountService userService;
	private final CommentService commentService;


	//-------------------------------------------------------------------------
	//		Constructor
	//-------------------------------------------------------------------------
	public TopicRepository() {
		userService = new UserAccountService();
		commentService = new CommentService();
	}


	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public TopicDTO findById(Integer id) throws SQLException {
		TopicDTO topic = null;
		
		try (
			Connection c = buildDatabaseConnection();
			PreparedStatement stmt = buildFindTopicByIdStatement(c);
		) {
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				topic = new TopicDTO(
					rs.getInt("id_topic"),
					rs.getString("title"),
					rs.getString("content"),
					userService.findByLogin(rs.getString("login"))
				);
				
				topic.setComments(
					commentService.findAllCommentsFromTopicWithId(id)
				);
			}
		}
		
		return topic;
	}


	private PreparedStatement buildFindTopicByIdStatement(Connection c) 
	throws SQLException {
		return c.prepareStatement(buildQuery(
			"SELECT *",
			"FROM   topic",
			"WHERE  id_topic = ?"
		));
	}
	
	public void insert(TopicDTO topic) throws SQLException {
		try (Connection c = buildDatabaseConnection()) {
			c.setAutoCommit(false);
			
			insertTopic(topic, c);
			addPointsForAuthor(topic, c);
			
			c.commit();
		}
	}

	private void insertTopic(TopicDTO topic, Connection c) throws SQLException {
		try (PreparedStatement stmt = buildInsertTopicStatement(c)) {
			stmt.setString(1, topic.getTitle());
			stmt.setString(2, topic.getContent());
			stmt.setString(3, topic.getAuthor().getLogin());
			stmt.executeUpdate();
		}
	}

	private PreparedStatement buildInsertTopicStatement(Connection c) 
	throws SQLException {
		return c.prepareStatement(buildQuery(
			"INSERT INTO topic",
			"(title, \"content\", login)",
			"VALUES (?, ?, ?)"
		));
	}

	private void addPointsForAuthor(TopicDTO topic, Connection c) 
	throws SQLException {
		try (PreparedStatement stmt = buildAddPointsStatement(c)) {
			stmt.setInt(1, Points.NEW_TOPIC.getValue());
			stmt.setString(2, topic.getAuthor().getLogin());
			stmt.executeUpdate();
		}
	}


	private PreparedStatement buildAddPointsStatement(Connection c) 
	throws SQLException {
		return c.prepareStatement(buildQuery(
			"UPDATE user_account",
			"SET    points = points + ?",
			"WHERE  login = ?"
		));
	}
	
	public List<TopicDTO> getTopics() throws SQLException {
		List<TopicDTO> topics = new ArrayList<>();
		
		try (
			Connection c = buildDatabaseConnection();
			PreparedStatement stmt = buildFindAllTopicsStatement(c);
		) {
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				TopicDTO topic = new TopicDTO(
					rs.getInt("id_topic"),
					rs.getString("title"),
					rs.getString("content"),
					userService.findByLogin(rs.getString("login"))
				);
				topic.setComments(
					commentService.findAllCommentsFromTopicWithId(rs.getInt("id_topic"))
				);
				topics.add(topic);
			}
		}
		
		return topics;
	}


	private PreparedStatement buildFindAllTopicsStatement(Connection c) 
	throws SQLException {
		return c.prepareStatement(buildQuery(
			"SELECT *",
			"FROM   topic"
		));
	}
}
