package Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Database.Database;
import Utils.Props;

/**
 * Servlet implementation class MovieServlet
 */
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletProcessor servletProcessor;
    private final Props props = new Props();
    private final Database database = new Database(props);
       
    public MovieServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		response.setContentType("application/json;charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		String mssg = null;
		String line = null;
		//JSONObject responsemssg = null;
		
		try {
			servletProcessor = new ServletProcessor(props, database);
			
			URL url = new URL("https://beep2.cellulant.com:9001/assessment/");
	        URLConnection urlConnection = url.openConnection();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			
			StringBuilder stringbuilder = new StringBuilder();
			
			while((line = reader.readLine()) != null) {
				stringbuilder.append(line);
			}
			reader.close();
			mssg = new String(stringbuilder.toString());
			servletProcessor.processRequest(mssg);
			
		}finally {
			out.close();
		}
	}

}
