package Processor;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Database.DBFunctions;
import Database.Database;
import Utils.Props;

public class ServletProcessor 
{
	private Props props;
	private Database database;
	private DBFunctions dbFunctions;
	
	/**
	 * Construction of an instance of UssdServletProcessor class
	 * @param props
	 * @param database 
	 */
	public ServletProcessor(Props props, Database database) {
		this.props = props;
		this.database = database;
	}
	
	public ServletProcessor() {
		super();
	}
	
	/**
	 * Recieves a request from the frontend in json format and then processes it according to the processing code
	 * 
	 * @param request json key-value pair(s) from the front end
	 * @return a key-value pair of results from the database
	 */
	public void processRequest(String request) {
		JSONObject jsonresponse = null;
		jsonresponse = new JSONObject();
		props = new Props();
		database = new Database(props);
		dbFunctions = new DBFunctions(props);
		boolean isValid = isJSONValid(request);
		
		if(isValid == false) {
			// the request is invalid if it's empty or holds a non json format object
			jsonresponse.put("ResponseCode", "99");
			jsonresponse.put("Message", "Invalid response/request detected!");
		}else {
			JSONArray jsonrequest = new JSONArray(request);
			
			Iterator i = jsonrequest.iterator();
			int j = 0;
			
			while(i.hasNext()) {
				j++;
				JSONObject obj = (JSONObject) i.next();
				String query = "INSERT INTO movielisting VALUES ('"+obj.getInt("movieID")+"','"+obj.getString("title")+"','"+obj.getString("genre")+"')";
				
				if(obj.getString("title").contains("\'")) {
					query = "INSERT INTO movielisting VALUES ('"+obj.getInt("movieID")+"',"
							+ "'"+obj.getString("title").replace("\'","''")+"','"+obj.getString("genre")+"')";
					dbFunctions.RunQuery(query);
				}
				else {
					dbFunctions.RunQuery(query);
				}
			}
		}
	}
	
	
	public boolean isJSONValid(String test) {
		try {
            new JSONArray(test);
        } catch (JSONException ex) {
        	System.out.println("json array cannot be created because: ");
        	ex.printStackTrace();
            return false;
        }
        return true;
    }
}
