package wniemiec.app.nforum.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import wniemiec.app.nforum.dto.UserDTO;
import wniemiec.app.nforum.dto.UserNewDTO;
import wniemiec.app.nforum.repositories.UserAccountRepository;
import wniemiec.app.nforum.services.exceptions.NewElementException;

/**
 * Responsible for providing user account services.
 */
public class UserAccountService {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private UserAccountRepository userRepository = new UserAccountRepository();
	

	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	public UserDTO findByLogin(String login) {
		try {
			return userRepository.findByLogin(login);
		} 
		catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	
	public UserDTO findByLoginAndPassword(String login, String password) {
		if ((login == null) || (password == null))
			return null;
		
		try {
			return userRepository.findByLoginAndPassword(
				login, 
				DigestUtils.sha256Hex(password)
			);
		} 
		catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	
	public boolean insert(UserNewDTO newUser) 
			throws NewElementException {
		validate(newUser);
		
		boolean success = false;
		
		try {
			success = userRepository.insert(
				newUser.getName(), 
				newUser.getLogin(), 
				newUser.getEmail(), 
				DigestUtils.sha256Hex(newUser.getPassword())
			);
		} 
		catch (SQLException e) {
			throw new NewElementException(e.getMessage());
		}
		
		return success;
	}
	
	private void validate(UserNewDTO newUser) throws NewElementException {
		if (newUser.getName() == null)
			throw new NewElementException("Name cannot be null");
		
		if (newUser.getEmail() == null)
			throw new NewElementException("Email cannot be null");
		
		if (newUser.getPassword() == null)
			throw new NewElementException("Password cannot be null");
		
		if (newUser.getLogin() == null)
			throw new NewElementException("Login cannot be null");
	}

	public List<UserDTO> getRanking() {
		try {
			return userRepository.getRanking();
		} 
		catch (SQLException e) {
			System.err.println(e.toString());
			return new ArrayList<>();
		}
	}
}
