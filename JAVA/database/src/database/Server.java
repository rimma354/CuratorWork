package database;

import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.io.*;

public class Server {

	private ServerSocket server;
	private Socket clientSocket;
	private final String jdbcDriverName = "oracle.jdbc.driver.OracleDriver";

	public Server() {
		server = null;
		clientSocket = null;
	}

	public void start(int port) throws ServerStartUpException {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			throws new ServerStartUpException("Couldn't listen to port [" + port + "]");
		}

	}

	private String listen() throws IOException {
		BufferedReader in = null;
		String request = null;
		try {
			System.out.print("Waiting for a client...");
			clientSocket = server.accept();
			System.out.println("Client connected");
	
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println("Waiting for request...");
			request = in.readLine();
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if(null != in)
				in.close();
		}

		return request;

	}

	private void setUpDatabaseDriver() throws IllegalStateException {
		try
		{
			Class.forName(jdbcDriverName);
			System.out.print("JDBC-Driver is OK!\n");
		}
		catch(ClassNotFoundException e) 
		{
			System.out.print("JDBC-Driver is wrong!\n");
			throw new IllegalStateException("JDBC-Driver [" + jdbcDriverName + "] is not available");
		}
	}

	public Connection setUpDatabaseConnection() throws SQLException {
		try {
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String userid="testuser";
			String userp="testpassword";
			DriverManager.getConnection(url,userid,userp);
		    System.out.println("Connection with URL=" + url + " is OK!");
		} catch(SQLException er) 
        {
			System.out.println("Connection [url:" + url + "] is wrong!");
			throw er;
		}
	}

	public String executeLogic() {
		Statement stm=con.createStatement();
		StringBuilder response = new StringBuilder();
				
		ResultSet rz=stm.executeQuery(request);
		System.out.println("Request is: "+request);
		String result = new String("");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
		while(rz.next()) {	
			result = rz.getInt(1)+"\t"+ "\t"+rz.getString(2)+"\t"+rz.getString(3)+"\t"+rz.getString(4)+"\t"+
			sdf.format(rz.getDate(5))+"\t"+sdf.format(rz.getDate(6))+"\t"+rz.getDouble(7);
			response.append(result).append("\n");
		}
				
		rz.close();
		stm.close();
		return response;
	}
	
	public static void main(String[] args) throws IOException {
			System.out.println("Server is started");

		final int serverPort = 9191;
		final Server server = new Server();

		server.start(serverPort);

		String response, request;

		Locale.setDefault(Locale.ENGLISH);	

		try {
			request = server.listen();
			server.setUpDatabaseDriver();
			Connection con = server.setUpDatabaseConnection();
		} catch(Exception e) {
			if(null != clientSocket)
				clientSocket.close();		
			if(null != server)
				server.close();
			throw e;
		} 

		PrintWriter out;
		try {
			response = server.executeLogic();

			out = new PrintWriter(clientSocket.getOutputStream(), true);
			out.println(response);

		catch(SQLException er) {	
			System.out.println("request is wrong!");
			System.out.println(er.getMessage());
		} finally {
			if(null != clientSocket)
				clientSocket.close();		
			if(null != server)
				server.close();
			if(null != out)
				out.close();
			if(null != con)
				con.close();		
		}
		
	}

}
