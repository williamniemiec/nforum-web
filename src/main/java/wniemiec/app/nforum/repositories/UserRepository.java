package wniemiec.app.nforum.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wniemiec.app.nforum.dto.UserDTO;

public class UserRepository {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
		}
	}
	
	public UserDTO findByLogin(String login) 
			throws SQLException {
		if (login == null)
			return null;
		
		UserDTO user = null;
		
		try (Connection c = DriverManager.getConnection(
			"jdbc:postgresql://localhost/nforum", "postgres", "root"
		)) {
			String sql = new StringBuilder()
				.append("SELECT	* ")
				.append("FROM	usuario ")
				.append("WHERE	login = ? ")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, login);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new UserDTO(
					rs.getString("login"),
					rs.getString("nome"),
					rs.getString("email"),
					rs.getInt("pontos")
				);
			}
		}
		
		return user;
	}
	
	public UserDTO findByLoginAndPassword(String login, String password) 
			throws SQLException {
		UserDTO user = null;
		
		try (Connection c = DriverManager.getConnection(
			"jdbc:postgresql://localhost/nforum", "postgres", "root"
		)) {
			String sql = new StringBuilder()
				.append("SELECT	* ")
				.append("FROM	usuario ")
				.append("WHERE	login = ? ")
				.append("		AND senha = ?")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, login);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new UserDTO(
					rs.getString("login"),
					rs.getString("nome"),
					rs.getString("email"),
					rs.getInt("pontos")
				);
			}
		}
		
		return user;
	}
	
	public boolean insert(String name, String login, String email, String password) 
			throws SQLException {
		try (Connection c = DriverManager.getConnection(
			"jdbc:postgresql://localhost/nforum", "postgres", "root"
		)) {
			String sql = new StringBuilder()
				.append("INSERT INTO usuario ")
				.append("(nome, login, email, senha, pontos) ")
				.append("VALUES (?, ?, ?, ?, 0) ")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, login);
			stmt.setString(3, email);
			stmt.setString(4, password);	
			
			return (stmt.executeUpdate() != 0);
		}
	}

	public boolean insertPoints(String login, int points) throws SQLException {
		try (Connection c = DriverManager.getConnection(
			"jdbc:postgresql://localhost/nforum", "postgres", "root"
		)) {
			String sql = new StringBuilder()
				.append("UPDATE	usuario ")
				.append("SET 	pontos = pontos + ? ")
				.append("WHERE 	login = ?")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, points);
			stmt.setString(2, login);	
			
			return (stmt.executeUpdate() != 0);
		}
	}
	
	public List<UserDTO> getRanking() throws SQLException {
		List<UserDTO> users = new ArrayList<>();
		
		try (Connection c = DriverManager.getConnection(
			"jdbc:postgresql://localhost/nforum", "postgres", "root"
		)) {
			String sql = new StringBuilder()
				.append("SELECT		* ")
				.append("FROM		usuario ")
				.append("ORDER BY	pontos DESC")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				users.add(new UserDTO(
					rs.getString("login"),
					rs.getString("nome"),
					rs.getString("email"),
					rs.getInt("pontos")
				));
			}
		}
		
		return users;
	}
}
