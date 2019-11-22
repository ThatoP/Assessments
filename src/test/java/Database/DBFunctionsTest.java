package Database;

import static org.junit.Assert.*;

import org.junit.Test;

import Utils.Props;

public class DBFunctionsTest {
	Props props = new Props();
	DBFunctions dbFunctions = new DBFunctions(props);
	
	String query = "INSERT INTO movielisting VALUES ('1','Last Vegas','Comedy')";
	
	@Test
	public final void testDBFunctions() {
		assertNotNull(props);
		assertNotNull(dbFunctions);
	}

	@Test
	public final void testRunQuery() {
		assertNotNull(query);
		assertTrue(query.contains("INSERT INTO movielisting VALUES "));
	}

}
