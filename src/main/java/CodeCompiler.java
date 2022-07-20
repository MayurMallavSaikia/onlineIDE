

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.*;

/**
 * Servlet implementation class CodeCompiler
 */
public class CodeCompiler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeCompiler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
          response.setContentType("text/html");
		
		   PrintWriter out=response.getWriter();
		   String output =null;
		   String readLine = null;
		    String CodeLang="";
	        String versionIndex = "";

		String language=request.getParameter("languages");
		String stdin=request.getParameter("inputText");
		
		if(language.equals("c_cpp")) {
		    CodeLang="cpp";
			versionIndex="5";
		}
		else if(language.equals("python")) {
			CodeLang="python3";
			versionIndex="4";
		}
		else if(language.equals("java")) {
			CodeLang="java";
			versionIndex="4";
		}
		
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("clientId", "1f48c43ccd28e9a81b46fe567288951a");
			jsonObject.put("clientSecret", "19bd872fbd373c253b952b30f99ab6df76b26673dc21eb8c754694257882557f");
			jsonObject.put("script", request.getParameter("codeText"));
			jsonObject.put("stdin", request.getParameter("inputText"));
			jsonObject.put("language", CodeLang);
			jsonObject.put("versionIndex", versionIndex);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String input = jsonObject.toString();
		
		
		
			
			 try {
		            URL url = new URL("https://api.jdoodle.com/v1/execute");
		            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		            connection.setDoOutput(true);
		            connection.setRequestMethod("POST");
		            connection.setRequestProperty("Content-Type", "application/json");

		            OutputStream outputStream = connection.getOutputStream();
		            outputStream.write(input.getBytes());
		            outputStream.flush();
		            int responseCode = connection.getResponseCode();


		      		 if (responseCode == HttpURLConnection.HTTP_OK) {
		           		BufferedReader in = new BufferedReader(
		              		 new InputStreamReader(connection.getInputStream()));
		           		StringBuffer responseJSON = new StringBuffer();
		          		 while ((readLine = in .readLine()) != null) {
		              		 responseJSON.append(readLine);
		           			} in .close();
		           				
		           			JSONObject jsonObject0_;
							try {
								jsonObject0_ = new JSONObject(responseJSON.toString());
								out.println(jsonObject0_.get("output")+"<br><br>");
			           			System.out.println(jsonObject0_.get("statusCode"));
			           			out.println("CPU Time: "+jsonObject0_.get("cpuTime")+"sec(s), ");
			           			out.println("Memory: "+jsonObject0_.get("memory")+"kB(s)");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		           			
		            
		      		 }
		            
		     } catch (MalformedURLException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
			
			
		}
		
			
	}
	
	
	
	
}


