package GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

public class Client_handler extends Thread{
	private  BufferedReader p1Input;
	private  PrintStream p1Output;
	private  BufferedReader p2Input;
	private  PrintStream p2Output;
	private  Socket player1;
	private  Socket player2;
	private int playerNumber;
	//Random random; za generisanje ID
	//RUN igricu na serveru, a sa klijentom salji keypressed i radi izmene na igrici
	public Client_handler(Socket p1,Socket p2,int player) {
		player1=p1;
		player2=p2;
		playerNumber=player;
	}
	
	@Override
	public void run() {
		try {
			
			p1Input = new BufferedReader(new InputStreamReader(player1.getInputStream()));
			p1Output = new PrintStream(player1.getOutputStream());
			p2Input = new BufferedReader(new InputStreamReader(player2.getInputStream()));
			p2Output = new PrintStream(player2.getOutputStream());
			
			p1Output.write(playerNumber);
			
			int response;
			int event;
			while(true) {
				response=Integer.parseInt(p1Input.readLine());
				event=Integer.parseInt(p1Input.readLine());
			//System.out.println(response+" "+(char)response);
				System.out.println(response);
				System.out.println(event);
				p2Output.println(Integer.toString(response));
				p2Output.println(Integer.toString(event));
			}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
