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
//	JFrame f;
	Waiting w;
	
	public Client(GameMode mode) {
		try {
			this.mode=mode;
			communicationSocket = new Socket("127.0.0.1",port);
			//2a06:5b00:f0d:d500:d991:4a48:9698:2bf0
			serverInput=new ObjectInputStream(communicationSocket.getInputStream());
			serverOutput=new ObjectOutputStream(communicationSocket.getOutputStream());
			
			sendInitPacket();
			//////////////
//			addGif("waiting.gif");
			w=new Waiting();
			//AKO GA UGASI OVDE DOK CEKA, OBRADI
			//cak i ovome koji se drugi prikljuci iskoci waiting na kratko, da li to ostaviti ili ugasiti nekako
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
//			f.dispose();
			w.sound.audioStop();
			w.dispose();
			////////////
			frame=new ClientFrame(mode);
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
			if(frame!=null)frame.dispose();
		}
		try {communicationSocket.close();}
		catch (IOException e) {}
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
	
//	public void addGif(String name) {
//	    Icon icon = new ImageIcon("src/resources/images/"+name);
//	    JLabel label = new JLabel(icon);
//	 
//	    f = new JFrame("Animation");
//	    f.getContentPane().add(label);
//	  	f.setUndecorated(true);
//	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    f.pack();
//	    f.setLocationRelativeTo(null);
//	    f.setVisible(true);
//	    //f.setTitle("Ostvarili ste bonus +1");
//	    ImageIcon image=new ImageIcon("src/resources/images/arcade1.png");
//	  	//f.setIconImage(image.getImage());
////	    try {
////			Thread.sleep(2000);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//	    
//	}

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
}			
