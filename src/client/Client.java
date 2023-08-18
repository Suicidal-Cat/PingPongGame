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
	
	public Client(GameMode mode) {
		try {
			this.mode=mode;
			communicationSocket = new Socket("127.0.0.1",port);
			//2a06:5b00:f0d:d500:9523:7ecf:5671:d874
			serverInput=new ObjectInputStream(communicationSocket.getInputStream());
			serverOutput=new ObjectOutputStream(communicationSocket.getOutputStream());
			
			sendInitPacket();
			
			client=new Thread(this);
			client.start();
		}catch(IOException e) {
			System.out.println("Greska u komunikaciji sa serverom");
		}
	}
	
	@Override
	public void run() {	
		receiveInitPacket();
		System.out.println("Krenuo klijent");
		//	frame.panel.image=Toolkit.getDefaultToolkit().createImage(packet.data);
		frame=new ClientFrame(mode);
		frame.panel.addKeyListener(new ClientInput());
		try {
			while(true) {
				Object packet=serverInput.readObject();
				frame.panel.updateComponents(packet);
			}
		}catch (ClassNotFoundException e) {
			System.out.println("Pogresan paket!");
		}catch (IOException e) {
			System.out.println("Greska pri komunikaciji sa serverom");
		}
	}
		
	private void sendInitPacket() {
		try {
			serverOutput.writeObject(new InitPacket(mode));
			System.out.println("Poslao sam");
			serverOutput.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void receiveInitPacket() {
		try {
			System.out.println("CEKAM DA KRENE");
			InitPacket packet = (InitPacket)serverInput.readObject();
			System.out.println("Primio od servera!!"+packet.playerId);
			playerNumber=packet.playerId;
		} catch (ClassNotFoundException e) {
			System.out.println("Pogresan paket od servera!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//client inputs			
	public class ClientInput extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			try {
				if(e.getKeyCode()==KeyEvent.VK_UP)
				serverOutput.writeObject(new ClientPacket(ClientControl.UP_PRESSED,playerNumber));
				else if(e.getKeyCode()==KeyEvent.VK_DOWN)
					serverOutput.writeObject(new ClientPacket(ClientControl.DOWN_PRESSED,playerNumber));
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
