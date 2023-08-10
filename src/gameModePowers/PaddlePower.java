package gameModePowers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.Console;

import game.Paddle;

public class PaddlePower extends Paddle{

	public PaddlePower(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT, id);
	}

	public void keyPressed(KeyEvent e) {
		switch (id) {
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed);
			}
			if(e.getKeyCode() == KeyEvent.VK_J) {
				powerPush=true;
			}
			if(e.getKeyCode() == KeyEvent.VK_K) {
				powerSpeed=true;
			}
			if(e.getKeyCode() == KeyEvent.VK_L) {
				powerBlock=true;
			}
			break;
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
			}
			if(e.getKeyCode() == KeyEvent.VK_Z) {
				powerPush=true;
			}
			if(e.getKeyCode() == KeyEvent.VK_X) {
				powerSpeed=true;
			}
			if(e.getKeyCode() == KeyEvent.VK_C) {
				powerBlock=true;
			}
			break;
		}

	}
	public void draw(Graphics g) {
		if (id == 1) {
			g.setColor(new Color(122, 183, 255));
		} else
			g.setColor(new Color(180, 125, 232));
		g.fillRect(x, y, width, height);
		if(powerPush) {
			g.setColor(Color.RED);
			if(id==1)g.drawLine(x+width,y,x+width,y+height);
			else g.drawLine(x,y,x,y+height);
		}

	}
}
