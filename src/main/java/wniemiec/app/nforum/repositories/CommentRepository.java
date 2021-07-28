package wniemiec.app.nforum.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wniemiec.app.nforum.dto.CommentDTO;
import wniemiec.app.nforum.dto.CommentNewDTO;
import wniemiec.app.nforum.repositories.enums.Points;
import wniemiec.app.nforum.services.UserService;

public class CommentRepository {
	
	private UserService userService = new UserService();

	static {
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
		}
	}
	
	public List<CommentDTO> findAllCommentsFromTopicWithId(Integer id) 
			throws SQLException {
		List<CommentDTO> comments = new ArrayList<>();
		
		try (Connection c = DriverManager.getConnection(
			"jdbc:postgresql://localhost/nforum", "postgres", "root"
		)) {
			String sql = new StringBuilder()
				.append("SELECT	* ")
				.append("FROM	comentario ")
				.append("WHERE	id_topico = ? ")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				comments.add(new CommentDTO(
					rs.getInt("id_comentario"),
					rs.getString("comentario"),
					userService.findByLogin(rs.getString("login"))
				));
			}
		}
		
		return comments;
	}
	
	public void insert(CommentNewDTO comment) throws SQLException {
		try (Connection c = DriverManager.getConnection(
			"jdbc:postgresql://localhost/nforum", "postgres", "root"
		)) {
			String sql = new StringBuilder()
				.append("INSERT	INTO comentario ")
				.append("(comentario, login, id_topico) ")
				.append("VALUES (?, ?, ?) ")
				.toString();
			
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, comment.getContent());
			stmt.setString(2, comment.getAuthorId());
			stmt.setInt(3, comment.getTopicId());
			stmt.executeUpdate();
			
			sql = new StringBuilder()
				.append("UPDATE	usuario ")
				.append("SET 	pontos = pontos + ? ")
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
