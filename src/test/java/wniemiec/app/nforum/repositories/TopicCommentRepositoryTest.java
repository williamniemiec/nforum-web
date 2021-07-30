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

public class TopicCommentRepositoryTest extends RepositoryTest {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private CommentRepository commentRepository;
	

	//-------------------------------------------------------------------------
	//		Constructors
	//-------------------------------------------------------------------------
	public TopicCommentRepositoryTest() {
		commentRepository = new CommentRepository();
	}
	

	//-------------------------------------------------------------------------
	//		Tests
	//-------------------------------------------------------------------------
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
		ITable currentTable = currentDatabase.getTable("topic_comment");
		
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDatabase = loader.load("../../../../dataset-dbunit-TopicCommentRepositoryTest-insert.xml");
		ITable expectedTable = expectedDatabase.getTable("topic_comment");
		
		org.dbunit.Assertion.assertEquals(expectedTable, currentTable);
	}
}
