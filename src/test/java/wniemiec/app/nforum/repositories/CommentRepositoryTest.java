package wniemiec.app.nforum.repositories;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Test;

import wniemiec.app.nforum.dto.CommentDTO;
import wniemiec.app.nforum.dto.CommentNewDTO;

public class CommentRepositoryTest extends RepositoryTest {
	
	private CommentRepository commentRepository;
	
	public CommentRepositoryTest() {
		commentRepository = new CommentRepository();
	}
	
	@Test
	public void testFindAllCommentsFromTopicWithId() throws SQLException {
		List<CommentDTO> comments = commentRepository.findAllCommentsFromTopicWithId(1);
		
		assertEquals(2, comments.size());
		assertEquals("first", comments.get(0).getContent());
		assertEquals("second", comments.get(1).getContent());
	}
	
	@Test
	public void testInsert() throws Exception {
		commentRepository.insert(new CommentNewDTO(
			"third",
			1,
			"user2"
		));
		
		IDataSet currentDatabase = jdt.getConnection().createDataSet();
		ITable currentTable = currentDatabase.getTable("COMENTARIO");
		
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDatabase = loader.load("../../../../dataset-dbunit-CommentRepositoryTest-insert.xml");
		ITable expectedTable = expectedDatabase.getTable("COMENTARIO");
		
		org.dbunit.Assertion.assertEquals(expectedTable, currentTable);
	}
}
