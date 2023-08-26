package client;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GameResultFrame extends JFrame{
	
	public GameResultFrame(int result,JFrame game) {
		//1 is for win
		Icon icon=null;
		if(result==1) {
			switch(new Random().nextInt(4)) {
				case 0:icon=new ImageIcon("src/resources/images/winlose/1W.gif");break;
				case 1:icon=new ImageIcon("src/resources/images/winlose/2W.gif");break;
				case 2:icon=new ImageIcon("src/resources/images/winlose/3W.gif");break;
				case 3:icon=new ImageIcon("src/resources/images/winlose/4W.gif");break;
			}
		}
		else {
			switch(new Random().nextInt(4)) {
				case 0:icon=new ImageIcon("src/resources/images/winlose/1D.gif");break;
				case 1:icon=new ImageIcon("src/resources/images/winlose/2D.gif");break;
				case 2:icon=new ImageIcon("src/resources/images/winlose/3D.gif");break;
				case 3:icon=new ImageIcon("src/resources/images/winlose/4D.gif");break;
			}
		}
	    JLabel label = new JLabel(icon);
	    this.add(label);
	  	setUndecorated(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(new Dimension(495,570));
	    setLocationRelativeTo(game);
		setVisible(true);
	}
	
	public void close() {
		this.dispose();
	}

}
