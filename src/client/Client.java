package client;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import javax.swing.*;

import game.GameFrame;
import game.GameMode;
import gameInterface.FirstFrame;
import gameInterface.Waiting;
import packet.*;

public class Client extends KeyAdapter implements Runnable{

	private int playerNumber;
	private  Socket communicationSocket;
	private ObjectInputStream serverInput;
	private ObjectOutputStream serverOutput;
	int port = 9521;
	ClientFrame frame;
	GameMode mode;
	Thread client;
	boolean isRunning;
	Waiting w;
	GameResultFrame endScreen;
	JFrame game;
	
	public Client(GameMode mode,JFrame position) {//ovde 
		try {
			this.mode=mode;
			game=position;
			communicationSocket = new Socket("127.0.0.1",port);
			//if you want to play on different devices connected to same network
			//u can pass your public IP adress here instead of localhost
			serverInput=new ObjectInputStream(communicationSocket.getInputStream());
			serverOutput=new ObjectOutputStream(communicationSocket.getOutputStream());
			
			sendInitPacket();
			w=new Waiting(position);
			client=new Thread(this);
			client.start();
		}catch(IOException e) {
			System.out.println("Greska u komunikaciji sa serverom");
		}
	}
	
	@Override
	public void run() {	
		try {
			receiveInitPacket();
			
			w.sound.audioStop();
			
			frame=new ClientFrame(mode);
			frame.setLocationRelativeTo(w);
			w.dispose();
			frame.panel.addKeyListener(new ClientInput());
			isRunning=true;
				while(isRunning) {
					Object packet=serverInput.readObject();
					frame.panel.updateComponents(packet);
				}
		}catch (ClassNotFoundException e) {
			System.out.println("Pogresan paket!");
		}catch (IOException e) {
			System.out.println("KRAJ IGRE");
			isRunning=false;
		}
		try {communicationSocket.close();}
		catch (IOException e) {}
		
		if(frame.panel.score.player1>=6 || frame.panel.score.player2>=6) {
			ShowResultFrame(frame);
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(endScreen!=null)endScreen.close();
		if(frame!=null) {
			frame.dispose();
		}
		game.setVisible(true);
	}
		
	private void sendInitPacket() throws IOException{
			if(FirstFrame.u==null || FirstFrame.u.userName==null) {
				serverOutput.writeObject(new InitPacket(mode,"guest"));
			}else 	
				serverOutput.writeObject(new InitPacket(mode,FirstFrame.u.userName));		
			serverOutput.flush();

	}
	
	private void receiveInitPacket() throws IOException{
		try {
			System.out.println("Cekam na protivnika");
			InitPacket packet = (InitPacket)serverInput.readObject();
			System.out.println(packet.playerId);
			playerNumber=packet.playerId;
		} catch (ClassNotFoundException e) {
			System.out.println("Pogresan paket od servera!");
			e.printStackTrace();
		}

	}

	//client inputs			
	class ClientInput extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			try {
				if(e.getKeyCode()==KeyEvent.VK_UP)
					serverOutput.writeObject(new ClientPacket(ClientControl.UP_PRESSED,playerNumber));
				else if(e.getKeyCode()==KeyEvent.VK_DOWN)
					serverOutput.writeObject(new ClientPacket(ClientControl.DOWN_PRESSED,playerNumber));
				else if(mode==GameMode.Powers) {
					if(e.getKeyCode() == KeyEvent.VK_Z)
						serverOutput.writeObject(new ClientPacket(ClientControl.Z_PRESSED,playerNumber));
					else if(e.getKeyCode() == KeyEvent.VK_X)
						serverOutput.writeObject(new ClientPacket(ClientControl.X_PRESSED,playerNumber));	
					else if(e.getKeyCode() == KeyEvent.VK_C)
						serverOutput.writeObject(new ClientPacket(ClientControl.C_PRESSED,playerNumber));	
				}
			}catch(IOException ex) {
				System.out.println("Greska pri slanju paketa");
			}
		}

		public void keyReleased(KeyEvent e) {
			try {
				if(e.getKeyCode()==KeyEvent.VK_UP)
				serverOutput.writeObject(new ClientPacket(ClientControl.UP_REALISED,playerNumber));
				else if(e.getKeyCode()==KeyEvent.VK_DOWN)
					serverOutput.writeObject(new ClientPacket(ClientControl.DOWN_REALISED,playerNumber));
			}catch(IOException ex) {
				System.out.println("Greska pri slanju paketa");
			}
		}
	}
	
	private void ShowResultFrame(JFrame f) {
		if(playerNumber==1) {
			if(frame.panel.score.player1>=6)endScreen=new GameResultFrame(1,f);
			else endScreen=new GameResultFrame(0,f);
		}
		else {
			if(frame.panel.score.player2>=6)endScreen=new GameResultFrame(1,f);
			else endScreen=new GameResultFrame(0,f);
		}				
	}
}			
