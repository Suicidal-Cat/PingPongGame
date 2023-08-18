package client;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import game.GameMode;

public class ClientFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1215308126292310466L;
	ClientPanel panel;
	
	public ClientFrame(GameMode mode) {
		panel=getMode(mode);
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		ImageIcon image=new ImageIcon("src/resources/images/arcade1.png");
		this.setIconImage(image.getImage());

	}
	private ClientPanel getMode(GameMode mode) {
		if(mode==GameMode.Classic)return new ClientPanel();
		else if(mode==GameMode.Powers)return new ClientPanel();
		else return new ClientAdvancePanel();
	}
}
