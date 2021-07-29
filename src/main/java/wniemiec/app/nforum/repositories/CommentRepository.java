package wniemiec.app.nforum.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wniemiec.app.nforum.config.DatabaseConfig;
import wniemiec.app.nforum.dto.CommentDTO;
import wniemiec.app.nforum.dto.CommentNewDTO;
import wniemiec.app.nforum.repositories.enums.Points;
import wniemiec.app.nforum.services.UserAccountService;

public class CommentRepository {
	
	private UserAccountService userService = new UserAccountService();

	static {
		try {
			Class.forName(DatabaseConfig.getDriver());
		}
		catch (ClassNotFoundException e) {
		}
	}
	
	public List<CommentDTO> findAllCommentsFromTopicWithId(Integer id) 
			throws SQLException {
		List<CommentDTO> comments = new ArrayList<>();
		
		try (Connection c = DriverManager.getConnection(
			DatabaseConfig.getHost(), DatabaseConfig.getUsername(), DatabaseConfig.getPassword()
		)) {
			String sql = new StringBuilder()
				.append("SELECT	* ")
				.append("FROM	topic_comment ")
				.append("WHERE	id_topic = ? ")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
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
		try (Connection c = DriverManager.getConnection(
			DatabaseConfig.getHost(), DatabaseConfig.getUsername(), DatabaseConfig.getPassword()
		)) {
			String sql = new StringBuilder()
				.append("INSERT	INTO topic_comment ")
				.append("(\"comment\", login, id_topic) ")
				.append("VALUES (?, ?, ?) ")
				.toString();
			
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, comment.getContent());
			stmt.setString(2, comment.getAuthorId());
			stmt.setInt(3, comment.getTopicId());
			stmt.executeUpdate();
			
			sql = new StringBuilder()
				.append("UPDATE	user_account ")
				.append("SET 	points = points + ? ")
				.append("WHERE 	login = ?")
				.toString();
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, Points.NEW_COMMENT.getValue());
			stmt.setString(2, comment.getAuthorId());
			stmt.executeUpdate();
			
			c.commit();
		}
	}
}
