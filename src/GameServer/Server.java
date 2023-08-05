package GameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	public static void main(String[] args) {
		int port = 9521;
		ServerSocket serverSoket;
		Socket player1;
		Socket player2;
		try {
			serverSoket = new ServerSocket(port);
			while (true) {
				System.out.println("Waiting for conncetion...");
				player1 = serverSoket.accept();
				player2 = serverSoket.accept();
				System.out.println("Connected!!!");

				Client_handler klijent1 = new Client_handler(player1,player2,1);
				Client_handler klijent2 = new Client_handler(player2,player1,2);
				klijent1.start();
				klijent2.start();
			}
		} catch (IOException e) {
			System.out.println("Server startup error");
		}
	}
}
