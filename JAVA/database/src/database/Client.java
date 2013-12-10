package database;

import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args) throws IOException {

		System.out.println("Client is started");

		Socket socket = null;

		System.out.println("Connecting to localhost... ");

		socket = new Socket("localhost", 9191);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		String header="id_project\tproject_name\tcustomer\texecutor\tdate_start\tdate_finish\tproject_cost";
		String line="-------------------------------------------------------------------------------------------------------------";

		String request="select * from projects", response;
		
		out.println(request);
		System.out.println(header);
		System.out.println(line);
		while ((response = in.readLine())!=null) {
			System.out.println(response);
		 }
		out.close();
		in.close();
		socket.close();
	}
}
