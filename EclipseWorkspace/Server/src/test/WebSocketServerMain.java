package test;

import java.util.Scanner;

public class WebSocketServerMain {

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
		
		Scanner scan = new Scanner(System.in);
		while(true) {

			try {
				String result = scan.nextLine();
				server.sendToEveryone(result);
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}

}
