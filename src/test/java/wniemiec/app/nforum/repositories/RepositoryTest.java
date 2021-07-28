package wniemiec.app.nforum.repositories;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.Before;

import wniemiec.app.nforum.config.DatabaseConfig;

public abstract class RepositoryTest {
	
	protected static JdbcDatabaseTester jdt;
	
	@Before
    public void setUp() throws Exception {
		jdt = new JdbcDatabaseTester(
			"org.postgresql.Driver",
			"jdbc:postgresql://localhost/nforum", 
			"postgres", 
			"root"
		);
		
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("../../../../dataset-dbunit-init.xml"));
        jdt.onSetup();
        DatabaseConfig.resetIndex();
    }

    @After
    public void tearDown() throws Exception {
        jdt.onTearDown();
    }
}
