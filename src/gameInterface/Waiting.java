package gameInterface;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gameSound.Sound;

public class Waiting extends JFrame{

public static Sound sound=new Sound("Intro2.wav");;
	
	public Waiting() {
		ImageIcon imageIcon = new ImageIcon("src/resources/images/waiting.gif");
	  	JLabel label = new JLabel(imageIcon);
	  
	  	ImageIcon image=new ImageIcon("src/resources/images/arcade1.png");
	  	this.setIconImage(image.getImage());
	  
	  	if(!Intro.sound.isMute()) {
		  	sound.audioStart();
		  	sound.audioLoop();
	  	}
	  	
		
		this.add(label);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//obradi izuzetak ako ugasi dok ceka
		this.setSize(1100,617);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);//da na centru ekrana bude prozor
		this.setTitle("Pong Game");
//		Thread.sleep(5000);
//		FirstFrame first=new FirstFrame();
//		dispose();
	} 
		
	
}

