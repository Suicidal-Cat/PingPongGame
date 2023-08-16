package gameInterface;

import java.io.IOException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gameSound.*;

public class Intro extends JFrame{
	
	public static Sound sound;
	
	public Intro() throws InterruptedException{
		ImageIcon imageIcon = new ImageIcon("src/resources/images/introResized.gif");
	  	JLabel label = new JLabel(imageIcon);
	  
	  	ImageIcon image=new ImageIcon("src/resources/images/arcade1.png");
	  	this.setIconImage(image.getImage());
	  
	  	sound=new Sound("Intro1.wav");
	  	sound.audioStart();
	  	sound.audioLoop();
		
		this.add(label);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1100,617);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);//da na centru ekrana bude prozor
		this.setTitle("Pong Game");
		Thread.sleep(5000);
		FirstFrame first=new FirstFrame();
		dispose();
	
}
}