package Database;

import static org.junit.Assert.*;
import org.junit.Test;
import Utils.Props;

public class DatabaseTest {
	
	Props props = new Props();
	Database db = new Database(props);
	
	@Test
	public final void testDatabase() {
		assertNotNull(props);
		assertNotNull(db);
	}

	@Test
	public final void testGetDatabaseConnection() {
		try {
			assertNotNull(!(db.getDatabaseConnection().equals(null)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

