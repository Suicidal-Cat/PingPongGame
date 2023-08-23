package game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import client.ClientControl;

public class Paddle extends Rectangle {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 7673898929813484348L;
	protected int id;
	protected int yVelocity;// brzina pomeranja pravougaonika
	protected int speed = 8;
	public boolean powerPush;
	public boolean powerBlock;
	 
	 

	public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
	}

	/*public void keyPressed(KeyEvent e) {
		switch (id) {
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed);
			}
			break;
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
			}
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (id) {
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
			}
			break;
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;
		}
	}*/
	public void updatePaddle(ClientControl control) {
		if(control==ClientControl.UP_PRESSED)setYDirection(-speed);
		else if(control==ClientControl.DOWN_PRESSED)setYDirection(speed);
		else if(control==ClientControl.UP_REALISED)setYDirection(0);
		else if(control==ClientControl.DOWN_REALISED)setYDirection(0);
	}

	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}

	public void move() {
		y = y + yVelocity;
	}

	public void draw(Graphics g) {
		if (id == 1) {
			g.setColor(new Color(122, 183, 255));
			
		} else
			g.setColor(new Color(148, 87, 235));
		g.fillRect(x, y, width, height);
		
		
			
		
	}
	public void resetPowers() {};
	public void updatePaddlePositon(Rectangle r) {
		x=r.x;
		y=r.y;
		width=r.width;
		height=r.height;	
	}
	public boolean[] getFlags(){return new boolean[] {powerPush,powerBlock};};
	public void setFlags(boolean[]flags){powerPush=flags[0];powerBlock=flags[1];};
}
