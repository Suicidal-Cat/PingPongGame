package gameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

import game.GameFrame;
import game.GameMode;
import game.GamePanel;
import packet.ClientPacket;
import packet.GameAdvancePacket;
import packet.GamePacket;
import packet.InitPacket;

public class Client_handler extends Thread{
	private Socket player;
	private ObjectInputStream clientInput;
	private ObjectOutputStream clientOutput;
	public Client_handler opponent;
	public GameFrame game;
	public GamePanel panel;
	public GameMode mode;
	public int playerNumber;
	public String username;
	public boolean foundGame,isRunning;

	
	public Client_handler(Socket p1){
		try {
		player=p1;
		foundGame=false;
		clientOutput=new ObjectOutputStream(player.getOutputStream());
		clientInput=new ObjectInputStream(player.getInputStream());
		recieveInitPacket();
		Server.players.add(this);
		findGame();
		}catch(IOException e) {
			isRunning=false;
			Server.players.remove(this);
		}
	}
	
	@Override //reading packet for Client
	public void run() {	
		try {
			isRunning=true;
			sendInitPacket();
		
			if(playerNumber==1)panel.startGame();
			while(isRunning) {
				ClientPacket packet=(ClientPacket)clientInput.readObject();
				if(packet.playerNumber==1)panel.paddle1.updatePaddle(packet.control);
				else panel.paddle2.updatePaddle(packet.control);
			}
		
		}
		catch(ClassNotFoundException e) {
			System.out.println("Pogresan paket!");
		}
		catch(IOException e) {
			opponent.isRunning=false;
			isRunning=false;
			try {
				opponent.player.close();
			} catch (IOException e1) {
			}
		}
		
		
		if(playerNumber==1) {
			//update database
			
			if(game.panel.score.player1>=6 || game.panel.score.player2>=6) {
			System.out.println("Username prvog: "+username+"\nUsername drugog "+opponent.username+
					" \nRezultat prvog i drugog: "+game.panel.score.player1+": "
					+game.panel.score.player2);
			}
			
			System.out.println("KRAJ IGRE "+playerNumber);
			Server.players.remove(this);
			if(game!=null) {
				game.dispose();
			}
			try {
				player.close();
			} catch (IOException e) {}
		}

					
	}
	
	private void findGame() {// da li uvesti mutex ovde ? da samo jedan pristupa
		if(Server.players.size()==1) {
			return;
		}
		for (Client_handler player : Server.players) {
			if(!player.foundGame && player!=this && player.mode==this.mode) {
				game=new GameFrame(mode,this,player);
				this.panel=game.panel;
				player.panel=game.panel;
				playerNumber=1;
				player.playerNumber=2;
				opponent=player;
				player.opponent=this;
				player.foundGame=true;
				foundGame=true;
				this.start();
				player.start();
			}
		}
	}
	
	private void recieveInitPacket() throws IOException{
		try {
			InitPacket packet=(InitPacket)clientInput.readObject();
			mode=packet.mode;
			username=packet.username;
			System.out.println("Dobio init od klijenta "+mode.toString());
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Pogresan paket od klijenta!");
		}
	}
	
	private void sendInitPacket() throws IOException{
			clientOutput.writeObject(new InitPacket(playerNumber));
			clientOutput.flush();
	}
	
	public void sendGamePacket(GamePacket p) throws IOException{
			clientOutput.writeObject(p);
			clientOutput.flush();
	}
	public void sendGamePacket(GameAdvancePacket p) throws IOException{
			clientOutput.writeObject(p);
			clientOutput.flush();
	}
	
	public void updatePlayer(GamePacket packet) throws IOException{
			clientOutput.writeObject(packet);
			clientOutput.flush();
	}
	public void updatePlayer(GameAdvancePacket packet) throws IOException{
			clientOutput.writeObject(packet);
			clientOutput.flush();
	}

}
