package Processor;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import Database.Database;
import Utils.Props;

public class ServletProcessorTest {
	Props props = new Props();
	ServletProcessor sp = new ServletProcessor();
	
	String list = "[\r\n" + 
			"    {\r\n" + 
			"        \"movieID\": 1,\r\n" + 
			"        \"title\": \"Alpha Dog\",\r\n" + 
			"        \"genre\": \"Crime|Drama\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"        \"movieID\": 2,\r\n" + 
			"        \"title\": \"Taxidermia\",\r\n" + 
			"        \"genre\": \"Comedy|Drama|Horror\"\r\n" + 
			"    }]";
	
	@Test
	public final void testServletProcessorPropsDatabase() {
		assertNotNull(props);
	}

	@Test
	public final void testProcessRequest() {
		assertNotNull(list);
		assertTrue(sp.isJSONValid(list));
		
		JSONArray jsonList = new JSONArray(list);
		Iterator i = jsonList.iterator();
		
		while(i.hasNext()) {
			JSONObject movie = (JSONObject) i.next();
			assertNotNull(movie.getInt("movieID"));
			assertNotNull(movie.getString("title"));
			assertFalse(movie.getString("title").equals(""));
			assertNotNull(movie.getString("genre"));
			assertFalse(movie.getString("genre").equals(""));
		}
	}

	@Test
	public final void testIsJSONValid() {
		assertNotNull(new JSONArray(list));
	}

}
