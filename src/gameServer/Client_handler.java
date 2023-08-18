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
	public GameFrame game;
	public GamePanel panel;
	public GameMode mode;
	public int playerNumber;
	public boolean foundGame,isRunning;

	
	public Client_handler(Socket p1){
		try {
		player=p1;
		foundGame=false;
		isRunning=true;
		clientOutput=new ObjectOutputStream(player.getOutputStream());
		clientInput=new ObjectInputStream(player.getInputStream());
		recieveInitPacket();
		Server.players.add(this);
		findGame();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override //reading packet for Client
	public void run() {	
		try {
			System.out.println("Krenuo sam"+playerNumber);
			sendInitPacket();
			System.out.println("Poslao sam"+playerNumber);
		
			if(playerNumber==1)panel.startGame();
			while(true) {
				ClientPacket packet=(ClientPacket)clientInput.readObject();
				if(packet.playerNumber==1)panel.paddle1.updatePaddle(packet.control);
				else panel.paddle2.updatePaddle(packet.control);
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Pogresan paket!");
		}
		catch(IOException e){
			e.printStackTrace();
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
				player.foundGame=true;
				foundGame=true;
				this.start();
				player.start();
				System.out.println("Nasao sam "+playerNumber);
			}
		}
	}
	
	private void recieveInitPacket() {
		try {
			InitPacket packet=(InitPacket)clientInput.readObject();
			mode=packet.mode;	
			System.out.println("dobio init od klijenta"+mode.toString());
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Pogresan paket od klijenta!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendInitPacket() {
		try {
			clientOutput.writeObject(new InitPacket(playerNumber));
			clientOutput.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendGamePacket(GamePacket p) {
		try {
			clientOutput.writeObject(p);
			clientOutput.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendGamePacket(GameAdvancePacket p) {
		try {
			clientOutput.writeObject(p);
			clientOutput.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePlayer(GamePacket packet){
		try {
			clientOutput.writeObject(packet);
			clientOutput.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void updatePlayer(GameAdvancePacket packet) {
		try {
			clientOutput.writeObject(packet);
			clientOutput.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

}
