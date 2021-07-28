package wniemiec.app.nforum.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wniemiec.app.nforum.dto.TopicDTO;
import wniemiec.app.nforum.repositories.enums.Points;
import wniemiec.app.nforum.services.CommentService;
import wniemiec.app.nforum.services.UserService;

public class TopicRepository {
	
	private UserService userService = new UserService();
	private CommentService commentService = new CommentService();

	static {
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
		}
	}
	
	public TopicDTO findById(Integer id) throws SQLException {
		TopicDTO topic = null;
		
		try (Connection c = DriverManager.getConnection(
			"jdbc:postgresql://localhost/nforum", "postgres", "root"
		)) {
			String sql = new StringBuilder()
				.append("SELECT	* ")
				.append("FROM	topico ")
				.append("WHERE	id_topico = ? ")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				topic = new TopicDTO(
					rs.getInt("id_topico"),
					rs.getString("titulo"),
					rs.getString("conteudo"),
					userService.findByLogin(rs.getString("login"))
				);
				
				topic.setComments(commentService.findAllCommentsFromTopicWithId(id));
			}
		}
		
		return topic;
	}
	
	public void insert(TopicDTO topic) throws SQLException {
		try (Connection c = DriverManager.getConnection(
			"jdbc:postgresql://localhost/nforum", "postgres", "root"
		)) {
			String sql = new StringBuilder()
				.append("INSERT INTO topico ")
				.append("(titulo, conteudo, login) ")
				.append("VALUES (?, ?, ?) ")
				.toString();
			
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, topic.getTitle());
			stmt.setString(2, topic.getContent());
			stmt.setString(3, topic.getAuthor().getLogin());
			stmt.executeUpdate();
			
			sql = new StringBuilder()
				.append("UPDATE	usuario ")
				.append("SET 	pontos = pontos + ? ")
				.append("WHERE 	login = ?")
				.toString();
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, Points.NEW_TOPIC.getValue());
			stmt.setString(2, topic.getAuthor().getLogin());
			stmt.executeUpdate();
			
			c.commit();
		}
	}
	
	public List<TopicDTO> getTopics() throws SQLException {
		List<TopicDTO> topics = new ArrayList<>();
		
		try (Connection c = DriverManager.getConnection(
			"jdbc:postgresql://localhost/nforum", "postgres", "root"
		)) {
			String sql = new StringBuilder()
				.append("SELECT	* ")
				.append("FROM	topico ")
				.toString();

			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				TopicDTO topic = new TopicDTO(
					rs.getInt("id_topico"),
					rs.getString("titulo"),
					rs.getString("conteudo"),
					userService.findByLogin(rs.getString("login"))
				);
				topic.setComments(commentService.findAllCommentsFromTopicWithId(rs.getInt("id_topico")));
				topics.add(topic);
			}
		}
		
		return topics;
	}
}
