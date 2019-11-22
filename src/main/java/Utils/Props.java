package Utils;

/**
 * @author Thato Puoetsile
 * @for Technical Assessment - Cellulant
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Props 
{
	private transient Properties properties;
	private transient final String error = "ERROR: %s may not have been set";
	private transient List<String> loadErrors;
	private static final String PROPS_FILE = "C:/Users/tylersoft/tet/MovieS/src/main/java/Utils/Properties.properties";
	private transient String url = "";
	private transient String pword = "";
	private transient String uname = "";
	
	//Constructor
	public Props() {
		loadProperties(PROPS_FILE);
		}
	
	/**
	 * Loads properties from the properties file into their respective variables
	 * 
	 * @param fileName name of the properties file
	 */
	public void loadProperties(final String fileName) {
		FileInputStream in = null;
		try {
			//initializng FileReader
			in = new FileInputStream(fileName);
			
			//Initializing the properties file and enabling it to be read
			properties = new Properties(); 
			properties.load(in);
			
			
			url = readString("INITIAL_CONTEXT_URL");
			uname = readString("USERNAME");
			pword = readString("PASSWORD");
			
		}catch(Exception e) {
			System.err.println("Error: Failed to load properties files. \n Cause: " + e.getMessage());
			Logger.getLogger(Props.class.getName()).log(Level.SEVERE, null, e);
		}finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Props.class.getName()).log(Level.SEVERE, "ERROR: Failed to load properties file.\nCause: \n", ex);
            }
        }
	}
	
	/**
	 * Reads a String from the Properties file
	 * 
	 * @param propertyName
	 * @return a String from the Properties file
	 */
	public String readString(String propertyName) {
		String property = properties.getProperty(propertyName);
		//System.out.println("The property name is: "+property);
		
		if(property.isEmpty()) {
			getLoadErrors().add(String.format(error, propertyName));
		}
		return property;
	}
	
	/**
	 * A Getter method from the loadErrors variable
	 * @return loadErrors
	 */
	public List<String> getLoadErrors() {
        return loadErrors;
    }

	/**
	 * A Getter method from the url variable
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * A getter method from the pword variable which holds the password of the database
	 * @return password
	 */
	public String getPword() {
		return pword;
	}

	/**
	 * A getter method for the uname variable which holds the username of the database 
	 * @return uname
	 */
	public String getUname() {
		return uname;
	}
}
