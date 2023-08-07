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

import game.GameFrame;
import game.GameMode;
import gameModeClassic.GameClassic;

public class FirstFrame extends JFrame implements ActionListener{

	
	private ImageIcon pozadinaArcade;
	private JLabel myLabel;
	JButton button1,button2,button3;
	
	 public FirstFrame() {
	
		 pozadinaArcade=new ImageIcon("src/images/arcadePozadina1.jpg");
			myLabel=new JLabel(pozadinaArcade);
			myLabel.setSize(getMaximumSize());
		    Icon icon1 = new ImageIcon("src/images/gamepad.png");
		    Icon icon2 = new ImageIcon("src/images/arcade.png");
		    Icon icon3 = new ImageIcon("src/images/magic-wand.png");
		    

			
			this.add(myLabel);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(1100,617);
			this.setResizable(false);
			//this.setLayout(null);
			this.setVisible(true);
			this.setLocationRelativeTo(null);//da na centru ekrana bude prozor
			this.setTitle("Pong Game");

			
			button1=new JButton(icon1);
			button1.setBounds(425,125,250,50);
			button1.addActionListener(this);
			button1.setText("CLASSIC");
			button1.setFocusable(false);//sklanja one isprekidane linije oko teksta
			button1.setBackground(Color.decode("#6C74C6"));
			button1.setHorizontalTextPosition(JButton.LEFT);
			button1.setBorder(BorderFactory.createEtchedBorder());
			button1.setFont(new Font("Arial", Font.BOLD, 20));
			
			
			button2=new JButton(icon2);
			button2.setBounds(425,275,250,50);
			button2.addActionListener(this);
			button2.setText("ADVANCED");
			button2.setFocusable(false);//sklanja one isprekidane linije oko teksta
			button2.setBackground(Color.decode("#6C74C6"));
			button2.setHorizontalTextPosition(JButton.LEFT);
			button2.setBorder(BorderFactory.createEtchedBorder());
			button2.setFont(new Font("Arial", Font.BOLD, 20));

			
			button3=new JButton(icon3);
			button3.setBounds(425,425,250,50);
			button3.addActionListener(this);
			button3.setText("MAGIC");
			button3.setFocusable(false);//sklanja one isprekidane linije oko teksta
			button3.setBackground(Color.decode("#6C74C6"));
			button3.setHorizontalTextPosition(SwingConstants.LEFT);
			button3.setBorder(BorderFactory.createEtchedBorder());
			button3.setFont(new Font("Arial", Font.BOLD, 20));
			
			
			
			

			
			myLabel.add(button1);
			myLabel.add(button2);
			myLabel.add(button3);
			
			
			ImageIcon image=new ImageIcon("src/images/arcade1.png");
			this.setIconImage(image.getImage());
		
	
		
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==button1) {
			
			GameFrame gf=new GameFrame(GameMode.Classic);
			gf.show();//prikazuje frame za classic
			dispose();//brise trenutni frame
		}
		if (e.getSource()==button2) {
			
		}
		if (e.getSource()==button3) {
			
		}
	}


}
