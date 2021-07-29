package wniemiec.app.nforum.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wniemiec.app.nforum.config.DatabaseConfig;
import wniemiec.app.nforum.dto.UserDTO;

public class UserAccountRepository {

	static {
		try {
			Class.forName(DatabaseConfig.getDriver());
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
			DatabaseConfig.getHost(), DatabaseConfig.getUsername(), DatabaseConfig.getPassword()
		)) {
			String sql = new StringBuilder()
				.append("SELECT	* ")
				.append("FROM	user_account ")
				.append("WHERE	login = ? ")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, login);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new UserDTO(
					rs.getString("login"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getInt("points")
				);
			}
		}
		
		return user;
	}
	
	public UserDTO findByLoginAndPassword(String login, String password) 
			throws SQLException {
		UserDTO user = null;
		
		try (Connection c = DriverManager.getConnection(
			DatabaseConfig.getHost(), DatabaseConfig.getUsername(), DatabaseConfig.getPassword()
		)) {
			String sql = new StringBuilder()
				.append("SELECT	* ")
				.append("FROM	user_account ")
				.append("WHERE	login = ? ")
				.append("		AND \"password\" = ?")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, login);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new UserDTO(
					rs.getString("login"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getInt("points")
				);
			}
		}
		
		return user;
	}
	
	public boolean insert(String name, String login, String email, String password) 
			throws SQLException {
		try (Connection c = DriverManager.getConnection(
			DatabaseConfig.getHost(), DatabaseConfig.getUsername(), DatabaseConfig.getPassword()
		)) {
			String sql = new StringBuilder()
				.append("INSERT INTO user_account ")
				.append("(\"name\", login, email, \"password\", points) ")
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
			DatabaseConfig.getHost(), DatabaseConfig.getUsername(), DatabaseConfig.getPassword()
		)) {
			String sql = new StringBuilder()
				.append("UPDATE	user_account ")
				.append("SET 	points = points + ? ")
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
			DatabaseConfig.getHost(), DatabaseConfig.getUsername(), DatabaseConfig.getPassword()
		)) {
			String sql = new StringBuilder()
				.append("SELECT		* ")
				.append("FROM		user_account ")
				.append("ORDER BY	points DESC")
				.toString();
			
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				users.add(new UserDTO(
					rs.getString("login"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getInt("points")
				));
			}
		}
		
		return users;
	}
}
