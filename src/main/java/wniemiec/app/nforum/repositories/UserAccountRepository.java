package wniemiec.app.nforum.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wniemiec.app.nforum.dto.UserDTO;

/**
 * Responsible for accessing the user account table from database.
 */
public class UserAccountRepository extends Repository {
	
	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public UserDTO findByLogin(String login) throws SQLException {
		if (login == null)
			return null;
		
		UserDTO user = null;
		
		try (Connection c = buildDatabaseConnection()) {
			PreparedStatement stmt = c.prepareStatement(buildQuery(
				"SELECT	*",
				"FROM	user_account",
				"WHERE	login = ?"
			));
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
		
		try (Connection c = buildDatabaseConnection()) {
			PreparedStatement stmt = c.prepareStatement(buildQuery(
				"SELECT	*",
				"FROM	user_account",
				"WHERE	login = ?",
				"		AND \"password\" = ?"
			));
			
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
		try (Connection c = buildDatabaseConnection()) {
			PreparedStatement stmt = c.prepareStatement(buildQuery(
				"INSERT INTO user_account",
				"(\"name\", login, email, \"password\", points)",
				"VALUES (?, ?, ?, ?, 0)"
			));
			
			stmt.setString(1, name);
			stmt.setString(2, login);
			stmt.setString(3, email);
			stmt.setString(4, password);	
			
			return (stmt.executeUpdate() != 0);
		}
	}

	public boolean insertPoints(String login, int points) throws SQLException {
		try (Connection c = buildDatabaseConnection()) {
			PreparedStatement stmt = c.prepareStatement(buildQuery(
				"UPDATE	user_account",
				"SET 	points = points + ?",
				"WHERE 	login = ?"
			));
			
			stmt.setInt(1, points);
			stmt.setString(2, login);	
			
			return (stmt.executeUpdate() != 0);
		}
	}
	
	public List<UserDTO> getRanking() throws SQLException {
		List<UserDTO> users = new ArrayList<>();
		
		try (Connection c = buildDatabaseConnection()) {
			PreparedStatement stmt = c.prepareStatement(buildQuery(
				"SELECT		*",
				"FROM		user_account",
				"ORDER BY	points DESC"
			));
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
