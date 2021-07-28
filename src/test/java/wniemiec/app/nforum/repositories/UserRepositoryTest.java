package wniemiec.app.nforum.repositories;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Test;

import wniemiec.app.nforum.dto.UserDTO;

public class UserRepositoryTest extends RepositoryTest {
	
	private UserRepository userRepository;
	
	public UserRepositoryTest() {
		userRepository = new UserRepository();
	}
	
	@Test
	public void testFindByLogin() throws SQLException {
		UserDTO user = userRepository.findByLogin("user1");
		
		assertEquals("user 1", user.getName());
		assertEquals("user1@email.com", user.getEmail());
	}
	
	@Test
	public void testFindByLoginAndPassword() throws SQLException {
		UserDTO user = userRepository.findByLoginAndPassword("user1", "123");
		
		assertEquals("user 1", user.getName());
		assertEquals("user1@email.com", user.getEmail());
	}
	
	@Test
	public void testInsert() throws Exception {
		userRepository.insert("user 3", "user3", "user3@email.com", "123");
		
		IDataSet currentDatabase = jdt.getConnection().createDataSet();
		ITable currentTable = currentDatabase.getTable("USUARIO");
		
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDatabase = loader.load("../../../../dataset-dbunit-UserRepositoryTest-insert.xml");
		ITable expectedTable = expectedDatabase.getTable("USUARIO");
		
		org.dbunit.Assertion.assertEquals(expectedTable, currentTable);
	}
	
	@Test
	public void testInsertPoints() throws Exception {
		userRepository.insertPoints("user1", 9);
		
		IDataSet currentDatabase = jdt.getConnection().createDataSet();
		ITable currentTable = currentDatabase.getTable("USUARIO");
		
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDatabase = loader.load("../../../../dataset-dbunit-UserRepositoryTest-insertPoints.xml");
		ITable expectedTable = expectedDatabase.getTable("USUARIO");
		
		org.dbunit.Assertion.assertEquals(expectedTable, currentTable);
	}
	
	@Test
	public void testGetRanking() throws SQLException {
		List<UserDTO> ranking = userRepository.getRanking();
		
		assertEquals(2, ranking.size());
		assertEquals("user2", ranking.get(0).getLogin());
		assertEquals("user1", ranking.get(1).getLogin());
	}
}
