package gameInterface;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Intro extends JFrame{
	
	
	public Intro() throws InterruptedException{
	  ImageIcon imageIcon = new ImageIcon("src/images/introResized.gif");
	  JLabel label = new JLabel(imageIcon);
	this.add(label);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(1100,617);
	this.setResizable(false);
	//this.setLayout(null);
	this.setVisible(true);
	this.setLocationRelativeTo(null);//da na centru ekrana bude prozor
	this.setTitle("Pong Game");
	Thread.sleep(5000);
	FirstFrame first=new FirstFrame();
	
}
}