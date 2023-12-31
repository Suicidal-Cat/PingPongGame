package gameInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client.Client;
import game.GameFrame;
import game.GameMode;
import game.GamePanel;
import gameData.Forma;
import gameData.User;
import gameModeClassic.GameClassic;
import gameSound.Sound;
import gameInterface.*;
import gameInterface.MatchHistory.MatchHistory;

@SuppressWarnings("serial")
public class FirstFrame extends JFrame implements ActionListener{

	
	private ImageIcon pozadinaArcade;
	private JLabel myLabel;
	JButton button1,button2,button3,button4,button5;
	Sound modeSound = new Sound("gameMode.wav");
	Icon iconSoundOff = new ImageIcon("src/resources/images/sound-off.png");
	Icon iconSound = new ImageIcon("src/resources/images/sound.png");
	Icon icon1 = new ImageIcon("src/resources/images/gamepad.png");
    Icon icon2 = new ImageIcon("src/resources/images/arcade.png");
    Icon icon3 = new ImageIcon("src/resources/images/magic-wand.png");
    Icon forma = new ImageIcon("src/resources/images/user.png");
	public static User u;
	
	 public FirstFrame(Intro intro) {
	
		 Intro.sound.audioLoop();
		 	u=new User();
		 	pozadinaArcade=new ImageIcon("src/resources/images/arcadePozadina1.jpg");
			myLabel=new JLabel(pozadinaArcade);
			myLabel.setSize(getMaximumSize());
		    

		    

			
			this.add(myLabel);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(1100,617);
			this.setResizable(false);
			//this.setLayout(null);
			this.setVisible(true);
			this.setLocationRelativeTo(intro);//da na centru ekrana bude prozor
			this.setTitle("Pong Game");

			
			button1=new JButton(icon1);
			button1.setBounds(425,125,250,50);
			button1.addActionListener(this);
			button1.setText("CLASSIC");
			button1.setFocusable(false);//sklanja one isprekidane linije oko teksta
			button1.setBackground(Color.decode("#6C74C6"));
			button1.setHorizontalTextPosition(JButton.LEFT);
			button1.setFont(new Font("Arial", Font.BOLD, 20));
			
			
			button2=new JButton(icon2);
			button2.setBounds(425,275,250,50);
			button2.addActionListener(this);
			button2.setText("ADVANCED");
			button2.setFocusable(false);//sklanja one isprekidane linije oko teksta
			button2.setBackground(Color.decode("#6C74C6"));
			button2.setHorizontalTextPosition(JButton.LEFT);
			button2.setFont(new Font("Arial", Font.BOLD, 20));

			
			button3=new JButton(icon3);
			button3.setBounds(425,425,250,50);
			button3.addActionListener(this);
			button3.setText("POWERS");
			button3.setFocusable(false);//sklanja one isprekidane linije oko teksta
			button3.setBackground(Color.decode("#6C74C6"));
			button3.setHorizontalTextPosition(SwingConstants.LEFT);
			button3.setFont(new Font("Arial", Font.BOLD, 20));
			
			
			button4=new JButton(iconSoundOff);
			button4.setBounds(50,50,55,55);
			button4.addActionListener(this);
//			button4.setText("MUTE");
			button4.setFocusable(false);//sklanja one isprekidane linije oko teksta
			button4.setBackground(Color.decode("#6C74C6"));
			button4.setHorizontalTextPosition(JButton.LEFT);
			button4.setFont(new Font("Arial", Font.BOLD, 20));
			
			button5=new JButton(forma);
			button5.setBounds(50,480,55,55);
			button5.addActionListener(this);
			button5.setFocusable(false);//sklanja one isprekidane linije oko teksta
			button5.setBackground(Color.decode("#6C74C6"));
			
			myLabel.add(button1);
			myLabel.add(button2);
			myLabel.add(button3);
			myLabel.add(button4);
			myLabel.add(button5);

			
			
			ImageIcon image=new ImageIcon("src/resources/images/arcade1.png");
			this.setIconImage(image.getImage());
		
	
		
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==button1) {
			if(!Intro.sound.isMute()) modeSound.audioRestart();
			new Thread(new Client(GameMode.Classic,this));
			Intro.sound.audioStop();
			dispose();//brise trenutni frame
			
		}
		if (e.getSource()==button2) {
			if(!Intro.sound.isMute()) modeSound.audioRestart();
			new Thread(new Client(GameMode.Advanced,this));
			Intro.sound.audioStop();
			dispose();
		}
		if (e.getSource()==button3) {
			if(!Intro.sound.isMute()) modeSound.audioRestart();
			new Thread(new Client(GameMode.Powers,this));
			Intro.sound.audioStop();
			dispose();
		}
		if(e.getSource()==button4) {
			if(!Intro.sound.isMute()) {
				Intro.sound.audioStop();
				Intro.sound.setMute(true);
				button4.setIcon(iconSound);
			}
			else {
				Intro.sound.audioStart();
				Intro.sound.setMute(false);
				button4.setIcon(iconSoundOff);
				modeSound.audioRestart();
			}
		}
		if(e.getSource()==button5) {
			if(!Intro.sound.isMute()) {
				modeSound.audioRestart();
			}
			if(u.userName!=null && !u.userName.equals("") && !u.userName.equals("guest")) {
				MatchHistory m=new MatchHistory(this);
			//	m.setVisible(true);
				return;
			}
			Forma gf=new Forma(this);
			//gf.setVisible(true);	
		}

	}


}
