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
	public static void main(String[] args) throws IOException {
		System.out.println("Server is started");
		BufferedReader in = null;
		PrintWriter out = null;

		ServerSocket server = null;
		Socket clientSocket = null;

		try {
			server = new ServerSocket(9191);
		} catch (IOException e) {
			System.out.println("Couldn't listen to port 9000");
		}

		try {
			System.out.print("Waiting for a client...");
			clientSocket = server.accept();
			System.out.println("Client connected");
		} catch (IOException e) {
			System.out.println("Can't accept");
			System.exit(-1);
		}

		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(clientSocket.getOutputStream(), true);

		String request, response;

		System.out.println("Waiting for request...");
		request = in.readLine();
		Locale.setDefault(Locale.ENGLISH);	
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.print("JDBC-Driver is OK!\n");
		}
		catch(ClassNotFoundException e) 
		{
			System.out.print("JDBC-Driver is wrong!\n");
     		System.exit(0);
		}
		
		String url = new String(""); 
		try
		{
			url="jdbc:oracle:thin:@localhost:1521:XE";
			String userid="testuser";
			String userp="testpassword";
			Connection con=DriverManager.getConnection(url,userid,userp);
		    System.out.println("Connection with URL=" + url + " is OK!");
			try
			{
				Statement stm=con.createStatement();
				
				ResultSet rz=stm.executeQuery(request);
				System.out.println("Request is: "+request);
				String result = new String("");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
				while(rz.next()) 
				{	
					result = rz.getInt(1)+"\t"+ "\t"+rz.getString(2)+"\t"+rz.getString(3)+"\t"+rz.getString(4)+"\t"+
					sdf.format(rz.getDate(5))+"\t"+sdf.format(rz.getDate(6))+"\t"+rz.getDouble(7);
					response=result;
					out.println(response);	
				}
				
				rz.close();
				stm.close();
			}
			catch(SQLException er)
			{	
				System.out.println("request is wrong!");
				System.out.println(er.getMessage());
			}
		con.close();			
		}
		catch(SQLException er) 
        {
			System.out.println("Connection  " + url + " is wrong!");
			System.out.println(er.getMessage());
		}
		
		out.close();
		in.close();
		clientSocket.close();
		server.close();
	}

}
