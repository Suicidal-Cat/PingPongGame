package game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import gameInterface.Intro;
import gameModeAdvanced.GameAdvanced;
import gameModeClassic.GameClassic;
import gameModePowers.GamePowers;
import gameServer.Client_handler;

public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2841649482840416987L;
	public GamePanel panel;

	public GameFrame(GameMode mode,Client_handler p1,Client_handler p2) {
		//u zavisnosti od izabrane opcije menjacemo instancu panela
		panel = getMode(mode,p1,p2);
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	//	this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		ImageIcon image=new ImageIcon("src/resources/images/arcade1.png");
		this.setIconImage(image.getImage());

	}
	private GamePanel getMode(GameMode mode,Client_handler p1,Client_handler p2) {
		if(mode==GameMode.Classic)return new GameClassic(p1,p2);
		else if(mode==GameMode.Powers)return new GamePowers(p1,p2);
		else return new GameAdvanced(p1,p2);
	}
}
