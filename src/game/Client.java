package game;
/*package Game;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.MenuKeyEvent;

public class Client extends KeyAdapter implements Runnable {

	private int playerNumber;
	private  Socket communicationSocket;
	private  BufferedReader serverInput;
	private  PrintStream serverOutput;
	private  BufferedReader keyboardInput;
	GameFrame frame;

	@Override
	public void run() {

		try {
			communicationSocket = new Socket("localhost", 9521);
			serverInput = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
			serverOutput = new PrintStream(communicationSocket.getOutputStream());
			keyboardInput = new BufferedReader(new InputStreamReader(System.in));
			
			playerNumber=serverInput.read();
			System.out.println(playerNumber);
			frame=new GameFrame();
			frame.panel.addKeyListener(this);
			int response;
			int event;
			while(true) {//problem kad je realeased and pressed, isto keyPressed koristi switch
				response=Integer.parseInt(serverInput.readLine());
				event=Integer.parseInt(serverInput.readLine());
				System.out.println(response+" "+event);
				if(response==KeyEvent.VK_UP || response==KeyEvent.VK_DOWN) {
					if(event==KeyEvent.KEY_PRESSED) {
						if(playerNumber==1)frame.panel.paddle2.keyPressed(response);
						else frame.panel.paddle1.keyPressed(response);
					}
					if(event==KeyEvent.KEY_RELEASED) {
						if(playerNumber==1)frame.panel.paddle2.keyReleased(response);
						else frame.panel.paddle1.keyReleased(response);
					}
				}
			
			}
		}
		 catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void keyPressed(KeyEvent e) {
		if(playerNumber==1)frame.panel.paddle1.keyPressed(e.getKeyCode());
		else frame.panel.paddle2.keyPressed(e.getKeyCode());
		serverOutput.println(Integer.toString(e.getKeyCode()));
		serverOutput.println(Integer.toString(e.getID()));
		System.out.println(e.getID()+" "+playerNumber);
	}

	public void keyReleased(KeyEvent e) {
		if(playerNumber==1)frame.panel.paddle1.keyReleased(e.getKeyCode());
		else frame.panel.paddle2.keyReleased(e.getKeyCode());
		serverOutput.println(Integer.toString(e.getKeyCode()));
		serverOutput.println(Integer.toString(e.getID()));
		System.out.println(e.getID()+" "+playerNumber);
	}

}

 * public void keyPressed(int key) {
		switch (id) {
		case 1:
			if (key == KeyEvent.VK_UP) {
				setYDirection(-speed);
			}
			if (key == KeyEvent.VK_DOWN) {
				setYDirection(speed);
			}
			break;
		case 2:
			if (key == KeyEvent.VK_UP) {
				setYDirection(-speed);
			}
			if (key == KeyEvent.VK_DOWN) {
				setYDirection(speed);
			}
			break;
		}
	}

	public void keyReleased(int key) {
		switch (id) {
		case 1:
			if (key == KeyEvent.VK_UP) {
				setYDirection(0);
			}
			if (key == KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;
		case 2:
			if (key == KeyEvent.VK_UP) {
				setYDirection(0);
			}
			if (key == KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;
		}
	}*/
